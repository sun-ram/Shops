angular.module('aviate.controllers')
.controller("favouriteCtrl", ['$scope','$http','$stateParams','$rootScope','$state', 'FavouriteServices','MyCartServices','MyCartFactory',
                              function($scope,$http,$stateParams, $rootScope,$state, FavouriteServices,MyCartServices,MyCartFactory) {

	$scope.addFavourite = function(){
		$scope.favourite = {};
		$scope.favourite.name = $scope.favouritName;
		$scope.favourite.salesOrderId = $stateParams.salesOrderId;
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		FavouriteServices.addFavourite($scope.favourite).then(function(data){
			$scope.getFavourite();
			$state.go('app.home');
		});	
	}

	$scope.addFavouriteToCart = function(salesOrderId){

		$scope.favourite ={};
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		$scope.favourite.salesOrderId = salesOrderId;
		FavouriteServices.addFavouriteToCart($scope.favourite).then(function(data){
			MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
				MyCartFactory.myCartTotalPriceCalculation();
				$state.go('app.cart');
			});

		});	
	}
	
	/*$scope.itemPopupFadeOut = function (){
		  $('.wrapper').addClass('active');
		  $('.item').removeClass('active').removeClass('margin');
		  $('.close').fadeOut(300);
		  $(this).addClass('active').addClass('margin');
		  $('.close', this).delay(700).fadeIn(300);
		};

		$scope.itemPopupFadeIn = function (event){
		  event.stopPropagation();
		  $('.wrapper').removeClass('active');
		  $('.item').removeClass('active').removeClass('margin');
		  $('.close').fadeOut(300);
		};
*/
}]);
