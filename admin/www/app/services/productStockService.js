angular.module('aviateAdmin.services')
.service('productStockService',['$q','api','toastr', function($q, api, toastr) {
	this.getProductStockList = function(product){
		var d = $q.defer();
		api.ProductStock.getProductStockList(product, function(err, result){
			if (result.status === CONSTANT.STATUS.SUCCESS) {
				d.resolve(result.productinventory);
			} else {
				toastr.error("Stock is Empty");
				d.resolve(result.binProductList);
			}
		})
		return d.promise;
	};

}]);