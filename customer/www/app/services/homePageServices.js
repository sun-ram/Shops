angular.module('aviate.services')
.service('homePageServices',['$q','api','toastr','CONSTANT',
                             function($q, api, toastr, CONSTANT) {
	this.futureProducts = function(storeId){
		var d = $q.defer();
        var product = {'storeId' : storeId};
		api.Product.getFutureProducts(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(result.products);
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
		api.Product.getTopCategory(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(result.products);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.offerProduct = function(product){
		var d = $q.defer();
		api.Product.offerProduct(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(result.productOfferList);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.allCategoriesWithProduct = function(product){
		var d = $q.defer();
		api.Product.allCategoriesWithProduct(product, function(err, result){
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
	
	this.getBannerList = function(banner){
		var d = $q.defer();
		api.Banner.getBannerList(banner, function(err, result){
			if(result){
				d.resolve(result.bannerList);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.getShippingCharge = function(merchant){
		var d = $q.defer();
		api.shippingCharge.getShippingCharge(merchant, function(err, result){
			if(result){
				d.resolve(result.shippingChargesList);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	
	this.getStoreHolidays = function(storeHoliday){
		var d = $q.defer();
		api.storeHolidays.getStoreHolidays(storeHoliday, function(err, result){
			if(result){
				d.resolve(result.storeHoliday);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.getTax = function(merchant){
		var d = $q.defer();
		api.tax.getTax(merchant, function(err, result){
			if(result){
				d.resolve(result);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	
	this.isBundleProduct = function(product){
		var d = $q.defer();
		api.Product.isBundleProduct(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(result.products);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.comboOffer = function(product){
		var d = $q.defer();
		api.Product.comboOffer(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(result.products);
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
