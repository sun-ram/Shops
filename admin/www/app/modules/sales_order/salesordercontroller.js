angular.module('aviateAdmin.controllers').controller("salesordercontroller", 
		['$scope', '$state','$filter','ngTableParams','$timeout','$q','$rootScope','$localStorage','SalesOrderServices','toastr','$mdDialog',
		 function($scope, $state, $filter, ngTableParams, $timeout,$q,$rootScope,$localStorage,SalesOrderServices,toastr,$mdDialog) {

			$scope.selected = [];
			$rootScope.salesOrderDetails = $localStorage.salesorderline;
			$scope.salesOrderPagination.order= 'customer.name';
			
			if($localStorage.state){
				$scope.salesOrderList = $localStorage.salesorderfilter;
				$localStorage.state=false;
			}else{
				$scope.salesOrderList = $localStorage.salesorderlist;

			}
			
			 $scope.showTabShoper=function(salesOrder,ev) {
				 var userVo = {};
					if($rootScope.user.role == "MERCHANTADMIN"){
						userVo.merchant = {
								"merchantId":$rootScope.user.merchantId
						};
					}else if($rootScope.user.role == "STOREADMIN"){
						userVo.store = {
								"storeId":$rootScope.user.storeId
						};
					}
					SalesOrderServices.getShoperDetails(userVo).then(function(data) {
						 $scope.showShoperDetails(salesOrder,ev,data);

					});
					
			 };
			 
			 $scope.showShoperDetails = function(salesOrder,ev,userList) {
				 $mdDialog.show({
						templateUrl: 'app/modules/modals/salerOrderAssignModal.html',
						parent: angular.element(document.body),
						targetEvent: ev,
						clickOutsideToClose:true,
						controller: function($scope,$rootScope){
							$scope.userList1=userList;
							$scope.heading="Assign Shoper";
							$scope.salesId=salesOrder.salesOrderId;
							$scope.assignShopper = function() {
								if($scope.shoper!=undefined){
										var salesOrder = {
											salesOrderId:$scope.salesId,
											user:{},
										};
										salesOrder.user.userId= $scope.shoper;
										SalesOrderServices.updateShoperIntoSalesOrder(salesOrder).then(function(data) {
											$rootScope.getSalesOrderList();
											$mdDialog.cancel();
		
										});
									}
								else{
									alert("Choose the Shoper");
								}
							};
							$scope.cancel = function() {
								$mdDialog.cancel();
							};
						}
					})
					.then(function(answer) {	
						$scope.status = 'You said the information was "' + answer + '".';
					}, function() {
						$scope.status = 'You cancelled the dialog.';
					});
				  };
			 
			 $scope.showTabBacker=function(salesOrder,ev) {
				 var userVo = {};
					if($rootScope.user.role == "MERCHANTADMIN"){
						userVo.merchant = {
								"merchantId":$rootScope.user.merchantId
						};
					}else if($rootScope.user.role == "STOREADMIN"){
						userVo.store = {
								"storeId":$rootScope.user.storeId
						};
					}
					SalesOrderServices.getBackerDetails(userVo).then(function(data) {
						 $scope.showBackerDetails(salesOrder,ev,data);

					});
					
			 };
			
			 $scope.showBackerDetails = function(salesOrder,ev,userList) {
				 $mdDialog.show({
						templateUrl: 'app/modules/modals/salerOrderAssignModal.html',
						parent: angular.element(document.body),
						targetEvent: ev,
						clickOutsideToClose:true,
						controller: function($scope,$rootScope){
							$scope.userList1=userList;
							$scope.heading="Assign Backer";
							$scope.salesId=salesOrder.salesOrderId;
							$scope.assignShopper = function() {
								if($scope.shoper!=undefined){
										var salesOrder = {
											salesOrderId:$scope.salesId,
											user:{},
										};
										salesOrder.user.userId= $scope.shoper;
										SalesOrderServices.updateBackerIntoSalesOrder(salesOrder).then(function(data) {
											$rootScope.getSalesOrderList();
											$mdDialog.cancel();
		
										});
									}
								else{
									alert("Choose the Shoper");
								}
							};
							$scope.cancel = function() {
								$mdDialog.cancel();
							};
						}
					})
					.then(function(answer) {	
						$scope.status = 'You said the information was "' + answer + '".';
					}, function() {
						$scope.status = 'You cancelled the dialog.';
					});
				  };
			
			
			$rootScope.getSalesOrderList = function () {
				var salesOrderVo = {};
				if($rootScope.user.role == "MERCHANTADMIN"){
					salesOrderVo.merchant = {
							"merchantId":$rootScope.user.merchantId
					};
				}else if($rootScope.user.role == "STOREADMIN"){
					salesOrderVo.store = {
							"storeId":$rootScope.user.storeId
					};
				}

				SalesOrderServices.getSalesOrder(salesOrderVo).then(function(data) {
					$scope.salesOrderList = data;
					$localStorage.salesorderlist = data;
					$scope.count=data.length;

				});
			};

			$scope.getMerchantStore = function () {

				var merchantVo = {
						"merchantId":$rootScope.user.merchantId
				};
				SalesOrderServices.getMerchantStore(merchantVo).then(function(data) {
					$scope.merchantStore = data;

				});
			};


			$scope.storeFilter= function(){
				var request = {
						"storeId":$scope.storeId
				};
				$scope.search=$scope.storeId;
				/*SalesOrderServices.getSalesOrderByStore(request).then(function(data) {
					$localStorage.salesorderfilter = data;
					$scope.salesOrderList = $localStorage.salesorderfilter;

				});*/
			};

			$scope.getSalesByDate = function () {
				if($scope.fromDate != undefined){

					if($scope.toDate != undefined){
							
						if($scope.fromDate > $scope.toDate){
							toastr.error("To date must less than from date");
						}else{
							var salesOrderVo = {
									"fromDate":$scope.fromDate.toLocaleDateString(),
									"deliveryDate":$scope.toDate.toLocaleDateString()
							};
							
							if($rootScope.user.role == "MERCHANTADMIN"){
								salesOrderVo.merchantId=$rootScope.user.merchantId
							}else if($rootScope.user.role == "STOREADMIN"){
								salesOrderVo.storeVo = {
										"storeId":$rootScope.user.storeId
								};
							}

							SalesOrderServices.getSalesByDate(salesOrderVo).then(function(data) {
								$localStorage.salesorderfilter = data;
								$scope.salesOrderList = $localStorage.salesorderfilter;

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
			
			$scope.salesOrder = function () {
				$localStorage.state=true;
				$state.go('app.salesorder');
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