angular.module('aviate.directives').directive('footerNav', [
        '$rootScope', '$document', '$state', '$timeout','$mdUtil','$mdSidenav','$log','$mdDialog'
,        function($rootScope, $document, $state, $timeout, $mdUtil, $mdSidenav, $log ,$mdDialog) {

            return {
                // scope: false,
                restrict: 'E',
                templateUrl: './app/modules/main/nav-footer.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller, CONSTANT){
                	
                	
                	
                	
                }

            };
        }
    ]);
