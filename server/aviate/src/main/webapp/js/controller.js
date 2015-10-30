'use strict';

var serviceUrl = 'http://localhost:8080/';
//var serviceUrl = 'http://182.74.202.178:8181/';
var aviate = angular.module('aviate');

/* logincontroller */

aviate.controller("logincontroller",function($rootScope,$localStorage,$scope,$http){
	//used in forgetpassword switch function
	$scope.loginShow = true;
	$scope.loginfunc = function(){
		var menuJson = angular.toJson({"emailId":$scope.user.emailId,"password":$scope.user.password});		
		$http({
			url: serviceUrl + 'aviate/json/customer/login',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			if(result.status=='SUCCESS'){
				var email = $scope.user.emailId;
				$rootScope.username = email.split('@')[0];
				var userdetails = {};
				$localStorage.userId = result.customerId;
				$rootScope.userId =  $localStorage.userId;
				$localStorage.apiresponse = $scope.username;
				$rootScope.usernames=$localStorage.apiresponse;
				$rootScope.list=true;
				$scope.login = false;	
				$scope.loginWindowClose();
				if($rootScope.cartItem){
					for(var i=0;i<$rootScope.cartItem.length;i++){
						$rootScope.addToCartDB($rootScope.cartItem[i].productDetails.productId,$rootScope.cartItem[i].noOfQuantityInCart
								,$rootScope.cartItem[i].productDetails.productPrice.price);
					}
					$rootScope.cartItem = [];
					$rootScope.cartTotalAmount = 0;
				}
				$rootScope.getCartListFromDB();
				$rootScope.myCartTotalPriceCalculation();
				$rootScope.myList();
			}
			if(result.status=='FAILURE'){
				alert("Username and password is wrong");
			}
		})
	}
	
    $scope.showModal = false;
    $scope.toggleModal = function(){
        $scope.showModal = !$scope.showModal;
    };

	$scope.forGetPasswordFunction = function(){
		if($scope.user.emailId != ""){
			$scope.loginShow = false;
			var menuJson = angular.toJson({"emailId":$scope.user.emailId});		
			$http({
				url: serviceUrl + 'aviate/json/customer/forgetpassword',
				method: 'POST',
				data: menuJson,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function(result, status, headers) {
				if(result.status=='SUCCESS'){
					$('#textLoader').hide();
					alert("Password sent to mail: "+$scope.user.emailId);
					$scope.loginShow=true;
				}else{
					$scope.loginShow = false;
					/*alert(result.errorString);*/
				}
			})
		}else{
			alert("enter Email-Id");
		}
	}
});

/* signupcontroller */

aviate.controller("signupcontroller",function($rootScope,$localStorage,$scope,$http){

	$scope.signUp = function(){
		if($scope.user.password == $scope.confirmPassword){
			var menuJson = angular.toJson({"emailId":$scope.user.emailId,"password":$scope.user.password,"role":"CUSTOMER"});		
			$http({
				url: serviceUrl + 'aviate/json/customer/signup',
				method: 'POST',
				data: menuJson,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function(result, status, headers) {
				if(result.status=='SUCCESS'){
					var email = $scope.user.emailId;
					$rootScope.username = email.split('@')[0];
					var userdetails = {};
					$localStorage.apiresponse = $scope.username;
					$rootScope.usernames=$localStorage.apiresponse;
					$localStorage.userId = result.customerId;
					$rootScope.userId =  $localStorage.userId;
					$rootScope.list=true;
					$scope.login = false;	
					$scope.signupWindowClose();
					if($rootScope.cartItem){
						for(var i=0;i<$rootScope.cartItem.length;i++){
							$rootScope.addToCartDB($rootScope.cartItem[i].productDetails.productId,$rootScope.cartItem[i].noOfQuantityInCart
									,$rootScope.cartItem[i].productDetails.productPrice.price);
						}
						$rootScope.cartItem = [];
						$rootScope.cartTotalAmount = 0;
					}
					$rootScope.getCartListFromDB();
				}
				if(result.status=='FAILURE'){
					alert(result.errorString);
				}
			})
		}
		else{
			alert("password not match");
		}
	}
});

/* headercontroller */

aviate.controller("headercontroller",function($rootScope,$scope,$localStorage,$http,$route,$location){

	// local Storage declaration
	
	$scope.loginShow = true;
	$scope.loginfunc = function(){
		var menuJson = angular.toJson({"emailId":$scope.user.emailId,"password":$scope.user.password});		
		$http({
			url: serviceUrl + 'aviate/json/customer/login',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			if(result.status=='SUCCESS'){
				var email = $scope.user.emailId;
				$rootScope.username = email.split('@')[0];
				var userdetails = {};
				$localStorage.userId = result.customerId;
				$rootScope.userId =  $localStorage.userId;
				$localStorage.apiresponse = $scope.username;
				$rootScope.usernames=$localStorage.apiresponse;
				$rootScope.list=true;
				$scope.login = false;	
				$scope.loginWindowClose();
				if($rootScope.cartItem){
					for(var i=0;i<$rootScope.cartItem.length;i++){
						$rootScope.addToCartDB($rootScope.cartItem[i].productDetails.productId,$rootScope.cartItem[i].noOfQuantityInCart
								,$rootScope.cartItem[i].productDetails.productPrice.price);
					}
					$rootScope.cartItem = [];
					$rootScope.cartTotalAmount = 0;
				}
				$rootScope.getCartListFromDB();
				$rootScope.myCartTotalPriceCalculation();
				$rootScope.myList();
			}
			if(result.status=='FAILURE'){
				alert("Username and password is wrong");
			}
		})
	}

	$scope.forGetPasswordFunction = function(){
		if($scope.user.emailId != ""){
			$scope.loginShow = false;
			var menuJson = angular.toJson({"emailId":$scope.user.emailId});		
			$http({
				url: serviceUrl + 'aviate/json/customer/forgetpassword',
				method: 'POST',
				data: menuJson,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function(result, status, headers) {
				if(result.status=='SUCCESS'){
					$('#textLoader').hide();
					alert("Password sent to mail: "+$scope.user.emailId);
					$scope.loginShow=true;
				}else{
					$scope.loginShow = false;
					/*alert(result.errorString);*/
				}
			})
		}else{
			alert("enter Email-Id");
		}
	}
	$scope.signUp = function(){
		if($scope.user.password == $scope.confirmPassword){
			var menuJson = angular.toJson({"emailId":$scope.user.emailId,"password":$scope.user.password,"role":"CUSTOMER"});		
			$http({
				url: serviceUrl + 'aviate/json/customer/signup',
				method: 'POST',
				data: menuJson,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function(result, status, headers) {
				if(result.status=='SUCCESS'){
					var email = $scope.user.emailId;
					$rootScope.username = email.split('@')[0];
					var userdetails = {};
					$localStorage.apiresponse = $scope.username;
					$rootScope.usernames=$localStorage.apiresponse;
					$localStorage.userId = result.customerId;
					$rootScope.userId =  $localStorage.userId;
					$rootScope.list=true;
					$scope.login = false;	
					$scope.signupWindowClose();
					if($rootScope.cartItem){
						for(var i=0;i<$rootScope.cartItem.length;i++){
							$rootScope.addToCartDB($rootScope.cartItem[i].productDetails.productId,$rootScope.cartItem[i].noOfQuantityInCart
									,$rootScope.cartItem[i].productDetails.productPrice.price);
						}
						$rootScope.cartItem = [];
						$rootScope.cartTotalAmount = 0;
					}
					$rootScope.getCartListFromDB();
				}
				if(result.status=='FAILURE'){
					alert(result.errorString);
				}
			})
		}
		else{
			alert("password not match");
		}
	}
	
	
	if($localStorage.localStorageProductDetail){
		$rootScope.productDetail = $localStorage.localStorageProductDetail;
	}else{
		$rootScope.productDetail = {};
	}

	if($localStorage.localStorageProductList){
		$rootScope.productList = $localStorage.localStorageProductList;
	}else{
		$rootScope.productList = [];
	}
	if(!$localStorage.localStorageMyList > 0){
		$rootScope.myLists = [];
	}else{
		$rootScope.myLists = $localStorage.localStorageMyList;
	}

	if($localStorage.apistoreName != null){
		$rootScope.selectedStore = $localStorage.apistoreName;
		$rootScope.superMarketId = $localStorage.apistoreId;
	}

	if($localStorage.localStoreCartlist == null){
		$rootScope.cartItem = [];
	}
	else{
		$rootScope.cartItem = $localStorage.localStoreCartlist;
	}
	if($localStorage.myCart == null){
		$rootScope.cartTotalAmount =0;
		$localStorage.myCart ={};
	}else{
		$rootScope.cartTotalAmount = $localStorage.myCart.cartTotalAmount;
		$rootScope.grossAmount = $localStorage.myCart.grossAmount;
		$rootScope.taxAmount = $localStorage.myCart.taxAmount;
		$rootScope.shippingCharges = $localStorage.myCart.shippingCharges;
	}


	$rootScope.loginWindowOpen =function(){	
		$rootScope.loginShow = true;
		$scope.login = true;
		$scope.signup = false;
	}
	$scope.loginWindowClose =function(){
		$scope.login = false;		 
	}
	$scope.signupWindowOpen =function(){
		$scope.signup = true;	
		$scope.login = false;
	}
	$scope.signupWindowClose =function(){
		$scope.signup = false;		 
	}
	$scope.navWindow =function(){
		$scope.nav = true;		 
	}
	if (angular.isDefined($localStorage.apiresponse)) {
		$rootScope.usernames=$localStorage.apiresponse;
		$rootScope.list=true;
	} 
	$rootScope.usernames=$localStorage.apiresponse;	

	if($localStorage.breadMenu != null){
		$scope.breadcrumbMenu = $localStorage.breadMenu;
	}
	if($localStorage.productTypeMenu!= null){
		$scope.productTypeMenu = $localStorage.productTypeMenu;
	}
	if($localStorage.productTypeMenuId!= null){
		$scope.productTypeMenuId = $localStorage.productTypeMenuId;
	}
	if($localStorage.shop){
		$rootScope.tax = $localStorage.shop.tax;
		$localStorage.shop.taxDetails = $rootScope.taxDetails;
	}else{
		$localStorage.shop = {};
	}
	$scope.logout = function() {
		angular.copy($scope.emptyUser, $scope.user);
		// my cart
		delete $localStorage.localStoreCartlist;
		delete $localStorage.myCart;
		$rootScope.cartItem = [];
		$rootScope.cartTotalAmount = 0;
		$rootScope.emptyFun();

		// My List
		delete $localStorage.localStorageMyList 
		$rootScope.myLists = [];


		delete $localStorage.apiresponse;
		delete $localStorage.apitotal;
		delete $localStorage.userId;
		delete $localStorage.checkout;
		delete $scope.deliveryAddress;
		delete $rootScope.contactNo;

		$rootScope.usernames="";
		$rootScope.username="";
		$rootScope.list=false;

		$location.path('/product');
	};

	$rootScope.emptyFun = function() {
		if($rootScope.productList.length > 0){
			for(var i = 0;i < $rootScope.productList.length; i++){
				$rootScope.productList[i].noOfQuantityInCart = 0;
			}
		}
		if($rootScope.productList.length > 0){
			$rootScope.productDetail.noOfQuantityInCart = 0;
		}
	}

	$scope.shopList = function(){
		var menuJson = angular.toJson({"city":$scope.cityList});		
		$http({
			url: serviceUrl + 'aviate/json/product/getshoplist',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$scope.cityList =[];
			$scope.storeList =[];
			$scope.storeId =[];
			for(var i=0;i<=result.shoplist.length-1;i++){
				$scope.city = result.shoplist[i].city;
				$scope.storename = result.shoplist[i].storeName;
				$scope.storeId = result.shoplist[i].storeId;
				if ($scope.cityList.indexOf($scope.city) == -1) {
					$scope.cityList.push($scope.city);
				}
				if ($scope.storename != null) {
					$scope.storeList.push({
						"storename" : $scope.storename,
						"storeId" : $scope.storeId });
				}
			}
		})
	}

	$scope.searchByCity = function(city){
		var menuJson = angular.toJson({"city":city});		
		$http({
			url: serviceUrl + 'aviate/json/product/getshoplist',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$scope.addresslist =[];
			$scope.storeList =[];
			$scope.storeId =[];
			for(var i=0;i<=result.shoplist.length-1;i++){
				$scope.address = result.shoplist[i].address;
				$scope.storename = result.shoplist[i].storeName;
				$scope.storeId = result.shoplist[i].storeId;
				if ($scope.addresslist.indexOf($scope.address) == -1) {
					$scope.addresslist.push($scope.address);
				}
				if ($scope.storename != null) {
					$scope.storeList.push({
						"storename" : $scope.storename,
						"storeId" : $scope.storeId });
				}
			}
		})
	}

	$scope.searchByAddress = function(area){
		var menuJson = angular.toJson({"area":area,"city":$scope.selectedcity});		
		$http({
			url: serviceUrl + 'aviate/json/product/getshoplist',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$scope.storeList =[];
			$scope.storeId =[];
			for(var i=0;i<=result.shoplist.length-1;i++){
				$scope.storename = result.shoplist[i].storeName;
				$scope.storeId = result.shoplist[i].storeId;
				if ($scope.storename != null) {
					$scope.storeList.push({
						"storename" : $scope.storename,
						"storeId" : $scope.storeId });
				}
			}
		})
	}

	$scope.getTax = function(){
		var menuJson = angular.toJson({"storeId":$rootScope.superMarketId});
		$http({
			url: serviceUrl + 'aviate/json/product/gettaxbyshop',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$rootScope.taxs = result.tax;
			$localStorage.shop.tax = $rootScope.taxs;
			$rootScope.tax = $localStorage.shop.tax;
		})
	}

	$scope.getTaxDetails = function(){
		var menuJson = angular.toJson({"storeId":$rootScope.superMarketId});
		$http({
			url: serviceUrl + 'aviate/json/product/gettaxdetailsbyshop',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$rootScope.taxDetails  = [];
			$rootScope.taxDetails = result.taxDetails;
			$localStorage.shop.taxDetails = $rootScope.taxDetails;
			$rootScope.taxDetails = $localStorage.shop.taxDetails;
		})
	}


	$scope.getShippingCharges = function(){
		var menuJson = angular.toJson({"storeId":$rootScope.superMarketId});
		$http({
			url: serviceUrl + 'aviate/json/product/getshippingcharge',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$rootScope.shippingCharges  = 0;
			$rootScope.shippingCharges = parseInt(result.shippingCharge);
			$localStorage.myCart.shippingCharges = $rootScope.shippingCharges;
			$rootScope.shippingCharges = $localStorage.myCart.shippingCharges;
		})
	}

	$scope.selectedShop = function(storeId,storeName){
		$rootScope.superMarketName = storeName;
		$localStorage.apistoreName = $rootScope.superMarketName;
		$rootScope.selectedStore = $localStorage.apistoreName;
		$rootScope.superMarketId = storeId;
		$localStorage.apistoreId = $rootScope.superMarketId;
		$rootScope.selectedStoreId = $localStorage.apistoreId;
		var menuJson = angular.toJson({"storeId":storeId});		
		$http({
			url: serviceUrl + 'aviate/json/product/getcategories',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$rootScope.category = result.category;
		})
	}

	$scope.selecedSubcategory = function(Subcategory){
		var menuJson = angular.toJson({"productTypeId":Subcategory});		 
		$http({
			url: serviceUrl + 'aviate/json/product/getproducts',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$rootScope.productList =[];
			for(var i=0;i<=result.products.length;i++){
				$scope.productName = result.products[i].productName;	
				$scope.productBrand = result.products[i].brand;	
				$scope.measurement = result.products[i].measurement;
				$scope.avilability = result.products[i].avilability;
				$scope.productId = result.products[i].productId;
				$scope.groupCount = result.products[i].groupCount;
				var selectedQuantity = 0;
				if(!$rootScope.cartItem.length == 0){
					for(var k=0; k<$rootScope.cartItem.length;k++){ 
						if( $rootScope.cartItem[k].product.productId == $scope.productId){
							selectedQuantity = $rootScope.cartItem[k].quantity;
						}
					}
				}
				$scope.productImages =[];
				for(var j=0;j<=result.products[i].productImages.length-1;j++){	
					if(result.products[i].productImages[j].imagePosition == "ORIGINALFRONT"){
						$scope.imageId = result.products[i].productImages[j].imageId;
						$scope.imageType = result.products[i].productImages[j].imageType;
						$scope.imageUrl = result.products[i].productImages[j].imageUrl;
						$scope.productImages.push({
							"imageId" : $scope.imageId,
							"imageType" : $scope.imageType,
							"imageUrl" : $scope.imageUrl})			
					}/*else{
						$scope.productImages.push({
							"imageId" : $scope.imageId,
							"imageType" : $scope.imageType,
							"imageUrl" : $scope.imageUrl = "../images/noproduct1.png"})
					}*/
				}
				$scope.price = result.products[i].productPrice.price;
				$scope.priceId = result.products[i].productPrice.priceId;
				$scope.tax = result.products[i].productPrice.tax;
				$rootScope.productList.push({
					"productName" : $scope.productName,
					"productBrand" : $scope.productBrand,
					"measurement" : $scope.measurement,
					"avilability" : $scope.avilability,
					"productId" : $scope.productId,
					"groupCount" : $scope.groupCount,
					"productImages" : $scope.productImages,
					"price" : $scope.price,
					"priceId" : $scope.priceId,
					"tax" : $scope.tax,
					"selectedQuantity" : selectedQuantity});
				$scope.orderList = "productName";
			}
			$location.path('/');
		})	    
	}

	/*####################### Search box ###########################*/

	$scope.searchBox = function(searchText){
		var menuJson = angular.toJson({"storeId" : $rootScope.superMarketId,"searchString" :searchText});		 
		$http({
			url: 'json/product/searchproducts',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$rootScope.searchProductList =[];
			for(var i=0;i<=result.products.length;i++){
				$scope.productName = result.products[i].productName;	
				$scope.productBrand = result.products[i].brand;	
				$scope.measurement = result.products[i].measurement;
				$scope.avilability = result.products[i].avilability;
				$scope.productId = result.products[i].productId;
				$scope.groupCount = result.products[i].groupCount;
				var selectedQuantity = 0;
				if(!$rootScope.cartItem.length == 0){
					for(var k=0; k<$rootScope.cartItem.length;k++){ 
						if( $rootScope.cartItem[k].product.productId == $scope.productId){
							selectedQuantity = $rootScope.cartItem[k].quantity;
						}
					}
				}
				$scope.productImages =[];
				for(var j=0;j<=result.products[i].productImages.length-1;j++){	
					if(result.products[i].productImages[j].imagePosition == "ORIGINALFRONT"){
						$scope.imageId = result.products[i].productImages[j].imageId;
						$scope.imageType = result.products[i].productImages[j].imageType;
						$scope.imageUrl = result.products[i].productImages[j].imageUrl;
						$scope.productImages.push({
							"imageId" : $scope.imageId,
							"imageType" : $scope.imageType,
							"imageUrl" : $scope.imageUrl})			
					}
				}
				$scope.price = result.products[i].productPrice.price;
				$scope.priceId = result.products[i].productPrice.priceId;
				$scope.tax = result.products[i].productPrice.tax;
				$rootScope.searchProductList.push({
					"productName" : $scope.productName,
					"productBrand" : $scope.productBrand,
					"measurement" : $scope.measurement,
					"avilability" : $scope.avilability,
					"productId" : $scope.productId,
					"groupCount" : $scope.groupCount,
					"productImages" : $scope.productImages,
					"price" : $scope.price,
					"priceId" : $scope.priceId,
					"tax" : $scope.tax,
					"selectedQuantity" : selectedQuantity});
				$scope.orderList = "productName";
			}
		})	 
	}


	/*####################### my cart using Local storage ###########################*/


	$rootScope.productListUpdate = function(product, quantity){
		for(var i = 0; i < $rootScope.productList.length;i++){
			if($rootScope.productList[i].productDetails.productId == product.productDetails.productId)
				$rootScope.productList[i].noOfQuantityInCart = quantity;
		}
	}

	$rootScope.myCartTotalPriceCalculation = function(){
		$rootScope.taxAmount = 0;
		$rootScope.grossAmount = 0;
		$rootScope.cartTotalAmount = 0;
		var totalAmount = 0;
		for(var i=0; i<$rootScope.cartItem.length; i++){
			var subTotal = 0;
			subTotal = $rootScope.cartItem[i].noOfQuantityInCart * $rootScope.cartItem[i].productDetails.productPrice.price;
			$rootScope.cartItem[i].subTotal = subTotal;
			totalAmount += subTotal;
		}

		$localStorage.localStoreCartlist = $rootScope.cartItem;
		$rootScope.cartTotalAmount = totalAmount;
		$localStorage.myCart.cartTotalAmount = $rootScope.cartTotalAmount;
		$rootScope.taxAmount = $rootScope.cartTotalAmount*($rootScope.tax/100);
		$localStorage.myCart.taxAmount = $rootScope.taxAmount;
		$rootScope.grossAmount = $rootScope.taxAmount+$rootScope.cartTotalAmount+$rootScope.shippingCharges;
		$localStorage.myCart.grossAmount =$rootScope.grossAmount;

	}

	$rootScope.addToCartFun = function(product){
		var isExistInCart = false;
		if(product.noOfQuantityInCart > 0){
			if($localStorage.userId){
				$rootScope.addToCartDB(product.productDetails.productId, product.noOfQuantityInCart, product.productDetails.productPrice.price);
				$rootScope.productListUpdate(product, product.noOfQuantityInCart);
			}else{
				for(var i = 0; i<$rootScope.cartItem.length; i++){
					if($rootScope.cartItem[i].productDetails.productId == product.productDetails.productId){
						$rootScope.cartItem[i] = product;
						isExistInCart = true;
					}
				}
				if(!isExistInCart){
					$rootScope.cartItem.push({
						"noOfQuantityInCart":product.noOfQuantityInCart,
						"productDetails":product.productDetails});
				}
				$rootScope.productListUpdate(product, product.noOfQuantityInCart);
			}
		}else if(product.noOfQuantityInCart == 0){
			for(var i = 0; i<$rootScope.cartItem.length; i++){
				if($rootScope.cartItem[i].productDetails.productId == product.productDetails.productId){
					$rootScope.deletefromCart(product, i);
				}
			}
		}
		$rootScope.myCartTotalPriceCalculation();
	}

	$rootScope.deletefromCart = function(product, index){
		if($localStorage.userId){
			$rootScope.removeFromCartDB(product.productDetails.productId);
			$rootScope.productListUpdate(product, 0);
			$rootScope.getCartListFromDB();
		}else{
			$scope.cartItem.splice(index, 1);
			$rootScope.myCartTotalPriceCalculation();
			$rootScope.productListUpdate(product, 0);
		}
	}

	$scope.changeStore =function(){
		$scope.apiresponce = $localStorage.apiresponse;
		$scope.userId = $localStorage.userId;
		localStorage.clear();
		if($localStorage.apiresponse == undefined)
		$localStorage.apiresponse = $scope.apiresponce;
		$localStorage.userId = $scope.userId;
		location.reload();
	}

	$rootScope.cartWindow = true;
	if($rootScope.cartItem.length == 0){
		$rootScope.cartWindow = false;
	}

	/*####################### End of cart using Local storage ###########################*/

	$scope.changeimage = function(imageurl){
		$rootScope.showImageindescription = imageurl;
		console.log($scope.showImageindescription);
	}

	$rootScope.addProductsToList = function(getProductList){
		$rootScope.productList = [];
		var isProductMyList = false;
		for(var i = 0; i<getProductList.products.length ; i++){
			var noOfQuantityInCart = 0;
			var productDetails = getProductList.products[i];
			if(!$rootScope.cartItem.length == 0){
				for(var j=0; j < $rootScope.cartItem.length; j++){ 
					if( $rootScope.cartItem[j].productDetails.productId == productDetails.productId){
						noOfQuantityInCart = $rootScope.cartItem[j].noOfQuantityInCart;
					}
				}
			}
			if(!$rootScope.myLists.length == 0){
				for(var k=0; k < $rootScope.myLists.length; k++){ 
					if( $rootScope.myLists[k].productDetails.productId == productDetails.productId){
						isProductMyList = true;
					}
				}
			}
			$rootScope.productList.push({
				"noOfQuantityInCart" : noOfQuantityInCart,
				"productDetails" : productDetails,
				"isProductMyList" : isProductMyList
			});
		}
		$localStorage.localStorageProductList = $rootScope.productList;
		$rootScope.productList = $localStorage.localStorageProductList;
	}

	$rootScope.productDescriptionFun = function(productObj){
		$rootScope.addListShow = true;
		var showImageindescription = '';
		if($rootScope.myLists){
			for(var i = 0;i<$rootScope.myLists.length;i++)
				if(true){
					$rootScope.addListShow = false;
				}
		}
		for(var i = 0; i < productObj.productDetails.productImages.length; i++){
			if(productObj.productDetails.productImages[i].imagePosition == 'ORIGINALFRONT'){
				showImageindescription = productObj.productDetails.productImages[i].imageUrl;
			}
		}if(productObj.productDetails.productImages.length == 0){
			showImageindescription = 'http://182.74.202.178:8181/aviate/ImageServlet?imageName=1eb789bde1fa452e92';
		}
		productObj.addListShow = $rootScope.addListShow;
		$rootScope.productDetail = productObj;
		$rootScope.showImageindescription = showImageindescription;
		$localStorage.localStorageProductDetail = $rootScope.productDetail;
	}

	$scope.selectedCategory = function(categoryId){
		var menuJson = angular.toJson({"categoryId":categoryId});		 
		$http({
			url: serviceUrl + 'aviate/json/product/getallproductsbycategory',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			$rootScope.addProductsToList(result);
			$rootScope.orderList = "productDetails.productName";
			$location.path('/product');

			var text = $("ul li:hover");
			$rootScope.breadcrumbMenus = [];
			for(var i = 0;i<text.length; i++){
				var a = text[i].children[0];
				$scope.id = a.getAttribute("categoryId");	
				$scope.menu = a.textContent;	
				$rootScope.breadcrumbMenus.push({"menu" : $scope.menu,"id" : $scope.id});
			}
			if($rootScope.breadcrumbMenus.length != 0){
				$localStorage.breadMenu = [];
				$localStorage.breadMenu = $rootScope.breadcrumbMenus;
				$scope.breadcrumbMenu = $localStorage.breadMenu;
				delete $localStorage.productTypeMenu;
				$scope.productTypeMenu = $localStorage.productTypeMenu;
			}
		});
	} 

	$scope.getProductTypes = function(categoryId){
		var menuJson = angular.toJson({"categoryId":categoryId});		 
		$http({
			url: serviceUrl + 'aviate/json/update/product/getproducttypes',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {

			$rootScope.producttypeList = [];
			$rootScope.producttypeList = result.productType;
			


		});
	} 

	$scope.getProductsByProductTypeId = function(Subcategory){

		var menuJson = angular.toJson({"productTypeId":Subcategory});		 
		$http({
			url: serviceUrl + 'aviate/json/product/getproducts',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers) {
			if(result.products.length > 0){
				$rootScope.addProductsToList(result);
				$rootScope.orderList = "productDetails.productName";
				$location.path('/');
			}else{
				$rootScope.productList = [];
				$localStorage.localStorageProductList = [];
				$location.path('/');
			}

			var text = $("ul li:hover");
			$rootScope.breadcrumbMenus = [];
			for(var i = 0;i<text.length-1; i++){
				var a = text[i].children[0];
				$scope.id = a.getAttribute("categoryId");	
				$scope.menu = a.textContent;	
				$rootScope.breadcrumbMenus.push({"menu" : $scope.menu,"id" : $scope.id});
			}
			$scope.productType =  text[text.length-1].children[0].textContent;
			$scope.productTypeId = text[text.length-1].children[0].getAttribute("productTypeId");	
			$localStorage.productTypeMenu = $scope.productType;
			$localStorage.productTypeMenuId = $scope.productTypeId;
			$scope.productTypeMenuId = $localStorage.productTypeMenuId;
			$scope.productTypeMenu = $localStorage.productTypeMenu;
			$localStorage.breadMenu = [];
			$localStorage.breadMenu = $rootScope.breadcrumbMenus;
			$scope.breadcrumbMenu = $localStorage.breadMenu;
		})
	}

	$scope.selectedType = function(categoryId,categoryName){
		var menuJson = angular.toJson({"categoryId":categoryId});		 
		$http({
			url: serviceUrl + 'aviate/json/product/getnextlevelcategorybyid',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers){
			$rootScope.categoryList =[];
			$rootScope.producttypeList=[];

			if(result.categories != null && result.categories.length != 0){
				for(var i=0;i<result.categories.length;i++){
					$scope.categoryId=result.categories[i].categoryId;
					$scope.categoryName=result.categories[i].categoryName;
					$rootScope.categoryList.push({
						"categoryId":$scope.categoryId,
						"categoryName":$scope.categoryName});
				}
			}else if(result.productTypes != null && result.productTypes.length != 0){
				$scope.productTypeId=categoryId;
				$scope.productTypeName= "ALL";
				$scope.producttypeList.push({
					"productTypeId":$scope.productTypeId,
					"productTypeName":$scope.productTypeName});
				for(var j=0;j<=result.productTypes.length-1;j++){
					$scope.productTypeId=result.productTypes[j].productTypeId;
					$scope.productTypeName= result.productTypes[j].productTypeName;
					$scope.producttypeList.push({
						"productTypeId":$scope.productTypeId,
						"productTypeName":$scope.productTypeName});
				}
			}
			//$scope.selectedCategory(categoryId);
		})
	}
	$scope.removeMenu = function(index){
		if(index ==0){
			$localStorage.breadMenu.splice(1,4);
		}
		if(index ==1){
			$localStorage.breadMenu.splice(2,4);
		}
		if(index ==2){
			$localStorage.breadMenu.splice(3,4);
		}

		$localStorage.productTypeMenu = null;
		$scope.productTypeMenu = null;
	}
	/*##################### My cart using database storage ##########################*/

	$rootScope.addToCartDB = function(productId, quantity, subTotal){
		var menuJson = angular.toJson({"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId, "productId" : productId, 
			"price" : subTotal, "quantity" : quantity});		 
		$http({
			url: serviceUrl + 'aviate/json/product/addtocart',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers) {
			if(result.status == 'SUCCESS'){
				$rootScope.cartItem = [];
				$rootScope.getCartListFromDB();
			}
		})
	}

	$rootScope.removeFromCartDB = function(productId){
		var menuJson = angular.toJson({"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId, "productId" : productId});		 
		$http({
			url: serviceUrl + 'aviate/json/product/deletefromcart',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers) {
			if(result.status == 'SUCCESS'){
				$rootScope.getCartListFromDB();
			}
		})
	}

	$rootScope.getCartListFromDB = function(){
		var menuJson = angular.toJson({"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId});		 
		$http({
			url: serviceUrl + 'aviate/json/product/getmycartlist',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers) {
			if(result.status == 'SUCCESS'){
				$rootScope.cartItem = [];
				for(var i = 0; i<result.myCartList.length;i++){
					$rootScope.cartItem.push({
						"productDetails":result.myCartList[i].product,
						"noOfQuantityInCart":result.myCartList[i].quantity});
				}
				$rootScope.myCartTotalPriceCalculation();
			}
		})
	}

	/*##################### End of My cart using database storage ##########################*/

	/*##################### My list ##########################*/
	$scope.addtoMyList = function(productId){
		if($localStorage.userId == null){
			$rootScope.loginWindowOpen();
		}else{
			var menuJson = angular.toJson({"customerId":$localStorage.userId,"productId" : productId,"storeId" : $rootScope.superMarketId});		 
			$http({
				url: serviceUrl + 'aviate/json/customer/addmylist',
				method: 'POST',
				data: menuJson,
				headers: {
					'Content-Type': 'application/json'
				} 
			}).success(function(result, status, headers) {
				if(result.status == 'FAILURE'){
					alert("Status"+result.errorString);
				}
				if(result.status == 'SUCCESS'){
					$rootScope.myList();
					$scope.productDetail.isProductMyList=! $scope.productDetail.isProductMyList;
				}
			})
		}
	}

	$rootScope.myList = function(){
		var menuJson = angular.toJson({"customerId":$localStorage.userId,"storeId":$rootScope.superMarketId});		 
		$http({
			url: serviceUrl + 'aviate/json/customer/getmylist',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers) {
			$rootScope.myLists = [];
			if(result.status=='SUCCESS'){
			for(var i = 0; i<result.customerMyList.length; i++){
				var noOfQuantityInCart = 0;
				for(var j = 0; j<$rootScope.cartItem.length; j++){
					if($rootScope.cartItem[j].productDetails.productId == result.customerMyList[i].productId){
						noOfQuantityInCart = $rootScope.cartItem[j].noOfQuantityInCart;
					}
				}
				$rootScope.myLists.push({
					"noOfQuantityInCart":noOfQuantityInCart,
					"productDetails":result.customerMyList[i].products,
					"myListId":result.customerMyList[i].myListId});
			}
			}
		})
	}

	$scope.deletefromList = function(productId){
		var menuJson = angular.toJson({
			"customerId":$localStorage.userId,
			"productId":productId,
			"storeId":$rootScope.superMarketId
		});		 
		$http({
			url: serviceUrl + 'aviate/json/customer/removemylist',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers) {
			$rootScope.myList();
		})
	}
	$scope.checkouts = function(){
		if($rootScope.cartItem.length > 0){

			if($localStorage.userId){
				$location.path('/address');
			}else{
				$rootScope.loginWindowOpen();
			}
		}
		else{
			alert("Your cart is empty, so you are unable to proccess checkout");
		}
	}
	/*##################### End of My list ##########################*/
});	

aviate.controller("checkoutcontroller",function($rootScope,$localStorage,$scope,$http,$location){

	$rootScope.isSelectedAddress = 'Add-addressfront';
	
	
	var secondSlot=false;

	if(angular.isDefined($localStorage.checkout)){
		$rootScope.contactNo = $localStorage.checkout.contactNo;
		$scope.deliveryAddress = $localStorage.checkout.address;
		$rootScope.deliveryDate = $localStorage.checkout.date;
		$rootScope.deliveryTime = $localStorage.checkout.time;
		$rootScope.totalAmount = $localStorage.checkout.totalAmount;
	}else{
		$localStorage.checkout = {};
		$location.path('/address');
	}

	$scope.selectedAddress = function(address,isSelectedAddress){
		$scope.deliveryAddress = address;
		$localStorage.checkout.address = $scope.deliveryAddress;
		$scope.isSelected = isSelectedAddress;
	}
	$scope.singleAddress = function(list,address){
		if(list.length == 1){
			$scope.selectedAddress(address,0);
		}
		else{
			delete $scope.isSelected;
		}
	}
	$scope.addressToDeliverySlot = function(contactNo,addressId){
		if(contactNo && addressId){
			$localStorage.checkout.contactNo = contactNo;
			$location.path('/delivery');
			next++;
		}else{
			alert('Enter contact number and select delivery address');
		}
	}

	$scope.editAddress = function(address){
		$scope.deliveryAddress = address;
		$location.path('/address');
	}

	$scope.showTime = function(){
		$('#durationExample').timepicker('show');
	}

	$scope.selectedDate = function(){
		
		secondSlot=true;
	
		if($scope.deliveryDate ){

			var time = document.getElementById('durationExample').value;
			var month,date,year;
			if(typeof $scope.deliveryDate=='string'){
				month=$scope.deliveryDate.slice(3,5);
				date=$scope.deliveryDate.slice(0,2);
				year=$scope.deliveryDate.slice(6,10);
			}else{
				month = ('0'+($scope.deliveryDate.getMonth()+1)).slice(-2);
				date=('0'+($scope.deliveryDate.getDate())).slice(-2);
				year=$scope.deliveryDate.getFullYear();
			}
			if(time){
				$localStorage.checkout.date = date+"-"+ month +'-'+year;
				$localStorage.checkout.time = time;
				$scope.deliveryTime = $localStorage.checkout.time;
				var a= true;/*confirm("delivery date : "+$scope.deliveryDate+" and time : "+time)*/
				if($localStorage.checkout.date == $scope.minValid)
				{
				var dTime,fTime;
				if(time.match("pm"))
					dTime = (parseInt(time.split(':')[0])%12)+12+":"+(time.split(':')[1]);
				else
					dTime = ('0'+time.split(':')[0]).slice(-2)+":"+(time.split(':')[1]);
				if(formatAMPM(new Date()).match("pm"))
					fTime = parseInt(formatAMPM(new Date()).split(':')[0])+12+":"+(formatAMPM(new Date()).split(':')[1]);
				else
					fTime = ('0'+formatAMPM(new Date()).split(':')[0]).slice(-2)+":"+(formatAMPM(new Date()).split(':')[1]);
				if(dTime.replace(":", "").substring(0, 4) < fTime.replace(":", "").substring(0, 4))
					{
					alert('Delivery Time is invalid');
					secondSlot =false;
					a= false;
					}
				}
				if(a){
					$location.path('/review_confirm');
				}
			}
		}else{
			alert('Enter delivery date and time');
		}
	}

	/*===========date control=========*/
	$scope.today = function(){
		$rootScope.deliveryDate = new Date();
		$rootScope.deliveryTime = "10:00am";
	};
	
	if(!$rootScope.deliveryDate){	
		$scope.today();
	}
	
	function formatAMPM(date) {
	  var hours = date.getHours();
	  var minutes;
	  if(date.getMinutes() < 30){
		  minutes='30';
	  }
	  else{
		  minutes='00';
		  hours = hours+1;
	  }
	  var ampm = hours >= 12 ? 'pm' : 'am';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  //minutes = minutes < 10 ? '0'+minutes : minutes;
	  var strTime = ('0'+(hours)).slice(-2) + ':' + minutes + '' + ampm;
	  return strTime;
	}
	
	$scope.toggleMin = function() {
		var month = ('0'+(new Date().getMonth()+1)).slice(-2);
		var date=('0'+(new Date().getDate())).slice(-2);
		var year=new Date().getFullYear();
		$scope.minValid = date+"-"+month+"-"+year;
		 $scope.min = year+"-"+month+"-"+date;
		
	    $scope.minDate = $scope.min;
	  };
	$scope.toggleMin();
	if($localStorage.checkout.date == $scope.minValid)
		{
		var dTime,fTime;
		if($rootScope.deliveryTime.match("pm"))
			dTime = parseInt($rootScope.deliveryTime.split(':')[0])+12+":"+($rootScope.deliveryTime.split(':')[1]);
		else
			dTime = ('0'+$rootScope.deliveryTime.split(':')[0]).slice(-2)+":"+($rootScope.deliveryTime.split(':')[1]);
		if(formatAMPM(new Date()).match("pm"))
			fTime = (parseInt(formatAMPM(new Date()).split(':')[0])%12)+12+":"+(formatAMPM(new Date()).split(':')[1]);
		else
			fTime = ('0'+formatAMPM(new Date()).split(':')[0]).slice(-2)+":"+(formatAMPM(new Date()).split(':')[1]);
		if(dTime.replace(":", "").substring(0, 4) < fTime.replace(":", "").substring(0, 4) && secondSlot)
			{
			alert('Delivery Time is invalid');
			}
	}
	
	$scope.clear = function (){
		$scope.deliveryDate = null;
	};
	/*$scope.disabled = function(date, mode) {
		return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	};*/
	$scope.open = function($event){
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};
	$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1
	};
	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd-MM-yyyy', 'shortDate'];
	$scope.format = $scope.formats[2];

	/*===========End date control=========*/

	$scope.addAddress=function(){
		$rootScope.closeButton = 'close';
		var menuJson = angular.toJson({
			"addressId":$scope.deliveryAddress.addressId?$scope.deliveryAddress.addressId:"",
					"firstName":$scope.deliveryAddress.firstName,
					"lastName":$scope.deliveryAddress.lastName,
					"addressSpecificInstruction":$scope.deliveryAddress.addressSpecificInstruction,
					"customerId":$localStorage.userId,
					"address":$scope.deliveryAddress.address,
					"city":$scope.deliveryAddress.city,
					"state":$scope.deliveryAddress.state,
					"country":$scope.deliveryAddress.country,
					"postalCode":$scope.deliveryAddress.postalCode});
		$http({
			url: serviceUrl + 'aviate/json/checkout/updateaddress',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers){
			if(result.status=="SUCCESS"){
				alert("Address added successfully");
				$scope.getAddressList();
				$scope.deliveryAddress = {};
				$('#myModal').modal('hide');
			}else{
				alert("Address is invalid");
			}	
		})
	}

	$scope.getAddressList=function(){
		var menuJson = angular.toJson({
			"customerId":$localStorage.userId});
		$http({
			url: serviceUrl + 'aviate/json/checkout/getlistofaddress',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers){
			$rootScope.customerAddressList = [];
			$rootScope.customerAddressList = result.addressList;
		})
	}

	$scope.deleteAddress=function(){
		var menuJson = angular.toJson({
			"addressId":$scope.deliveryAddress.addressId});
		$http({
			url: serviceUrl + 'aviate/json/checkout/deleteaddress',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers){
			if(result.status=="SUCCESS")
			{
				alert("Addresss deleted successfully");
				$scope.getAddressList();
				$scope.deliveryAddress = {};
				$localStorage.checkout.address = {};
				delete $scope.isSelected;
			}
			else{
				alert("Address deletion Fails");
			}	
		})
	}
	$scope.conformOrder = function(){

		var menuJson = angular.toJson({
			"customerId":$localStorage.userId,
			"addressId":$scope.deliveryAddress.addressId,
			"storeId":$rootScope.superMarketId,
			"deliveryDate":$scope.deliveryDate,
			"deliveryTime":$rootScope.deliveryTime,
			"contactNo":$rootScope.contactNo,
			"totalAmount":$rootScope.cartTotalAmount,
			"orderGrossAmount":$rootScope.grossAmount,
			"totalTaxAmount":$rootScope.taxAmount,
			"shippingCharge":$rootScope.shippingCharges
		});
		$http({
			url: serviceUrl + 'aviate/json/checkout/conformorder',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			} 
		}).success(function(result, status, headers){
			$scope.orderId = result.orderId;
			$scope.$apply();
			$scope.payment();
		})
	}

	$scope.payment = function() {
		$scope.addresslist =[];
		var paymentJSON = angular.toJson
		({
			"amount":$rootScope.grossAmount
		});
		$http({
			url: "http://182.74.202.178:8181/aviate/json/get/payment",
			method: 'POST',
			data : paymentJSON,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status, headers){
			$scope.timeStamp = result.timestamp;
			$scope.hash = result.hashMessage;
			$scope.TOTAL = $rootScope.grossAmount;
			$scope.shipping = ($scope.cartTotalAmount * .05);
			$scope.$apply();
			document.forms["FirstData"].submit();
		});
	}
})

.directive('validNumber', function() {

	return {
		require : '?ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) {
				return;
			}

			ngModelCtrl.$parsers.push(function(val) {
				var clean = val.replace(/[^0-9]+/g, '');
				if (val !== clean) {
					ngModelCtrl.$setViewValue(clean);
					ngModelCtrl.$render();
				}
				return clean;
			});

			element.bind('keypress', function(event) {
				if (event.keyCode === 32) {
					event.preventDefault();
				}
			});
		}
	};
})

.directive('modal', function () {
    return {
      template: '<div class="modal fade">' + 
          '<div class="modal-dialog">' + 
            '<div class="modal-content">' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                '<h4 class="modal-title">{{ title }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude></div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });
