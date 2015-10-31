angular.module('aviateAdmin.controllers')
.controller("appCtrl", 
		['$scope', '$rootScope','$state','$localStorage','$filter','$mdDialog',
		 function($scope,$rootScope, $state,$localStorage, $filter,$mdDialog) {
			
			/*$scope.query = {
					limit: 5,
					page: 1
			};*/

			$scope.merchantPagination = {
					limit: 5,
					page: 1
			};
			
			$scope.employeePagination = {
					limit: 5,
					page: 1
			};

			$scope.storePagination = {
					limit: 5,
					page: 1
			};
			
			$scope.unitsPagination = {
					limit: 5,
					page: 1
			};
			
			$scope.warehousePagination = {
					limit: 5,
					page: 1
			};
			
			$scope.salesOrderPagination = {
					limit: 5,
					page: 1
			};
			
			$scope.salesOrderLinePagination = {
					limit: 5,
					page: 1
			};
			
			$scope.productsPagination = {
					limit: 5,
					page: 1
			};
			
			$scope.productStockPagination = {
					limit: 5,
					page: 1
			};
			
			$scope.productCategoryPagination = {
					limit: 5,
					page: 1
			};

		}]);