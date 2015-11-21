angular.module('aviate.services')
.service('AuthServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	this.signIn = function(user){
		var d = $q.defer();
		api.User.signIn(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					var userDetails = {};
					userDetails.userId= result.customerVo.customerId;
					userDetails.role = "CUSTOMER"
					userDetails.emailId = result.customerVo.email;
					userDetails.userName = result.customerVo.email.split('@')[0];
					ipCookie("user",userDetails);
					$rootScope.user = ipCookie("user");
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

	this.signUp = function(user){
		var d = $q.defer();
		api.User.signUp(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					var userDetails = {};
					userDetails.userId= result.customerVo.customerId;
					userDetails.role = "CUSTOMER"
					userDetails.emailId = result.customerVo.email;
					userDetails.userName = result.customerVo.email.split('@')[0];
					ipCookie("user",userDetails);
					$rootScope.user = ipCookie("user");
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

	this.forGetPassword = function(user){
		var d = $q.defer();
		api.User.forGetPassword(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.SUCCESS_CODE.FORGETPASSWORDCONFIRMATION);
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
