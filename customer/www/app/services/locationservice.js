angular.module('aviate.services')
.service('LocationService',['$q','api','toastr','CONSTANT','$log',
                            function($q, api, toastr, CONSTANT,$log) {

	this.getProducts = function(product){
		var d = $q.defer();
		api.User.getAllProducts(product, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {


					//If You Want, Need to write logics
					d.resolve(userDetails);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};

	this.getStoreByLocation = function(location){
		var d = $q.defer();
		api.Location.getStoreByLocation(location, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.shoplist);
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
