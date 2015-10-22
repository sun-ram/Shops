angular.module('app')
.run(function($rootScope, $state, $http, ipCookie) {

	var user = ipCookie('user');
	if (user == undefined || user == null) {
		$rootScope.user = null;
	}else {
		$rootScope.user = ipCookie('user');
	}
});
