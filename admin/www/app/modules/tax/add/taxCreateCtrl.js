angular.module('aviateAdmin.controllers')
	.controller("taxCreateCtrl", 
	['$scope', '$state','toastr','TaxServices','$rootScope',
	 function($scope, $state, toastr, TaxServices, $rootScope) {
		$scope.addTax = function(){
			$scope.tax.merchant = {
					merchantId:"2c9fa0375119c5c801511b14c88200a0",
					name:"Jayam"
			}
			TaxServices.addNewTax($scope.tax).then(function(data){
				//toastr.success(data.status);
				$scope.tax = null;
				$state.go('app.tax');
			});
		};
	}]);

