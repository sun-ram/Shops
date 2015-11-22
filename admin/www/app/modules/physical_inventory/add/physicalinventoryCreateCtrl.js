angular.module('aviateAdmin.controllers')
.controller("physicalinventoryCreateCtrl", 
		['$scope','$localStorage','$location','$rootScope','$state','toastr','PhysicalInventoryServices','WarehouseService','ProductService',
		 function($scope,$localStorage,$location,$rootScope, $state, toastr, PhysicalInventoryServices, WarehouseService, ProductService) {
			
			$scope.addMovement = function(movement, index){
				movement.merchant = {};
				movement.store = {};
				movement.store.storeId = $rootScope.user.storeId;
				movement.merchant.merchantId = $rootScope.user.merchantId;
				PhysicalInventoryServices.addMovement(movement).then(function(data){
					$scope.isMovementAdded = true;
					//#push 0 to position 1
					$scope.movement.movementLines.splice(index,0,data.movement.movementList)
					$scope.movement.movementLines.push({});
				});
			};
			
			$scope.removeMovement = function(movement){
				PhysicalInventoryServices.removeMovement(movement).then(function(data){
					$scope.movement = {};
				});
			};
			
			$scope.addMovementLine = function(){
				PhysicalInventoryServices.addMovementLine($scope.movement).then(function(data){
					$scope.movement.movementLines.push({});
				});
			};
			
			
			$scope.getWarehouse = function() {
				$scope.warehouse = {};
				$scope.warehouse.store={};
				$scope.warehouse.store.storeId = $rootScope.user.storeId;
				WarehouseService.warehouseList($scope.warehouse).then(function(data) {
					$scope.warehouseData = data.warehouses;
				});
			};
			
			$scope.getWarehouse();
			
			$scope.setStoragebin = function(warehouse){
				$scope.bins = warehouse.storagebins;
				$scope.movement.warehouse = warehouse;
				$scope.movement.movementLines = [{}];
			}
			
			$scope.getProducts = function() {
				$scope.merchant = {};
				$scope.merchant.merchant = {};
				$scope.merchant.merchant.merchantId = $rootScope.user.merchantId;
				ProductService.getAllProductList($scope.merchant).then(function(data) {
					$scope.products = data.productList;
				});
			};
			
			$scope.getProducts();
			
			$scope.removeMovementLine = function(index){
				if($scope.movement.movementLines.length == 1){
					$scope.removeMovement($scope.movement);
					$scope.movement.movementLines = [{}];
				}else{
					if($scope.movement.movementLines[index].movementLineId){
						PhysicalInventoryServices.removeMovementLine($scope.movement.movementLines[index]).then(function(data){
							$scope.movement.movementLines.splice(index, 1);
						});
					}else{
						$scope.movement.movementLines.splice(index, 1);
					}
				}
			}
			
			$scope.movement = {};

			$scope.addNewRow = function(index){
				if($scope.movement.movementLines.length-1 != index){
					return;
				}
				
				if($scope.movement.movementLines.length == 1){
					$scope.addMovement(angular.copy($scope.movement));
				}else{
					$scope.addMovementLine($scope.movement.movementLines[index]);
				}
			}

			/*$scope.getInventoryWarehouse = function() {
				$scope.warehouseData = {};
				PhysicalInventoryServices.getInventoryWarehouse({'filterType': 'ALL','storeId':$rootScope.user.storeId}).then(function(data){
					//$localStorage.warehouseData = {};
					//$localStorage.warehouseData = data.warehouseList;
					$scope.warehouseData = data.warehouseList;

				});
			};*/
		}]);