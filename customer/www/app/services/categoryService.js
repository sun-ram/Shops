angular.module('aviate.services')
.service('CategoryService',['$q','api','toastr','CONSTANT','$log',
                            function($q, api, toastr, CONSTANT,$log) {

	this.getCategoryList = function(storeId){
		var d = $q.defer();
		api.Category.getProductCategory(storeId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.productCategoryVo);
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
