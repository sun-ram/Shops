angular.module('aviateAdmin.controllers')
.controller("appCtrl", 
		['$scope', '$rootScope','$state','$localStorage','$filter','$mdDialog', 'CommonServices',
		 function($scope,$rootScope, $state,$localStorage, $filter,$mdDialog, CommonServices) {
			
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
			
			$scope.physicalInventoryListPagination = {
					limit: 5,
					page: 1
			};
			
			$scope.storagebinPagination={
					limit: 5,
					page: 1
			};
			
			$scope.deliveryTimeSlotsPagination={
					limit: 5,
					page: 1
			};
			
			$scope.getState = function(country){
				$scope.cunt = JSON.parse(country);
				$scope.states = $scope.cunt.states;
			}
			
			$scope.getCountries = function(){
				CommonServices.getCountries($scope.country).then(function(data){
					$scope.countries=data;
				});
			}
			$scope.getCountries();
			
		}]);