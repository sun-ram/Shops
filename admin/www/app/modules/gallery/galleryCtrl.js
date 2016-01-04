/**
 * Anbukkani Gajendran
 */

angular.module('aviateAdmin.controllers')
	.controller("galleryCtrl", ['$scope', '$state','toastr','$rootScope','GalleryServices',
	 function($scope, $state, toastr, $rootScope, GalleryServices) {
		
		$scope.galleryFolder = [{
				"fileName":"home",
				"filePath":"home",
				"galleries":{
					"fileName":"Sample1",
					"filePath":"Sample1",
				}
			},
			{
				"fileName":"picture",
				"filePath":"picture",
				"galleries":{
					"fileName":"Sample",
					"filePath":"Sample",
				}
			}];
		
		
		  $scope.optimizeData = function (data, exceptionIndex){
              
              if(data && data.length>0){
                  for(var i=0;data[i];i++){
                      if(typeof exceptionIndex == 'undefined' || exceptionIndex != i){
                    	 if(data[i].isSummary=='Y'){
          					data[i].folderIconName = "folder";
          				}
                          $scope.optimizeInnerData(data[i]);
                      }
                  }
              }else{
                  $scope.optimizeInnerData(data);
              }   
          };
		  
		  $scope.getRootGallery = function (newData, keyCatId, currentRoot){
              if(newData && newData.length>0){
                  for(var i=0;newData[i];i++){
                     $scope.executeGetRoot(newData[i], keyCatId, i);
                  }
              }else{
                  $scope.executeGetRoot(newData, keyCatId, currentRoot);
              }
          };
          
          $scope.executeGetRoot = function (data, keyCatId, currentRoot){
              if(data.galleryId == keyCatId){
                  currentRootgalleryIndex = currentRoot;
					data.extrIconName = (data.extrIconName == 'arrow_drop_down') ? 'arrow_drop_up': 'arrow_drop_down';
              }
              for(var j=0;data.galleries && data.galleries[j];j++){
                  $scope.executeGetRoot(data.galleries[j], keyCatId, currentRoot);
              }
//              if(data.productTypes && data.productTypes.length>0){
//                  for(var j=0;data.productTypes[j];j++){
//                      if(data.productTypes[j].productTypeId == keyCatId){
//                          currentRootCatagoryIndex = currentRoot;
//                      }
//                  
//                  }
//              }
          };
          
          $scope.manipulateSubtree = function (newData, parentId, parentchanged){
				//resetting selected class property to unselect to all elements
               newData.selectionClass="";
                  if(newData.galleryId && newData.galleryId == parentId){
                           newData.selectionClass="selectedField";
                          if((!newData.galleries || newData.galleries.length <= 0) /*&& (!newData.productTypes || newData.productTypes.length <= 0)*/){
                                  newData.hide=false;
                          }
                  }
                  if(newData.parentGalleryId && newData.parentGalleryId == parentId  && !parentchanged){
						
                        newData.hide = !newData.hide;
                        parentchanged=true;
                  }
                 /* if(newData.productTypes && newData.productTypes.length>0){
                      for(var j=0;newData.productTypes[j];j++){
                          if( newData.productCategoryId ==  parentId){
                              newData.productTypes[j].hide = !newData.productTypes[j].hide;
                          }
                         newData.productTypes[j].selectionClass = "";
                          if(newData.productTypes[j].productTypeId == parentId){
                              newData.productTypes[j].selectionClass = "selectedField";
                          }
                      }
                  }*/
                  for(var j=0;newData.galleries && newData.galleries[j];j++){
                      if(parentchanged && !newData.galleries[j].hide){
                          newData.galleries[j].hide = newData.hide;
                      }
                      $scope.findSubtree(newData.galleries[j],parentId,parentchanged);
                  }
          };
		  
          $scope.findSubtree = function (newData, parentId,parentchanged){
              //incase data contains array of category
              if(newData && newData.length>0){
                  for(var i=0;newData[i];i++){
                     $scope.manipulateSubtree(newData[i], parentId, parentchanged);
                  }
              }else{
                  $scope.manipulateSubtree(newData, parentId, parentchanged);
              }
              
          };
          
		  $scope.getGalleryByGalleryId = function(galleryId){
      		if(galleryId){
                  currentRootGalleryIndex = -1;
                  $scope.getRootGallery($scope.galleries,galleryId, null);
                  $scope.optimizeData ($scope.galleries, currentRootGalleryIndex);
                  $scope.findSubtree($scope.galleries, galleryId,false);
      		}
      		
      	};
		   
      	$scope.optimizeInnerData = function (data){
//      		console.log("Data --->",data);
				data.extrIconName = "arrow_drop_down";
              if(typeof data.rootParent == 'undefined'){
                  data.hide = true;
              }
              for(var j=0;data.galleries && data.galleries[j];j++){
                  $scope.optimizeData(data.galleries[j]);
              }
          };
		
		$scope.galleryList = function(){
			$scope.data={};
			GalleryServices.getGalleryList($scope.data).then(function(data){
    			console.log("Data --->",data);
    			for(var i=0;i<data.length;i++){
    				data[i].rootParent = i;
    			}
                $scope.optimizeData(data);
    			$scope.galleries=data;
                console.log("Manipulated data --->",data);
               });
          };
        $scope.galleryList();
        
        $scope.addGallery=function (){
        	$scope.data={};
        	GalleryServices.addGallery($scope.data).then(function(data){
//    			console.log("Data --->",data);
//    			for(var i=0;i<data.length;i++){
//    				data[i].rootParent = i;
//    			}
//                $scope.optimizeData(data);
//    			$scope.galleries=data;
//                console.log("Manipulated data --->",data);
               });
        };
		
	}]);