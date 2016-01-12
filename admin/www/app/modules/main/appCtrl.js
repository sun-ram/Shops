angular.module('aviateAdmin.controllers')
.controller("appCtrl", 
		['$scope', '$rootScope','$state','$localStorage','$filter','$mdDialog', 'CommonServices','toastr',
		 function($scope,$rootScope, $state,$localStorage, $filter,$mdDialog, CommonServices,toastr) {
			$rootScope.progressbar = false;
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
			$scope.discountPagination={
					limit: 5,
					page: 1
			};
			$scope.discountProductPagination={
					limit: 5,
					page: 1
			};
			$scope.offerProductPagination={
					limit: 5,
					page: 1
			};
			$scope.offerPagination={
					limit: 5,
					page: 1
			};
			$scope.billingPagination={
					limit: 5,
					page: 1
			};
			
			$scope.salesOrderReturnLinePagination={
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
						$scope.setDefaultCountry();
					});
				}else{
					$scope.countries=$localStorage.countries;
					$scope.setDefaultCountry();
				}
			};
			
			$scope.setDefaultCountry = function(){
				for (var i = 0; i < $scope.countries.length; i++) { 
					if($scope.countries[i].name === 'India'){
						$scope.country = $scope.countries[i];
						$scope.states=$scope.countries[i].states;
						//$scope.state = $scope.states[0];
					}
				}
			};
			
			if($rootScope.websocket!=null && $rootScope.websocket!=undefined){
				$rootScope.websocket.onopen = function(evt) { onOpen(evt); };
				$rootScope.websocket.onmessage = function(evt) { onMessage(evt); };
				$rootScope.websocket.onerror = function(evt) { onError(evt); };
				$rootScope.websocket.onclose = function(evt) { onClose(evt); };
			}
						
			function send_message() {
				var msg = '{"message":"Hai", "touser":"jai"}';	
				$rootScope.websocket.send(msg);
			}
			
			function onOpen() {
			 console.log("CONNECTED");
			}
			
			function onClose() {
				console.log("DISCONNECTED");
			}
			
			function onMessage(evt) {
			 console.log("RECEIVED: " + evt.data);
			 $scope.socketData = JSON.parse(evt.data); 
			 $scope.salesList = JSON.parse($scope.socketData.salesOrder);
			 if($scope.socketData.tag=="SalesOrder"){
				 $scope.$broadcast('salesOrder:update',evt.data);
				 if($scope.socketData.message=="New" && !$scope.salesOrderCountReceived){
					 $scope.salesOrderCountReceived =1;
					 $localStorage.salesOrderCountReceived = $scope.salesOrderCountReceived;
				 }else if($scope.socketData.message=="New"){
					 $scope.salesOrderCountReceived = $localStorage.salesOrderCountReceived + 1;
					 $localStorage.salesOrderCountReceived = $scope.salesOrderCountReceived;
				 }
				 $scope.$apply();
			 }
			}
			
			function onError(evt) {
				console.log(evt.data);
			}
			
			function disconnect() {
				$rootScope.websocket.close();
			}
			
			$scope.getCountries();
			
		}]);