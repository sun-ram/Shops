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
		$rootScope.myList = null;
		$rootScope.myList = myCart;
	}else{
		$rootScope.myList = {};
		$rootScope.myList = [];
	}
	
	if ((user != undefined && user != null) && (store != undefined && store != null)) {
		
		MyCartServices.getCartList(user).then(function(data){
			console.log('get cartlist success in run');
		});
/*
		MyCartServices.getCartList(user).then(function(data){
			console.log('get Mylist success in run');
		});*/
	}
	
	//var myCart = ipCookie('myCart');
	//var myCart = ipCookie('myList');
	
});
