angular.module('aviateAdmin.controllers')
	.controller("taxCreateCtrl", 
	['$scope','$rootScope', '$state','toastr','TaxServices','$rootScope',
	 function($scope,$rootScope, $state, toastr, TaxServices, $rootScope) {
		$scope.addTax = function(){
			$scope.tax.merchantVo = {
					merchantId:$rootScope.user.merchantId
			}
			$scope.tax.userId=$rootScope.user.userName;
			TaxServices.addNewTax($scope.tax).then(function(data){
				//toastr.success(data.status);
				$scope.tax = null;
				$state.go('app.tax');
			});
		};
	}]);

