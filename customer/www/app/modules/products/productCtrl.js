angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products', '$rootScope', 'ipCookie', 'MyCartFactory', 'MyCartServices','$mdDialog','MyListServices',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope,ipCookie, MyCartFactory, MyCartServices,$mdDialog,MyListServices) {


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
			
			$scope.removeFromMyList = function(product){
				MyListServices.removeMyList({"customerId":$rootScope.user.userId,"productId" : product.productId,"storeId" : $rootScope.store.storeId}).then(function(data){
					$scope.getMyList();
					$scope.productDetails.isProductMyList = false;
				});	
			};
			
			$scope.getMyList = function(){
				MyListServices.getMyList({"customerId":$rootScope.user.userId, "storeId" : $rootScope.store.storeId}).then(function(data){
					$scope.myListProducts = data;
				});
			};
			
			$scope.getProducts = function(){
				if($rootScope.user == null || $rootScope.user == undefined){
					$scope.productList = products;
				}else{
				$scope.getMyList();
				MyCartFactory.checkCartProductsQuantity(products,function(data){
					MyCartFactory.checkProductInMyList(data,function(dataInList){
						$scope.productList = dataInList;
					});
				});
				}
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

