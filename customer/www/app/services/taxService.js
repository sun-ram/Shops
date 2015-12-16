angular.module('aviate.services')
.service('TaxServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {
	this.getTax = function(merchant){
		var d = $q.defer();
		api.TaxService.getTax(merchant, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.taxList);
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
	
	this.addNewTax = function(tax){
		var d = $q.defer();
		api.TaxService.addNewTax(tax, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDTAX);
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
	
	this.deleteTax = function(id){
		var d = $q.defer();
		api.TaxService.deleteTax(id, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETETAX);
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
	
	this.updateTax = function(tax){
		var d = $q.defer();
		api.TaxService.updateTax(tax, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.UPDATETAX);
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
	
	this.setTaxObj = function(tax){
		this.obj = tax;
	};

	this.getTaxObj = function(){
		return this.obj;
	};

	
}]);
