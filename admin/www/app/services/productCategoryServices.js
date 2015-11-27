angular.module('aviateAdmin.services')
.service('ProductCategoryServices',['$q','api','toastr','CONSTANT','$rootScope', function($q, api, toastr,CONSTANT, $rootScope) {
	this.getAllCategory = function(){
		var d = $q.defer();
		var product = {
				merchant : {}
		};
		product.merchant.merchantId= $rootScope.user.merchantId;
		api.ProductCategory.getAllCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.categories);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	this.getParentCategory = function(){
		console.log("Inside the service checking");
		var d = $q.defer();
		var product = {
				merchant : {}
		};
		product.merchant.merchantId= $rootScope.user.merchantId;
		console.log("Inside the service checking",product);
		api.ProductCategory.getParentCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.productCategories);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	this.getCategoryList = function(){
		var d = $q.defer();
		var product = {
				"merchantId": $rootScope.user.merchantId
		};
		api.ProductCategory.getCategoryList(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.category);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	this.getproductTypeList = function(){
		var d = $q.defer();
		/*var product = {
				"merchantId": $rootScope.user.merchantId
		};*/
		var product = {
				merchant : {}
		};
		product.merchant.merchantId= $rootScope.user.merchantId;
		api.ProductCategory.getproductTypeList(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.productTypeVo);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.removeProductCategory = function(Id){
		var d = $q.defer();
		var product = {
				"productCategoryId": Id
		};
		api.ProductCategory.removeProductCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.removeProductType = function(Id){
		var d = $q.defer();
		var product = {
				"productTypeId": Id
		};
		api.ProductCategory.removeProductType(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	this.addProductCategory = function(product){
		var d = $q.defer();
		api.ProductCategory.addProductCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	this.addProductCategory1 = function(product){
		var d = $q.defer();
		api.ProductCategory.addProductCategory1(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	this.addProductType = function(product){
		var d = $q.defer();
		api.ProductCategory.addProductType(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.updateproductCategory = function(product){
		var d = $q.defer();
		api.ProductCategory.updateproductCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.updateproductType = function(product){
		var d = $q.defer();
		api.ProductCategory.updateproductType(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
}]);