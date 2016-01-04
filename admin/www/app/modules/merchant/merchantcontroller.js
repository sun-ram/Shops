angular.module('aviateAdmin.controllers').controller("merchantcontroller", 
		['$scope', '$rootScope','$state','$localStorage','$filter','$mdDialog','ngTableParams','MerchantServices','merchants',
		 function($scope,$rootScope, $state,$localStorage, $filter,$mdDialog,ngTableParams, MerchantServices, merchants) {
			
			/*$scope.query = {
					limit: 5,
					page: 1
			};*/
			$rootScope.progressbar = false;
			$scope.merchantData = merchants;
			$scope.originalList = $scope.merchantData;
			$scope.noOfRecords = $scope.merchantData.length;

			console.info($scope.merchantData);
			$scope.srch = true;
			
			$scope.redirectToMerchant=function(){
				MerchantServices.getMerchant().then(function(data){
					$scope.merchantData=data;
					$scope.originalList = $scope.merchantData;
					$scope.noOfRecords = $scope.merchantData.length;

				});
			};
			
			$scope.redirectToMerchantDetails = function(merchant){
				MerchantServices.setMerchantObj(merchant);
				$state.go('app.merchantdetails');
			};
			
			$scope.redirectToEditMerchant = function(merchant){
				/*if(merchant.productImages!=null){
				$rootScope.merchantImage=merchant.productImages.imageUrl;
				}
				else{
					$rootScope.merchantImage="";
				}*/
				MerchantServices.setMerchantObj(merchant);
				$state.go('app.editmerchant');
			}

			$scope.deleteMerchant= function(merchantId) {
						
							var confirm = $mdDialog.confirm()
			        .title('Would you like to delete Merchant?')
					        .ok('Delete')
					        .cancel('Cancel');
					  $mdDialog.show(confirm).then(function() {
				
							  $scope.merchant = {};
				 				$scope.merchant.merchantId = merchantId;
				 				MerchantServices.deleteMerchant($scope.merchant).then(function(data){
				 					$scope.redirectToMerchant();
								
				 				});
						
			  }, function() {
						  
						  });		
					  
			};
			
			
		}]);