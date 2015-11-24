angular.module('aviateAdmin.controllers')
.controller("physicalinventoryDetailsCtrl", 
		['$scope', '$rootScope','$state','$filter','$window', 'ngTableParams','PhysicalInventoryServices',
		 function($scope, $rootScope, $state, $filter,$window, ngTableParams,PhysicalInventoryServices) {

			$scope.getMovement = function(){
				$scope.movement = PhysicalInventoryServices.getMovementObj();
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
				PhysicalInventoryServices.setMovementObj(movement);
				$state.go('app.newphysicalinventory');
			}

		}]);