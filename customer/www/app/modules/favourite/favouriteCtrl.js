angular.module('aviate.controllers')
.controller("favouriteCtrl", ['$scope','$http','$stateParams','$rootScope','$state', 'FavouriteServices','MyCartServices','MyCartFactory',
                              function($scope,$http,$stateParams, $rootScope,$state, FavouriteServices,MyCartServices,MyCartFactory) {

	$scope.addFavourite = function(){
		$scope.favourite = {};
		$scope.favourite.name = $scope.favouriteName;
		$scope.favourite.salesOrderId = $stateParams.salesOrderId;
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		FavouriteServices.addFavourite($scope.favourite).then(function(data){
			$scope.getFavourite();
			$state.go('app.home');
		});	
	}

	$scope.addFavouriteToCart = function(favouriteId){

		$scope.favourite ={};
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		$scope.favourite.favouriteId = favouriteId;
		FavouriteServices.addFavouriteToCart($scope.favourite).then(function(data){
			MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
				MyCartFactory.myCartTotalPriceCalculation();
				$state.go('app.cart');
			});

		});	
	}
	
	$scope.viewProductInFavourite = function(){
		
		$scope.favourite ={};
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		$scope.favourite.salesOrderId = salesOrderId;
		FavouriteServices.viewProductInFavourite($scope.favourite).then(function(data){
			$scope.productInFavourite = data;

		});	
	}	

	$scope.favouriteToCheckout = function(favouriteId){
		$scope.favourite ={};
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		$scope.favourite.favouriteId = favouriteId;
		FavouriteServices.favouriteToCheckout($scope.favourite).then(function(data){
			MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
				MyCartFactory.myCartTotalPriceCalculation();
				$state.go('app.cart');
			});

		});	
		
	}
}]);
