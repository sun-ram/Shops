angular.module('aviateAdmin.controllers')
.controller("shippingChargesController",['$scope', '$rootScope','$state','$filter','$mdDialog','ngTableParams','ShippingChargeServices','toastr',
			 function($scope, $rootScope ,$state, $filter,$mdDialog,ngTableParams,ShippingChargeServices, toastr) {
	

			 /* $scope.query = {
					    limit: 5,
					    page: 1
					  };*/
				
				$scope.count = 3;
				$scope.srch = true;
				$scope.merchantVo = {
						merchantId:$rootScope.user.merchantId
				}
				
				$scope.getShippingCharges=function(){
					
					ShippingChargeServices.getShippingCharge($scope.merchantVo).then(function(data){
						$scope.shippingChargeList=data;
					});
					
				};
				
				$scope.redirectToShippingCharges = function(shippingCharge){
					ShippingChargeServices.setShippingChargeObj(shippingCharge);
					$state.go('app.shippingchargedetails');
				}
				
				$scope.redirectToEditShippingCharge = function(shippingCharge){
					ShippingChargeServices.setShippingChargeObj(shippingCharge);
					$state.go('app.editshippingcharges');
				}
				
				$scope.updateShippingCharges = function(shippingCharge){
					if(shippingCharge.amountRange == "" || shippingCharge.amountRange == undefined){
						toastr.error("Please Enter Amount Range");
						return;
					}else if(shippingCharge.chargingAmount == "" || shippingCharge.chargingAmount == undefined){
						toastr.error("Please Enter Charging Amount");
						return;
					}
					shippingCharge.userId=$rootScope.user.userId;

					ShippingChargeServices.updateShippingCharge(shippingCharge).then(function(data){
						localStorage.removeItem('shippingCharge');
						$state.go('app.shippingCharges');
					});
				};

				$scope.deleteShippingCharge= function(shippingCharge) {
									
									var confirm = $mdDialog.confirm()
							        .title('Would you like to delete Shipping Charge?')
							        .ok('Delete')
							        .cancel('Cancel');
							  $mdDialog.show(confirm).then(function() {
					 					ShippingChargeServices.deleteShippingCharge(shippingCharge).then(function(data){
					 						$state.go('app.shippingCharges');
					 						$scope.getShippingCharges();
					 					});
									
								  }, function() {
								  
								  });				};
				
				/*$scope.storeDetails = function(store){
					$localStorage.store = store;
					$state.go('app.storedetails');
					
				},
				$scope.storedetails=function(store)
				{
					$localStorage.store = store;
					$state.go('app.storedetails');
				}*/
				
				
			}]);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	