angular.module('aviateAdmin.controllers')
.controller("movementCreateCtrl", 
		['$scope','$localStorage','$location','$rootScope','$state','toastr','movementServices','WarehouseService','ProductService',
		 function($scope,$localStorage,$location,$rootScope, $state, toastr, movementServices, WarehouseService, ProductService) {
			
			
			$rootScope.isNewMovement = true;
			
			$scope.getWarehouse = function() {
				$rootScope.warehouse = {};
				$scope.warehouse.store={};
				$scope.warehouse.store.storeId = $rootScope.user.storeId;
				WarehouseService.warehouseList($scope.warehouse).then(function(data) {
					$scope.warehouseData = data.warehouses;
					$rootScope.warehouse =   data.warehouses [0];
				});
			};
			
			$scope.getWarehouse();
			
			$scope.setStoragebin = function(warehouse){
				$scope.bins = warehouse.storagebins;
				$scope.movement = {};
				$scope.movement.warehouse = warehouse;
				$scope.movement.movementLines = [{}];
			};
			
			$scope.addMovement = function(movement, index){
				movement.merchant = {};
				movement.store = {};
				movement.store.storeId = $rootScope.user.storeId;
				movement.merchant.merchantId = $rootScope.user.merchantId;
				movement.userId = $rootScope.user.userName;

				movementServices.addMovement(movement).then(function(data){
					$state.go('app.editmovement',{'movementId': data.movement.movementId});
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
			
			$scope.addMovementLine = function(movementLine, index){
				movementLine.userId = $rootScope.user.userName;

				movementServices.addMovementLine(movementLine).then(function(data){
					$scope.movement.movementLines[index] = data.movementLine;
					$scope.movement.movementLines.push({});
				});
			};
			
			$scope.getProducts = function() {
				$scope.merchant = {};
				$scope.merchant.merchant = {};
				$scope.merchant.merchant.merchantId = $rootScope.user.merchantId;
				ProductService.getAllProductList($scope.merchant).then(function(data) {
					$scope.products = data.products;
				});
			};
			
			$scope.getProducts();
			
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
			};
			
			$scope.backToDetails = function(){
				localStorage.removeItem('movementDetails');
				movementServices.setMovementObj();
				$state.go('app.movement');
			};

			$scope.copyToeditMovementLine = function(movementLine){
				$scope.editMovementLine = angular.copy(movementLine);
			};
			
			$scope.copyToMovementLine = function(movementLine , el){
				movementLine = angular.copy(el);
			};
			
			
		}]);