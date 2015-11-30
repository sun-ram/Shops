angular.module('aviate.controllers')
.controller("favouriteCtrl", ['$scope','$http','$stateParams','ProductService', '$rootScope','$state', 'CheckOutServices','$localStorage','$stateParams','MyCartServices','MyCartFactory',
                                   function($scope,$http,$stateParams,ProductService, $rootScope,$state, CheckOutServices,$localStorage,$stateParams,MyCartServices,MyCartFactory) {
	
	
	
	$scope.addFavourite = function(){
		
		$scope.favourite = {};
		$scope.favourite.name = $scope.favouritName;
		$scope.favourite.salesOrderId = $stateParams.orderNo;
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		
		CheckOutServices.addFavourite($scope.favourite).then(function(data){
			
		});	
	}
	
   	$scope.getFavourite = function(){
   		$scope.favourite ={};
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		CheckOutServices.getFavourite($scope.favourite).then(function(data){
			$scope.favouriteList = data.favourites;
		});	
   	};
   	
   	$scope.addFavouriteToCart = function(salesOrderId){
   		
   		$scope.favourite ={};
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		$scope.favourite.salesOrderId = salesOrderId;
		CheckOutServices.addFavouriteToCart($scope.favourite).then(function(data){
			MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
				MyCartFactory.myCartTotalPriceCalculation();
				console.log('get MyCartlist success in Main Nav');
				$state.go('app.cart');
			});
			
		});	
   	}
	
}]);
