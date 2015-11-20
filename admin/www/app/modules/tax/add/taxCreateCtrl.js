angular.module('aviateAdmin.controllers')
	.controller("taxCreateCtrl", 
	['$scope', '$state','toastr','TaxServices','$rootScope',
	 function($scope, $state, toastr, TaxServices, $rootScope) {
		$scope.addTax = function(){
			$scope.tax.merchantVo = {
					merchantId:"ff80818151128155015112824b3a0001",
					name:"Jayam"
			}
			TaxServices.addNewTax($scope.tax).then(function(data){
				//toastr.success(data.status);
				$scope.tax = null;
				$state.go('app.tax');
			});
		};
	}]);

