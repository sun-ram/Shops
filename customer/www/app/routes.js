angular.module('app')
.config(['$stateProvider', '$urlRouterProvider', "apiProvider", "$httpProvider", "myConfig",
         function ($stateProvider, $urlRouterProvider, apiProvider, $httpProvider, myConfig){


	//================= Routes
	$urlRouterProvider.otherwise('/home');
	$stateProvider
	.state('app', {
		abstract: true,
		templateUrl: 'app/modules/app/app.html',
		controller : 'appCtrl'
	})

	// Sign In module
	.state('app.signin', {
		url: '/signin',
		templateUrl: 'app/modules/cart/cart.html',
		controller : 'signInCtrl'
	})

	// Home Screen module
	.state('app.home', {
		url: '/home',
		templateUrl: 'app/modules/home/home.html',
		controller : 'homeCtrl'
	})

	// Sign Up module
	.state('app.signUp', {
		url: '/signUp',
		templateUrl: 'app/modules/cart/cart.html',
		controller : 'signInCtrl'
	})
	
	// Product module
	.state('app.products', {
		url: '/products/:categoryId',
		templateUrl: 'app/modules/products/product.html',
		controller : 'productCtrl',
		resolve:   {
			products:  function(ProductService,$http, $stateParams){
				return ProductService.getProductsFromCategory({'productCategory' : {'productCategoryId' : $stateParams.categoryId}}).then(function(data){
					return data;
				});

			}
		}

	})

	.state('app.productType', {
		url: '/product/:productTypeId',
		templateUrl: 'app/modules/products/product.html',
		controller : 'productCtrl',
		resolve:   {
			products:  function(ProductService,$http,$stateParams){
				return ProductService.getProductsByProductTypeId({'productType' : {productTypeId : $stateParams.productTypeId}}).then(function(data){
					return data;
				});
			}
		}

	})

	// Product Details module
	.state('app.productsdetails', {
		url: '/productdetails/:productId',
		templateUrl: 'app/modules/products/productDetails/productDetails.html',
		controller : 'productCtrl',
	})
	
		.state('app.productOffer', {
			url: '/productOffer/:offerId',
			templateUrl: 'app/modules/products/productOffer.html',
		controller : 'productOfferCtrl'
	})

	// footer pages
	.state('app.aboutus', {
		url: '/aboutus',
		templateUrl: 'app/modules/footer/aboutus.html'
	})
	.state('app.privacypolicy', {
		url: '/privacypolicy',
		templateUrl: 'app/modules/footer/privacyPolicy.html'
	})
	.state('app.termsandconditions', {
		url: '/termsandconditions',
		templateUrl: 'app/modules/footer/termsAndConditions.html'
	})


	// Check Out module
	.state('app.checkout', {
		url: '/checkout',
		templateUrl: 'app/modules/checkout/checkOut.html',
		controller : 'checkOutCtrl'
	})

	// Cart module
	.state('app.cart', {
		url: '/cart',
		templateUrl: 'app/modules/cart/my-cart.html',
		controller : 'cartCtrl'
	})

	//Location Module
	.state('app.location', {
		url: '/location',
		templateUrl: 'app/modules/location/location.html',
		controller : 'locationcontroller'
	})
	
	//Favourite Module
	
	.state('app.favourite', {
		url: '/favourite/:salesOrderId',
		templateUrl: 'app/modules/favourite/favourite.html',
		controller : 'favouriteCtrl'
	})
	
	.state('app.favouriteList', {
		url: '/mylist',
		templateUrl: 'app/modules/favourite/favouriteList.html',
		controller : 'favouriteCtrl'
	})
    .state('app.orderHistory', { 
		url: '/orderHistory',
		templateUrl: 'app/modules/orderHistory/orderHistory.html',
		controller : 'orderHistoryCtrl'
	})
	
	.state('auth', {
		abstract: true,
		templateUrl: 'app/modules/auth/auth.html',
	})
	
	// Reset Password
	.state('auth.resetPassword', {
		url: '/resetpassword/:tokenId',
		controller : 'authCtrl'
	})
	


	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.headers.common = 'Content-Type: application/json';
	delete $httpProvider.defaults.headers.common['X-Requested-With'];

	apiProvider.setApiUrl(myConfig.backend);

}]);
