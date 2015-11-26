angular.module('aviateAdmin.services')
.service('OrderNumberServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {
	this.getOrderNumber = function(store){
		var d = $q.defer();
		api.OrderNumber.getOrderNumber(store, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.orderNumber);
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
	
	this.addOrderNumber = function(orderNumber){
		var d = $q.defer();
		api.OrderNumber.addOrderNumber(orderNumber, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDORDERNUMBER);
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
	
	this.deleteOrderNumber = function(id){
		var d = $q.defer();
		api.OrderNumber.deleteOrderNumber(id, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETEORDERNUMBER);
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
	
	this.updateOrderNumber = function(orderNumber){
		var d = $q.defer();
		api.OrderNumber.updateOrderNumber(orderNumber, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.UPDATEORDERNUMBER);
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
	
	this.setOrderNumberObj = function(orderNumber){
		this.obj = orderNumber;
	};

	this.getOrderNumberObj = function(){
		return this.obj;
	};

	
}]);
