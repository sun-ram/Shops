angular.module('app')
.run(function($rootScope, $state, $http, ipCookie) {

	var store = ipCookie('store');
	if (store == undefined || store == null) {
		$rootScope.store = null;
		$rootScope.findlocation=true;
		$state.go('app.home');
	}else {
		$rootScope.store = ipCookie('store');
		$rootScope.findlocation=false;
	}
});
