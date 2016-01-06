angular.module('app')
.run(function($rootScope, $state, $http, ipCookie, SocketServices, $localStorage) {
	var user = ipCookie('adminuser');
	if (user === undefined || user === null) {
		$rootScope.user = null;
		$state.go('login');
	}else {
		$rootScope.user = ipCookie('adminuser');
		SocketServices.getSocket($rootScope.user);
	}
	
	$rootScope.breadCrumbGallery = $localStorage.breadCrumbGallery;

	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		var requireLogin = toState.data.requireLogin;
		var authenticated = ($rootScope.user !== null);
		console.log(toState.name + ' ' + toState.data.requireLogin);
		if (requireLogin && !authenticated) {
			event.preventDefault();
			$state.go('login');
		}
	});
	
	window.onbeforeunload = function (event) {
		$rootScope.websocket.close();
	};
	
});
