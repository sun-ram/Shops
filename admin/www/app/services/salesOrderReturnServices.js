angular.module('aviateAdmin.services')
.service('SalesOrderReturnServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {

	this.getSalesOrderReturn = function(salesOrderReturnVo){
		var d = $q.defer();
		api.SalesOrderReturn.getSalesOrderReturn(salesOrderReturnVo, function(err, result){
			if(result.status === CONSTANT.STATUS.SUCCESS){
				d.resolve(result.salesOrderReturnList);
			}
			else{
				toastr.error(result.errorString);
			}
		});
		return d.promise;
	};
	
	this.processRefundRequest = function(req){
		var d = $q.defer();
		api.SalesOrderReturn.processRefundRequest(req, function(err, result){
				d.resolve(result);
		});
		return d.promise;
	};
	
}]);
