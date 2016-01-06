angular.module('aviateAdmin.directives').directive('breadCrumb', [
        '$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdSidenav', '$mdUtil', '$log',
        function($rootScope, $document, $state, ipCookie, $timeout,$mdSidenav, $mdUtil, $log ) {
            return {
                // scope: false,
                restrict: 'E',
                templateUrl: 'app/directives/breadCrumb.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller) {
                	$scope.changeState = function(crumb,crumbList){
                		for(var i = 0; i < crumbList.length; i++){
                			if(crumbList[i].id === crumb.id){
                				crumbList.splice(i+1,crumbList.length);
                			}
                		}
                		$state.go('app.gallery.folder',{'folderId':crumb.id});
                	};
                }
            };
        }
        
        
    ]);
