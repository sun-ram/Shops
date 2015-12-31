angular.module('aviateAdmin.controllers')
	.controller("shippingChargeCreateCtrl", 
	['$scope','$rootScope','$state','toastr','ShippingChargeServices','$rootScope',
	 function($scope,$rootScope, $state, toastr, ShippingChargeServices, $rootScope) {
		$scope.addShippingCharge = function(){
			$scope.shipping.merchantVo = {
					merchantId:$rootScope.user.merchantId
			}
			$scope.shipping.userId=$rootScope.user.userName;
			ShippingChargeServices.addNewShippingCharge($scope.shipping).then(function(data){
				//toastr.success(data.status);
				$scope.shipping = null;
				$state.go('app.shippingCharges');
			});
		};
	}]);

