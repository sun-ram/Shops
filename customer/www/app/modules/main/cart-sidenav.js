
angular.module('aviate.directives').directive('cartsidenav', [
        '$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdSidenav', '$mdUtil', '$log','CategoryService','CONSTANT',
        function($rootScope, $document, $state, ipCookie, $timeout,$mdSidenav, $mdUtil, $log, CategoryService, CONSTANT) {
            return {
                // scope: false,
                restrict: 'E',
                templateUrl: './app/modules/main/cartsideNav.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller) {
                	
                	/*$scope.toggleSidenav = function(menuId) {
                		$mdSidenav(menuId).toggle();
                	};*/
                	
                	$scope.rupeesSymbol = CONSTANT.RUPEESSYMBOL;
                   
                	$scope.toggleSubList = function(id){
                        alert("djhasd");
                		if(id == $scope.headerNavId){
                            /*$rootScope.numLimit = 5;*/
                			$scope.show = false;
                		} else {
                           /* $rootScope.numLimit = 3;*/
                			$scope.show = true;
                		}
                		$scope.headerNavId = id;
                	};
                }
            };
        }
    ]);
