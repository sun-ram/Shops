angular.module('aviateAdmin.controllers')
	.controller("physicalinventoryDetailsCtrl", 
	['$scope', '$rootScope','$state','$filter','$window', 'ngTableParams','PhysicalInventoryServices',
	 function($scope, $rootScope, $state, $filter,$window, ngTableParams,PhysicalInventoryServices) {
		$scope.getInventory = function(){
			$scope.physicalinventory = PhysicalInventoryServices.getPhysicalInventoryObj();
			$scope.temp = localStorage.getItem('physicalinventoryDetails');
			if($scope.physicalinventory){
				localStorage.setItem('physicalinventoryDetails',JSON.stringify($scope.physicalinventory));
			}else if($scope.temp && $scope.temp != 'undefined'){
				$scope.physicalinventory = JSON.parse($scope.temp);
			}else{
				localStorage.removeItem('physicalinventoryDetails');
				$state.go('app.newphysicalinventory');
			}
		};
		$scope.getInventory();
		
		$scope.redirectToEditPhysicalinventory = function(physicalinventory){
			PhysicalInventoryServices.setPhysicalInventoryObj(physicalinventory);
			$state.go('app.editphysicalinventory');
		};
		
		$scope.redirectToInventoryLine = function(physicalinventory){
			//localStorage.setItem("physicalinventory", physicalinventory);
			PhysicalInventoryServices.setPhysicalInventoryObj(physicalinventory);
			$state.go('app.addInventoryLines');
		};
	
		
	}]);