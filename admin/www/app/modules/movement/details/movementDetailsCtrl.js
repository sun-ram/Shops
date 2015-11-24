angular.module('aviateAdmin.controllers')
.controller("movementDetailsCtrl", 
		['$scope', '$rootScope','$state','$filter','$window', 'ngTableParams','movementServices',
		 function($scope, $rootScope, $state, $filter,$window, ngTableParams,movementServices) {
			
			$scope.getMovement = function(){
				$scope.movement = movementServices.getMovementObj();
				$scope.temp = localStorage.getItem('physicalinventoryDetails');
				if($scope.movement){
					localStorage.setItem('physicalinventoryDetails',JSON.stringify($scope.movement));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.movement = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('physicalinventoryDetails');
					$state.go('app.newphysicalinventory');
				}
			};

			$scope.getMovement();

			$scope.redirectToEditMovement = function(movement){
				movementServices.setMovementObj(movement);
				$rootScope.fromDetails = true;
				$state.go('app.newphysicalinventory');
			}

		}]);