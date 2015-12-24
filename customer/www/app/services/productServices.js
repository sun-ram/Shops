angular.module('aviate.services')
.service('ProductService',['$q', 'api', 'toastr', 'CONSTANT', 'MyCartFactory',
                           function($q, api, toastr, CONSTANT, MyCartFactory) {


	this.getProductsFromCategory = function(category){
		var d = $q.defer();
		api.Product.getProductsFromCategory(category, function(err, result){		
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					MyCartFactory.checkCartProductsQuantity(result.products,function(data){
						MyCartFactory.checkMyListProductsList(data,function(dataInList){
							d.resolve(dataInList);
						});
					});
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
					MyCartFactory.checkCartProductsQuantity(result.products,function(data){
						MyCartFactory.checkMyListProductsList(data,function(dataInList){
							d.resolve(dataInList);
						});
					});
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.getProductsByProductId = function(productId){
		var d = $q.defer();
		api.Product.getProductsByProductId(productId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					MyCartFactory.checkSingleProductsinCart(result,function(data){
						MyCartFactory.checkProductInMyList(data,function(dataInList){
							d.resolve(dataInList);
						});
					});
					
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.getProductOffer = function(productOfferId){
		var d = $q.defer();
		api.Product.getProductOfferById(productOfferId, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.productOfferList[0]);
					
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
