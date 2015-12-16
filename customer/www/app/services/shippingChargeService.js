angular.module('aviate.services')
.service('ShippingChargeServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {
	this.getShippingCharge = function(merchant){
		var d = $q.defer();
		api.ShippingService.getShippingCharge(merchant, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.shippingChargesList);
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
	
	this.addNewShippingCharge = function(shippingCharge){
		var d = $q.defer();
		api.ShippingService.addNewShippingCharge(shippingCharge, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDSHIPPING);
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
	
	this.deleteShippingCharge = function(id){
		var d = $q.defer();
		api.ShippingService.deleteShippingCharge(id, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETESHIPPING);
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
	
	this.updateShippingCharge = function(merchant){
		var d = $q.defer();
		api.ShippingService.updateShippingCharge(merchant, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.UPDATESHIPPING);
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
	
	this.setShippingChargeObj = function(shippingCharge){
		this.obj = shippingCharge;
	};

	this.getShippingChargeObj = function(){
		return this.obj;
	};

	
}]);
