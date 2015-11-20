angular.module('aviateAdmin.controllers')
.controller("taxController",['$scope', '$rootScope','$state','$filter','$mdDialog','ngTableParams','TaxServices',
			 function($scope, $rootScope ,$state, $filter,$mdDialog,ngTableParams,TaxServices) {
	

			 /* $scope.query = {
					    limit: 5,
					    page: 1
					  };*/
				
				$scope.count = 3;
				$scope.srch = true;
				$scope.merchantVo = {
						merchantId:"ff80818151128155015112824b3a0001"
				}
				
				$scope.getTax=function(){
					TaxServices.getTax($scope.merchantVo).then(function(data){
						$scope.taxList=data;
					});
				};
				
				$scope.redirectToTax = function(tax){
					TaxServices.setTaxObj(tax);
					$state.go('app.taxdetails');
				}
				
				$scope.redirectToEditTax = function(tax){
					TaxServices.setTaxObj(tax);
					$state.go('app.edittax');
				}

				$scope.deleteTax= function(tax) {
									
									var confirm = $mdDialog.confirm()
							        .title('Would you like to delete Tax?')
							        .ok('Delete')
							        .cancel('Cancel');
							  $mdDialog.show(confirm).then(function() {
					 					TaxServices.deleteTax(tax).then(function(data){
					 						$state.go('app.tax');
					 						$scope.getTax();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	