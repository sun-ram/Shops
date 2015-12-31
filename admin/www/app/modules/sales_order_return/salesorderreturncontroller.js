angular.module('aviateAdmin.controllers').controller("salesorderreturncontroller", 
		['$scope', '$state','$filter','ngTableParams','$timeout','$q','$rootScope','$localStorage','SalesOrderReturnServices','toastr','$mdDialog',
		 function($scope, $state, $filter, ngTableParams, $timeout,$q,$rootScope,$localStorage,SalesOrderReturnServices,toastr,$mdDialog) {
			
			//Need to write sales order return changes here
			$rootScope.getSalesOrderReturnList = function () {
				var salesOrderReturnVo = {};
				salesOrderReturnVo.store = {
						"storeId":$rootScope.user.storeId
				};
				SalesOrderReturnServices.getSalesOrderReturn(salesOrderReturnVo).then(function(data) {
					$scope.salesOrderReturnList = data;
					$scope.originalList = data;
					/*$localStorage.salesorderlist = data;*/
					$scope.noOfRecords=data.length;

				});
			};
			
		}])
		.config(function($mdDateLocaleProvider) {
			$mdDateLocaleProvider.formatDate = function(date) {
				if(date != undefined){
					return moment(date).format('DD-MM-YYYY');
				}else{
					return "";
				}	
			};
		});