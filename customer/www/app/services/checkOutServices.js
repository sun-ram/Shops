angular.module('aviate.services')
.service('CheckOutServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	
	this.addAddress = function(user){
		var d = $q.defer();
		api.CheckOut.addAddress=(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve($rootScope.myList);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.getAddressList = function(user){
		var d = $q.defer();
		api.CheckOut.getAddressList(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					ipCookie("myList",result);
					$rootScope.myList = ipCookie("myList");
					d.resolve($rootScope.myList);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.removeAddress = function(user){
		var d = $q.defer();
		api.CheckOut.removeAddress(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve($rootScope.myList);
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
