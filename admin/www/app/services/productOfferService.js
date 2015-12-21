angular.module('aviateAdmin.services')
.service('ProductOfferServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {

	this.getProductOffer = function(productoffer){
		var d = $q.defer();
		api.ProductOffer.getList(productoffer, function(err, result){
			if(result){
				d.resolve(result.productOfferList);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.addProductOffer = function(productoffer){
		var d = $q.defer();
		api.ProductOffer.addNewProductOffer(productoffer, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDPRODUCTOFFER);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.updateProductOffer = function(productoffer){
		var d = $q.defer();
		api.ProductOffer.updateProductOffer(productoffer, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.UPDATEPRODUCTOFFER);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.deleteProductOffer = function(productoffer){
		var d = $q.defer();
		api.ProductOffer.deleteProductOffer(productoffer, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETEPRODUCTOFFER);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.addProductOfferLine = function(productofferline){
		var d = $q.defer();
		api.ProductOffer.addNewProductOfferLine(productofferline, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDPRODUCTOFFER);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.deleteProductOfferLine = function(productofferline){
		var d = $q.defer();
		api.ProductOffer.deleteProductOfferLine(productofferline, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETEPRODUCTOFFER);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.getProductOfferLine = function(productoffer){
		var d = $q.defer();
		api.ProductOffer.getOfferLineList(productoffer, function(err, result){
			if(result){
				d.resolve(result.productOfferLineList);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.updateProductOfferLine = function(productoffer){
		var d = $q.defer();
		api.ProductOffer.updateProductOfferLine(productoffer, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.UPDATEPRODUCTOFFER);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.setProductOfferObj = function(productoffer){
		this.obj = productoffer;
	};

	this.getProductOfferObj = function(){
		return this.obj;
	};
	
	this.setProductOfferLineObj = function(productoffer){
		this.obj = productoffer;
	};

	this.getProductOfferLineObj = function(){
		return this.obj;
	};

}]);
