angular.module('aviateAdmin.services')
.service('SalesOrderServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {

	this.getSalesOrder = function(salesOrderVo){
		var d = $q.defer();
		api.SalesOrder.getSalesOrder(salesOrderVo, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.salesOrderList);
			}
			else{
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	this.getShoperDetails = function(userVo){
		var d = $q.defer();
		api.SalesOrder.getShoperDetails(userVo, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.userVoList);
			}
			else{
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	this.getBackerDetails = function(userVo){
		var d = $q.defer();
		api.SalesOrder.getBackerDetails(userVo, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.userVoList);
			}
			else{
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	this.updateShoperIntoSalesOrder = function(salesOrder){
		var d = $q.defer();
		api.SalesOrder.updateShoperIntoSalesOrder(salesOrder, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result);
			}
			else{
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	this.updateBackerIntoSalesOrder = function(salesOrder){
		var d = $q.defer();
		api.SalesOrder.updateBackerIntoSalesOrder(salesOrder, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result);
			}
			else{
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	this.getSalesByDate = function(menuJson){
		var d = $q.defer();
		api.SalesOrder.getSalesByDate(menuJson, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.salesOrderList);
			}
			else{
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	this.getMerchantStore = function(menuJson){
		var d = $q.defer();
		api.SalesOrder.getMerchantStore(menuJson, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.store);
			}
			else{
				toastr.error(result.errorString);
			}
		});
	
		
		return d.promise;getSalesOrderByStore
	};
	
	this.getSalesOrderByStore = function(menuJson){
		var d = $q.defer();
		api.SalesOrder.getSalesOrderByStore(menuJson, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.salesOrderList);
			}
			else{
				toastr.error(result.errorString);
			}
		});	
		return d.promise;
	};
}]);
