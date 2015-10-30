angular.module('aviateAdmin.directives').directive('mainNav', [
        '$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdUtil','$mdSidenav','$log',
        function($rootScope, $document, $state, ipCookie, $timeout, $mdUtil, $mdSidenav, $log ) {

            return {
                // scope: false,
                restrict: 'E',
                templateUrl: './app/modules/main/nav-main.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller) {

                	 $scope.toggleSidenav = buildToggler('left');
                	 
                	  function buildToggler(navID) {
                	      var debounceFn =  $mdUtil.debounce(function(){
                	            $mdSidenav(navID).toggle().then(function () {
                	                $log.debug("toggle " + navID + " is done");
                	              });
                	          },200);
                	      return debounceFn;
                	    };
                    
 	        	   $scope.logout = function() {
                     $rootScope.user = null;
                     ipCookie('user', null);
                     $state.go('login');
                   };
                }

            };
        }
    ]);
