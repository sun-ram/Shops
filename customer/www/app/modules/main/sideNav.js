
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
                	
                    $scope.optimizeData = function (data){
                        
                        if(data && data.length>0){
                            for(var i=0;data[i];i++){
                                if(data[i].parentCategory){
                                    data[i].hide = true;
                                }
                                for(var j=0;data[i].category && data[i].category[j];j++){
                                    $scope.optimizeData(data[i].category[j]);
                                }
                            }
                        }else{
                            if(data.parentCategory){
                                    data.hide = true;
                                }
                                for(var j=0;data.category && data.category[j];j++){
                                    $scope.optimizeData(data.category[j]);
                                }
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
                    $scope.findSubtree = function (newData, parentId,parentchanged){
                        //incase data contains array of category
                        if(newData && newData.length>0){
                            for(var i=0;newData[i];i++){
                                //if sele
                                newData[i].selectionClass="";
                                if(newData[i].categoryId && newData[i].categoryId == parentId){
                                     newData[i].selectionClass="selectedField";
                                }
                                if(newData[i].parentCategoryId && newData[i].parentCategoryId == parentId && !parentchanged){
                                    newData[i].hide = !newData[i].hide;
                                    parentchanged=true;
                                }
                                if(newData[i].productType && newData[i].productType.length>0){
                                    for(var j=0;newData[i].productType[j];j++){
                                        if(newData[i].categoryId ==  parentId){
                                            newData[i].productType[j].hide = !newData[i].productType[j].hide;
                                        }
                                        newData[i].productType[j].selectionClass = "";
                                        //code for change selected field class
                                        if(newData[i].productType[j].productTypeId == parentId){
                                            newData[i].productType[j].selectionClass = "selectedField";
                                            console.log("--------------->selected field",newData.productType[j]);
                                        }
                                    }
                                }
                                for(var j=0;newData[i].category && newData[i].category[j];j++){
                                    if(parentchanged){
                                        newData[i].category[j].hide = newData[i].hide;
                                    }
                                    $scope.findSubtree(newData[i].category[j],parentId,parentchanged);
                                }
                            }
                        }else{
                            
                            newData.selectionClass="";
                            if(newData.categoryId && newData.categoryId == parentId){
                                     newData.selectionClass="selectedField";
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
                                        console.log("--------------->selected field",newData.productType[j]);
                                    }
                                }
                            }
                            for(var j=0;newData.category && newData.category[j];j++){
                                if(parentchanged){
                                    newData.category[j].hide = newData.hide;
                                }
                                $scope.findSubtree(newData.category[j],parentId,parentchanged);
                            }
                        }
                        
                    }
                    
                    	
                	$scope.getProductsByCategoryId = function(categoryId){
                        $scope.findSubtree($scope.categoryList, categoryId,false);
                		$state.go('app.products',{'categoryId': categoryId})
                	}
                	
                	$scope.getProductsByProductTypeId = function(productTypeId){
                        $scope.findSubtree($scope.categoryList, productTypeId,false);
                		$state.go('app.productType',{'productTypeId': productTypeId})
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
