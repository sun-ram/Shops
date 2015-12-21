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
			
			$scope.physicalInventoryAdjustmentPagination = {
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
			
			$scope.getCity = function(states){
				$scope.cities = states.city;
			};
			
			$scope.getState = function(country){
				$scope.states = country.states;
			};
			
			$scope.getCountries = function(){
				if(!$localStorage.countries){
					CommonServices.getCountries($scope.country).then(function(data){
						$scope.countries=data;
						$localStorage.countries=data;
						for (var i = 0; i < data.length; i++) { 
							if($scope.countries[i].name === 'India'){
								$scope.country = $scope.countries[i];
								$scope.states=$scope.countries[i].states;
								console.log("Anbukkani " ,  $scope.states[0] );
								//$scope.state = $scope.states[0];
							}
				        }
					});
				}else{
					$scope.countries=$localStorage.countries;
				}
			};
			$scope.getCountries();
			
		}]);