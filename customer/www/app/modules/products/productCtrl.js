angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products', '$rootScope', 'ipCookie', 'MyCartFactory', 'MyCartServices','$mdDialog','MyListServices',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope,ipCookie, MyCartFactory, MyCartServices,$mdDialog,MyListServices) {


			$scope.productList = products;
			
			$scope.addToMyList = function(product){
				if($rootScope.user == null || $rootScope.user == undefined){
					$rootScope.signInPopup();
				}else{
				MyListServices.addToMyList({"customerId":$rootScope.user.userId,"productId" : product.productId,"storeId" : $rootScope.store.storeId}).then(function(data){
					$scope.productDetails.isProductMyList = true;
					$scope.getMyList();
				});	
				}
			};
			
			$scope.getMyList = function(){
				MyListServices.getMyList({"customerId":$rootScope.user.userId, "storeId" : $rootScope.store.storeId}).then(function(data){
					$scope.myListProducts = data;
				});
			};
			
			
			$scope.productDetails = function(ev,products){
				$rootScope.productDetails = products;
				$mdDialog.show({
					templateUrl: 'app/modules/products/productDetails/productDetails.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: "productDetailsCtrl"
				})
				.then(function() {
					
				}, function() {

				});
			}

		}]);

