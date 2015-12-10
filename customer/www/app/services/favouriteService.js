/**
 * @Author Prabakaran
 * 
 */

angular.module('aviate.services')
.service('FavouriteServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                              function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {

	this.addFavourite = function(favourite){
		var d = $q.defer();
		api.CheckOut.addFavourite(favourite, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success("Favourite Added Successfully");
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

	this.getFavourite = function(){
		favourite ={};
		favourite.customerId = $rootScope.user.userId;
		favourite.merchantId = $rootScope.store.merchant.merchantId;
		favourite.storeId = $rootScope.store.storeId;
		var d = $q.defer();
		api.CheckOut.getFavourite(favourite, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.favourites);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};

	this.addFavouriteToCart = function(favourite){
		var d = $q.defer();
		api.CheckOut.addFavouriteToCart(favourite, function(err, result){
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
	
	this.favouriteToCheckout = function(favourite){
		var d = $q.defer();
		api.CheckOut.favouriteToCheckout(favourite, function(err, result){
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
	
	this.viewProductInFavourite = function(favourite){
		var d = $q.defer();
		api.CheckOut.viewProductInFavourite(favourite, function(err, result){
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
