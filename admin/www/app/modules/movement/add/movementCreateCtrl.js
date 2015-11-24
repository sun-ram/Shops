angular.module('aviateAdmin.controllers')
.controller("movementCreateCtrl", 
		['$scope','$localStorage','$location','$rootScope','$state','toastr','PhysicalInventoryServices','WarehouseService','ProductService',
		 function($scope,$localStorage,$location,$rootScope, $state, toastr, PhysicalInventoryServices, WarehouseService, ProductService) {
			$scope.isMovementEdit = false;
			
			$scope.getWarehouse = function() {
				$scope.warehouse = {};
				$scope.warehouse.store={};
				$scope.warehouse.store.storeId = $rootScope.user.storeId;
				WarehouseService.warehouseList($scope.warehouse).then(function(data) {
					$scope.warehouseData = data.warehouses;
				});
			};
			
			$scope.setStoragebin = function(warehouse){
				$scope.bins = warehouse.storagebins;
				$scope.movement.warehouse = warehouse;
				$scope.movement.movementLines = [{}];
			}
			
			$scope.getMovement = function(){
				$scope.movement = PhysicalInventoryServices.getMovementObj();
				$scope.temp = localStorage.getItem('physicalinventoryDetails');
				if($scope.movement){
					$scope.bins = $scope.movement.warehouse.storagebins;
					localStorage.setItem('physicalinventoryDetails',JSON.stringify($scope.movement));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.movement = JSON.parse($scope.temp);
					$scope.isMovementEdit = true;
					$scope.bins = $scope.movement.warehouse.storagebins;
					/*$scope.movement.warehouse = $scope.movement.warehouse;*/
				}else{
					$scope.movement = {};
					$scope.getWarehouse();
					localStorage.removeItem('physicalinventoryDetails');
				}
			};
			
			$scope.getMovement();
			
			
			$scope.addMovement = function(movement, index){
				movement.merchant = {};
				movement.store = {};
				movement.store.storeId = $rootScope.user.storeId;
				movement.merchant.merchantId = $rootScope.user.merchantId;
				PhysicalInventoryServices.addMovement(movement).then(function(data){
					$scope.isMovementAdded = true;
					$scope.movement = data.movement;
					$scope.movement.movementLines.push({});
				});
			};
			
			$scope.updateMovementLine= function(index, editMovementLine){
				editMovementLine.movementId = $scope.movement.movementId;
				PhysicalInventoryServices.addMovementLine(editMovementLine).then(function(data){
					localStorage.setItem('physicalinventoryDetails',JSON.stringify($scope.movement));
					$scope.editMovementLine = null;
				});
			};
			
			$scope.checkAddNew = function(index){
				if(!($scope.movement.movementLines[index].qty && $scope.movement.movementLines[index].toStoragebin && $scope.movement.movementLines[index].product)){
					return true;
				} else { 
					return false;
				}
			};
			
			$scope.showClearButton = function(index){
				if(!($scope.movement.movementLines[index].qty || $scope.movement.movementLines[index].toStoragebin || $scope.movement.movementLines[index].product)){
					return false;
				} else { 
					return true;
				}
			};
			
			$scope.deleteMovement = function(movement){
				PhysicalInventoryServices.removeMovement(movement).then(function(data){
					$scope.movement = {};
					localStorage.removeItem('physicalinventoryDetails');
				});
			};
			
			$scope.deleteMovementLine = function(index){
				PhysicalInventoryServices.removeMovementLine($scope.movement.movementLines[index]).then(function(data){
					$scope.movement.movementLines.splice(index, 1);
				});
			};
			
			$scope.addMovementLine = function(movementLine, index){
				PhysicalInventoryServices.addMovementLine(movementLine).then(function(data){
					$scope.movement.movementLines[index] = data.movementLine;
					$scope.movement.movementLines.push({});
				});
			};
			
			$scope.getProducts = function() {
				$scope.merchant = {};
				$scope.merchant.merchant = {};
				$scope.merchant.merchant.merchantId = $rootScope.user.merchantId;
				ProductService.getAllProductList($scope.merchant).then(function(data) {
					$scope.products = data.productList;
				});
			};
			
			$scope.getProducts();
			
			$scope.removeMovementLine = function(index,edit){
				if($scope.movement.movementLines.length == 1){
					if($scope.movement.movementLines[index].movementLineId){
						$scope.deleteMovement($scope.movement);
					}
					$scope.movement.movementLines = [{}];
				}else{
					if($scope.movement.movementLines[index].movementLineId){
							$scope.deleteMovementLine(index);
					}else{
						if(edit == true){
							$scope.movement.movementLines.splice(index,1);
						} else {
							$scope.movement.movementLines[index] = {};
						}
					}
				}
			}
			
			$scope.addNewRow = function(index){
				
				if(!($scope.movement.movementLines[index].qty && $scope.movement.movementLines[index].toStoragebin && $scope.movement.movementLines[index].product)){
					return;
				}
				
				if($scope.movement.movementLines.length-1 != index){
					return;
				}
				
				if($scope.movement.movementLines.length == 1){
					if($scope.movement.movementId){
						$scope.movement.movementLines.push({});
					}else{
						$scope.addMovement(angular.copy($scope.movement));
					}
				}else{
					if($scope.movement.movementLines.movementLineId){
						//$scope.updateMovementLine($scope.movement.movementLines[index]);
					}else{
						$scope.movement.movementLines[index].movementId = $scope.movement.movementId; 
						$scope.addMovementLine($scope.movement.movementLines[index], index);
					}
				}
			}

			$scope.processMovement = function(movement) {
				$scope.warehouseData = {};
				PhysicalInventoryServices.processMovement({'movementId': movement.movementId}).then(function(data){
					$state.go('app.physical_inv');
				});
			};
			
			$scope.backToDetails = function(){
				if($rootScope.fromDetails){
					$state.go('app.physicalinventorydetails');
					$rootScope.fromDetails = false;
				} else {
					$state.go('app.physical_inv');
				}
			}
			
		}]);