
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
                        
                        for(var i=0;data[i];i++){
                            data[i].hide = false;
                            for(var j=0;data[i].category && data[i].category[j];j++){
                                $scope.optimizeData(data[i].category[j]);
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
                        if(newData && newData.length){
                            for(var i=0;newData[i];i++){
                                if(newData[i].parentCategoryId && newData[i].parentCategoryId == parentId && !parentchanged){
                                    newData[i].hide = !newData[i].hide;
                                    parentchanged=true;
                                }
                                for(var j=0;newData[i].category && newData[i].category[j];j++){
                                    if(parentchanged){
                                        newData[i].category[j].hide = newData[i].hide;
                                    }
                                    $scope.findSubtree(newData[i].category[j],parentId,parentchanged);
                                }
                            }
                        }else{
                            if(newData.parentCategoryId && newData.parentCategoryId == parentId  && !parentchanged){
                                    newData.hide = !newData.hide;
                                  parentchanged=true;
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
