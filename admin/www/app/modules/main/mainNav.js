angular.module('aviateAdmin.directives').directive('mainNav', [
        '$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdUtil','$mdSidenav','$log', '$window',
        function($rootScope, $document, $state, ipCookie, $timeout, $mdUtil, $mdSidenav, $log, $window ) {

            return {
                // scope: false,
                restrict: 'E',
                templateUrl: './app/modules/main/nav-main.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller) {
                	
                	$scope.toggleLeft = buildToggler('left');
        			
        			
        			function buildToggler(navID) {
        				var debounceFn =  $mdUtil.debounce(function(){
        				
        					$mdSidenav(navID).toggle().then(function () {
        						$log.debug("toggle " + navID + " is done");
        					});
        				},200);
        				return debounceFn;
        			};
                	 /*$scope.sidebarHidden = false;*/
                	 /*$scope.toggleSidenav = buildToggler('left');
                	 
                	  function buildToggler(navID) {
                	      var debounceFn =  $mdUtil.debounce(function(){
                	            $mdSidenav(navID).toggle().then(function () {
                	                $log.debug("toggle " + navID + " is done");
                	              });
                	          },200);
                	      return debounceFn;
                	    };*/
                	/*$scope.dumpSidebar = function(){
                	  	$scope.sidebarHidden = !$scope.sidebarHidden;
                	}*/
                    
                    /*$scope.$watch(
                                function () {
                                    return $window.innerWidth 
                                }, function(newVal, oldVal){
                                    if(newVal < 1000){
                                        $scope.sidebarHidden = true;
                                    }
                                }
                    );*/
                    
 	        	   $scope.logout = function() {
 	        		 $rootScope.websocket.close();
                     $rootScope.user = null;
                     ipCookie('adminuser', null);
                     $state.go('login');
                   };
                }

            };
        }
    ]);
