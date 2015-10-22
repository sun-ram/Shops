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
							$scope.cartItem.push({
								"productName":result.myCartList[i].productName,
								"productDetails":result.myCartList[i].product,
								"quantity":result.myCartList[i].quantity,
								"price":result.myCartList[i].price,
								"image":result.myCartList[i].productImages[1].imageUrl 	
								});
						}
					}
				})
			}


}]);
