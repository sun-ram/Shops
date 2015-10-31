angular.module('aviateAdmin.controllers').controller("productstockcontroller", 
		['$scope', '$state','$filter','ngTableParams','$timeout','$q','$rootScope','productStockService','$localStorage','SalesOrderServices',
		 function($scope, $state, $filter, ngTableParams, $timeout,$q,$rootScope,productStockService,$localStorage,SalesOrderServices) {
			
			
			$scope.selected = [];
			$scope.productStockList=[];
			$scope.salesOrderList = $localStorage.productstocklist;
			
			$scope.productStockPagination.order= 'warehouse.warehouseName';

			$scope.getProductStockList = function () {
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
				productStockService.getProductStockList(request).then(function(data) {
					$scope.productStockList = data;
					$localStorage.productstocklist = data;
					$scope.count = data.length;

				});
			};
			
			$scope.getStockListByStore = function () {

				var request = {
						"storeId":$scope.storesId
				};
				productStockService.getProductStockList(request).then(function(data) {
					$scope.productStockList = data;
					$scope.count = data.length;

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