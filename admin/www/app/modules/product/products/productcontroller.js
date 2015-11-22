angular.module('aviateAdmin.controllers')
.controller("productcontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices) {
	/*==========Get All Product Details==========*/



	/*$scope.query = {
			limit: 5,
			page: 1
	};*/

	$scope.count = 3;
	$scope.srch = true;


	$scope.getAllProductList = function() {
		
		$scope.product = {};
		$scope.product.merchant = {
				"merchantId":$rootScope.user.merchantId
		}
		ProductService.getAllProductList($scope.product).then(function(data) {

			$scope.productList = data.productList;

			console.log($scope.productList);

		})
	}

	$scope.updateProduct = function(product) {
		$scope.product = product;
		$scope.product.merchant = {};
		$scope.product.merchant.merchantId = $rootScope.user.merchantId;
		ProductService.addProduct($scope.product).then(function(data) {
			$scope.product = $localStorage.product;

			console.log($localStorage.products);
				toastr.success("product details have been updated successfully!!!");
				$localStorage.product={};
				$state.go("app.products");
				$scope.showInLineEdit = null;

		})
	}



	$scope.getMeasurementUnit = function (){

		$scope.productUnit ={};
		ProductService.getMeasurementUnit($scope.productUnit).then(function(data) {
			$scope.uom = data.uom;
		})
	}

	$scope.addProductRedirect = function (){

		$localStorage.product={};
		$scope.addshow = true;	
		$state.go('app.addproduct');


	}
	$scope.image={};
	$scope.image.originalFrontImage;

	$scope.addproduct = function() {
		
		if ($scope.product.productId == null && ($scope.image.originalFrontImage == undefined || $scope.image.originalFrontImage=="")) {
			toastr.warning("Please select Original Front Image");
			return;
		} 
		else {
			if($scope.image.originalFrontImage){
			/* Orginal Front Image base64 Start*/
			$scope.product.image ={};
			$scope.product.image.image =$scope.image.originalFrontImage.split(",")[1];

			/* Orginal Front Image base64 End*/

			/* Orginal Front Image Type Start*/
			$scope.product.image.type = $scope.product.image ? ($scope.image.originalFrontImage.substring(11).split(";")[0]) : "",
					$scope.product.image.name ="OriginalImage";
					/* Orginal Front Image Type End*/
			}
					$scope.product.merchant = {};
					$scope.product.merchant.merchantId = $rootScope.user.merchantId;
					ProductService.addProduct($scope.product).then(function(data) {
						$scope.product = $localStorage.product;

						console.log($localStorage.products);

						if($scope.product.productId){

							toastr.success("product details have been updated successfully!!!");
							$localStorage.product={};
							$state.go("app.products");

						}

						else{

							toastr.success("product details have been added successfully!!!");
							$localStorage.product={};
							$state.go("app.products");
						}

					})


		}
	}

	$scope.editproduct = function(products) {
		$localStorage.product = products;
		$state.go('app.addproduct');
}

	$scope.product = $localStorage.product;

	$scope.deleteProduct= function(productId){

		$scope.product= {};

		$scope.product.productId = productId;

		ProductService.deleteProduct($scope.product).then(function(data) {
			//$localStorage.totalProductType = false;
			$scope.getAllProductList();

			toastr.success("product details have been deleted successfully!!!");

		})

	}
	
	$scope.getproductCategory = function(){
		$scope.products = {};
		$scope.products.merchant = {};
		$scope.products.merchant.merchantId = $rootScope.user.merchantId;

		ProductService.getProductCategory($scope.products).then(function(data) {
			
			$scope.productCategoryVo = data.productCategoryVo;


		})

	}
	
	$scope.getProductType = function(productCategoryId){
		$scope.productType = {};
		$scope.productType.productCategory = {};
		$scope.productType.productCategory.productCategoryId = productCategoryId;

		ProductService.getProductType($scope.productType).then(function(data) {
			
			$scope.productTypeVo = data.productTypeVo;
			console.log($scope.productTypeVo);

		})

	}
	
	$scope.inLineEdit = function(product){
		
		$scope.productEdit = product;

	}
	
	$scope.cancelEdit = function(){
		scope.productEdit = null;
	}


	$scope.uploadFile = function (val1,val2){

		var id =$('#'+val2).val();

		var srs=id.replace("C:\\fakepath\\" ,"" );	

		$('#'+val1).html(srs);

	}




	$scope.typeList = [{
		Id:"Veg",Name:"Veg"
	},{
		Id:"Non-Veg",Name:"Non-Veg",
	},{
		Id:"Non-Eatable",Name:"Non-Eatable"
	}];

	$scope.exportXmls = function(){
		$scope.store = {};
		$scope.store.storeId = 79;
		ProductService.exportExcelFile($scope.store).then(function(data) {

			console.log("file exported successfully");

		})
	}

}
]);