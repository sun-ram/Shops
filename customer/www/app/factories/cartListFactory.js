angular.module('aviate.factories')
.factory('MyCartFactory', function ($rootScope, ipCookie, MyCartServices,toastr,CONSTANT) {

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
				toastr.success(CONSTANT.SUCCESS_CODE.ADDPRODUCT);
				factory.myCartTotalPriceCalculation();
				MyCartServices.addToCart(cartDetails).then(function(data){
					console.log('Add To My Cart in factory');
					factory.myCartTotalPriceCalculation();
					MyCartServices.getCartList({"customerId" : $rootScope.user.userId, "storeId" : $rootScope.store.storeId},  function(data){
						factory.myCartTotalPriceCalculation();
						console.log('Get To My Cart in factory');
					});
				})
			}else{
				toastr.success(CONSTANT.SUCCESS_CODE.ADDPRODUCT);
				for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
					if($rootScope.myCart.cartItem[i].product.productId == _product.productId){
						$rootScope.myCart.cartItem[i].quantity = _product.noOfQuantityInCart;
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
		//ipCookie("myCart",$rootScope.myCart);
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
				MyCartServices.getCartList({"customerId" : $rootScope.user.userId, "storeId" : $rootScope.store.storeId},  function(data){
					console.log('get Cart in factory');
				});
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
			_subTotal = $rootScope.myCart.cartItem[i].quantity * $rootScope.myCart.cartItem[i].product.productPrice.price;
			$rootScope.myCart.cartItem[i].product.subTotal = _subTotal;
			_totalAmount += _subTotal;
		}
		$rootScope.myCart.cartTotalAmount = _totalAmount;
		$rootScope.myCart.shippingCharges = 100;
		$rootScope.myCart.taxAmount = $rootScope.myCart.cartTotalAmount*(12.5/100);
		$rootScope.myCart.serviceTax = $rootScope.myCart.cartTotalAmount*(2.5/100);
		//cartTotalAmount*(2.5/100)
		$rootScope.myCart.grossAmount = $rootScope.myCart.taxAmount+$rootScope.myCart.cartTotalAmount+$rootScope.myCart.shippingCharges+$rootScope.myCart.serviceTax;
		//ipCookie("myCart",$rootScope.myCart);
		localStorage.setItem('myCart',JSON.stringify($rootScope.myCart));
	}


	factory.checkCartProductsQuantity = function (_productList , callback) {
		if($rootScope.myCart.cartItem.length > 0){
			for(var i = 0; $rootScope.myCart.cartItem.length > i; i++){
				for(var j = 0; _productList.length > j; j++){
					if(_productList[j].productId == $rootScope.myCart.cartItem[i].product.productId){
						_productList[j].noOfQuantityInCart = $rootScope.myCart.cartItem[i].quantity;
					}	
				}
			}
		}
		callback(_productList);
	}

	factory.checkSingleProductsinCart = function (_product , callback) {
		if($rootScope.myCart.cartItem.length > 0){
			for(var i = 0; $rootScope.myCart.cartItem.length > i; i++){
				if(_product.productId == $rootScope.myCart.cartItem[i].product.productId){
					_product.noOfQuantityInCart = $rootScope.myCart.cartItem[i].quantity;
				}
			}
		}
		callback(_product);
	}
	
	factory.checkProductInMyList = function (_product , callback) {
		var _isProductMyList = false;
		if($rootScope.myList.listItem.length > 0){
			for(var i = 0; $rootScope.myList.listItem.length > i; i++){
				if(_product.productId == $rootScope.myList.listItem[i].products.productId){
					_isProductMyList = true;
				}
			}
		}
		_product.isProductMyList = _isProductMyList
		callback(_product);
	}

	return factory;
});