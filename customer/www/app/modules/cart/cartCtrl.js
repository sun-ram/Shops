angular.module('aviate.controllers')
.controller("cartCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT','$http',
		 function($scope, $state, toastr, CONSTANT,$http) {

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
			}
			
			$scope.removeFromCartDB = function(productId){
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


}]);
