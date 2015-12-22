angular.module('aviateAdmin.controllers').controller("productDetailController", 
		['$scope', '$http', '$rootScope','$localStorage','$state','$filter','$window', 'ngTableParams','ProductService',
		 function($scope, $http, $rootScope, $localStorage, $state, $filter,$window, ngTableParams, ProductService) {

		$scope.getProductDetails = function (){
			$scope.productDetail = ProductService.getProductObj();
		};

		$scope.editproduct = function(products){
			$localStorage.product = products;
			$state.go('app.addproduct');

		}
		$scope.checkgroupCount = function(productDetail)

		{
			if(productDetail.groupCount)
				$scope.groupCount=true;
			else
				$scope.groupCount=false;
		}
}]);