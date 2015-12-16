angular.module('aviateAdmin.controllers')
.controller("productoffercontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices','myConfig','$mdDialog','ProductOfferServices',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices,myConfig, $mdDialog,ProductOfferServices) {
	
	$scope.getProductOfferList = function () {
		var productOffer = {};
		if($rootScope.user.role == "STOREADMIN"){
			productOffer.merchantVo = {
					"merchantId":$rootScope.user.merchantId
			};
		}else if($rootScope.user.role == "STOREADMIN"){
			productOffer.storevo = {
					"storeId":$rootScope.user.storeId
			};
		}
		ProductOfferServices.getProductOffer(productOffer).then(function(data) {
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
}
]);