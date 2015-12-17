angular.module('aviateAdmin.services')
.service('DiscountService',['$q', 'api', 'toastr', 'CONSTANT', function($q, api, toastr, CONSTANT) {
	
	
	this.merchantDiscountList = function(merchant){
		var d = $q.defer();
		api.Discount.getMerchantDicountList(merchant, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.saveDiscount = function(discount){
		var d = $q.defer();
		api.Discount.saveDiscount(discount, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
				toastr.success(CONSTANT.ADDEDDISCOUNT);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.deleteDiscount = function(discount){
		var d = $q.defer();
		api.Discount.deleteDiscount(discount, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
				toastr.success(CONSTANT.DELETEDISCOUNT);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
}]);