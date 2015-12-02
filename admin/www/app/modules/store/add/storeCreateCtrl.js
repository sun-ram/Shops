angular.module('aviateAdmin.controllers')
	.controller("storeCreateCtrl", 
	['$scope', '$state','toastr','StoreServices','$rootScope','CommonServices',
	 function($scope, $state, toastr, StoreServices, $rootScope,CommonServices) {
 
		
		
		$scope.addStore = function(){
			$scope.cnt =  $scope.country;
			$scope.st = $scope.state;
			$scope.store.user.address.country = {};
			$scope.store.user.address.state = {};
			$scope.store.user.address.country.countryId = $scope.cnt.countryId;
			$scope.store.user.address.country.name = $scope.cnt.name;
			$scope.store.user.address.state.stateId = $scope.st.stateId;
			$scope.store.user.address.state.name = $scope.st.name;
			
			//$scope.store.merchantId = $rootScope.user.merchantId;
			$scope.store.merchant = {};
			$scope.store.merchant.merchantId = {};
			$scope.store.merchant.merchantId = $rootScope.user.merchantId;
			if($scope.store.id){
				StoreServices.updateStore($scope.store).then(function(data){
					//toastr.success(data.status);
					$scope.store = null;
					$state.go('app.store');
				});
				
			}
			else{
			StoreServices.addNewStore($scope.store).then(function(data){
				//toastr.success(data.status);
				$scope.store = null;
				$state.go('app.store');
			});
			}
		};
		
		
	}]);

