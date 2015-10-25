angular.module('aviate.factories')
.factory('MyCartFactory', function ($rootScope, ipCookie, MyCartServices) {

	var _cart, 
	_isExistInCart, 
	factory = {};

	factory.addToCart = function (_product, _productList , callback) {
		_isExistInCart = false;
		if(_product.noOfQuantityInCart > 0){
			if($rootScope.user && $rootScope.user.userId){
				var cartDetails = {
						customerId : $rootScope.user.userId, 
						storeId : $rootScope.store.storeId, 
						productId : _product.productId, 
						price : _product.productPrice.price, 
						quantity : _product.noOfQuantityInCart
				}
				MyCartServices.addToCart(cartDetails).then(function(data){
					console.log('Add To My Cart in factory');
				})
			}else{
				for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
					if($rootScope.myCart.cartItem[i].productId == _product.productId){
						$rootScope.myCart.cartItem[i].quantity = noOfQuantityInCart;
						$rootScope.myCart.cartItem[i].product = _product;
						_isExistInCart = true;
					}
				}
				if(!_isExistInCart){
					$rootScope.myCart.cartItem.push({
						product:_product,
						quantity:_product.noOfQuantityInCart
					});
				}
				factory.myCartTotalPriceCalculation();
			}
		}else if(_product.noOfQuantityInCart == 0){
			for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
				if($rootScope.myCart.cartItem[i].productId == _product.productId){
					factory.removeFromCart(_product, i);
				}
			}
		}
		ipCookie("myCart",$rootScope.myCart);
		callback(_productList);
	}


	factory.removeFromCart = function (_product, _index) {
		if($rootScope.user && $rootScope.user.userId){
			var cartDetails = {
					customerId : $rootScope.user.userId, 
					storeId : $rootScope.store.storeId, 
					productId : _product.productId
			};
			MyCartServices.removeCartProduct(cartDetails).then(function(data){
				console.log('get Mylist success in Main Nav');
			});
		}else{
			$rootScope.myCart.cartItem.splice(_index, 1);
			factory.myCartTotalPriceCalculation();
			//ipCookie("myCart",$rootScope.myCart);
		}
	}

	factory.myCartTotalPriceCalculation = function () {
		var  _totalAmount = 0;
		for(var i=0; i<$rootScope.myCart.cartItem.length; i++){
			var _subTotal = 0;
			_subTotal = $rootScope.myCart.cartItem[i].product.noOfQuantityInCart * $rootScope.myCart.cartItem[i].product.productPrice.price;
			$rootScope.myCart.cartItem[i].product.subTotal = _subTotal;
			_totalAmount += _subTotal;
		}
		$rootScope.myCart.cartTotalAmount = _totalAmount;
		$rootScope.myCart.taxAmount = $rootScope.myCart.cartTotalAmount*($rootScope.tax/100);
		$rootScope.myCart.grossAmount = $rootScope.myCart.taxAmount+$rootScope.myCart.cartTotalAmount+$rootScope.shippingCharges;
		ipCookie("myCart",$rootScope.myCart);
	}

	return factory;
});