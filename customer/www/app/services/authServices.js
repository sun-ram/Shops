angular.module('aviate.services')
.service('AuthServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	this.signIn = function(user){
		var d = $q.defer();
		api.User.signIn(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					var userDetails = {};
					userDetails.userId= result.customer.customerId;
					userDetails.role = "CUSTOMER"
					if(result.customer.name){
						userDetails.userName = result.customer.name;
					}else if(result.customer.email){
						userDetails.userName = result.customer.email.split('@')[0];
					}else{
						userDetails.userName = "New User";
					}
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
					userDetails.userId= result.customer.customerId;
					userDetails.role = "CUSTOMER";
					if(result.customer.email){
						userDetails.emailId = result.customer.email;
						userDetails.userName = result.customer.email.split('@')[0];
					}else{
						userDetails.userName ="New User";
					}
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

	this.forGetPassword = function(req){
		var d = $q.defer();
		api.User.forGetPassword(req, function(err, result){
			if(result){
				d.resolve(result);
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.verifytoken = function(req){
		var d = $q.defer();
		api.User.verifyToken(req, function(err, result){
			if(result){
				d.resolve(result);
			}
		})
		return d.promise;
	};
	
	this.resetpass = function(req){
		var d = $q.defer();
		if(!req.user.password){
			//toastr.warning(CONSTANT.FORGETPASSWORDNEEDMAILID);
			return;
		}
		api.User.resetPassword(req, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.SUCCESS_CODE.PASSWORDCHANGED);
					//alert('password sent');
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
