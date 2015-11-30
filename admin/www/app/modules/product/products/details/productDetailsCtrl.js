angular.module('aviateAdmin.controllers').controller("productDetailController", 
		['$scope', '$http', '$rootScope','$localStorage','$state','$filter','$window', 'ngTableParams','ProductService',
		 function($scope, $http, $rootScope, $localStorage, $state, $filter,$window, ngTableParams, ProductService) {



			$scope.getProductDetails = function (){

			/*	console.log($localStorage.productDetails);
				$scope.productDetail = $localStorage.productDetails;*/
				$scope.productDetail = ProductService.getProductObj();
				console.log($scope.productDetail);
			};

			$scope.editproduct = function(products){
				$localStorage.product = products;
				$state.go('app.addproduct');

			}







		}]);