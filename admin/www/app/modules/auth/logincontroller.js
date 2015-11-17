angular.module('aviateAdmin.controllers')
.controller("logincontroller", function($rootScope, $localStorage, $scope, $http, $state,$mdToast, toastr, api, ipCookie, AuthService) {
	$scope.user = {}
	$scope.forgetpass = function(user) {
				$scope.forget = false;
				AuthService.forgetpass(user).then(function(data){
					$scope.user ="";
					$state.go('login');
				});
			};
			$scope.cancel=function()
			{
				$scope.forget=false;
				$scope.user ="";
				$state.go('login');
			}
	$scope.signIn = function() {
		$scope.saveauth();
		if($scope.login.$invalid){
			
			if($scope.login.emailId.$invalid){
				$scope.emailRequired = "true"
					return;
			}
			if($scope.login.password.$invalid){
				$scope.passwordRequired = "true"
					return;
			}

		}
		var userInfo = {
				"userName": $scope.user.emailId,
				"password": $scope.user.password
		};
		AuthService.login(userInfo).then(function(data) {
			$rootScope.user = data;
			if ($rootScope.user.role == 'SUPERADMIN') {
				$state.go('app.merchants');
			} else if($rootScope.user.role == 'MERCHANTADMIN') {
				$state.go('app.store');
			}else if($rootScope.user.role == 'STOREADMIN'){
				$state.go('app.products');
			}else {
				toastr.error("Invalied User");
			}
		});
	};
	
	$scope.saveauth = function() {
		if($scope.rememberme){
			if($scope.user.emailId==undefined){
				toastr.error("Please Enter Email Id");
			}
			else{
			$scope.user.rememberme=true;
			ipCookie('auth_info', $scope.user);
			}
			}else{
			ipCookie('auth_info', null);
		}
		}
	
	var authInfo = ipCookie('auth_info');
	
	if(authInfo != undefined || authInfo != null){
		if(authInfo.rememberme){
			$scope.user.emailId=authInfo.emailId;
			$scope.user.password = authInfo.password;
			$scope.rememberme=authInfo.rememberme;
	}}
});