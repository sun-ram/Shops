angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products','$rootScope',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope) {
				

			$scope.productList = products;
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
					if($rootScope.user.userId){
						$rootScope.addToCartDB(product.productId, product.noOfQuantityInCart, product.productDetails.productPrice.price);
						$rootScope.productListUpdate(product, product.noOfQuantityInCart);
					}else{
						for(var i = 0; i<$rootScope.cartItem.length; i++){
							if($rootScope.cartItem[i].productDetails.productId == product.productDetails.productId){
								$rootScope.cartItem[i] = product;
								isExistInCart = true;
							}
						}
						if(!isExistInCart){
							$rootScope.cartItem.push({
								"noOfQuantityInCart":product.noOfQuantityInCart,
								"productDetails":product.productDetails});
						}
						$rootScope.productListUpdate(product, product.noOfQuantityInCart);
					}
				}else if(product.noOfQuantityInCart == 0){
					for(var i = 0; i<$rootScope.cartItem.length; i++){
						if($rootScope.cartItem[i].productDetails.productId == product.productDetails.productId){
							$rootScope.deletefromCart(product, i);
						}
					}
				}
			//	$rootScope.myCartTotalPriceCalculation();
			}
			
			$rootScope.deletefromCart = function(product, index){
				if($localStorage.userId){
					$rootScope.removeFromCartDB(product.productDetails.productId);
					$rootScope.productListUpdate(product, 0);
					$rootScope.getCartListFromDB();
				}else{
					$scope.cartItem.splice(index, 1);
					$rootScope.myCartTotalPriceCalculation();
					$rootScope.productListUpdate(product, 0);
				}
			}


		}]);

