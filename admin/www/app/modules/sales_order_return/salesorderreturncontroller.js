angular.module('aviateAdmin.controllers').controller("salesorderreturncontroller", 
		['$scope', '$state','$filter','ngTableParams','$timeout','$q','$rootScope','$localStorage','SalesOrderServices','toastr','$mdDialog',
		 function($scope, $state, $filter, ngTableParams, $timeout,$q,$rootScope,$localStorage,SalesOrderServices,toastr,$mdDialog) {
			
			//Need to write sales order return changes here
			$rootScope.getSalesOrderReturnList = function () {
				alert("checking");
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