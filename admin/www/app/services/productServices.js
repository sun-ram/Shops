angular.module('aviateAdmin.services')
.service('ProductService',['$q','api','toastr', function($q, api, toastr) {
	this.productList = function(product){
		var d = $q.defer();
		api.Product.getList(product, function(err, result){
			if (result.status == 'SUCCESS') {
				console.log(result.products);
				d.resolve(result.products);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.addProduct = function(product){	
		var d = $q.defer();
		api.Product.save(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	
	
	
	
	this.getMeasurementUnit = function(product){
		var d = $q.defer();
		api.Product.getMeasureUnit(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	
	
	this.productType = function(product){
		var d = $q.defer();
		api.Product.getAllProductType(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.getProductCategory = function(product){
		var d = $q.defer();
		api.Product.getProductCategory(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})	
		return d.promise;
	};
	
	
	this.getAllProductListByCategory = function(product){
		var d = $q.defer();
		api.Product.getAllProductList(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	
	this.getTypeByCategoryId = function(product){
		var d = $q.defer();
		api.Product.getTypeByCategoryId(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.editProduct = function(product){
		var d = $q.defer();
		api.Product.updateProduct(product, function(err, result){
			if (result.status == 'SUCCESS') {
				toastr.success("product details have been updated successfully!!!");
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.deleteProduct = function(product){
		var d = $q.defer();
		api.Product.deleteProduct(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.exportExcelFile = function(storeId){
		var d = $q.defer();
		api.Product.exportExcelFile(storeId, function(err, result){
/*			if (result.status == 'SUCCESS') {
				toastr.success("SUCCESS");
			} else {
				toastr.error(result.errorString);
			}*/
		})
		return d.promise;
	};
	
	

	this.setProductObj = function(product){
		this.obj = product;
	};

	this.getProductObj = function(){
		return this.obj;
	};
	
	this.getAllProductList = function(merchant){	
		var d = $q.defer();
		api.Product.getAllProductListByMerchant(merchant, function(err, result){
			if(result){
				if (result.status == 'SUCCESS') {
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
	
	this.getProductCategory = function(product){	
		var d = $q.defer();
		api.Product.getProductCategory(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.getProductType = function(product){	
		var d = $q.defer();
		api.Product.getProductType(product, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	

}]);