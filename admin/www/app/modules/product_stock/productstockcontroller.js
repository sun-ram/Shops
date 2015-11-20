angular.module('aviateAdmin.controllers').controller("productstockcontroller", 
		['$scope', '$state','$filter','ngTableParams','$timeout','$q','$rootScope','productStockService','$localStorage','SalesOrderServices',
		 function($scope, $state, $filter, ngTableParams, $timeout,$q,$rootScope,productStockService,$localStorage,SalesOrderServices) {
			
			
			$scope.selected = [];
			$scope.productStockList=[];
			$scope.salesOrderList = $localStorage.productstocklist;
			
			$scope.productStockPagination.order= 'warehouse.warehouseName';

			$scope.getProductStockList = function () {
				var salesOrderVo = {};
				if(true){
					salesOrderVo.merchant = {
							"merchantId":"2c9fa0375119c5c801511a7afeb20088"
					};
				}else if($rootScope.user.role == "STOREADMIN"){
					request = {
							"storeId":$rootScope.user.storeId
					};
				}
				productStockService.getProductStockList(salesOrderVo).then(function(data) {
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

				var merchantVo = {
						"merchantId":"2c9fa0375119c5c801511a7afeb20088"
				};
				SalesOrderServices.getMerchantStore(merchantVo).then(function(data) {
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