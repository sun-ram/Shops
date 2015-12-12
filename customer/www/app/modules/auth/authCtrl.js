angular.module('aviate.controllers')
.controller("authCtrl",
		['$rootScope', '$scope', '$state', '$stateParams', '$mdDialog', 'toastr', 'CONSTANT', 'AuthServices', 'CheckOutServices',
		 function($rootScope, $scope, $state, $stateParams, $mdDialog, toastr, CONSTANT, AuthServices, CheckOutServices) {

       $scope.tokenId = $stateParams.tokenId;
   	
	   	if($scope.tokenId){
	   		var req = {"tokenId": $scope.tokenId}
	   		AuthServices.verifytoken(req).then(function(data){
	   			if(data.status === CONSTANT.STATUS.FAILURE){
	   				toastr.error(data.errorString);
	   				$state.go('app.home', {}, {reload: true}); //second parameter is for $stateParams
	   			}else{
	   				$mdDialog.show({
	   					templateUrl: 'app/modules/auth/resetPassword.html',
	   					parent: angular.element(document.body),
	   					clickOutsideToClose:false,
	   					escapeToClose : false,
	   					controller: function($scope, $stateParams, AuthServices){
	   						$scope.resetPassword = function(user) {
	   							if(user.password === $scope.confirmPassword){
	   								var req = {"tokenId": $stateParams.tokenId, "user": user};
	   								AuthServices.resetpass(req).then(function(data){
	   									$scope.user ="";
	   									$scope.cancel();
	   								});
	   							}else{
	   								toastr.error(CONSTANT.ERROR_CODE.PASSWORDNOTMATCH);
	   							}
	   						};
	   						
	   						$scope.cancel = function(){
	   							$mdDialog.cancel();
	   							$state.go('app.home', {}, {reload: true}); //second parameter is for $stateParams
	   						}
	   					}
	   				})
	   				.then(function() {
	   					
	   				}, function() {

	   				});
	   			}
	   		});
	   	}
	   	
}]);
