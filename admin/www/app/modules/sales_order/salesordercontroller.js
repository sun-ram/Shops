angular.module('aviateAdmin.controllers').controller("salesordercontroller", 
		['$scope', '$state','$filter','ngTableParams','$timeout','$q','$rootScope','$localStorage','SalesOrderServices','toastr',
		 function($scope, $state, $filter, ngTableParams, $timeout,$q,$rootScope,$localStorage,SalesOrderServices,toastr) {

			$scope.selected = [];
			$rootScope.salesOrderDetails = $localStorage.salesorderline;
			$scope.salesOrderList = $localStorage.salesorderlist;
			$scope.salesOrderPagination.order= 'address.firstName';

			$scope.getSalesOrderList = function () {
				var request = {};
				if($rootScope.user.role == "MERCHANTADMIN"){
					request = {
							"merchantId":$rootScope.user.merchantId
					};
				}else if($rootScope.user.role == "STOREADMIN"){
					request = {
							"storeId":$rootScope.user.storeId
					};
				}

				SalesOrderServices.getSalesOrder(request).then(function(data) {
					$scope.salesOrderList = data;
					$localStorage.salesorderlist = data;
					$scope.count=data.length;

				});
			};

			$scope.getMerchantStore = function () {

				var request = {
						"merchantId":$rootScope.user.merchantId
				};
				SalesOrderServices.getMerchantStore(request).then(function(data) {
					$scope.merchantStore = data;

				});
			};


			$scope.storeFilter= function(){
				var request = {
						"storeId":$scope.storeId
				};
				SalesOrderServices.getSalesOrderByStore(request).then(function(data) {
					$scope.salesOrderList = data;

				});
			};

			$scope.getSalesByDate = function () {
				if($scope.fromDate != undefined){

					if($scope.toDate != undefined){
							
						if($scope.fromDate > $scope.toDate){
							toastr.error("To date must less than from date");
						}else{
							var request = {
									"fromDate":$scope.fromDate.toLocaleDateString(),
									"toDate":$scope.toDate.toLocaleDateString()
							};
							
							if($rootScope.user.role == "MERCHANTADMIN"){
								request.merchantId=$rootScope.user.merchantId
							}else if($rootScope.user.role == "STOREADMIN"){
								request.storeId=$rootScope.user.storeId
							}

							SalesOrderServices.getSalesByDate(request).then(function(data) {
								$scope.salesOrderList = data;

							});

						}
					}else{
						toastr.error("Select End Date");
					}
				}else{
					toastr.error("Select Start Date");
				}
			};	

			$scope.getSalesDetails = function (salesorder) {
				$localStorage.salesorderline = salesorder;
				$rootScope.salesOrderDetails = $localStorage.salesorderline;
				$state.go('app.salesOrderLine');
			};	  

			$scope.onpagechange = function(page, limit) {
				var deferred = $q.defer();

				$timeout(function () {
					deferred.resolve();
				}, 2000);

				return deferred.promise;
			};

			$scope.onorderchange = function(order) {
				var deferred = $q.defer();

				$timeout(function () {
					deferred.resolve();
				}, 2000);

				return deferred.promise;
			};

		}]);