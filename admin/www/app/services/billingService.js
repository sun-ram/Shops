angular.module('aviateAdmin.services')
.service('BillingServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {

	this.getStoresByMerchant = function(merchant){
		var d = $q.defer();
		api.Billing.getStoresByMerchant(merchant, function(err, result){
			if(result){
				d.resolve(result.store);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	
	this.getBillsByMerchant = function(billing){
		var d = $q.defer();
		api.Billing.getBillsByMerchant(billing, function(err, result){
			if(result){
				d.resolve(result.billsList);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

}]);