angular.module('aviateAdmin.services')
.service('productStockService',['$q','api','toastr','CONSTANT', function($q, api, toastr, CONSTANT) {
	this.getProductStockList = function(product){
		var d = $q.defer();
		api.ProductStock.getProductStockList(product, function(err, result){
			if (result.status === CONSTANT.STATUS.SUCCESS) {
				d.resolve(result.productListVo);
			} else {
				toastr.error("Stock is Empty");
				d.resolve(result.binProductList);
			}
		})
		return d.promise;
	};

}]);