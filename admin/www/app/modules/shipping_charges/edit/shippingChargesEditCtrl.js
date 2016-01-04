angular.module('aviateAdmin.controllers')
.controller("shippingChargesEditCtrl", 
		['$scope','$rootScope', '$state','toastr','ShippingChargeServices',
		 function($scope,$rootScope, $state,  toastr, ShippingChargeServices) {

			$scope.getshippingCharge = function(){
				$scope.shippingCharge = ShippingChargeServices.getShippingChargeObj();
				$scope.temp = localStorage.getItem('shippingCharge');
				if($scope.shippingCharge){
					localStorage.setItem('shippingCharge',JSON.stringify($scope.shippingCharge));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.shippingCharge = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('shippingCharge');
					$state.go('app.newshippingcharges');
				}
			};
			$scope.getshippingCharge();

			$scope.updateShippingCharges = function(){
				$scope.shippingCharge.userId=$rootScope.user.userId;

				ShippingChargeServices.updateShippingCharge($scope.shippingCharge).then(function(data){
					localStorage.removeItem('shippingCharge');
					$state.go('app.shippingCharges');
				});
			};
			
			$scope.redirectToShippingCharges = function(shippingCharges){
				StoreServices.setShippingChargeObj(shippingCharges);
				$state.go('app.shippingCharges');
			}

		}]);