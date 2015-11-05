angular.module('aviateAdmin.services')
.service('AuthService',['$q','api','ipCookie','toastr','CONSTANT', function($q, api, ipCookie,toastr, CONSTANT) {
	this.login = function(user){
		var d = $q.defer();
		api.User.login(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					var userDetails = {};
					userDetails.userId= result.customerId;
					userDetails.role = result.role;
					userDetails.storeId = result.storeId;
					userDetails.merchantId = result.merchantId;
					userDetails.emailId = user.emailId;
					userDetails.userName = user.emailId.split('@')[0];
					ipCookie("user",userDetails);
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
	
	this.forgetpass = function(user){
				var d = $q.defer();
				if(!user.emailId){
					toastr.warning(CONSTANT.FORGETPASSWORDNEEDMAILID);
					return;
				}
				api.User.forgetpass(user, function(err, result){
					if(result){
						if (result.status === CONSTANT.STATUS.SUCCESS) {
							toastr.success(CONSTANT.FORGETPASSWORDCONFIRMATION);
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
