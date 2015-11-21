angular.module('aviateAdmin.services')
.service('BannerServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {

	this.getBanner = function(store){
		var d = $q.defer();
		api.Banner.getList(store, function(err, result){
			if(result){
				d.resolve(result.bannerList);
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.addNewBanner = function(banner){
		var d = $q.defer();
		api.Banner.addNewBanner(banner, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.UPLOADBANNER);
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
	
	this.updateBanner = function(banner){
		var d = $q.defer();
		api.Banner.updateBanner(banner, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.UPDATEBANNER);
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

	this.deleteBanner = function(banner){
		var d = $q.defer();
		api.Banner.deleteBanner(banner, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETEBANNER);
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

	this.setBannerObj = function(banner){
		this.obj = banner;
	};

	this.getBannerObj = function(){
		return this.obj;
	};

}]);