angular.module('aviateAdmin.controllers')
	.controller("orderNumberCreateCtrl", 
	['$scope', '$state','toastr','$rootScope','OrderNumberServices',
	 function($scope, $state, toastr, $rootScope, OrderNumberServices) {
		
		
		$scope.addOrderNumber = function(){
			$scope.orderNumber.merchant = {};
			$scope.orderNumber.store = {};
			$scope.orderNumber.merchant.merchantId = $rootScope.user.merchantId;
			$scope.orderNumber.store.storeId = $rootScope.user.storeId;
			OrderNumberServices.addOrderNumber($scope.orderNumber).then(function(data){
				$scope.orderNumber = null;
				$state.go('app.ordernumber');
			});
		}
	}]);

