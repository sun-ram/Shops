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
		
		this.getGalleryListById = function(id){
			var d = $q.defer();
			api.Gallery.getGalleryListById(id,function(err, result){
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
						d.resolve(result.gallery);
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
		
		this.deleteGallery = function(galleryId){
			var d = $q.defer();
			api.Gallery.deleteGallery(galleryId,function(err, result){
				if(result){
					if (result.status === CONSTANT.STATUS.SUCCESS) {
						d.resolve(result);
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