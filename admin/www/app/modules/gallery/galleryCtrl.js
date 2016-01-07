/**
 * Anbukkani Gajendran
 */

angular.module('aviateAdmin.controllers')
	.controller("galleryCtrl", ['$scope', '$state','toastr','$rootScope','GalleryServices', '$mdDialog','$localStorage', 'folder','$stateParams',
	 function($scope, $state, toastr, $rootScope, GalleryServices, $mdDialog,$localStorage, folder, $stateParams) {
		
		$scope.galleries = folder;
		
		$scope.selectedObj = {};
		
		/*$scope.openFolder =  function(folder){
			$rootScope.breadCrumbGallery.push({'name':folder.fileName,'id':folder.galleryId});
			$localStorage.breadCrumbGallery = $rootScope.breadCrumbGallery;
			$state.go('app.gallery.folder',{'folderId':folder.galleryId});
		};*/
		
		$scope.isListview = false;
		
		$scope.changeListView = function(){
			$scope.isListview = true;
		};
		
		$scope.changeGridView = function(){
			$scope.isListview = false;
		};
				
		$scope.setActive = function(folder){
			$scope.selectedObj = folder;
		};
		
		$scope.checkActive = function(folder){
			if($scope.selectedObj.galleryId === folder.galleryId)
				return 'active';
		};
		
		$scope.galleryList = function(){
			if($stateParams.folderId){
				return;
			}
			$scope.data={};
			GalleryServices.getGalleryList($scope.data).then(function(data){
				$rootScope.breadCrumbGallery = [];				
    			console.log("Data --->",data);
    			$scope.galleries=data;
           });
          };
          
		$scope.openFolders = function(folder){
			$rootScope.breadCrumbGallery.push({'name':folder.fileName,'id':folder.galleryId});
			$localStorage.breadCrumbGallery = $rootScope.breadCrumbGallery;
			$state.go('app.folder',{'folderId':folder.galleryId});
		};         
          
        $scope.galleryList();
        
        $scope.addGallery=function (ev, isSummary){
        	$mdDialog.show({
				templateUrl: 'app/modules/modals/addNewFolder.html',
				parent: angular.element(document.body),
				targetEvent: ev,
				clickOutsideToClose:true,
				controller: function($scope,getGallery, galleries, $stateParams){
					$scope.data = {};
					$scope.data.isSummary = isSummary;
					$scope.checkFolderIsExit = function(){
						/*if(!isChild){*/
							for(var i = 0; i < galleries.length; i++){
								if(galleries[i].fileName === $scope.data.fileName){
									$scope.errorMsg = "This folder already exit";
									$scope.flag = true; 
									return;
								}else{
									$scope.flag = false;
								}
							}
						/*}else{
							for(var j = 0; j < childGalleries.length; j++){
								if(childGalleries[j].fileName === $scope.data.fileName){
									$scope.errorMsg = "This folder already exit";
									$scope.flag = true; 
									return;
								}else{
									$scope.flag = false;
								}
							}
						}*/
					};
					
					$scope.splitProductType = function(img){
						$scope.reImg = {};
						$scope.reImg.image =img.split(",")[1];
						$scope.reImg.type = img ? (img.substring(11).split(";")[0]) : "";
						return $scope.reImg;
					}; 
					
					$scope.addFolder = function(){
						
						if($stateParams.folderId){
							$scope.data.parentGalleryId = $stateParams.folderId;
						}
						
						if($scope.data.isSummary === 'N'){
							if(!$scope.image){
								$scope.errorMsg = "chose Image";
								return
							}
							$scope.img = $scope.splitProductType($scope.image);
							$scope.data.strFile = $scope.img.image;
							$scope.data.type = $scope.img.type;
						}
						
						GalleryServices.addGallery($scope.data).then(function(data){
							galleries.push(data);
							$scope.cancel();
						});
					};
					
					$scope.cancel = function() {
						$mdDialog.cancel();
					};
				},
				locals:{
					getGallery:$scope.galleryList,
				   galleries:$scope.galleries
				}
			})
			.then(function(answer) {	
				$scope.status = 'You said the information was "' + answer + '".';
			}, function() {
				$scope.status = 'You cancelled the dialog.';
				console.log('updated gallery list ',$scope.galleries1);
			});
        };
		
	}]);