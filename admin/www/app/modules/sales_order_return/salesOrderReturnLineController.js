angular.module('aviateAdmin.controllers').controller("salesorderreturncontroller", 
		['$scope', '$state','$filter','ngTableParams','$timeout','$q','$rootScope','$localStorage','SalesOrderReturnServices','toastr','$mdDialog',
		 function($scope, $state, $filter, ngTableParams, $timeout,$q,$rootScope,$localStorage,SalesOrderReturnServices,toastr,$mdDialog) {
			
			if($localStorage.salesOrderReturnLineList){
				$scope.salesOrderReturnLineList = $localStorage.salesOrderReturnLineList;
			}else{
				$state.go('app.salesOrderReturn');
			}
			
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