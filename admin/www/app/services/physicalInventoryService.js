angular.module('aviateAdmin.services')
.service('physicalInventoryService',['$q','api','toastr','CONSTANT','$rootScope', function($q, api, toastr, CONSTANT, $rootScope) {
	

	this.addPhysicalInventory = function(inventory){
		var d = $q.defer();
		inventory.merchant = {};
		inventory.store = {};
		inventory.store.storeId = $rootScope.user.storeId;
		inventory.merchant.merchantId = $rootScope.user.merchantId;
		api.PhysicalInventory.addPhysicalInventory(inventory, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.movement);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.addPhysicalInventoryLine = function(inventoryLine){
		var d = $q.defer();
		api.PhysicalInventory.addPhysicalInventoryLine(inventoryLine, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.movementLine);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.deletePhysicalInventory = function(inventoryId){
		var d = $q.defer();
		api.PhysicalInventory.deletePhysicalInventory(inventoryId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.deletePhysicalInventoryLine = function(inventoryLineId){
		var d = $q.defer();
		api.PhysicalInventory.deletePhysicalInventoryLine(inventoryLineId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.getPhysicalInventoriesByStore = function(){
		var d = $q.defer();
		api.PhysicalInventory.getPhysicalInventoriesByStore($rootScope.user.storeId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.movements);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.getPhysicalInventory = function(inventoryId){
		var d = $q.defer();
		api.PhysicalInventory.getPhysicalInventory(inventoryId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.movement);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.physicalInventoryIsUpdate = function(inventoryId){
		var d = $q.defer();
		api.PhysicalInventory.physicalInventoryIsUpdate(inventoryId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	

}]);
