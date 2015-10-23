angular.module('aviate.services')
.service('homePageServices',['$q','api','toastr','CONSTANT',
                             function($q, api, toastr, CONSTANT) {
	this.futureProducts = function(storeId){
		var d = $q.defer();
        var product = {'storeId' : storeId};
		api.Product.futureProducts(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(userDetails);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};

	this.topCategories = function(storeId){
		var d = $q.defer();
		var product = {'storeId' : storeId};
		api.Product.getTopCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};

}]);
