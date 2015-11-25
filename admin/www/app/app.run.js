angular.module('app')
.run(function($rootScope, $state, $http, ipCookie) {
	var user = ipCookie('user');
	if (user == undefined || user == null) {
		$rootScope.user = null;
		$state.go('login');
	}else {
		$rootScope.user = ipCookie('user');
	}

	$rootScope.$on('$stateChangeStart', function(event, toState, toParams) {
		var requireLogin = toState.data.requireLogin;
		var authenticated = ($rootScope.user != null);
		console.log(toState.name + ' ' + toState.data.requireLogin);
		if (requireLogin && !authenticated) {
			event.preventDefault();
			$state.go('login');
		}
	});
});
