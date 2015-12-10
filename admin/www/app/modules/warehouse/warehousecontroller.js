aviateAdmin.controller("warehousecontroller", ['$scope','$rootScope','$mdDialog','$localStorage','$location','$state','toastr','CONSTANT', '$http','WarehouseService','$location','CommonServices','$timeout', 
                                              function($scope,$rootScope,$mdDialog,$localStorage, $location,$state,toastr,CONSTANT,$http,WarehouseService,$localStorage,CommonServices,$timeout) {
	
	
 	
	$scope.count = 3;
	$scope.srch = true;
	
	$scope.warehouseDetails = function(warehouse){
		localStorage.setItem('warehouse',JSON.stringify(warehouse));
		$scope.warehouse = JSON.parse(localStorage.getItem('warehouse'));
		$state.go('app.warehouseDetails');
		
	};
	
	$scope.getState = function(country){
		$scope.states = country.states;
	}
	
	var populateCountries = function(){
		$scope.warehouse = JSON.parse(localStorage.getItem('warehouse'));
		if($scope.warehouse){
			$scope.country = $scope.warehouse.address.country;
			if(!$scope.countries){
				$timeout(function(){populateCountries()},2000);
				console.log('Waiting for getting countries');
			}else{
				$scope.states = _.findWhere($scope.countries,{countryId:$scope.country.countryId}).states;
				console.log('populated state is ',$scope.states);
				$scope.state = $scope.warehouse.address.state;
				console.log('populated state is ',$scope.state);
			}
		}
	}
	
	if($scope.countries){
		populateCountries();
	}else{
		$timeout(function(){populateCountries()},5000);
	}
	
	$scope.addWarehouse = function(){
		
		$state.go('app.addWarehouse');
		
	},
	$scope.editWarehouse = function(warehouse) {
		localStorage.setItem('warehouse',JSON.stringify(warehouse));
		$scope.warehouse = JSON.parse(localStorage.getItem('warehouse'));
		$state.go('app.addWarehouse');
	},
	
	
	$scope.shopList = function(){
		WarehouseService.getShopList().then(function(data) {
			$localStorage.shops = {};
			$localStorage.shops = data.shoplist;
		});
		$scope.shops = {};

	} 
	
	$scope.saveWarehouse = function() {
		$scope.warehouse.store ={};
		$scope.warehouse.merchant={};
		$scope.warehouse.store.storeId = $rootScope.user.storeId;
		$scope.warehouse.merchant.merchantId= $rootScope.user.merchantId;
		
		$scope.cnt = $scope.country;
		$scope.st = $scope.state;
		$scope.warehouse.address.country = {};
		$scope.warehouse.address.state = {};
		$scope.warehouse.address.country.countryId = $scope.cnt.countryId;
		$scope.warehouse.address.country.name = $scope.cnt.name;
		$scope.warehouse.address.state.stateId = $scope.st.stateId;
		$scope.warehouse.address.state.name = $scope.st.name;
		
		WarehouseService.saveWarehouse($scope.warehouse).then(function(data) {
			$scope.warehouse = {};
			$state.go('app.warehouse');
		});
	}
	
	$scope.updateWarehouse = function() {
		
		$scope.warehouse.store ={};
		$scope.warehouse.merchant={};
		$scope.warehouse.store.storeId = $rootScope.user.storeId;
		$scope.warehouse.merchant.merchantId= $rootScope.user.merchantId;
		
		$scope.cnt = $scope.country;
		$scope.st = $scope.state;
		$scope.warehouse.address.country = {};
		$scope.warehouse.address.state = {};
		$scope.warehouse.address.country.countryId = $scope.cnt.countryId;
		$scope.warehouse.address.country.name = $scope.cnt.name;
		$scope.warehouse.address.state.stateId = $scope.st.stateId;
		$scope.warehouse.address.state.name = $scope.st.name;
		
		
		WarehouseService.updateWarehouse($scope.warehouse).then(function(data) {
			$state.go('app.warehouse');
		});
	}
	
	$scope.deleteWarehouse = function(warehouse) {
		var confirm = $mdDialog.confirm()
			      .title('Would you like to delete Warehouse?')
			        .ok('Delete')
				       .cancel('Cancel');
				 $mdDialog.show(confirm).then(function() {
			$scope.warehouse={};
			$scope.warehouse.store={};
			$scope.warehouse.store.storeId = $rootScope.user.storeId;
			$scope.warehouse.warehouseId = warehouse;
			WarehouseService.deleteWarehouse($scope.warehouse).then(function(data) {
			toastr.success(CONSTANT.DELETEWAREHOUSE);
			$scope.getWarehouse();
		});
		}, function() {
					  
				 });
	}
	
	$scope.deleteStorageBin = function(storagebin) {
		var confirm = $mdDialog.confirm()
			      .title('Would you like to delete StorageBin?')
			        .ok('Delete')
				       .cancel('Cancel');
				 $mdDialog.show(confirm).then(function() {
						$scope.storagebin={};
						$scope.storagebin.store={};
						$scope.storagebin.store.storeId = $rootScope.user.storeId;
						$scope.storagebin.storagebinId = storagebin.storagebinId;
						WarehouseService.deleteStorageBin($scope.storagebin).then(function(data) {
						toastr.success(CONSTANT.DELETESTORAGEBIN);
						var index = $scope.listOfStoragebins.indexOf(storagebin);
						$scope.listOfStoragebins.splice(index, 1);  
					});
				}, function() {
				  
				  });
	}
	
	
	$scope.getWarehouse = function() {
		localStorage.removeItem('warehouse');
		$scope.warehouse = {};
		$scope.warehouse.store={};
		$scope.warehouse.store.storeId = $rootScope.user.storeId;
		WarehouseService.warehouseList($scope.warehouse).then(function(data) {
			$scope.getWarehouseList = data.warehouses;
			if($scope.storagebin){
				$scope.warehouse = _.findWhere($scope.getWarehouseList,{warehouseId : $scope.storagebin.warehouse.warehouseId})
			}
			$scope.shopList();
			
		});
	};
	
	$scope.getListOfStoragebin = function(){
		localStorage.removeItem('storagebin');
		$scope.storagebin = {};
		$scope.storagebin.store={};
		$scope.storagebin.store.storeId = $rootScope.user.storeId;
		WarehouseService.getListOfStoragebin($scope.storagebin).then(function(data) {
			$scope.listOfWarehouse = data.warehouses;
			$scope.listOfStoragebins = data.storagebins;
			$scope.shopList();
			
		});
	};
	
	$scope.addStorageBin = function(){
		localStorage.setItem('storagebin',null);
		$state.go('app.addStorageBin');
	}
	
	$scope.saveStorageBin = function(){
	//	$scope.storagebin =storageBin;
		$scope.storagebin.store={};
		$scope.storagebin.merchant={};
		$scope.storagebin.warehouse={};
		$scope.storagebin.store.storeId = $rootScope.user.storeId;
		$scope.storagebin.merchant.merchantId = $rootScope.user.merchantId;
		$scope.storagebin.warehouse.warehouseId = $scope.warehouse.warehouseId;
		WarehouseService.saveStorageBin($scope.storagebin).then(function(data) {
			toastr.success(CONSTANT.ADDSTORAGEBIN);
			$state.go('app.storagebin');
		});
		
	}
	
	$scope.setStoragebin = function(warehouse){
		$scope.warehouse1 = JSON.parse(warehouse);
		$scope.storagebins = $scope.warehouse1.storagebins;
	}
	
	$scope.storagebinDetails = function(storagebin,whouse){
		localStorage.setItem('storagebin',JSON.stringify(storagebin));
		//$scope.storagebin = JSON.parse(localStorage.getItem('storagebin'));
		$state.go('app.storagebinDetails');
		
	}
	
	$scope.editStoragebin = function(storagebin,whouse) {
		localStorage.setItem('storagebin',JSON.stringify(storagebin));
		//$scope.storagebin = JSON.parse(localStorage.getItem('storagebin'));
		$state.go('app.addStorageBin');
	}
	$scope.storagebin = JSON.parse(localStorage.getItem('storagebin'));
	$scope.updateStoragebin = function(storagebin){
			$scope.storagebin.store={};
			$scope.storagebin.merchant={};
			$scope.storagebin.warehouse={};
			$scope.storagebin.store.storeId = $rootScope.user.storeId;
			$scope.storagebin.merchant.merchantId = $rootScope.user.merchantId;
			$scope.storagebin.warehouse.warehouseId = $scope.warehouse.warehouseId;
			WarehouseService.saveStorageBin($scope.storagebin).then(function(data) {
				toastr.success(CONSTANT.UPDATESTORAGEBIN);
				$state.go('app.storagebin');
			});
			
		}
}
]);

/*angular.module('aviateAdmin.controllers')
.controller("warehousecontroller", [
                                    '$scope',
                                    '$http',
                                    '$rootScope',
                                    '$state',
                                    '$filter',
                                    'ngTableParams',
                                    '$localStorage',
                                    function($scope, $http, $rootScope, $state, $filter,
                                    		ngTableParams,$localStorage) {
                                    	
                                  	  $scope.query = {
                              			    limit: 5,
                              			    page: 1
                              			  };
                              	  
                              		$scope.count = 3;
                              		$scope.srch = true;
                              		
                              		$scope.warehouseDetails = function(warehouse){
                              			$localStorage.warehouse = warehouse;
                              			$state.go('app.warehouseDetails');
                              			
                              		},
                              		$scope.addWarehouse = function(){
                              			$state.go('app.addWarehouse');
                              			
                              		},
                              		$scope.warehouse = $localStorage.warehouse;
                              		
                              		$scope.shopList = function(){
                              			EmployeeService.getShopList().then(function(data) {
                              				$localStorage.shops = {};
                              				$localStorage.shops = data.shoplist;
                              			});
                              			$scope.shops = {};
                              			$scope.shops = $localStorage.shops;

                              		},
                              		

		                        	$scope.editWarehouse = function(warehouse) {
		                        		$rootScope.warehouse = warehouse;
		                        		$state.go('app.addWarehouse');
		                        	}
                              		
                              		
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
    		                        		}).success(
    		                        				function(result, status, headers) {
    		                        					var data = result.shoplist;
    		                        					$scope.shopList = result.shoplist;
    		                        				})
    		                        	}
    		                        	$scope.addWarehouse = function() {
    		                        		$state.go('app.addWarehouse');
    		                        	}
    		                        	$rootScope.addwarehouseFunc = function(params) {
    		                        		var addwarehousereq = angular
    		                        		.toJson({
    		                        			"warehouseName": params.warehouseName,
    		                        			"whDescription": params.description ? params.description : "",
    		                        					"returnBinId": params.returnBinId ? params.returnBinId : "",
    		                        							"address": params.address,
    		                        							"address1": params.address1,
    		                        							"city": params.city,
    		                        							"state": params.state,
    		                        							"country": params.country,
    		                        							"postelcode": params.postelCode,
    		                        							"storeId": params.storeId
    		                        		});
    		                        		$http({
    		                        			url: serviceUrl + 'aviate/json/warehouse/addwarehouse',
    		                        			method: 'POST',
    		                        			data: addwarehousereq,
    		                        			headers: {
    		                        				'Content-Type': 'application/json'
    		                        			}
    		                        		})
    		                        		.success(
    		                        				function(result, status,
    		                        						headers) {
    		                        					if (result.status == "SUCCESS") {
    		                        						alert("Warehouse added successfully");
    		                        						params.warehouseName = null;
    		                        						params.description = null;
    		                        						params.returnBinId = null;
    		                        						params.address = null;
    		                        						params.address1 = null;
    		                        						params.city = null;
    		                        						params.state = null;
    		                        						params.country = null;
    		                        						params.postelCode = null;
    		                        						params.storeId = null;
    		                        					} else {
    		                        						alert(result.message);
    		                        					}
    		                        				})
    		                        	}
    		                        	// remove warehouse
    		                        	$scope.removewarehouse = function(warehouse) {
    		                        		var removewarehousereq = angular.toJson({
    		                        			"warehouseId": warehouse.warehouseId,
    		                        		});
    		                        		$http({
    		                        			url: serviceUrl + 'aviate/json/warehouse/removewarehouse',
    		                        			method: 'DELETE',
    		                        			data: removewarehousereq,
    		                        			headers: {
    		                        				'Content-Type': 'application/json'
    		                        			}
    		                        		})
    		                        		.success(
    		                        				function(result, status,
    		                        						headers) {
    		                        					if (result.status == "SUCCESS") {
    		                        						alert("Removed warehouse successfully");
    		                        						// $scope.store = {};
    		                        					} else {
    		                        						// alert(errorString);
    		                        					}
    		                        				})
    		                        	}

    		                        	// storage bin list
    		                        	$scope.storagebinlist = function() {
    		                        		var storagebinlistreq = angular.toJson({
    		                        			"storeId": $scope.storeId,
    		                        			"warehouseId": $scope.warehouseId
    		                        		});
    		                        		$http({
    		                        			url: serviceUrl + 'aviate/json/warehouse/storagebinlist',
    		                        			method: 'GET',
    		                        			data: storagebinlistreq,
    		                        			headers: {
    		                        				'Content-Type': 'application/json'
    		                        			}
    		                        		}).success(
    		                        				function(result, status, headers) {
    		                        					if (result.status == "SUCCESS") {
    		                        						$scope.storageBinList;
    		                        					} else {
    		                        						// alert(errorString);
    		                        					}
    		                        				})
    		                        	}


    		                        	// Storage bin creation and remove controllers

    		                        	$scope.getWarehouse = function() {
    		                        		var warehouseJson = angular.toJson({
    		                        			"filterType": "ALL",
    		                        			"storeId": "1"
    		                        		});
    		                        		$http({
    		                        			url: serviceUrl + 'aviate/json/inventory/warehouselist',
    		                        			method: 'POST',
    		                        			data: warehouseJson,
    		                        			headers: {
    		                        				'Content-Type': 'application/json'
    		                        			}
    		                        		})
    		                        		.success(
    		                        				function(result, status,
    		                        						headers) {
    		                        					var data = result.warehouseList;
    		                        					$scope.getWarehouseList = result.warehouseList;
    		                        				})
    		                        	}

    		                        	$scope.addStorageBin = function() {
    		                        		var addstoragebinreq = angular.toJson({
    		                        			"storeId": "1",
    		                        			"warehouseId": $scope.warehouseId,
    		                        			"storageBinName": $scope.sbname,
    		                        			"xRow": $scope.sbxrow,
    		                        			"yRow": $scope.sbyrow,
    		                        			"zRow": $scope.sbzrow
    		                        		});
    		                        		$http({
    		                        			url: serviceUrl + 'aviate/json/storagebin/addstoragebin',
    		                        			method: 'POST',
    		                        			data: addstoragebinreq,
    		                        			headers: {
    		                        				'Content-Type': 'application/json'
    		                        			}
    		                        		}).success(
    		                        				function(result, status, headers) {
    		                        					if (result.status == "SUCCESS") {
    		                        						alert("Storage Bin added succesfully");
    		                        						$scope.warehouseId = null;
    		                        						$scope.sbname = null;
    		                        						$scope.sbxrow = null;
    		                        						$scope.sbyrow = null;
    		                        						$scope.sbzrow = null;
    		                        					} else {
    		                        						alert(result.message);
    		                        					}
    		                        				})
    		                        	}

    		                        	// Remove storage bin
    		                        	$scope.removestoragebin = function() {
    		                        		var removestoragebinreq = angular.toJson({
    		                        			"storeId": $scope.storeId,
    		                        			"storageBinId": $scope.storagebinId,
    		                        		});
    		                        		$http({
    		                        			url: serviceUrl + 'aviate/json/storagebin/removestoragebin',
    		                        			method: 'DELETE',
    		                        			data: removestoragebinreq,
    		                        			headers: {
    		                        				'Content-Type': 'application/json'
    		                        			}
    		                        		}).success(
    		                        				function(result, status, headers) {
    		                        					if (result.status == "SUCCESS") {
    		                        						$scope.storageBinList;
    		                        					} else {
    		                        						// alert(errorString);
    		                        					}
    		                        				})
    		                        	}

    		                        }
                                    ]);*/