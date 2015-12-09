angular.module('aviateAdmin.controllers')
.controller("logincontroller", function($rootScope, $localStorage, $scope, $http, $state,$mdToast, toastr, api, ipCookie, AuthService, $stateParams, CONSTANT) {
	$scope.user = {}
	$scope.tokenId = $stateParams.tokenId;
	
	if($scope.tokenId){
		var req = {"tokenId": $scope.tokenId}
		AuthService.verifytoken(req).then(function(data){
			if(data.status === CONSTANT.STATUS.FAILURE){
				toastr.error(data.errorString);
				$state.go('login');
			}
		});
	}
	
	$scope.forgetpass = function(user) {
				$scope.forget = false;
				var req = {"user":user, "passwordResetUrl": window.location.origin + window.location.pathname + "#/resetpassword/", "userType": "admin"};
				AuthService.forgetpass(req).then(function(data){
					$scope.user ="";
					$state.go('login');
				});
			};
			
			$scope.resetpass = function(user) {
				if(user.password === $scope.confirmPassword){
					var req = {"tokenId": $scope.tokenId, "user": user};
					AuthService.resetpass(req).then(function(data){
						$scope.user ="";
						$state.go('login');
					});
				}else{
					toastr.error(CONSTANT.PASSWORDNOTMATCH);
				}
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
			
			if($scope.login.userName.$invalid){
				$scope.userNameRequired = "true"
					return;
			}
			if($scope.login.password.$invalid){
				$scope.passwordRequired = "true"
					return;
			}

		}
		var userInfo = {
				"userName": $scope.user.userName,
				"password": $scope.user.password
		};
		AuthService.login(userInfo).then(function(data) {
			$rootScope.user = data;
			if ($rootScope.user.role == 'SUPERADMIN') {
				$state.go('app.super_admin_dashboard');
			} else if($rootScope.user.role == 'MERCHANTADMIN') {
				$state.go('app.merchant_admin_dashboard');
				//$state.go('app.addproducttype');
			}else if($rootScope.user.role == 'STOREADMIN'){
				$state.go('app.store_admin_dashboard');
			}else {
				toastr.error("Invalied User");
			}
		});
	};
	
	$scope.saveauth = function() {
		if($scope.rememberme){
			if($scope.user.userName==undefined){
				toastr.error("Please Enter UserName");
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
			$scope.user.userName=authInfo.userName;
			$scope.user.password = authInfo.password;
			$scope.rememberme=authInfo.rememberme;
	}}
});