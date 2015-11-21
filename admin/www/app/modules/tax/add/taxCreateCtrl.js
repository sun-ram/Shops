angular.module('aviateAdmin.controllers')
	.controller("taxCreateCtrl", 
	['$scope', '$state','toastr','TaxServices','$rootScope',
	 function($scope, $state, toastr, TaxServices, $rootScope) {
		$scope.addTax = function(){
			$scope.tax.merchantVo = {
					merchantId:$rootScope.user.merchantId
			}
			TaxServices.addNewTax($scope.tax).then(function(data){
				//toastr.success(data.status);
				$scope.tax = null;
				$state.go('app.tax');
			});
		};
	}]);

