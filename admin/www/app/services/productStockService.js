angular.module('aviateAdmin.services')
.service('productStockService',['$q','api','toastr', function($q, api, toastr) {
	this.getProductStockList = function(product){
		var d = $q.defer();
		api.ProductStock.getProductStockList(product, function(err, result){
			if (result.binProductList.length>0) {
				d.resolve(result.binProductList);
			} else {
				toastr.error("Stock is Empty");
				d.resolve(result.binProductList);
			}
		})
		return d.promise;
	};

}]);