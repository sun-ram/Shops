angular.module('aviate.controllers')
.controller("signInCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT',
		 function($scope, $state, toastr, CONSTANT) {

       toastr.info('SIGN IN CTRL');
       
       $scope.tokenId = $stateParams.tokenId;
   	
	   	if($scope.tokenId){
	   		var req = {"tokenId": $scope.tokenId}
	   		AuthService.verifytoken(req).then(function(data){
	   			if(data.status === CONSTANT.STATUS.FAILURE){
	   				toastr.error(data.errorString);
	   				$state.go('home');
	   			}
	   		});
	   	}

}]);
