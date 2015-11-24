angular.module('aviateAdmin.controllers')
.controller("movementEditCtrl", 
		['$scope', '$state','toastr','movementServices','$rootScope','$rootScope',
		 function($scope, $state,  toastr, movementServices, $rootScope, $rootScope) {

			$scope.getInventory = function(){
				$scope.physicalinventory = movementServices.getPhysicalInventoryObj();
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
			
			$scope.getInventoryWarehouse = function()
			{
				$scope.warehouseData = {};

				movementServices.getInventoryWarehouse({'filterType': 'ALL','storeId':$rootScope.user.storeId}).then(function(data){
			
						//$localStorage.warehouseData = {};
						//$localStorage.warehouseData = data.warehouseList;
						$scope.warehouseData = data.warehouseList;

					});
			};
			$scope.getInventoryWarehouse();

			$scope.updatePhysicalInventory = function(){
				$scope.physicalinventory.storeId = $rootScope.user.storeId;
				$scope.physicalinventory.merchantId=$rootScope.user.merchantId;
				movementServices.updatePhysicalInventory($scope.physicalinventory).then(function(data){
					localStorage.removeItem('physicalinventoryDetails');
					$state.go('app.physical_inv');
				});
			};

		}]);