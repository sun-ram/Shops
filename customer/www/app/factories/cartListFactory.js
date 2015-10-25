angular.module('aviate.factories')
.factory('MyCartFactory', function ($rootScope, ipCookie, MyCartServices) {

	var _cart, 
	_isExistInCart, 
	factory = {};

	factory.addToCart = function (_product, _productList , callback) {
		_isExistInCart = false;
		if(_product.noOfQuantityInCart > 0){
			if($rootScope.user && $rootScope.user.userId){
				$scope.cartDetails = {
						customerId : $rootScope.user.userId, 
						storeId : $rootScope.store.storeId, 
						productId : $scope.myCart.cartItem[i].productId, 
						price : $scope.myCart.cartItem[i].productPrice.price, 
						quantity : $scope.myCart.cartItem[i].noOfQuantityInCart
				}
				MyCartServices.addToCart($scope.cartDetails).then(function(data){
					console.log('Add To My Cart in factory');
				})
			}else{
				for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
					if($rootScope.myCart.cartItem[i].productId == _product.productId){
						$rootScope.myCart.cartItem[i] = _product;
						_isExistInCart = true;
					}
				}
				if(!_isExistInCart){
					$rootScope.myCart.cartItem.push(_product);
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
			$scope.cartDetails = {
					customerId : $rootScope.user.userId, 
					storeId : $rootScope.store.storeId, 
					productId : _product.productId
			};
			MyCartServices.removeCartProduct($scope.cartDetails).then(function(data){
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
			_subTotal = $rootScope.myCart.cartItem[i].noOfQuantityInCart * $rootScope.myCart.cartItem[i].productPrice.price;
			$rootScope.myCart.cartItem[i].subTotal = _subTotal;
			_totalAmount += _subTotal;
		}
		$rootScope.myCart.cartTotalAmount = _totalAmount;
		$rootScope.myCart.taxAmount = $rootScope.myCart.cartTotalAmount*($rootScope.tax/100);
		$rootScope.myCart.grossAmount = $rootScope.myCart.taxAmount+$rootScope.myCart.cartTotalAmount+$rootScope.shippingCharges;
		ipCookie("myCart",$rootScope.myCart);
	}

	return factory;
});