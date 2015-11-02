angular.module('aviateAdmin.controllers')
.controller("productCategoryListController", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService',
                                              function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService) {


	/*$scope.query = {
			limit: 5,
			page: 1
	};*/

	$scope.count = 3;
	$scope.srch = true;



	$scope.isCollapsed = true;


	$scope.isVissible = false;

	$scope.productCategory = function (){

		$localStorage.products ={};
		$localStorage.products.merchantId = $rootScope.user.merchantId;
		console.log("products:"+$localStorage.products);
		$scope.product = $localStorage.products; 
		ProductService.getProductCategory($scope.product).then(function(data) {
			$scope.products= data;
			$scope.showPagination = true;
			$scope.productCategorySelection=$scope.products.category; 
			$scope.totalJson = true;
			console.log($scope.productCategorySelection);

		})

	}	

	$scope.getProductTypeByCategoryId = function (categoryId,index){

		/*var cat = categoryList.category[index].category;
		var lastindex = categoryList.category[index].category.length-1;
		var categoryId;
		var productcatId = categoryList.categoryId;
		while(cat.length!=0){
			categoryId = cat[lastindex].categoryId;
			cat = cat[lastindex].category;
			lastindex = cat.length-1;
		}*/
		$localStorage.totalProductType = true;
		$localStorage.categoryId=categoryId;
		/*$localStorage.productcatId =productcatId;*/
		$state.go('app.producttype');		
	}

	$scope.getProductDetails= function (productType){

		$localStorage.totalProductType = false;
		$localStorage.products.productTypeName = productType.productTypeName;
		$localStorage.products.productTypeId = productType.productTypeId;
		$localStorage.products.categoryId = productType.categoryId;
		$state.go('app.producttype');

	}



	$scope.toggle1 = function(data) {
		$scope.isCollapsed = !$scope.isCollapsed
	}

}
]);