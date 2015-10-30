angular.module('aviate', ['ngRoute','ngStorage','ui.bootstrap'])
//Define Routing for app
//Uri /AddNewOrder -> template add_order.html and Controller AddOrderController
//Uri /ShowOrders -> template show_orders.html and Controller AddOrderController
.config(['$routeProvider','$httpProvider',
  function($routeProvider, $httpProvider) {
	 $httpProvider.defaults.useXDomain = true;
	 delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $routeProvider.
    when('/product',{
    	templateUrl:"htmlfile/productList.html" , 
    	abstract: true,
    	controller: "headercontroller"	
    }).
    when('/login', {
        templateUrl: 'htmlfile/login.html',
        controller: 'logincontroller'
    }).
    when("/signup", {
   	  	templateUrl: "htmlfile/signup.html" ,
   	  	controller: "signupcontroller"
    }).
    when("/navigation",{
    	templateUrl:"htmlfile/navigation.html" , 
    	controller: 'headercontroller'
    }).
    when("/myCart",{
    	templateUrl:"htmlfile/myCart.html" , 
    	controller: 'headercontroller'
    }).
    when("/productDescription",{
    	templateUrl:"htmlfile/productDescription.html" , 
    	controller: 'headercontroller'
    }).
    when("/myList",{
    	templateUrl:"htmlfile/myList.html" , 
    	controller: 'headercontroller'
    }).
    when("/address",{
    	templateUrl:"htmlfile/address.html" , 
    	controller: 'checkoutcontroller'
    }).
    when("/delivery",{
    	templateUrl:"htmlfile/delivery.html" , 
    	controller: 'checkoutcontroller'
    }).
    when("/paymentType",{
    	templateUrl:"htmlfile/paymentType.html" , 
    	controller: 'headercontroller'
    }).
    when("/review_confirm",{
    	templateUrl:"htmlfile/Review_confirm.html" , 
    	controller: 'checkoutcontroller'
    }).
    otherwise({
        redirectTo: '/product'
    });
}])

	
