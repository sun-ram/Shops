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
			
			$scope.getSalesOrderReturnLineDetails = function (salesOrderReturn) {
				$localStorage.salesOrderReturnLineList = salesOrderReturn.salesOrder.salesOrderLineVo;
				$rootScope.salesOrderReturnLineList = $localStorage.salesOrderReturnLineList;
				$state.go('app.salesOrderReturnLine');
			};

			   $scope.processRefundRequest = function(salesOrderreturn) {

			        // get the form data
			        // there are many ways to get this data using jQuery (you can use the class or id also)
			        var formData = {
			            "salesOrderReturnId": salesOrderreturn.salesOrderReturnId
			        };
			        
			        SalesOrderReturnServices.processRefundRequest(formData).then(function(data) {
			        	console.log(data);
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