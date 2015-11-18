angular.module('aviateAdmin.controllers')
.controller("shippingChargesController",['$scope', '$rootScope','$state','$filter','$mdDialog','ngTableParams','ShippingChargeServices',
			 function($scope, $rootScope ,$state, $filter,$mdDialog,ngTableParams,ShippingChargeServices) {
	

			 /* $scope.query = {
					    limit: 5,
					    page: 1
					  };*/
				
				$scope.count = 3;
				$scope.srch = true;
				$scope.shipping = {};
				$scope.shipping.merchant = {
						merchantId:"ff80818151128155015112824b3a0001"
				}
				
				$scope.getShippingCharges=function(){
					if(ShippingChargeServices.getShippingChargeObj()){
						$scope.shippingChargeList = ShippingChargeServices.getShippingChargeObj();
					}else{
					ShippingChargeServices.getShippingCharge($scope.shipping).then(function(data){
						$scope.shippingChargeList=data;
					});
					}
				};
				
				$scope.redirectToShippingCharges = function(shippingCharge){
					ShippingChargeServices.setShippingChargeObj(shippingCharge);
					$state.go('app.shippingchargedetails');
				}
				
				$scope.redirectToEditShippingCharge = function(shippingCharge){
					ShippingChargeServices.setShippingChargeObj(shippingCharge);
					$state.go('app.editshippingcharges');
				}

				$scope.deleteShippingCharge= function(shippingCharge) {
									
									var confirm = $mdDialog.confirm()
							        .title('Would you like to delete Store?')
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	