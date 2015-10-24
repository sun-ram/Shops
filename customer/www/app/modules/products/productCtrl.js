angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products','$rootScope','ipCookie',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope,ipCookie) {
				

			$scope.productList = products;
			if($rootScope.myCart){
				
			}
			console.info($scope.productList);
			
			
			
			$scope.removeFromCart = function(productId){
				$scope.user.userId = $rootScope.user.userId;
				$scope.user.superMarketId = $rootScope.store.storeId;				
				$scope.user.productId = productId;
				MyCartServices.removeCartProduct($scope.user).then(function(data) {
					$scope.getCartList();
					
				});				
				
			};
			
			$scope.addTocartDB =function(productId, quantity, subTotal){
				
				$scope.user.userId = $rootScope.user.userId;
				$scope.user.superMarketId = $rootScope.store.storeId;				
				$scope.user.productId = productId;
				$scope.user.quantity = quantity;
				$scope.user.price = subTotal;
				
				MyCartServices.addToCart($scope.user).then(function(data) {
					$scope.getCartList();
					
				});		
			}
			
			
			$rootScope.addToCartFun = function(product){
				var isExistInCart = false;
				if(product.noOfQuantityInCart > 0){
					if($rootScope.user && $rootScope.user.userId){
						$rootScope.addToCartDB(product.productId, product.noOfQuantityInCart, product.productPrice.price);
						$scope.productListUpdate(product, product.noOfQuantityInCart);
					}else{
						for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
							if($rootScope.myCart.cartItem[i].productId == product.productId){
								$rootScope.myCart.cartItem[i] = product;
								isExistInCart = true;
							}
						}
						if(!isExistInCart){
							$rootScope.myCart.cartItem.push({
								"noOfQuantityInCart":product.noOfQuantityInCart,
								"product":product});
							ipCookie("myCart",$rootScope.myCart);
						}
						$scope.productListUpdate(product, product.noOfQuantityInCart);
					}
				}else if(product.noOfQuantityInCart == 0){
					for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
						if($rootScope.myCart.cartItem[i].productId == product.productId){
							$rootScope.deletefromCart(product, i);
						}
					}
					ipCookie("myCart",$rootScope.myCart);
				}
			//	$rootScope.myCartTotalPriceCalculation();
			}
			
			$rootScope.deletefromCart = function(product, index){
				if($localStorage.userId){
					$rootScope.removeFromCartDB(product.productDetails.productId);
					$scope.productListUpdate(product, 0);
					$rootScope.getCartListFromDB();
				}else{
					$scope.cartItem.splice(index, 1);
					$rootScope.myCartTotalPriceCalculation();
					$scope.productListUpdate(product, 0);
				}
				
				
			}
			$scope.productListUpdate = function(product, quantity){
				for(var i = 0; i < $scope.productList.length;i++){
					if($scope.productList[i].productId == product.productId)
						$scope.productList[i].noOfQuantityInCart = quantity;
				}
			}

		}]);

