angular.module('aviateAdmin.services')
.service('AuthService',['$q','api','ipCookie','toastr','CONSTANT', function($q, api, ipCookie,toastr, CONSTANT) {
	this.login = function(user){
		var d = $q.defer();
		api.User.login(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					var userDetails = {};
					userDetails.userId= result.user.userId;
					userDetails.role = result.user.role.name;
					if(result.user.store){
					userDetails.storeId = result.user.store.storeId;
					}
					userDetails.merchantId = result.user.merchant.merchantId;
					userDetails.emailId = result.user.emailid;
					userDetails.userName = result.user.userName;
					if(result.user.image){
						userDetails.logoUrl = result.user.image.url;
					}
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
	
	this.forgetpass = function(req){
				var d = $q.defer();
				api.User.forgetpass(req, function(err, result){
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
				api.User.verifytoken(req, function(err, result){
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
				api.User.resetpass(req, function(err, result){
					if(result){
						if (result.status === CONSTANT.STATUS.SUCCESS) {
							toastr.success(CONSTANT.PASSWORDCHANGED);
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
