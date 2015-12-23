
angular.module('aviateAdmin.controllers').controller("physicaInventoryCtrl", 
		['$scope','$rootScope','$localStorage','$mdDialog','$state','inventories','physicalInventoryService',
		 function($scope,$rootScope,$localStorage,$mdDialog,$state, inventories, physicalInventoryService) {

			$scope.physicalInventories = inventories;
			$scope.originalList = $scope.physicalInventories;
			$scope.noOfRecords = $scope.physicalInventories.length;
			
			console.log("movements",$scope.physicalInventories);
			
			$scope.removeInventory = function(inventory){
				var confirm = $mdDialog.confirm()
				.title('Would you like to delete Movement?')
				.ok('Delete')
				.cancel('Cancel');
				$mdDialog.show(confirm).then(function() {
					physicalInventoryService.deletePhysicalInventory(inventory.movementId).then(function(data){
						physicalInventoryService.getPhysicalInventoriesByStore().then(function(data){
							$scope.physicalInventories = data;
							$scope.originalList = $scope.physicalInventories;
							$scope.noOfRecords = $scope.physicalInventories.length;
						});
					});
				}, function() {

				});	
			};
			
			$scope.updateInventory = function(inventoryId){
				physicalInventoryService.physicalInventoryIsUpdate(inventoryId).then(function(data){
					$state.go('app.physicalInventoryAdjustment');
				});
			};
			
			
			
		}]);
