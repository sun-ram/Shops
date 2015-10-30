angular.module('aviateAdmin.controllers')
.controller("physicalinventoryEditCtrl", 
		['$scope', '$state','toastr','PhysicalInventoryServices','$rootScope','$rootScope',
		 function($scope, $state,  toastr, PhysicalInventoryServices, $rootScope, $rootScope) {

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
			
			$scope.getInventoryWarehouse = function()
			{
				$scope.warehouseData = {};

				PhysicalInventoryServices.getInventoryWarehouse({'filterType': 'ALL','storeId':$rootScope.user.storeId}).then(function(data){
			
						//$localStorage.warehouseData = {};
						//$localStorage.warehouseData = data.warehouseList;
						$scope.warehouseData = data.warehouseList;

					});
			};
			$scope.getInventoryWarehouse();

			$scope.updatePhysicalInventory = function(){
				$scope.physicalinventory.storeId = $rootScope.user.storeId;
				$scope.physicalinventory.merchantId=$rootScope.user.merchantId;
				PhysicalInventoryServices.updatePhysicalInventory($scope.physicalinventory).then(function(data){
					localStorage.removeItem('physicalinventoryDetails');
					$state.go('app.physical_inv');
				});
			};

		}]);