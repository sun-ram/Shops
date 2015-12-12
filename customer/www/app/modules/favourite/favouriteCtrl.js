angular.module('aviate.controllers')
.controller("favouriteCtrl", ['$scope','$http','$stateParams','$rootScope','$state', 'FavouriteServices','MyCartServices','MyCartFactory','$mdDialog',
                              function($scope,$http,$stateParams, $rootScope,$state, FavouriteServices,MyCartServices,MyCartFactory,$mdDialog) {

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
				$state.go('app.checkout');
			});

		});	
		
	}
	
	$rootScope.conformFavourite = function(ev){
		if($rootScope.myCart.cartItem.length == 0){
			$scope.favouriteToCheckout(ev);
		}else{
		$rootScope.favouritePopup(ev);
		}
		
	}
	
	$rootScope.favouritePopup = function(ev){
		$mdDialog.show({
			templateUrl: 'app/modules/favourite/favouriteCheckout.html',
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true,
			controller: function($scope, AuthServices, toastr, CONSTANT,ipCookie){
				$scope.title = 'SIGN IN';
				
				$scope.favouriteToCheckout = function(){
					$scope.favourite ={};
					$scope.favourite.customerId = $rootScope.user.userId;
					$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
					$scope.favourite.storeId = $rootScope.store.storeId;
					$scope.favourite.favouriteId = ev;
					FavouriteServices.favouriteToCheckout($scope.favourite).then(function(data){
						MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
							MyCartFactory.myCartTotalPriceCalculation();
							$state.go('app.checkout');
							$scope.cancel();
						});

					});	
					
				}
				
				$scope.addFavouriteToCart = function(){

					$scope.favourite ={};
					$scope.favourite.customerId = $rootScope.user.userId;
					$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
					$scope.favourite.storeId = $rootScope.store.storeId;
					$scope.favourite.favouriteId = ev;
					FavouriteServices.addFavouriteToCart($scope.favourite).then(function(data){
						MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
							MyCartFactory.myCartTotalPriceCalculation();
							$state.go('app.checkout');
							$scope.cancel();
						});

					});	
				}
				
				$scope.cancel = function() {
					$mdDialog.cancel();
				};

			}
		})
		.then(function() {

		}, function() {

		});
	}
}]);
