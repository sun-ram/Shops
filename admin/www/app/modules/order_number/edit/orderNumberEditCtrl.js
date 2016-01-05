angular.module('aviateAdmin.controllers')
.controller("orderNumberEditCtrl", 
		['$scope', '$state','toastr','OrderNumberServices',
		 function($scope, $state,  toastr, OrderNumberServices) {

			$scope.orderNumber = OrderNumberServices.getOrderNumberObj();
			
			$scope.updateOrderNumber = function(){
				$scope.orderNumber.userId=$rootScope.user.userId;
				OrderNumberServices.updateOrderNumber($scope.orderNumber).then(function(data){
					$scope.orderNumber=null;
					$state.go('app.ordernumber');
				});
			}
		}]);