angular.module('aviateAdmin.controllers')
.controller("productoffercontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices','myConfig','$mdDialog','ProductOfferServices','$stateParams','$mdDialog',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices,myConfig, $mdDialog,ProductOfferServices,$stateParams,$mdDialog) {
	
	$scope.productOffer ={};
	$scope.productOffer =ProductOfferServices.getProductOfferObj();
	$scope.productOfferLine = ProductOfferServices.getProductOfferLineObj();
	$scope.offerId = $stateParams.offerId;
	$scope.addNew=false;
	$scope.lineEdit=true;

	$scope.query = {
			limit: 5,
			page: 1
	};
	
	$scope.count = 3;
	$scope.srch = true;
	
	var myDate = new Date();

	var previousDay = new Date(myDate);

	previousDay.setDate(myDate.getDate()-1);
	
	$scope.minDate = previousDay;
	
	$scope.getProductOfferList = function () {
		$scope.productOffer ={};
		if($rootScope.user.role == "STOREADMIN"){
			$scope.productOffer.merchantVo = {
					"merchantId":$rootScope.user.merchantId
			};
		}else if($rootScope.user.role == "STOREADMIN"){
			$scope.productOffer.storevo = {
					"storeId":$rootScope.user.storeId
			};
		}
		ProductOfferServices.getProductOffer($scope.productOffer).then(function(data) {
			$scope.productOfferList = data;
			$localStorage.productOfferList = data;
		});
	};
	
	$scope.getAllProductList = function() {
		$scope.product = {};
		$scope.product.merchant = {
				"merchantId":$rootScope.user.merchantId
		}
		ProductService.getAllProductList($scope.product).then(function(data) {
			$scope.productList = data.products;
		})
	};
	
	$scope.redirectOfferLine = function(productOfferLine) {
		//ProductOfferServices.setProductOfferLineObj(productOfferLine);
		$state.go('app.productofferline',{'offerId':productOfferLine.productOfferId});
	};
	
	$scope.addOfferLine = function() {
		//ProductOfferServices.setProductOfferObj(productOfferLine);
		$scope.productOfferLine ={};
		$state.go('app.newproductofferline',{'offerId':$stateParams.offerId});
	};
	
	$scope.saveProductOffer = function(productOffer){
		$scope.productId = productOffer.productVo.productId;
		$scope.productOffer.merchantVo = {};
		$scope.productOffer.productVo = {};
		$scope.productOffer.productVo.productId = $scope.productId;
		$scope.productOffer.merchantVo.merchantId = $rootScope.user.merchantId;
		ProductOfferServices.addProductOffer($scope.productOffer).then(function(data) {
			$scope.results = data.productOfferList;
		    $state.go('app.productofferline',{'offerId':$scope.results[0].productOfferId});
		})

    };
    
    $scope.saveProductOfferLine = function(productOfferLine){
		$scope.lineEdit=false;
    	$scope.productOfferLine={};
    	$scope.productOfferLine.productVo={};
		$scope.productOfferLine.productVo.productId = productOfferLine.productVo.productId;
    	$scope.productOfferLine.discountAmount=productOfferLine.discountAmount;
    	$scope.productOfferLine.discountPercentage=productOfferLine.discountPercentage;
		$scope.productOfferLine.productOfferVo ={};
		$scope.productOfferLine.productOfferVo.productOfferId = $stateParams.offerId;
		ProductOfferServices.addProductOfferLine($scope.productOfferLine).then(function(data) {
			$scope.results = data;
			$scope.getProductOfferLineList(); 
			//$state.go('app.productofferline',{'offerId':$stateParams.offerId});

		})
   };
   
   $scope.deleteProductOffer = function(productOfferId){
       $scope.productOffer ={};
       $scope.productOffer.productOfferId=productOfferId;
	   ProductOfferServices.deleteProductOffer($scope.productOffer).then(function(data) {
			$scope.results = data;
			$scope.getProductOfferList();
		})
   };
   
   $scope.editProductOffer = function(productOffer){
	   ProductOfferServices.setProductOfferObj(productOffer);
	   $state.go('app.newproductoffer');
   };
   
   $scope.updateProductOffer = function(productOffer){
		$scope.productId = productOffer.productVo.productId;
		$scope.productOffer.merchantVo ={};
		$scope.productOffer.productVo ={};
		$scope.productOffer.productVo.productId = $scope.productId;
		$scope.productOffer.merchantVo.merchantId = $rootScope.user.merchantId;
		ProductOfferServices.updateProductOffer($scope.productOffer).then(function(data) {
			$scope.results = data;
		    $state.go('app.productoffer');
		})

   };
   
   $scope.editProductOfferLine = function(productOfferLine){
	   ProductOfferServices.setProductOfferLineObj(productOfferLine);
	   $state.go('app.newproductofferline',{'offerId':$stateParams.offerId});
   }
   
   $scope.getProductOfferId = function(){
	   $scope.productOfferLine = {};
	   $scope.productOfferLine = ProductOfferServices.getProductOfferObj();
   }
   
   $scope.deleteProductOfferLine = function(productOfferLineId){
	   $scope.lineEdit=false;
       $scope.productOfferLine ={};
       $scope.productOfferLine.productOfferLineId=productOfferLineId;
	   ProductOfferServices.deleteProductOfferLine($scope.productOfferLine).then(function(data) {
			$scope.results = data;
			$scope.getProductOfferLineList();
		})
   };
   
   $scope.getProductOfferLineList = function () {
	   $scope.lineEdit=false;
		$scope.productOffervo ={};
		$scope.productOffervo.productOfferId=$stateParams.offerId;
		ProductOfferServices.getProductOfferLine($scope.productOffervo).then(function(data) {
			$scope.productOfferLine = data;
			$scope.lineEdit=true;
			$localStorage.productOfferList = data;
		});
	};
	$scope.updateProductOfferLine = function(productOffer){
		$scope.lineEdit=false;
		$scope.productOfferLine={};
		$scope.productOfferLine.productVo ={};
		$scope.productOfferLine.productOfferLineId=productOffer.productOfferLineId;
		$scope.productOfferLine.discountAmount=productOffer.discountAmount;
		$scope.productOfferLine.discountPercentage=productOffer.discountPercentage;
		$scope.productOfferLine.productVo.productId = productOffer.productVo.productId;
		ProductOfferServices.updateProductOfferLine($scope.productOfferLine).then(function(data) {
			$scope.results = data;
			$scope.getProductOfferLineList(); 
		//	$state.go('app.productofferline',{'offerId':$stateParams.offerId});
		});
   };
   
   $scope.offerDetails = function(offer){
	   ProductOfferServices.setProductOfferObj(offer);
	   $state.go('app.productofferdetails');

	};
	
	$scope.getOfferDetails=function(){
		$scope.offerDetails = ProductOfferServices.getProductOfferObj();
	};
   
}
]);