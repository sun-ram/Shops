
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
                	var directParent;
                    var currentRootCatagoryIndex="";
                    
                    $scope.activemenu = function(event){
                		$(".others").removeClass("activemenu");
        				$(event.currentTarget).addClass("activemenu");
        			}
                    
                	$scope.optimizeInnerData = function (data){
//                		console.log("Data --->",data);
						data.extrIconName = "arrow_drop_down";
                        if(typeof data.rootParent == 'undefined'){
                            data.hide = true;
                        }
                        for(var j=0;data.categoriesVo && data.categoriesVo[j];j++){
                        	data.categoriesVo[j].parentCategoryId = data.productCategoryId;
                            $scope.optimizeData(data.categoriesVo[j]);
                           if(data.categoriesVo[j].productTypes && data.categoriesVo[j].productTypes.length>0){
                                for(var k=0; data.categoriesVo[j].productTypes[k]; k++){
                                    data.categoriesVo[j].productTypes[k].hide=true;
                                }
                            }
                        }
                        if(data.productTypes && data.productTypes.length>0){
                                for(var k=0; data.productTypes[k]; k++){
                                    data.productTypes[k].hide=true;
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
                    			//storeId:"ff808181512e282b01512e2d2d780000"
                				storeId:$rootScope.store.storeId
                    	}
                		CategoryService.getCategoryList(request).then(function(data){
                			console.log("Data --->",data);
                			for(var i=0;i<data.length;i++){
                				data[i].rootParent = i;
                			}
                            $scope.optimizeData(data);
                			$scope.categoryList=data;
                            console.log("Manipulated data --->",data);
                           });
                      }
                    $rootScope.categoryList();
                	                    
                    $scope.manipulateSubtree = function (newData, parentId, parentchanged){
						//resetting selected class property to unselect to all elements
                         newData.selectionClass="";
                            if(newData.productCategoryId && newData.productCategoryId == parentId){
                                     newData.selectionClass="selectedField";
                                    if((!newData.categoriesVo || newData.categoriesVo.length <= 0) && (!newData.productTypes || newData.productTypes.length <= 0)){
                                            newData.hide=false;
                                    }
                            }
                            if(newData.parentCategoryId && newData.parentCategoryId == parentId  && !parentchanged){
								
                                  newData.hide = !newData.hide;
                                  parentchanged=true;
                            }
                            if(newData.productTypes && newData.productTypes.length>0){
                                for(var j=0;newData.productTypes[j];j++){
                                    if( newData.productCategoryId ==  parentId){
                                        newData.productTypes[j].hide = !newData.productTypes[j].hide;
                                    }
                                   newData.productTypes[j].selectionClass = "";
                                    if(newData.productTypes[j].productTypeId == parentId){
                                        newData.productTypes[j].selectionClass = "selectedField";
                                    }
                                }
                            }
                            for(var j=0;newData.categoriesVo && newData.categoriesVo[j];j++){
                                if(parentchanged && !newData.categoriesVo[j].hide){
                                    newData.categoriesVo[j].hide = newData.hide;
                                }
                                $scope.findSubtree(newData.categoriesVo[j],parentId,parentchanged);
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
                        if(data.productCategoryId == keyCatId){
                            currentRootCatagoryIndex = currentRoot;
							data.extrIconName = (data.extrIconName == 'arrow_drop_down') ? 'arrow_drop_up': 'arrow_drop_down';
                        }
                        for(var j=0;data.categoriesVo && data.categoriesVo[j];j++){
                            $scope.executeGetRoot(data.categoriesVo[j], keyCatId, currentRoot);
                        }
                        if(data.productTypes && data.productTypes.length>0){
                            for(var j=0;data.productTypes[j];j++){
                                if(data.productTypes[j].productTypeId == keyCatId){
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
                   
                   $scope.togglemouseLeave = build('left');
                        function build(navID) {
                        var debounceFn =  $mdUtil.debounce(function(){

                            $mdSidenav(navID).toggle().then(function () {
                                $log.debug("toggle " + navID + " is done");
                            });
                        },200);
                        return debounceFn;
                    };

                }
            };
        }
    ]);