/**
 * Anbukkani Gajendran
 */

angular.module('aviateAdmin.controllers')
	.controller("galleryCtrl", ['$scope', '$state','toastr','$rootScope','GalleryServices', '$mdDialog','$localStorage',
	 function($scope, $state, toastr, $rootScope, GalleryServices, $mdDialog,$localStorage) {
		
		$scope.galleryList = function(){
			$scope.data={};
			GalleryServices.getGalleryList($scope.data).then(function(data){
	    			console.log("Data --->",data);
	    			$scope.galleries=data;
					if($scope.galleries.length>0){
						if($rootScope.breadCrumbGallery === undefined){
							$scope.openFolders($scope.galleries[0]);
						}
					}else{
						$state.go('app.gallery.folder');
					}
               });
          };
          
		$scope.openFolders = function(folder){
			$rootScope.breadCrumbGallery = [];
			$rootScope.breadCrumbGallery.push({'name':folder.fileName,'id':folder.galleryId});
			$localStorage.breadCrumbGallery = $rootScope.breadCrumbGallery;
			$state.go('app.gallery.folder',{'folderId':folder.galleryId});
		};         
          
		
        $scope.galleryList();
        
        $scope.addGallery=function (ev, isChild, childGalleries){
        	/*if(isChild){
        		$scope.$$childHead.folderList.push({
        			fileName: "test",
        			galleryId: "ff8081815211e710015211f091990000",
        			isSummary: "Y"
        		});
        		return;
        	}*/
        	$mdDialog.show({
				templateUrl: 'app/modules/modals/addNewFolder.html',
				parent: angular.element(document.body),
				targetEvent: ev,
				clickOutsideToClose:true,
				controller: function($scope,getGallery, galleries, $stateParams){
					
					$scope.checkFolderIsExit = function(){
						if(!isChild){
							for(var i = 0; i < galleries.length; i++){
								if(galleries[i].fileName === $scope.data.fileName){
									$scope.errorMsg = "This folder already exit";
									$scope.flag = true; 
									return;
								}else{
									$scope.flag = false;
								}
							}
						}else{
							for(var j = 0; j < childGalleries.length; j++){
								if(childGalleries[j].fileName === $scope.data.fileName){
									$scope.errorMsg = "This folder already exit";
									$scope.flag = true; 
									return;
								}else{
									$scope.flag = false;
								}
							}
						}
					};
					
					$scope.addFolder = function(){
						$scope.data.isSummary='Y';
						if(isChild){
							$scope.data.parentGalleryId = $stateParams.folderId;
						}
						
						GalleryServices.addGallery($scope.data).then(function(data){
							if(isChild){
								childGalleries.push(data);
							}else{
								galleries.push(data);
							}
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