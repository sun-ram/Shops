angular.module('aviateAdmin.controllers')
.controller("productoffercontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices','myConfig','$mdDialog','ProductOfferServices','$stateParams',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices,myConfig, $mdDialog,ProductOfferServices,$stateParams) {
	
	$scope.productOffer ={};
	$scope.productOffer =ProductOfferServices.getProductOfferObj();
	$scope.productOfferLine = ProductOfferServices.getProductOfferLineObj();
	$scope.offerId = $stateParams.offerId;
	
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
		$scope.productOffer.merchantVo = {};
		$scope.productOffer.productVo = {};
		$scope.productOffer.productVo = $scope.product;
		$scope.productOffer.merchantVo.merchantId = $rootScope.user.merchantId;
		ProductOfferServices.addProductOffer($scope.productOffer).then(function(data) {
			$scope.results = data;
		    $state.go('app.productoffer');
		})

    };
    
    $scope.saveProductOfferLine = function(productOfferLine){
    	$scope.productOfferLine={};
    	$scope.productOfferLine.productVo ={};
		$scope.productOfferLine.productVo = $scope.product;
    	$scope.productOfferLine.discountAmount=productOfferLine.discountAmount;
    	$scope.productOfferLine.discountPercentage=productOfferLine.discountPercentage;
		$scope.productOfferLine.productOfferVo ={};
		$scope.productOfferLine.productOfferVo.productOfferId = $stateParams.offerId;
		ProductOfferServices.addProductOfferLine($scope.productOfferLine).then(function(data) {
			$scope.results = data;
			$state.go('app.productofferline',{'offerId':$stateParams.offerId});

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
		$scope.productOffer.merchantVo ={};
		$scope.productOffer.productVo ={};
		$scope.productOffer.productVo = $scope.product;
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
       $scope.productOfferLine ={};
       $scope.productOfferLine.productOfferLineId=productOfferLineId;
	   ProductOfferServices.deleteProductOfferLine($scope.productOfferLine).then(function(data) {
			$scope.results = data;
			$scope.getProductOfferLineList();
		})
   };
   
   $scope.getProductOfferLineList = function () {
		$scope.productOffervo ={};
		$scope.productOffervo.productOfferId=$stateParams.offerId;
		ProductOfferServices.getProductOfferLine($scope.productOffervo).then(function(data) {
			$scope.productOfferLine = data;
			$localStorage.productOfferList = data;
		});
	};
	
	$scope.updateProductOfferLine = function(productOffer){
		$scope.productOfferLine={};
		$scope.productOfferLine.productVo ={};
		$scope.productOfferLine.productOfferLineId=productOffer.productOfferLineId;
		$scope.productOfferLine.discountAmount=productOffer.discountAmount;
		$scope.productOfferLine.discountPercentage=productOffer.discountPercentage;
		$scope.productOfferLine.productVo = $scope.product;
		ProductOfferServices.updateProductOfferLine($scope.productOfferLine).then(function(data) {
			$scope.results = data;
			$state.go('app.productofferline',{'offerId':$stateParams.offerId});
		})
   };
	
}
]);