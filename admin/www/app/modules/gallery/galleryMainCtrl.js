/**
 * Prabakaran A
 */

angular.module('aviateAdmin.controllers')
	.controller("galleryMainCtrl", ['$scope', '$state','toastr','$rootScope','GalleryServices', '$mdDialog','folder',
	 function($scope, $state, toastr, $rootScope, GalleryServices, $mdDialog, folder) {
		
		$scope.folderList = folder;
		
		$scope.openFolder =  function(folder){
			$rootScope.breadCrumbGallery.push({'name':folder.fileName,'id':folder.galleryId});
			$state.go('app.gallery.folder',{'folderId':folder.galleryId});
		};
		
}]);