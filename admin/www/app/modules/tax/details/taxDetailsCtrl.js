angular.module('aviateAdmin.controllers')
	.controller("shippingChargesDetailsCtrl", 
	['$scope', '$rootScope','$state','$filter','$window', 'ngTableParams','StoreServices','$rootScope',
	 function($scope, $rootScope, $state, $filter,$window, ngTableParams,StoreServices, $rootScope) {
		$scope.getStore = function(){
			$scope.store = StoreServices.getStoreObj();
			$scope.temp = localStorage.getItem('storeDetails');
			if($scope.store){
				localStorage.setItem('storeDetails',JSON.stringify($scope.store));
			}else if($scope.temp && $scope.temp != 'undefined'){
				$scope.store = JSON.parse($scope.temp);
			}else{
				localStorage.removeItem('storeDetails');
				$state.go('app.newstore');
			}
		};
		$scope.getStore();
		
		$scope.redirectToEditStore = function(store){
			$rootScope.fromDetailsPage = true;
			StoreServices.setStoreObj(store);
			$state.go('app.editstore');
		};
		
	}]);