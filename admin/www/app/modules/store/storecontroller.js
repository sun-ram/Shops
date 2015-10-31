angular.module('aviateAdmin.controllers')
.controller("storecontroller",['$scope', '$rootScope','$state','$filter','$mdDialog','ngTableParams','StoreServices','stores',
			 function($scope, $rootScope ,$state, $filter,$mdDialog,ngTableParams, StoreServices, stores) {
	

			 /* $scope.query = {
					    limit: 5,
					    page: 1
					  };*/
				
				$scope.count = 3;
				$scope.storeData = stores;
				$scope.srch = true;
				
				$scope.getStoreDetails=function(){
					StoreServices.getStore({'merchantId':$rootScope.user.merchantId}).then(function(data){
						$scope.storeData=data;
					});
				};
				
				$scope.redirectToStoreDetails = function(store){
					StoreServices.setStoreObj(store);
					$state.go('app.storedetails');
				}
				
				$scope.redirectToEditStore = function(store){
					StoreServices.setStoreObj(store);
					$state.go('app.editstore');
				}

				$scope.deleteStore= function(storeId) {
									
									var confirm = $mdDialog.confirm()
							        .title('Would you like to delete Store?')
							        .ok('Delete')
							        .cancel('Cancel');
							  $mdDialog.show(confirm).then(function() {
					
								  $scope.store = {};
					 					$scope.store.storeId = storeId;
					 					StoreServices.deleteStore($scope.store).then(function(data){
					 						$state.go('app.store');
					 						$scope.getStoreDetails();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	