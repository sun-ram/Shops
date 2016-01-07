angular.module('aviateAdmin.controllers')
.controller("taxController",['$scope', '$rootScope','$state','$filter','$mdDialog','ngTableParams','TaxServices','toastr',
			 function($scope, $rootScope ,$state, $filter,$mdDialog,ngTableParams,TaxServices, toastr) {
	

			 /* $scope.query = {
					    limit: 5,
					    page: 1
					  };*/
				
				$scope.count = 3;
				$scope.srch = true;
				$scope.merchantVo = {
						merchantId:$rootScope.user.merchantId
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
				
				$scope.updateTax = function(tax){
					if(tax.name == "" || tax.name == undefined){
						toastr.error("Please Enter Tax Name");
						return;
					}else if(tax.taxPercentage == "" || tax.taxPercentage == undefined){
						toastr.error("Please Enter Tax Percentage");
						return;
					}
					tax.userId=$rootScope.user.userId;
	            	TaxServices.updateTax(tax).then(function(data){
						localStorage.removeItem('tax');
					});
				};
				
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	