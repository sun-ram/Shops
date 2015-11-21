angular.module('aviateAdmin.controllers')
	.controller("shippingChargeCreateCtrl", 
	['$scope', '$state','toastr','ShippingChargeServices','$rootScope',
	 function($scope, $state, toastr, ShippingChargeServices, $rootScope) {
		$scope.addShippingCharge = function(){
			$scope.shipping.merchantVo = {
					merchantId:$rootScope.user.merchantId
			}
			ShippingChargeServices.addNewShippingCharge($scope.shipping).then(function(data){
				//toastr.success(data.status);
				$scope.shipping = null;
				$state.go('app.shippingCharges');
			});
		};
	}]);

