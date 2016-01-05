angular.module('aviateAdmin.controllers')
	.controller("orderNumberCreateCtrl", 
	['$scope', '$state','toastr','$rootScope','OrderNumberServices',
	 function($scope, $state, toastr, $rootScope, OrderNumberServices) {
		
		$scope.assignNextNumber = function(){
			$scope.orderNumber.nextNumber = parseInt($scope.orderNumber.startingNumber) + 1;
		}
			
		$scope.addOrderNumber = function(){
		    $scope.orderNumber.merchant = {};
			$scope.orderNumber.store = {};
			$scope.orderNumber.merchant.merchantId = $rootScope.user.merchantId;
			$scope.orderNumber.store.storeId = $rootScope.user.storeId;
			$scope.orderNumber.userId=$rootScope.user.userId;
            OrderNumberServices.addOrderNumber($scope.orderNumber).then(function(data){
				$scope.orderNumber = null;
				$state.go('app.ordernumber');
			});
		}
	}]);

