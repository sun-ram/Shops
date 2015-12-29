angular.module('aviateAdmin.controllers')
.controller("productoffercontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices','myConfig','$mdDialog','ProductOfferServices','$stateParams','$mdDialog','StoreServices',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices,myConfig, $mdDialog,ProductOfferServices,$stateParams,$mdDialog,StoreServices) {
	
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
	
	$scope.selection=[];
	  // toggle selection for a given employee by name
	  $scope.toggleSelection = function toggleSelection(storeId) {
	     var idx = $scope.selection.indexOf(storeId);
	     // is currently selected
	     if (idx > -1) {
	       $scope.selection.splice(idx, 1);
	     }else {
	         $scope.selection.push(storeId);
	       }

	  }  
	  
	$scope.getProductOfferList = function () {
		$scope.productOffer ={};
		if($rootScope.user.role == "MERCHANTADMIN"){
			$scope.productOffer.merchantVo = {
					"merchantId":$rootScope.user.merchantId
			};
		}else if($rootScope.user.role == "STOREADMIN"){
			$scope.productOffer.store = {
					"storeId":$rootScope.user.storeId
			};
		}
		ProductOfferServices.getProductOffer($scope.productOffer).then(function(data) {
			$scope.productOfferList = data;
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
	
	$scope.getStores = function(){
		$scope.merchant ={};
		$scope.merchant.merchantId = $rootScope.user.merchantId;
		
		StoreServices.getStore($scope.merchant).then(function(data) {
			$scope.storeList = data;
		})
	}
	
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
		$scope.productOffer.userId = $rootScope.user.userName;
		$scope.productOffer.storeList =[];

		if($rootScope.user.storeId){
			$scope.productOffer.storeList.push({"storeId":$rootScope.user.storeId});
		}else{
		for(var i=0;i<$scope.selection.length;i++){
			$scope.productOffer.storeList.push({"storeId":$scope.selection[i]});
			}
		}
		if($scope.productOffer.storeList.length!=0){
			ProductOfferServices.addProductOffer($scope.productOffer).then(function(data) {
				$scope.results = data.productOfferList;
				$localStorage.productOfferList = data.productOfferList;
			    $state.go('app.productofferline',{'offerId':$scope.results[0].productOfferId});
			})
		}else{
			toastr.error("Select Any Store");
		}
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
		$scope.productOfferLine.userId = $rootScope.user.userName;
		if($localStorage.productOfferList==null){
			$scope.productOfferLine.productOfferList=[];
			$scope.productOfferLine.productOfferList.push($scope.productOfferLine.productOfferVo);
		}else{
			$scope.productOfferLine.productOfferList=$localStorage.productOfferList;
		}
		
		ProductOfferServices.addProductOfferLine($scope.productOfferLine).then(function(data) {
			$scope.results = data;
			$localStorage.productOfferList=null;
			$scope.getProductOfferLineList(); 

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
	   if(productOffer!=undefined && !(_.isDate(productOffer.startTime))){
		   productOffer.startTime = timeFormat(productOffer.startTime);
		   productOffer.endTime = timeFormat(productOffer.endTime);
		   ProductOfferServices.setProductOfferObj(productOffer);
		   $state.go('app.newproductoffer');
	   }else{
		   ProductOfferServices.setProductOfferObj(productOffer);
		   $state.go('app.newproductoffer');
	   }
   };
   
   var timeFormat = function(time){ 
	   	 var hh = time.slice(0,2);
		 var mm = time.slice(3,5);
		 var ss = time.slice(6,7);
		 $scope.newTime = new Date(1970, 0, 1, hh, mm, 0);
		 console.log($scope.startTime);
		 return $scope.newTime;
	   }
   
   $scope.updateProductOffer = function(productOffer){
		$scope.productId = productOffer.productVo.productId;
		$scope.productOffer.merchantVo ={};
		$scope.productOffer.productVo ={};
		$scope.productOffer.productVo.productId = $scope.productId;
		$scope.productOffer.merchantVo.merchantId = $rootScope.user.merchantId;
		$scope.productOffer.userId = $rootScope.user.userName;
		if($scope.productOffer.fromDate!=null){
		$scope.productOffer.fromDate = $filter('date')(new Date($scope.productOffer.fromDate), 'yyyy-MM-dd');
		$scope.productOffer.todate = $filter('date')(new Date($scope.productOffer.todate), 'yyyy-MM-dd');
		}
	
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
		$scope.productOfferLine.userId = $rootScope.user.userName;
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
	
	 $scope.validateTime = function(){
		 if($scope.productOffer.startTime!=null && $scope.productOffer.endTime!=null && 
				 $scope.productOffer.startTime.getHours()==$scope.productOffer.endTime.getHours()){
			 $scope.endTime = true;
			 $scope.productOfferForm.$invalid=true;
		 }else{
			 $scope.endTime = false;
		 }
	 }
   
}
]);
