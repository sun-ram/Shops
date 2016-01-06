/**
 * Prabakaran A
 */

angular.module('aviateAdmin.controllers')
	.controller("galleryMainCtrl", ['$scope', '$state','toastr','$rootScope','GalleryServices', '$mdDialog','folder','$stateParams','$localStorage',
	 function($scope, $state, toastr, $rootScope, GalleryServices, $mdDialog, folder, $stateParams, $localStorage) {
		
		$scope.folderList = folder;
		
		$scope.selectedObj = {};
		
		$scope.openFolder =  function(folder){
			$rootScope.breadCrumbGallery.push({'name':folder.fileName,'id':folder.galleryId});
			$localStorage.breadCrumbGallery = $rootScope.breadCrumbGallery;
			$state.go('app.gallery.folder',{'folderId':folder.galleryId});
		};
		
		$scope.setActive = function(folder){
			$scope.selectedObj = folder;
		};
		
		$scope.checkActive = function(folder){
			if($scope.selectedObj.galleryId === folder.galleryId)
				return 'active';
		};
		
}]);