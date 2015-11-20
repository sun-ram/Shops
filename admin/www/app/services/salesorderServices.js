angular.module('aviateAdmin.services')
.service('SalesOrderServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {

	this.getSalesOrder = function(menuJson){
		var d = $q.defer();
		api.SalesOrder.getSalesOrder(menuJson, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.salesOrderList);
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
