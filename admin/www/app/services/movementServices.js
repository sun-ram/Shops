angular.module('aviateAdmin.services')
.service('movementServices',['$q','api','toastr','CONSTANT', function($q,api, toastr,CONSTANT) {
	this.getMovementsByStore = function(physicalinventories){
		var d = $q.defer();
		api.movement.getMovementsByStore(physicalinventories, function(err, result){
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
	
	/*this.getInventoryWarehouse = function(warehouses){
		var d = $q.defer();
		
		api.movement.getInventoryWarehouse(warehouses, function(err, result){
			if(result){
				d.resolve(result);
			

				
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
	};*/
	
	this.addMovement = function(movement){
		var d = $q.defer();
		api.movement.addMovement(movement, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
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
	
	this.addMovementLine = function(movementLine){
		var d = $q.defer();
		api.movement.addMovementLine(movementLine, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
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
	
	/*this.updatePhysicalInventory = function(physicalinventories){
		var d = $q.defer();
		api.movement.updatePhysicalInventory(physicalinventories, function(err, result){
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
	};*/
	
	this.removeMovement = function(movement){
		var d = $q.defer();
		api.movement.removeMovement(movement, function(err, result){
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
	
	this.removeMovementLine = function(movementLine){
		var d = $q.defer();
		api.movement.removeMovementLine(movementLine, function(err, result){
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
	
	this.processMovement = function(movement){
		var d = $q.defer();
		api.movement.processMovement(movement, function(err, result){
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
	
	this.getMovement = function(movementId){
		var d = $q.defer();
		api.movement.getMovement(movementId, function(err, result){
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


	this.setMovementObj = function(movement){
		this.obj = movement;
	};

	this.getMovementObj = function(){
		return this.obj;
	};

	
}]);
