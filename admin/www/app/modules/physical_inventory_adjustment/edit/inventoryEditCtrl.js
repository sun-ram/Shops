angular.module('aviateAdmin.controllers')
.controller("inventoryEditCtrl", 
		['$scope', '$state','toastr','movementServices','$rootScope','movement', 'ProductService',
		 function($scope, $state,  toastr, movementServices,  $rootScope, movement, ProductService) {
			/*
			$scope.movement = movement;
			
			$scope.isEditMovement = true;
			
			console.log("movement-------> ",$scope.movement);
			
			$scope.setStoragebin = function(){
				$scope.bins = $scope.movement.warehouse.storagebins;
				$scope.movement.movementLines.push({});
			};
			
			$scope.setStoragebin();
			
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
				if($scope.movement.movementLines.length == 2 && !$scope.movement.movementLines[1].movementLineId){
					if($scope.movement.movementLines[index].movementLineId){
						movementServices.removeMovement(movement).then(function(data){
							$state.go('app.newmovement');
						});
					}
					
				}else{
					if($scope.movement.movementLines[index].movementLineId){
						$scope.deleteMovementLine(index);
					}else{
						if(edit === true){
							$scope.movement.movementLines.splice(index,1);
						} else {
							$scope.movement.movementLines[index] = {};
						}
					}
				}
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
			
			$scope.processMovement = function(movement) {
				$scope.warehouseData = {};
				movementServices.processMovement({'movementId': movement.movementId}).then(function(data){
					$state.go('app.movement');
				});
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
					}else{
						$scope.movement.movementLines[index].movementId = $scope.movement.movementId; 
						$scope.addMovementLine($scope.movement.movementLines[index], index);
					}
				}
			};
			
			$scope.updateMovementLine= function(index, editMovementLine){
				editMovementLine.movementId = $scope.movement.movementId;
				movementServices.addMovementLine(editMovementLine).then(function(data){
					$scope.editMovementLine = null;
				});
			};
			
			$scope.backToDetails = function(){
				localStorage.removeItem('movementDetails');
				movementServices.setMovementObj();
				if($rootScope.fromDetails){
					$state.go('app.movementdetails');
					$rootScope.fromDetails = false;
				} else {
					$state.go('app.movement');
				}
			};
			
			$scope.copyToEditMovementLine = function(movementLine){
				$scope.editMovementLine = angular.copy(movementLine);
			};
			
			$scope.copyToMovementLine = function(index){
				$scope.movement.movementLines[index] = $scope.editMovementLine;
				$scope.editMovementLine = null;
			};
			
			$scope.checkAddNew = function(index){
				if(!($scope.movement.movementLines[index].qty && $scope.movement.movementLines[index].toStoragebin && $scope.movement.movementLines[index].product)){
					return true;
				} else { 
					return false;
				}
			};*/

		}]);