angular.module('aviateAdmin.controllers')
.controller("movementCreateCtrl", 
		['$scope','$localStorage','$location','$rootScope','$state','toastr','movementServices','WarehouseService','ProductService',
		 function($scope,$localStorage,$location,$rootScope, $state, toastr, movementServices, WarehouseService, ProductService) {
			$scope.isMovementEdit = false;
			
			$scope.getWarehouse = function() {
				$rootScope.warehouse = {};
				$scope.warehouse.store={};
				$scope.warehouse.store.storeId = $rootScope.user.storeId;
				WarehouseService.warehouseList($scope.warehouse).then(function(data) {
					$scope.warehouseData = data.warehouses;
					$rootScope.warehouse =   data.warehouses [0];
				});
			};
			
			$scope.setStoragebin = function(warehouse){
				$scope.bins = warehouse.storagebins;
				$scope.movement.warehouse = warehouse;
				$scope.movement.movementLines = [{}];
			};
			
			$scope.getMovement = function(){
				$scope.movement = movementServices.getMovementObj();
				$scope.temp = localStorage.getItem('physicalinventoryDetails');
				if($scope.movement){
					$scope.bins = $scope.movement.warehouse.storagebins;
					localStorage.setItem('physicalinventoryDetails',JSON.stringify($scope.movement));
					$scope.isMovementEdit = true;
					$scope.movement.movementLines.push({});
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.movement = JSON.parse($scope.temp);
					$scope.isMovementEdit = true;
					if($scope.movement 
							&& $scope.movement.movementLines 
							&& $scope.movement.movementLines[$scope.movement.movementLines.length-1] != {}){
						$scope.movement.movementLines.push({});
					}
					
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
				movementServices.addMovement(movement).then(function(data){
					$scope.isMovementAdded = true;
					$scope.movement = data.movement;
					$scope.movement.movementLines.push({});
				});
			};
			
			$scope.updateMovementLine= function(index, editMovementLine){
				editMovementLine.movementId = $scope.movement.movementId;
				movementServices.addMovementLine(editMovementLine).then(function(data){
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
				movementServices.removeMovement(movement).then(function(data){
					$scope.movement = {};
					localStorage.removeItem('physicalinventoryDetails');
				});
			};
			
			$scope.deleteMovementLine = function(index){
				movementServices.removeMovementLine($scope.movement.movementLines[index]).then(function(data){
					$scope.movement.movementLines.splice(index, 1);
				});
			};
			
			$scope.addMovementLine = function(movementLine, index){
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
			};
			
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

			$scope.processMovement = function(movement) {
				$scope.warehouseData = {};
				movementServices.processMovement({'movementId': movement.movementId}).then(function(data){
					$state.go('app.physical_inv');
				});
			};
			
			$scope.backToDetails = function(){
				localStorage.removeItem('physicalinventoryDetails');
				movementServices.setMovementObj();
				if($rootScope.fromDetails){
					$state.go('app.physicalinventorydetails');
					$rootScope.fromDetails = false;
				} else {
					$state.go('app.physical_inv');
				}
			};
			$scope.copyToeditMovementLine = function(movementLine){
				$scope.editMovementLine = angular.copy(movementLine);
			};
			
			$scope.copyToMovementLine = function(movementLine , el){
				movementLine = angular.copy(el);
			};
			
			$scope.copyObject = function(obj, copy){

			    // Handle the 3 simple types, and null or undefined
			    if (null == obj || "object" != typeof obj) return obj;

			    // Handle Date
			    if (obj instanceof Date) {
			        copy = new Date();
			        copy.setTime(obj.getTime());
			        return copy;
			    }

			    // Handle Array
			    if (obj instanceof Array) {
			        copy = [];
			        for (var i = 0, len = obj.length; i < len; i++) {
			            copy[i] = $scope.copyObject(obj[i], copy);
			        }
			        return copy;
			    }

			    // Handle Object
			    if (obj instanceof Object) {
			        copy = {};
			        for (var attr in obj) {
			            if (obj.hasOwnProperty(attr)) copy[attr] = $scope.copyObject(obj[attr], copy);
			        }
			        return copy;
			    }

			    throw new Error("Unable to copy obj! Its type isn't supported.");
			};
			
		}]);