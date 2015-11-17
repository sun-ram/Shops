angular.module('aviateAdmin.controllers')
	.controller("shippingChargeCreateCtrl", 
	['$scope', '$state','toastr','StoreServices','$rootScope',
	 function($scope, $state, toastr, StoreServices, $rootScope) {
		$scope.addStore = function(){
			$scope.storeDetail.merchantId = $rootScope.user.merchantId;
			StoreServices.addNewStore($scope.storeDetail).then(function(data){
				//toastr.success(data.status);
				$scope.storeDetail = null;
				$state.go('app.shippingCharges');
			});
		};
	}]);

