angular.module('aviateAdmin.controllers')
.controller("storeEditCtrl", 
		['$scope', '$state','toastr','StoreServices',
		 function($scope, $state,  toastr, StoreServices) {

			$scope.getStore = function(){
				$scope.storeDetail = StoreServices.getStoreObj();
				$scope.temp = localStorage.getItem('storeDetails');
				if($scope.storeDetail){
					localStorage.setItem('storeDetails',JSON.stringify($scope.storeDetail));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.storeDetail = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('storeDetails');
					$state.go('app.newstore');
				}
			};
			$scope.getStore();

			$scope.updateStore = function(){
				StoreServices.addNewStore($scope.storeDetail).then(function(data){
					localStorage.removeItem('storeDetails');
					$state.go('app.store');
				});
			};
			
			$scope.redirectToStoreDetails = function(store){
				StoreServices.setStoreObj(store);
				$state.go('app.storedetails');
			}

		}]);