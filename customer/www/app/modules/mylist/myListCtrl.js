angular.module('aviate.controllers')
.controller("myListCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$http', 'MyListServices', 'ipCookie',
		 function($scope, $state, toastr, CONSTANT, $http, MyListServices, ipCookie) {


			$scope.addToMyList = function(){
				MyListServices.addToMyList().then(function(data){
					$scope.getMyList();
					$scope.productDetail.isProductMyList=! $scope.productDetail.isProductMyList;
				});	
			};

			$scope.getMyList = function(){
				MyListServices.getMyList().then(function(data){
					$scope.product = data;
				});
			};

			$scope.removeFromMyList = function(){
				MyListServices.removeMyList().then(function(data){
					$scope.getMyList();
				});	
			};





			/*

			$scope.myList = function(){
				var menuJson = angular.toJson({"customerId":"1","storeId":"1"});		 
				$http({
					url: "http://localhost:8080/aviate/json/customer/getmylist",
					method: 'POST',
					data: menuJson,
					headers: {
						'Content-Type': 'application/json'
					} 
				}).success(function(result, status, headers) {
					$scope.myLists = [];
					if(result.status=='SUCCESS'){
						$scope.myListItem = [];
						for(var i = 0; i<result.customerMyList.length;i++){
							if(result.customerMyList[i].products.productImages.length != 0){
								$scope.image = result.customerMyList[i].products.productImages[1].imageUrl;
								}
							$scope.myListItem.push({
								"productName":result.customerMyList[i].products.productName,
								"productId":result.customerMyList[i].products.productId,
								"productPrice":result.customerMyList[i].products.productPrice.price,
								"image":$scope.image								
							});

						}
					}
				})
			}

			$scope.deletefromList = function(productId){
				var menuJson = angular.toJson({
					"customerId":$localStorage.userId,
					"productId":productId,
					"storeId":$rootScope.superMarketId
				});		 
				$http({
					url: serviceUrl + 'aviate/json/customer/removemylist',
					method: 'POST',
					data: menuJson,
					headers: {
						'Content-Type': 'application/json'
					} 
				}).success(function(result, status, headers) {
					$rootScope.myList();
				})
			}

			 */}]);
