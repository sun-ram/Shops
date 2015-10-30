angular.module('aviateAdmin.services')
.service('PhysicalInventoryServices',['$q','api','toastr','CONSTANT', function($q,api, toastr,CONSTANT) {
	this.getInventory = function(physicalinventories){
		var d = $q.defer();
		api.PhysicalInventory.getInventory(physicalinventories, function(err, result){
			if(result){
				d.resolve(result.inventoryList);
				/*
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.physicalinventorylist);
				}else{
					toastr.error(result.errorString);	
				}
			*/}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.getInventoryWarehouse = function(warehouses){
		var d = $q.defer();
		
		api.PhysicalInventory.getInventoryWarehouse(warehouses, function(err, result){
			if(result){
				d.resolve(result);
			

				
				/*if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}*/
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.addNewPhysicalInventory = function(physicalinventories){
		var d = $q.defer();
		api.PhysicalInventory.addNewPhysicalInventory(physicalinventories, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDPHYSICALINVENTORY);
					d.resolve(result);
				} else {
					toastr.error(result.message);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.updatePhysicalInventory = function(physicalinventories){
		var d = $q.defer();
		api.PhysicalInventory.updatePhysicalInventory(physicalinventories, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDPHYSICALINVENTORY);
					d.resolve(result);
				} else {
					toastr.error(result.message);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.removeInventory = function(physicalinventory){
		var d = $q.defer();
		api.PhysicalInventory.removeInventory(physicalinventory, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETEPHYSICALINVENTORY);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.conformInventroy = function(physicalinventory){
		var d = $q.defer();
		api.PhysicalInventory.conformInventroy(physicalinventory, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.INV_PROCESSED);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};


	this.setPhysicalInventoryObj = function(physicalinventory){
		this.obj = physicalinventory;
	};

	this.getPhysicalInventoryObj = function(){
		return this.obj;
	};

	
}]);
