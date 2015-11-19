angular.module('aviateAdmin.controllers')
.controller("storeEditCtrl", 
		['$scope', '$state','toastr','StoreServices','CommonServices',
		 function($scope, $state,  toastr, StoreServices,CommonServices) {

			$scope.getStore = function(){
				$scope.store = StoreServices.getStoreObj();
				$scope.temp = localStorage.getItem('store');
				if($scope.store){
					localStorage.setItem('store',JSON.stringify($scope.store));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.store = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('store');
					$state.go('app.newstore');
				}
			};
			$scope.getStore();

			$scope.updateStore = function(){
				$scope.cnt = JSON.parse($scope.country);
				$scope.st = JSON.parse($scope.state);
				$scope.store.user.address.country = {};
				$scope.store.user.address.state = {};
				$scope.store.user.address.country.countryId = $scope.cnt.countryId;
				$scope.store.user.address.country.name = $scope.cnt.name;
				$scope.store.user.address.state.stateId = $scope.st.stateId;
				$scope.store.user.address.state.name = $scope.st.name;
				
				StoreServices.updateStore($scope.store).then(function(data){
					localStorage.removeItem('store');
					$state.go('app.store');
				});
			};
			
			$scope.redirectToStoreDetails = function(store){
				StoreServices.setStoreObj(store);
				$state.go('app.storedetails');
			}
			
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
			

		}]);