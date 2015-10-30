angular.module('aviateAdmin.controllers')
	.controller("merchantDetailsCtrl", 
	['$scope', '$rootScope','$state','$filter','$window', 'ngTableParams','MerchantServices','$rootScope',
	 function($scope, $rootScope, $state, $filter,$window, ngTableParams,MerchantServices, $rootScope) {
		$scope.getMerchant = function(){
			$scope.merchant = MerchantServices.getMerchantObj();
			$scope.temp = localStorage.getItem('merchantDetails');
			if($scope.merchant){
				localStorage.setItem('merchantDetails',JSON.stringify($scope.merchant));
			}else if($scope.temp && $scope.temp != 'undefined'){
				$scope.merchant = JSON.parse($scope.temp);
			}else{
				localStorage.removeItem('merchantDetails');
				$state.go('app.newmerchant');
			}
		};
		$scope.getMerchant();
		
		$scope.redirectToEditMerchant = function(merchant){
			$rootScope.fromDetailsPage = true;
			MerchantServices.setMerchantObj(merchant);
			$state.go('app.editmerchant');
		};
		
	}]);