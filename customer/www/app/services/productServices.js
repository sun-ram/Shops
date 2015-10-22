angular.module('aviate.services')
.service('ProductService',['$q','api','toastr','CONSTANT',
                           function($q, api, toastr, CONSTANT) {


	this.getProductsFromCategory = function(category){
		var d = $q.defer();
		api.Product.getProductsFromCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
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
	
	this.getProductsByProductTypeId = function(productType){
		var d = $q.defer();
		api.Product.getProductsByProductTypeId(productType, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
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
