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
						"customerId" : $rootScope.user.userId, 
						"storeId" : $rootScope.store.storeId, 
						"productId" : $scope.myCart.cartItem[i].productDetails.productId, 
						"price" : $scope.myCart.cartItem[i].productDetails.productPrice.price, 
						"quantity" : $scope.myCart.cartItem[i].noOfQuantityInCart
				}

				MyCartServices.addToCart($scope.cartDetails).then(function(data){
					console.log('get Mylist success in Main Nav');
				});
				/*$rootScope.addToCartDB(product.productDetails.productId, product.noOfQuantityInCart, product.productDetails.productPrice.price);
					$rootScope.productListUpdate(product, product.noOfQuantityInCart);*/
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
			}
		}else if(_product.noOfQuantityInCart == 0){
			for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
				if($rootScope.myCart.cartItem[i].productId == _product.productId){
					//$rootScope.removeFromCart(product, i);
					console.info(_product, i);
					factory.removeFromCart(_product, i);
				}
			}
		}
		ipCookie("myCart",$rootScope.myCart);
		callback(_productList);
	}


	factory.removeFromCart = function (_product, _index) {
		if($rootScope.user && $rootScope.user.userId){
			//$rootScope.removeFromCartDB(product.productDetails.productId);
			//{"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId, "productId" : productId}
			MyCartServices.removeCartProduct($scope.cartDetails).then(function(data){
				console.log('get Mylist success in Main Nav');
			});

			//$rootScope.productListUpdate(_product, 0);
			//$rootScope.getCartListFromDB();
		}else{
			$rootScope.myCart.cartItem.splice(_index, 1);
			ipCookie("myCart",$rootScope.myCart);
			//$rootScope.productListUpdate(product, 0);
		}
	}


	factory.productListUpdate = function(product, quantity){
		for(var i = 0; i < $rootScope.productList.length;i++){
			if($rootScope.productList[i].productDetails.productId == product.productDetails.productId)
				$rootScope.productList[i].noOfQuantityInCart = quantity;
		}
	}

	return factory;
});