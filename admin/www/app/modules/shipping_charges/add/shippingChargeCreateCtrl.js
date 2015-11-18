angular.module('aviateAdmin.controllers')
	.controller("shippingChargeCreateCtrl", 
	['$scope', '$state','toastr','ShippingChargeServices','$rootScope',
	 function($scope, $state, toastr, ShippingChargeServices, $rootScope) {
		$scope.addShippingCharge = function(){
			$scope.shipping.merchant = {
					merchantId:"ff80818151128155015112824b3a0001",
					name:"praba"
			}
			ShippingChargeServices.addNewShippingCharge($scope.shipping).then(function(data){
				//toastr.success(data.status);
				$scope.shipping = null;
				$state.go('app.shippingCharges');
			});
		};
	}]);

