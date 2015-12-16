angular.module('aviateAdmin.services')
.service('DiscountService',['$q', 'api', 'toastr', 'CONSTANT', function($q, api, toastr, CONSTANT) {
	
	
	this.merchantDiscountList = function(discount){
		var d = $q.defer();
		api.Discount.getMerchantDicountList(discount, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
}]);