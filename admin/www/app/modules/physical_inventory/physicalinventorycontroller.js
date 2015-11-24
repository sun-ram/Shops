angular.module('aviateAdmin.controllers').controller("physicalinventorycontroller", 
		['$scope','$rootScope','$localStorage','$state','PhysicalInventoryServices','movementLists',
		 function($scope,$rootScope,$localStorage,$state, PhysicalInventoryServices, movementLists) {

			$scope.count = 3;
			$scope.movements = movementLists;
			$scope.srch = true;
			
			$scope.redirectGetPhysicalInventory = function(){
				PhysicalInventoryServices.getInventory({'storeId':$rootScope.user.storeId}).then(function(data){
					$scope.physicalinventoryData=data;
				});
			}

			$scope.redirectToMovementDetails = function(movement){
				PhysicalInventoryServices.setMovementObj(movement);
				$state.go('app.physicalinventorydetails');
			}

			$scope.redirectToEditMovement = function(movement){
				PhysicalInventoryServices.setMovementObj(movement);
				$state.go('app.newphysicalinventory');
			}

			$scope.processMovement = function(movement) {
				$scope.warehouseData = {};
				PhysicalInventoryServices.processMovement({'movementId': movement.movementId}).then(function(data){
					$state.go('app.newphysicalinventory');
				});
			};
			
			$scope.removeMovement = function(movement){
				PhysicalInventoryServices.removeMovement(movement).then(function(data){
					PhysicalInventoryServices.getInventory({'store':{'storeId':$rootScope.user.storeId}}).then(function(data){
						$scope.movements = data;
    				});
				});
			};

			$rootScope.conformInventroy = function(inventoryId) {
				PhysicalInventoryServices.conformInventroy({"inventoryId": inventoryId}).then(function(data){
					console.log(data);
					//$scope.getPhysicalinventoryDetails();
					$scope.redirectGetPhysicalInventory();
				});
			}

			$scope.viewLine = function(physicalinventory) {
				PhysicalInventoryServices.setPhysicalInventoryObj(physicalinventory);
				console.log(physicalinventory);
				/*$rootScope.inventoryId = physicalinventory.inventoryId;
				$localStorage.inventorDetails.inventoryId = $rootScope.inventoryId ;
				$rootScope.inventoryName = physicalinventory.inventoryName;
				$localStorage.inventorDetails.inventoryName = $rootScope.inventoryName ;*/
				$state.go('app.addInventoryLines');
			}


		}]);













/*angular.module('aviateAdmin.controllers')
.controller("physicalinventorycontroller", [ '$scope', '$http', '$rootScope', '$state', '$filter', 'ngTableParams','$localStorage',
                                                        function($scope, $http, $rootScope, $state, $filter, ngTableParams, $localStorage) {


	$rootScope.logout = function() {
		delete $localStorage.userDetails;
		$rootScope.user = {};
		$state.go('login');
	}
	$scope.addInventory = function() {
		$state.go('app.addInventory');
	}
	if (angular.isDefined($localStorage.userDetails)) {
		$rootScope.user = $localStorage.userDetails;
	}
	if($localStorage.storeAdmin){
		$rootScope.shopDetails = $localStorage.storeAdmin;
	}


	if (angular.isDefined($localStorage.inventorDetails)) {
		$rootScope.warehouseName = $localStorage.inventorDetails.warehouseName ;
		$rootScope.storeName  = $localStorage.inventorDetails.storeName;
		$rootScope.inventoryName = $localStorage.inventorDetails.inventoryName ;
		$scope.inventoryId = $localStorage.inventorDetails.inventoryId;
		$rootScope.warehouseId = $localStorage.inventorDetails.warehouseId;
		$rootScope.storeId = $localStorage.inventorDetails.storeId;
	}else{
		$localStorage.inventorDetails = {};
	}

	// Inventory Processes
	$scope.getStore = function() {
		var menuJson = angular.toJson({
			"filterType": "ALL"
		});
		$http({
			url: serviceUrl + 'aviate/json/product/getshoplist',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			var data = result.shoplist;
			$scope.shopList = result.shoplist;
		})
	}

	$scope.getInventoryWarehouse = function() {
		var warehouseJson = angular.toJson({
			"filterType": "ALL",
			"storeId":$rootScope.user.storeId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventory/warehouselist',
			method: 'POST',
			data: warehouseJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
			var data = result.warehouseList;
			$scope.getWarehouseList = result.warehouseList;
		})
	}
	$rootScope.viewLine = function(inventroy) {
		$scope.inventoryId = inventroy.inventoryId;
		$localStorage.inventorDetails.inventoryId = $scope.inventoryId ;
		$rootScope.inventoryName = inventroy.inventoryName;
		$localStorage.inventorDetails.inventoryName = $rootScope.inventoryName ;
		$location.path("/addInventoryLines");
	}

	$rootScope.conformInventroy = function(inventoryId) {
		var conformInventoryJsonReq = angular
		.toJson({
			"inventoryId": inventoryId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventory/conforminventroylinedata',
			method: 'POST',
			data: conformInventoryJsonReq,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$location.path("/physical_inv");
		})
	}


	$rootScope.addinventoryFunc = function(params,test) {
		params.store = test;
		$rootScope.warehouseName = params.p.warehouseName;
		$rootScope.storeName = params.store.storeName;
		$localStorage.inventorDetails.warehouseName = $rootScope.warehouseName ;
		$localStorage.inventorDetails.storeName = $rootScope.storeName ;
		$localStorage.inventorDetails.warehouseId = params.p.warehouseId ;
		$localStorage.inventorDetails.storeId = params.store.storeId ;
		var addInventoryJsonReq = angular
		.toJson({
			"storeId": params.store.storeId,
			"warehouseId": params.p.warehouseId,
			"inventoryName": params.inventoryName,
			"description": params.description ? params.description : ""
		});
		$http({
			url: serviceUrl + 'aviate/json/inventory/addinventory',
			method: 'POST',
			data: addInventoryJsonReq,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			if (result.status == "SUCCESS") {
				//alert("Inventory added successfully");
				params.storeId = params.store.storeId;
				params.warehouseId = null;
				$rootScope.inventoryName = params.inventoryName;
				$localStorage.inventorDetails.inventoryName = $rootScope.inventoryName ;
				params.description = null;
				$scope.inventoryId = result.inventoryId;
				$localStorage.inventorDetails.inventoryId = $scope.inventoryId ;
				$location.path("/addInventoryLines");
			} else {
				alert(result.message);
			}
		})
	}

	// Inventory Line processes

	$scope.getProducts = function() {
		var productJson = angular.toJson({
			"filterType": "ALL",
			"storeId": $rootScope.user.storeId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventoryline/productlist',
			method: 'POST',
			data: productJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
			var data = result.productList;
			$scope.productList = result.productList;
		})
	}

	$scope.removeInventory = function(inventoryId) {
		var productJson = angular.toJson({
			"inventoryId": inventoryId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventory/removeinventory',
			method: 'POST',
			data: productJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
		})
	}


	$scope.removeInventoryLine = function(inventoryLineId) {
		var productJson = angular.toJson({
			"inventoryLineId": inventoryLineId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventoryline/removeinventoryline',
			method: 'POST',
			data: productJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
			$scope.getInventroyLineList();
		})
	}

	$scope.getInventory = function() {
		var listOfInventoryReq = angular.toJson({
			"storeId": $rootScope.user.storeId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventoryline/inventoryList',
			method: 'POST',
			data: listOfInventoryReq,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			var data = result.inventoryList;
			$scope.inventoryList = result.inventoryList;
			$scope.tableParams = new ngTableParams({ page: 1, count: 10
			}, {
				total: data.length,
				getData: function($defer, params) {
					var orderedData = params.sorting() ? $filter('orderBy')
							(data, params.orderBy()) : data;
							$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
				}
			});
		})
	}

	$scope.editInventory = function(inventory) {
		$rootScope.editInventoryObject = inventory;
		$location.path("/addInventory");
	}

	$scope.productUom = function(productId) {
		var listOfUomJsonReq = angular.toJson({
			"productId": productId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventoryline/getUomList',
			method: 'POST',
			data: listOfUomJsonReq,
			headers: {
				'Content-Type': 'application/json'
			}
		}) .success( function(result, status, headers) {
			$scope.productUomList = result.productUomList;
		})
	}
	$scope.warehouseBin = function(warehouseId) {
		var warehouseBinJsonReq = angular.toJson({
			"warehouseId": warehouseId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventoryline/storagebinlist',
			method: 'POST',
			data: warehouseBinJsonReq,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
			$scope.warehouseBinList = result.binList;
		})
	}
	$scope.getInventroyLineList = function() {
		var warehouseBinJsonReq = angular.toJson({
			"inventoryId": $localStorage.inventorDetails.inventoryId
		});
		$http({
			url: serviceUrl + 'aviate/json/inventoryline/inventorylinelist',
			method: 'POST',
			data: warehouseBinJsonReq,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
			$rootScope.listOfInventroyLine = [];
			$rootScope.listOfInventroyLine = result.listOfInventroyLine;
		})
	}
	$scope.addInventoryLines = function() {
		var addInventoryLineReq = angular.toJson({
			"storeId": $rootScope.user.storeId,
			"warehouseId": $localStorage.inventorDetails.warehouseId,
			"productId": $scope.productId,
			"uomId": $scope.uomId,
			"storageBinId": $scope.storageBinId,
			"totalQuantity": $scope.totalQuantity,
			"inventoryId": $localStorage.inventorDetails.inventoryId,
			//"bookedQuantity":$scope.bookedQuantity,
			//"availableQuantity":$scope.availableQuantity,
			"sbDesc": $scope.sbDesc ? $scope.sbDesc : ""
		});
		$http({
			url: serviceUrl + 'aviate/json/inventoryline/addinventoryline',
			method: 'POST',
			data: addInventoryLineReq,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
			if (result.status == "SUCCESS") {
				//alert("Inventory Line added successfully");
				$scope.warehouseId = null;
				$scope.productId = null;
				$scope.uomId = null;
				$scope.storageBinId = null;
				$scope.totalQuantity = null;
				$scope.inventoryId = null;
				$scope.sbDesc = null
				$scope.getInventroyLineList();

			} else {

			}
		})
	}
}
]);*/