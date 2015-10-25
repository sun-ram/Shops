angular.module('app')
.config(['$stateProvider', '$urlRouterProvider', "apiProvider", "$httpProvider", "myConfig",
         function ($stateProvider, $urlRouterProvider, apiProvider, $httpProvider, myConfig){


	//================= Routes
	$urlRouterProvider.otherwise('/home');
	$stateProvider
	.state('app', {
		abstract: true,
		templateUrl: 'app/modules/app/app.html'
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
				return ProductService.getProductsFromCategory({'categoryId':$stateParams.categoryId}).then(function(data){
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
				return ProductService.getProductsByProductTypeId({'productTypeId':$stateParams.productTypeId}).then(function(data){
					return data;
				});
			}
		}

	})

	// Product Details module
	.state('app.productsdetails', {
		url: '/productdetails/:productId',
		templateUrl: 'app/modules/products/productDetails/productDetails.html',
		controller : 'productDetailsCtrl'
	})

	// MyList module
	.state('app.mylist', {
		url: '/mylist',
		templateUrl: 'app/modules/mylist/myList.html',
		controller : 'myListCtrl'
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

	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.headers.common = 'Content-Type: application/json';
	delete $httpProvider.defaults.headers.common['X-Requested-With'];

	apiProvider.setApiUrl(myConfig.backend);

}]);
