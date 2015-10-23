angular.module('aviate.services')
.service('MyListServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	
	this.addToMyList = function(user){
		var d = $q.defer();
		api.MyList.AddToMyList(user, function(err, result){
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
	
	this.getMyList = function(user){
		var d = $q.defer();
		api.MyList.getMyList(user, function(err, result){
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
	
	this.removeMyList = function(user){
		var d = $q.defer();
		api.MyList.removeMyList(user, function(err, result){
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
