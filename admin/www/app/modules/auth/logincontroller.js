angular.module('aviateAdmin.controllers')
.controller("logincontroller", function($rootScope, $localStorage, $scope, $http, $state,$mdToast, toastr, api, ipCookie, AuthService) {

	$scope.signIn = function() {

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
				"emailId": $scope.user.emailId,
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
});