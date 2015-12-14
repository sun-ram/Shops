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
                console.log('choosed stored ',$scope.store);
                
                $scope.country = $scope.store.user.address.country;
                
                $scope.states = _.findWhere($scope.countries,{countryId:$scope.country.countryId}).states;
                
                $scope.state = $scope.store.user.address.state;
                
                $scope.cities = _.findWhere($scope.states,{stateId:$scope.state.stateId}).city;
				$scope.cty = $scope.store.user.address.city;
			};
            if(!$scope.countries){
                setTimeout(function(){
                    $scope.getStore();
                },3000)
            }else{
                $scope.getStore();
            }
			$scope.updateStore = function(){
				$scope.cnt = $scope.country;
				$scope.st = $scope.state;
				$scope.ct = $scope.cty;
				$scope.store.user.address.country = {};
				$scope.store.user.address.state = {};
				$scope.store.user.address.city = {};
				$scope.store.user.address.country.countryId = $scope.cnt.countryId;
				$scope.store.user.address.country.name = $scope.cnt.name;
				$scope.store.user.address.state.stateId = $scope.st.stateId;
				$scope.store.user.address.state.name = $scope.st.name;
				$scope.store.user.address.city.name=$scope.ct.name;
				$scope.store.user.address.city.cityId=$scope.ct.cityId;
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
				$scope.cunt = country;
				$scope.states = $scope.cunt.states;
			}

		}]);