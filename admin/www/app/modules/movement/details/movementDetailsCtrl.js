angular.module('aviateAdmin.controllers')
.controller("movementDetailsCtrl", 
		['$scope', '$rootScope','$state','$filter','$window', 'ngTableParams','movementServices',
		 function($scope, $rootScope, $state, $filter,$window, ngTableParams,movementServices) {
			
			$scope.getMovement = function(){
				$scope.movement = movementServices.getMovementObj();
				$scope.temp = localStorage.getItem('movementDetails');
				if($scope.movement){
					localStorage.setItem('movementDetails',JSON.stringify($scope.movement));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.movement = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('movementDetails');
					$state.go('app.newmovement');
				}
			};

			$scope.getMovement();

			$scope.redirectToEditMovement = function(movement){
				movementServices.setMovementObj(movement);
				$state.go('app.editmovement',{'movementId': movement.movementId});
			};

		}]);