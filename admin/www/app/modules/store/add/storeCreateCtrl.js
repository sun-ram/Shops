angular.module('aviateAdmin.controllers')
	.controller("storeCreateCtrl", 
	['$scope', '$state','toastr','StoreServices','$rootScope','CommonServices',
	 function($scope, $state, toastr, StoreServices, $rootScope,CommonServices) {
 
		
		
		$scope.addStore = function(){
			$scope.cnt =  $scope.country;
			$scope.store.user.address.country = {};
			$scope.store.user.address.state = {};
			$scope.store.user.address.city = {};
			$scope.store.user.address.country.countryId = $scope.cnt.countryId;
			$scope.store.user.address.country.name = $scope.cnt.name;
			$scope.store.user.address.state.stateId = $scope.state.stateId;
			$scope.store.user.address.state.name = $scope.state.name;
			$scope.store.user.address.city.name=$scope.cty.name;
			$scope.store.user.address.city.cityId=$scope.cty.cityId;
			$scope.store.userId=$rootScope.user.userName;	
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

