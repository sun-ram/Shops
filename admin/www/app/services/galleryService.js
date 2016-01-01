/**
 * 
 */
angular.module('aviateAdmin.services')
.service('GalleryServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {
	 
		
		this.getGalleryList = function(data){
			var d = $q.defer();
			api.Gallery.getGalleryList(data,function(err, result){
				if(result){
					if (result.status === CONSTANT.STATUS.SUCCESS) {
						d.resolve(result.galleries);
					}else{
						toastr.error(result.errorString);	
					}
				}
				else{
					toastr.error(err.errorCode);
				}
			});
			return d.promise;
		};
		
		this.addGallery = function(data){
			var d = $q.defer();
			api.Gallery.add(data,function(err, result){
				if(result){
					if (result.status === CONSTANT.STATUS.SUCCESS) {
						d.resolve(result.galleries);
					}else{
						toastr.error(result.errorString);	
					}
				}
				else{
					toastr.error(err.errorCode);
				}
			});
			return d.promise;
		};
		
		
	}]);