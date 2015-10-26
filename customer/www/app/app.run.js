angular.module('app')
.run(function($rootScope, $state, $http, ipCookie, MyCartServices, MyCartFactory) {

	var store = ipCookie('store');
	$rootScope.geoLocation ={}

	if (store == undefined || store == null) {
		$rootScope.store = null;
		$rootScope.geoLocation.findlocation=true;
		$state.go('app.home');
	}else {
		$rootScope.store = ipCookie('store');
		$rootScope.geoLocation.findlocation=false;
	};

	var user = ipCookie('user');

	if (user != undefined || user != null) {
		$rootScope.user = null;
		$rootScope.user = user;
	};

	var myCart = ipCookie('myCart');

	if (myCart != undefined || myCart != null) {
		$rootScope.myCart = null;
		$rootScope.myCart = myCart;
		MyCartFactory.myCartTotalPriceCalculation();
	}else{
		$rootScope.myCart = {};
		$rootScope.myCart.cartItem = [];
	}

	var myList = ipCookie('myList');

	if (myList != undefined || myList != null) {
		$rootScope.myList = {};
		$rootScope.myList.listItem = [];
		$rootScope.myList.listItem = myList;
	}else{
		$rootScope.myList = {};
		$rootScope.myList.listItem = [];
	}

	if ((user != undefined && user != null) && (store != undefined && store != null)) {

		MyCartServices.getCartList({"customerId" : $rootScope.user.userId, "storeId" : $rootScope.store.storeId},  function(data){
			console.log('Get To My Cart in factory');
			MyCartFactory.myCartTotalPriceCalculation();
		});

		$rootScope.$on('$stateChangeSuccess', function (ev, to, toParams, from, fromParams) {
			$rootScope.previousState = from;
			//$rootScope.previousState.name = from.name+'('+JSON.stringify(fromParams).replace("\"","")+')';
			
		});
	}
});
