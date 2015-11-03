
angular.module('aviate.directives').directive('sideNav', [
        '$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdSidenav', '$mdUtil', '$log','CategoryService',
        function($rootScope, $document, $state, ipCookie, $timeout,$mdSidenav, $mdUtil, $log, CategoryService ) {
            return {
                // scope: false,
                restrict: 'E',
                templateUrl: './app/modules/main/side-nav.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller) {
                	
                	/*$scope.toggleSidenav = function(menuId) {
                		$mdSidenav(menuId).toggle();
                	};*/
                    var currentRootCatagoryIndex="";
                	$scope.optimizeInnerData = function (data){
                        if(data.parentCategory){
                            data.hide = true;
                        }
                        for(var j=0;data.category && data.category[j];j++){
                            $scope.optimizeData(data.category[j]);
                           if(data.category[j].productType && data.category[j].productType.length>0){
                                for(var k=0; data.category[j].productType[k]; k++){
                                    data.category[j].productType[k].hide=true;
                                }
                            }
                        }
                    }
                    
                    $scope.optimizeData = function (data, exceptionIndex){
                        
                        if(data && data.length>0){
                            for(var i=0;data[i];i++){
                                if(typeof exceptionIndex == 'undefined' || exceptionIndex != i){
                                    $scope.optimizeInnerData(data[i]);
                                }
                            }
                        }else{
                            $scope.optimizeInnerData(data);
                        }   
                        
                    }
                    
                	$rootScope.categoryList = function(){
                		var request={
                    			storeId:$rootScope.store.storeId
                    	}
                		CategoryService.getCategoryList(request).then(function(data){
                            $scope.optimizeData(data);
                			$scope.categoryList=data;
                           });
                      }
                    $rootScope.categoryList();
                	
                  $scope.todos = [
      {
        productImage : 'assets/images/fruit.jpg',
        quantity: '1 x Rs.250',
        productName: 'South Indian Banana',
        weight : '2Kg'
        
      },
      {
        productImage : 'assets/images/fruit.jpg',
        quantity: '1 x Rs.550',
        productName: 'Strawberry',
        weight : '2Kg'
        
      }
    ];
                    
                    $scope.manipulateSubtree = function (newData, parentId, parentchanged){
                         newData.selectionClass="";
                            if(newData.categoryId && newData.categoryId == parentId){
                                     newData.selectionClass="selectedField";
                                    if((!newData.category || newData.category.length <= 0) && (!newData.productType || newData.productType.length <= 0)){
                                            newData.hide=false;
                                    }
                            }
                            if(newData.parentCategoryId && newData.parentCategoryId == parentId  && !parentchanged){
                                  newData.hide = !newData.hide;
                                  parentchanged=true;
                            }
                            if(newData.productType && newData.productType.length>0){
                                for(var j=0;newData.productType[j];j++){
                                    if( newData.categoryId ==  parentId){
                                        newData.productType[j].hide = !newData.productType[j].hide;
                                    }
                                   newData.productType[j].selectionClass = "";
                                    if(newData.productType[j].productTypeId == parentId){
                                        newData.productType[j].selectionClass = "selectedField";
                                    }
                                }
                            }
                            for(var j=0;newData.category && newData.category[j];j++){
                                if(parentchanged && !newData.category[j].hide){
                                    newData.category[j].hide = newData.hide;
                                }
                                $scope.findSubtree(newData.category[j],parentId,parentchanged);
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
                        
                    }
                    $scope.executeGetRoot = function (data, keyCatId, currentRoot){
                        if(data.categoryId == keyCatId){
                            currentRootCatagoryIndex = currentRoot;
                        }
                        for(var j=0;data.category && data.category[j];j++){
                            $scope.executeGetRoot(data.category[j], keyCatId, currentRoot);
                        }
                        if(data.productType && data.productType.length>0){
                            for(var j=0;data.productType[j];j++){
                                if(data.productType[j].productTypeId == keyCatId){
                                    currentRootCatagoryIndex = currentRoot;
                                }
                            
                            }
                        }
                    };
                    $scope.getRootCatagory = function (newData, keyCatId, currentRoot){
                        if(newData && newData.length>0){
                            for(var i=0;newData[i];i++){
                               $scope.executeGetRoot(newData[i], keyCatId, i);
                            }
                        }else{
                            $scope.executeGetRoot(newData, keyCatId, currentRoot);
                        }
                    };
                	$scope.getProductsByCategoryId = function(categoryId){
                		if(categoryId){
                            currentRootCatagoryIndex = -1;
                            $scope.getRootCatagory($scope.categoryList,categoryId, null);
                            $scope.optimizeData ($scope.categoryList, currentRootCatagoryIndex);
	                        $scope.findSubtree($scope.categoryList, categoryId,false);
	                		$state.go('app.products',{'categoryId': categoryId});
                		}
                		
                	}
                	
                	$scope.getProductsByProductTypeId = function(productTypeId){
                		if(productTypeId){
                            currentRootCatagoryIndex = -1;
                            $scope.getRootCatagory($scope.categoryList,productTypeId, null);
                            $scope.optimizeData ($scope.categoryList, currentRootCatagoryIndex);
	                        $scope.findSubtree($scope.categoryList, productTypeId,false);
	                		$state.go('app.productType',{'productTypeId': productTypeId});
                		}
                	}
                	
                	$scope.checkParent = function(parent,c,subDetailsShow){
                		if(parent){
                			return true;
                		} else {
                			if(c.parentCategoryId == subDetailsShow){
                				return true;
                			} else {
                				return false;
                			}
                		}
                	}
                	
                	$scope.toggleSubList = function(id){
                		if(id == $scope.headerNavId){
                			$scope.show = false;
                		} else {
                			$scope.show = true;
                		}
                		$scope.headerNavId = id;
                	};
                }
            };
        }
    ]);
