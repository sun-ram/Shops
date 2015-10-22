angular.module('aviate.services')
.service('homePageServices',['$q','api','toastr','CONSTANT',
                             function($q, api, toastr, CONSTANT) {
	this.featureProducts = function(product){
		var d = $q.defer();
		api.Products.featureProducts(product, function(err, result){
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

	this.topCategories = function(product){
		var d = $q.defer();
		api.Products.getTopCategoryProducts(product, function(err, result){
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

}]);
