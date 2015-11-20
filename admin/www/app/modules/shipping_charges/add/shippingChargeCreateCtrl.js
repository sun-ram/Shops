angular.module('aviateAdmin.controllers')
	.controller("shippingChargeCreateCtrl", 
	['$scope', '$state','toastr','ShippingChargeServices','$rootScope',
	 function($scope, $state, toastr, ShippingChargeServices, $rootScope) {
		$scope.addShippingCharge = function(){
			$scope.shipping.merchantVo = {
					merchantId:"2c9fa0375119c5c801511b14c88200a0",
					name:"Jayam"
			}
			ShippingChargeServices.addNewShippingCharge($scope.shipping).then(function(data){
				//toastr.success(data.status);
				$scope.shipping = null;
				$state.go('app.shippingCharges');
			});
		};
	}]);

