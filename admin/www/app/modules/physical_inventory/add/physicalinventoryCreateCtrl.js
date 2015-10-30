angular.module('aviateAdmin.controllers')
.controller("physicalinventoryCreateCtrl", 
		['$scope','$localStorage','$location','$rootScope','$state','toastr','PhysicalInventoryServices',
		 function($scope,$localStorage,$location,$rootScope, $state, toastr, PhysicalInventoryServices) {
			$scope.addPhysicalInventory = function(){

				$scope.inventoryData = {
						'storeId':$rootScope.user.storeId,
						'merchantId':$rootScope.user.merchantId,
						'warehouseId':$scope.physicalinventoryDetail.warehouseId,
						'inventoryName':$scope.physicalinventoryDetail.inventoryName,
						'description':$scope.physicalinventoryDetail.description? $scope.physicalinventoryDetail.description: ""
				};
				PhysicalInventoryServices.addNewPhysicalInventory($scope.inventoryData).then(function(data){
					//toastr.success(data.status);
				});
			};
			$scope.getInventoryWarehouse = function()
			{
				$scope.warehouseData = {};

				PhysicalInventoryServices.getInventoryWarehouse({'filterType': 'ALL','storeId':$rootScope.user.storeId}).then(function(data){

					//$localStorage.warehouseData = {};
					//$localStorage.warehouseData = data.warehouseList;
					$scope.warehouseData = data.warehouseList;

				});
			};
		}]);