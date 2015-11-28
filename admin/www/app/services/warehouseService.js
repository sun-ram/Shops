angular.module('aviateAdmin.services')
.service('WarehouseService',['$q','api','toastr','CONSTANT',function($q, api, toastr,CONSTANT) {
	
	this.getShopList = function(){
		var d = $q.defer();
		var store ={};
		api.Employee.shopList(store,function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.message);
			}
		})
		return d.promise;
	};
	
	this.saveWarehouse = function(warehouse){
		var d = $q.defer();
		api.Warehouse.save(warehouse, function(err, result){
			if (result.status == 'SUCCESS') {
				toastr.success(CONSTANT.ADDWAREHOUSE);
				d.resolve(result);
			} else {
				toastr.error(result.message);
			}
		})
		return d.promise;
	};	
	
	this.warehouseList = function(warehouse){
		var d = $q.defer();
		api.Warehouse.warehouseList(warehouse, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	
	
	this.getListOfStoragebin = function(storagebin){
		var d = $q.defer();
		api.Warehouse.getStoragebins(storagebin, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	
	
	this.updateWarehouse = function(warehouse){
		var d = $q.defer();
		api.Warehouse.update(warehouse, function(err, result){
			if (result.status == 'SUCCESS') {
				toastr.success(CONSTANT.UPDATEWAREHOUSE);
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.deleteWarehouse = function(warehouse){
		var d = $q.defer();
		api.Warehouse.deleteWarehouse(warehouse, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	
	
	this.saveStorageBin = function(storageBin){
		var d = $q.defer();
		api.Warehouse.saveStorageBin(storageBin, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.deleteStorageBin = function(storageBin){
		var d = $q.defer();
		api.Warehouse.deleteStorageBin(storageBin, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	
	
	this.warehouseBins = function(warehouseBin){
		var d = $q.defer();
		api.Warehouse.warehouseBins(warehouseBin, function(err, result){
				d.resolve(result.binList);
		})
		return d.promise;
	};	
}]);