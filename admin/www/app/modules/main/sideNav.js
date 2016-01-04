angular.module('aviateAdmin.directives').directive('sideNav', [
        '$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdSidenav', '$mdUtil', '$log',
        function($rootScope, $document, $state, ipCookie, $timeout,$mdSidenav, $mdUtil, $log ) {
            return {
                // scope: false,
                restrict: 'E',
                templateUrl: './app/modules/main/side-nav.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller) {
	        	  
                	/*$scope.toggleSidenav = function(menuId) {
                		$mdSidenav(menuId).toggle();
                	};*/
                    $scope.tabChoosen = function (key){
                        $scope.currentOption = key; 
                    }
                    
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
