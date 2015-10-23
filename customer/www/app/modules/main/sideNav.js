
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
                	var request={
                			storeId:"6"
                	}
                	$scope.categoryList = function(){
                		CategoryService.getCategoryList(request).then(function(data){
                			$scope.categoryList=data;
                           });
                      }
                	$scope.categoryList();
                	
                	
                	$scope.getProductsByCategoryId = function(categoryId){
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
