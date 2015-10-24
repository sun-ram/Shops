angular.module('aviate.controllers')
.controller("cartCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT','$http','$rootScope',
		 function($scope, $state, toastr, CONSTANT,$http,$rootScope) {
			
			
			$scope.getCartList= function() {
				$scope.user.userId = $rootScope.user.userId;
				$scope.user.superMarketId = $rootScope.store.storeId;
				MyCartServices.getCartList($scope.user).then(function(data) {
					$scope.cartItem = data;
					
				});
			};
			
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
					if($localStorage.userId){
						$rootScope.addToCartDB(product.productDetails.productId, product.noOfQuantityInCart, product.productDetails.productPrice.price);
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
				$rootScope.myCartTotalPriceCalculation();
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

/*			$scope.removeFromCartDB = function(productId){
				var menuJson = angular.toJson({"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId, "productId" : productId});		 
				$http({
					url: serviceUrl + 'aviate/json/product/deletefromcart',
					method: 'POST',
					data: menuJson,
					headers: {
						'Content-Type': 'application/json'
					} 
				}).success(function(result, status, headers) {
					if(result.status == 'SUCCESS'){
						$scope.getCartListFromDB();
					}
				})
			}
			
			$scope.getCartListFromDB = function(){
				var menuJson = angular.toJson({"customerId" : "1", "storeId" : "1"});		 
				$http({
					url: 'http://localhost:8080/aviate/json/product/getmycartlist',
					method: 'POST',
					data: menuJson,
					headers: {
						'Content-Type': 'application/json'
					} 
				}).success(function(result, status, headers) {
					if(result.status == 'SUCCESS'){
						$scope.cartItem = [];
						for(var i = 0; i<result.myCartList.length;i++){
							if(result.myCartList[i].product.productImages.length != 0){
							$scope.image = result.myCartList[i].product.productImages[1].imageUrl;
							}
							$scope.cartItem.push({
								"productName":result.myCartList[i].product.productName,
								"productDetails":result.myCartList[i].product,
								"quantity":result.myCartList[i].quantity,
								"price":result.myCartList[i].price,
								"image":$scope.image
								});
						}
					}
				})
			}*/
			


}]);
