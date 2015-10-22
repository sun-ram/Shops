angular.module('aviate.directives').directive('mainNav', [
        '$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdUtil','$mdSidenav','$log','$mdDialog'
,        function($rootScope, $document, $state, ipCookie, $timeout, $mdUtil, $mdSidenav, $log ,$mdDialog) {

            return {
                // scope: false,
                restrict: 'E',
                templateUrl: './app/modules/main/nav-main.html',
                replace: true,
                link: function($scope, iElm, iAttrs, controller, AuthServices, CONSTANT){

                	 $scope.toggleSidenav = buildToggler('left');
                	 
               	  function buildToggler(navID) {
               	      var debounceFn =  $mdUtil.debounce(function(){
               	            $mdSidenav(navID).toggle().then(function () {
               	                $log.debug("toggle " + navID + " is done");
               	              });
               	          },200);
               	      return debounceFn;
               	    };

                      $scope.signUpPopup = function(ev){
                          $mdDialog.show({
                            templateUrl: 'app/modules/auth/signUp.html',
                            parent: angular.element(document.body),
                            targetEvent: ev,
                            clickOutsideToClose:true,
                            controller: function($scope, AuthServices, toastr, CONSTANT){
                               $scope.isSignUp = true;
                               $scope.signUp = function(user) {
                            	   user.role = CONSTANT.SUCCESS_CODE.ROLE;
                            	   if(user.password !== $scope.confirmPassword){
                            		   toastr.warning(CONSTANT.WARNING_CODE.MISSMATCHPASSWORD);
                            		   return;
                            	   }
                                   AuthServices.signUp(user).then(function(data){
                                	   $scope.cancel();
                                       toastr.success(CONSTANT.SUCCESS_CODE.SIGNUPSUCCESS);
                                   });
                                 };

                              $scope.cancel = function() {
                                $mdDialog.cancel();
                              };

                            }
                          })
                          .then(function() {

                          }, function() {

                          });
                        }
                      
                      
                      $scope.signInPopup = function(ev){
                          $mdDialog.show({
                            templateUrl: 'app/modules/auth/signIn.html',
                            parent: angular.element(document.body),
                            targetEvent: ev,
                            clickOutsideToClose:true,
                            controller: function($scope, AuthServices, toastr, CONSTANT){
                            	$scope.isSignUp = false;
                               $scope.signIn = function(user) {
                                   AuthServices.signIn(user).then(function(data){
                                	   $scope.cancel();
                                       toastr.success(CONSTANT.SUCCESS_CODE.SIGNINSUCCESS);
                                   });
                                 };
                                 
                                 $scope.forGetPassword = function(user) {
                                	 if(!user.emailId){
                                		 toastr.warning(CONSTANT.WARNING_CODE.FORGETPASSWORDNEEDMAILID);
                                		 return;
                                	 }
                                     AuthServices.forGetPassword(user).then(function(data){
                                        
                                     });
                                   };

                              $scope.cancel = function() {
                                $mdDialog.cancel();
                              };

                            }
                          })
                          .then(function() {

                          }, function() {

                          });
                        }

     	        	   $scope.logout = function() {
                         $rootScope.user = null;
                         ipCookie('user', null);
                       };
                    }

            };
        }
    ]);
