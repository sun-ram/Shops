angular.module('aviateAdmin.controllers')
	.controller("storeCreateCtrl", 
	['$scope', '$state','toastr','StoreServices','$rootScope','CommonServices',
	 function($scope, $state, toastr, StoreServices, $rootScope,CommonServices) {
		
		$scope.getState = function(country){
			$scope.cunt = JSON.parse(country);
			$scope.states = $scope.cunt.states;
		}
		
		$scope.getCountries = function(){
			CommonServices.getCountries($scope.country).then(function(data){
				$scope.countries=data;
			});
		}
		$scope.getCountries();
		
		$scope.addStore = function(){
			
			$scope.cnt = JSON.parse($scope.country);
			$scope.st = JSON.parse($scope.state);
			$scope.store.user.address.country = {};
			$scope.store.user.address.state = {};
			$scope.store.user.address.country.countryId = $scope.cnt.countryId;
			$scope.store.user.address.country.name = $scope.cnt.name;
			$scope.store.user.address.state.stateId = $scope.st.stateId;
			$scope.store.user.address.state.name = $scope.st.name;
			
			//$scope.store.merchantId = $rootScope.user.merchantId;
			$scope.store.merchant = {};
			$scope.store.merchant.merchantId = {};
			$scope.store.merchant.merchantId = "2c9fa0375119c5c8015119e22dd50004";
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

