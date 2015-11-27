angular.module('aviate.controllers')
.controller("favouriteCtrl", ['$scope','$http','$stateParams','ProductService', '$rootScope','$state', 'CheckOutServices','$localStorage','$stateParams',
                                   function($scope,$http,$stateParams,ProductService, $rootScope,$state, CheckOutServices,$localStorage,$stateParams) {
	
	
	
	$scope.addFavourite = function(){
		
		$scope.favourite = {};
		$scope.favourite.name = $scope.favouritName;
		$scope.favourite.salesOrderId = $stateParams.orderNo;
		$scope.favourite.customerId = $rootScope.user.userId;
		$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
		$scope.favourite.storeId = $rootScope.store.storeId;
		
		CheckOutServices.addFavourite($scope.favourite).then(function(data){
			$scope.productDetails.isProductMyList = true;
		});	
	}
	
}]);
