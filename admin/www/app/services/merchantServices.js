angular.module('aviateAdmin.services')
.service('MerchantServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {

	this.getMerchant = function(merchant){
		var d = $q.defer();
		api.Merchant.getList(merchant, function(err, result){
			if(result){
				d.resolve(result.merchant);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.addNewMerchant = function(merchant){
		var d = $q.defer();
		api.Merchant.addNewMerchant(merchant, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDMERCHENT);
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
	
	this.updateMerchant = function(merchant){
		var d = $q.defer();
		api.Merchant.updateMerchant(merchant, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDMERCHENT);
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

	this.deleteMerchant = function(merchant){
		var d = $q.defer();
		api.Merchant.deleteMerchant(merchant, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETEMERCHANT);
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

	this.setMerchantObj = function(merchant){
		this.obj = merchant;
	};

	this.getMerchantObj = function(){
		return this.obj;
	};

}]);
