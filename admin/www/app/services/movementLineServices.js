angular.module('aviateAdmin.services')
.service('movementLineServices',['$q','api','toastr','CONSTANT', function($q,api, toastr,CONSTANT) {


	this.getProducts = function(products){
		var d = $q.defer();

		api.PhysicalInventoryLine.getProducts(products, function(err, result){
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

	this.productUom = function(uom){
		var d = $q.defer();

		api.PhysicalInventoryLine.productUom(uom, function(err, result){
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


	this.warehouseBin = function(bin){
		var d = $q.defer();

		api.PhysicalInventoryLine.warehouseBin(bin, function(err, result){
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


	this.addInventoryLines = function(physicalinventoryline){
		var d = $q.defer();
		api.PhysicalInventoryLine.addInventoryLines(physicalinventoryline, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDPHYSICALINVENTORYLINE);
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

	this.productUom = function(physicalinventoryline){
		var d = $q.defer();
		api.PhysicalInventoryLine.productUom(physicalinventoryline, function(err, result){
			if(result){
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	
	this.getPhysicalInvemntoryLines = function(physicalinventoryline){
		var d = $q.defer();
		api.PhysicalInventoryLine.getInventoryLines(physicalinventoryline, function(err, result){
			if(result){
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	this.removeInventoryLines = function(physicalinventoryline){
		var d = $q.defer();
		api.PhysicalInventoryLine.removeInventoryLines(physicalinventoryline, function(err, result){
			if(result){
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};

}]);
