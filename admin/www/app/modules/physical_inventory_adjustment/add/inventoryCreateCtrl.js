angular.module('aviateAdmin.controllers')
.controller("physicalInventoryCreateCtrl", 
		['$scope','$localStorage','$location','$rootScope','$state','toastr','physicalInventoryService','WarehouseService','ProductService', 'productStockService','inventory','$stateParams',
		 function($scope,$localStorage,$location,$rootScope, $state, toastr, physicalInventoryService, WarehouseService, ProductService, productStockService, inventory, $stateParams) {

			$scope.physicalInventory = inventory;

			if(!angular.equals({}, $scope.physicalInventory)){
				$scope.physicalInventoryList = inventory.movementLines;
			}else{
				$scope.physicalInventoryList = [];
			}
			console.log('state: ', $stateParams);

			$scope.getProductStockList = function () {
				$scope.user = {};
				$scope.user.storevo = {
						"storeId":$rootScope.user.storeId
				};
				productStockService.getProductStockList($scope.user).then(function(data) {
					$scope.productStockList = data;
					console.log("product Stock List", $scope.productStockList);
				});
			};

			$scope.getProductStockList();

			$scope.getWarehouse = function() {
				$scope.warehouse = {};
				$scope.warehouse.store={};
				$scope.warehouse.store.storeId = $rootScope.user.storeId;
				WarehouseService.warehouseList($scope.warehouse).then(function(data) {
					$scope.warehouseData = data.warehouses;
					$scope.warehouse =   data.warehouses [0];
				});
			};

			$scope.getWarehouse();



			$scope.addPhysicalInventory = function(inventory){
				inventory.userId = $rootScope.user.userId;

				physicalInventoryService.addPhysicalInventory(inventory).then(function(data){
					$state.go('app.addNewInventory',{'inventoryId': data.movementId});
				});
			};

			$scope.addPhysicalInventoryLine = function(inventoryLine){
				inventoryLine.userId = $rootScope.user.userId;

				inventoryLine.movementId = $scope.physicalInventory.movementId;
				physicalInventoryService.addPhysicalInventoryLine(inventoryLine).then(function(data){
					$scope.physicalInventoryList.push(data);
					$scope.updateStocksInUi();
				});
			};

			$scope.updatePhysicalInventoryLine = function(inventoryLine, index){
				inventoryLine.userId = $rootScope.user.userId;

				physicalInventoryService.addPhysicalInventoryLine(inventoryLine).then(function(data){
					$scope.physicalInventoryList[index] = data;
					$scope.updateStocksInUi();
				});
			};

			$scope.removeInventoryLine = function(physicalInventoryLine, index){

				if($scope.physicalInventoryList.length == 1){
					physicalInventoryService.deletePhysicalInventory($scope.physicalInventory.movementId).then(function(data){
						$state.go('app.addNewInventory',{'inventoryId': null});
					});
				}else{
					physicalInventoryService.deletePhysicalInventoryLine(physicalInventoryLine.movementLineId).then(function(data){
						$scope.movement.movementLines.splice(index, 1);
					});
				}
			};

			$scope.updateInventory = function(){
				physicalInventoryService.physicalInventoryIsUpdate($scope.physicalInventory.movementId).then(function(data){
					$state.go('app.physicalInventoryAdjustment');
				});
			};

			$scope.addInventory = function(stocks){
				$scope.inventoryLine = {};
				$scope.inventoryLine.qty = stocks.inventoryQty;
				$scope.inventoryLine.product = {
						'productId':stocks.productId
				};
				$scope.inventoryLine.toStoragebin = {
						'storagebinId': stocks.storagebinId
				};
				if(angular.equals({}, $scope.physicalInventory)){
					$scope.inventoryLines = [];
					$scope.inventoryLines.push($scope.inventoryLine);
					$scope.physicalInventory.warehouse = $scope.warehouse;
					$scope.physicalInventory.movementLines =$scope.inventoryLines;
					$scope.addPhysicalInventory($scope.physicalInventory);
				}else{
					$scope.addPhysicalInventoryLine($scope.inventoryLine);
				}
			};

			$scope.updateInventoryLine = function(movementLine, index){
				movementLine.movementId = $scope.physicalInventory.movementId;
				$scope.updatePhysicalInventoryLine(movementLine, index);
				console.log("inventoryList", $scope.physicalInventoryList);
			};

			$scope.updateStocksInUi = function(){
				for(var i = 0; i < $scope.productStockList.length; i++){
					$scope.changeIsExistMovement($scope.productStockList[i]);
				}
			};

			$scope.changeIsExistMovement = function(stocks){

				if($scope.physicalInventoryList.length === 0){
					stocks.isMovementExist = false;
					return;
				}

				for(var i = 0; i <= $scope.physicalInventoryList.length-1; i++){
					$scope.inventory = $scope.physicalInventoryList[i];
					if(stocks.isMovementExist === false){
						stocks.isMovementExist = ($scope.inventory.product.productId==stocks.productId && stocks.storagebinId == $scope.inventory.toStoragebin.storagebinId)? true: false;
						if(stocks.isMovementExist === true){
							stocks.inventoryIndex = i;
						}
					}
				}

			};




		}]);