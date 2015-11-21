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
		
		$scope.product = {}
		$scope.product.merchant = {
				"merchantId":$rootScope.user.merchantId
		}
		ProductService.getAllProductList($scope.product).then(function(data) {

			$scope.data = data.categories;

			console.log($scope.data);

		})
	}

	$scope.updateProduct = function(product) {
		product.productImages=null;
		ProductService.editProduct(product);
	}


	$scope.getProductList = function() {

		if($localStorage.totalProductType){

			$scope.product ={};

			$scope.product.categoryId = $localStorage.categoryId;

			ProductService.getAllProductListByCategory($scope.product).then(function(data) {

				$scope.data = data.products;

				console.log($scope.data);

			})



		}

		else{
			$localStorage.products.merchantId = $rootScope.user.merchantId;
			$scope.product = {};
			$scope.product.merchantId =	$localStorage.products.merchantId;
			$scope.product.categoryId  = $localStorage.products.categoryId;
			$scope.product.productTypeId =$localStorage.products.productTypeId;
			console.log($scope.product);
			ProductService.productList($scope.product).then(function(data) {
				$scope.data = data;

				console.log($scope.data);

			})
		}
	}

	$scope.getproductTypeList = function (){

		console.log($scope.product);
		$scope.category = {'merchantId':$rootScope.user.merchantId};
		ProductService.productType($scope.category).then(function(data){
			console.log(data);
			$scope.productTypes = data.productTypes;

		})
	};


	$scope.getMeasurementUnit = function (){

		$scope.products ={};
		ProductService.getMeasurementUnit($scope.products).then(function(data) {
			$scope.measuredUnits = data.uom;
		})
	}

	$scope.showDetail = function (product){
		console.log(product);
		$localStorage.productDetails ={};
		$localStorage.productDetails.productName = product.productName;
		$localStorage.productDetails.productId =product.productId;
		$localStorage.productDetails.measurement =product.measurement;
		$localStorage.productDetails.productUnitOfMeasure=	product.productUnitOfMeasure;
		$localStorage.productDetails.type = product.type;
		$localStorage.productDetails.productPrice ={};
		$localStorage.productDetails.productPrice.price=product.productPrice.price;
		$localStorage.productDetails.productImages = product.productImages;
		$localStorage.productDetails.productTypeId = product.productTypeId;

		for(i=0;i<$scope.productTypes.length;i++){

			if(product.productTypeId == $scope.productTypes[i].productTypeId){


				$localStorage.productDetails.productTypeName =$scope.productTypes[i].productTypeName;
			}


		}

		for(i=0;i<product.productImages.length;i++){
			if(product.productImages[i].imagePosition =="ORIGINALFRONT"){

				$localStorage.productDetails.orginalfrontimage = product.productImages[i].imageUrl;
				console.log($localStorage.productDetails.orginalfrontimage);
			}
			if(product.productImages[i].imagePosition=="SMALLFRONT"){

				$localStorage.productDetails.smallfrontimage =product.productImages[i].imageUrl;

			}

			if(product.productImages[i].imagePosition == "SMALLBACK"){
				$localStorage.productDetails.smallbackimage =product.productImages[i].imageUrl;
			}

			if(product.productImages[i].imagePosition == "ORIGINALBACK"){
				$localStorage.productDetails.orginalbackimage =product.productImages[i].imageUrl;
			}
		}

		console.log($localStorage.productDetails);
		$state.go('app.productdetailsview');


	}

	$scope.addProductRedirect = function (){

		$localStorage.product={};
		$scope.addshow = true;	
		$state.go('app.addproduct');


	}

	$scope.addproduct = function() {

		if ($scope.image.originalFrontImage == "" || $scope.image.originalFrontImage==undefined) {
			toastr.warning("Please select Original Front Image");
			return;
		} 
		else {

			/* Orginal Front Image base64 Start*/
			$scope.product.image ={};
			$scope.product.image.image =$scope.image.originalFrontImage.split(",")[1];

			/* Orginal Front Image base64 End*/

			/* Orginal Front Image Type Start*/
			$scope.product.image.type = $scope.product.image ? ($scope.image.originalFrontImage.substring(11).split(";")[0]) : "",
					$scope.product.image.name ="OriginalImage";
					/* Orginal Front Image Type End*/
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
							$state.go("app.producttype");
						}

					})


		}
	}

	$scope.editproduct = function(products) {
		ProductService.setProductObj(products);
		$state.go('app.editproduct');
		/*

		console.log(products);
		$localStorage.product ={};
		$localStorage.product = products;
		$localStorage.product.productName = products.productName;
		$localStorage.product.productMeasurement =products.measurement;
		$localStorage.product.type = products.type;
		$localStorage.product.productPrice.price=products.productPrice.price;
		$localStorage.product.productTypeId = products.productTypeId;
		$localStorage.product.productUnitOfMeasure.abbreviation = products.productUnitOfMeasure.abbreviation;
		console.log(products.productImages);




		for(i=0;i<products.productImages.length;i++){
			if(products.productImages[i].imagePosition =="ORIGINALFRONT"){

				$localStorage.orginalfrontimage = products.productImages[i].imageUrl;




			}
			if(products.productImages[i].imagePosition=="SMALLFRONT"){

				$localStorage.smallfrontimage =products.productImages[i].imageUrl;


			}

			if(products.productImages[i].imagePosition == "SMALLBACK"){


				$localStorage.smallbackimage =products.productImages[i].imageUrl;

			}

			if(products.productImages[i].imagePosition == "ORIGINALBACK"){


				$localStorage.orginalbackimage =products.productImages[i].imageUrl;

			}


		}

		

	*/}



	$scope.deleteProduct= function(productId){

		$scope.product= {};

		$scope.product.productId = productId;

		ProductService.deleteProduct($scope.product).then(function(data) {
			//$localStorage.totalProductType = false;
			$scope.getProductList();

			toastr.success("product details have been deleted successfully!!!");

		})

	}
	
	$scope.getproductCategory = function(){

		$scope.product= {};
		$scope.product.merchant = {};
		$scope.product.merchant.merchantId = $rootScope.user.merchantId;

		ProductService.getProductCategory($scope.product).then(function(data) {
			
			$scope.productCategoryVo = data.productCategoryVo;


		})

	}
	
	$scope.getProductType = function(productCategoryId){

		$scope.product.productCategory = {};
		$scope.product.productCategory.productCategoryId = productCategoryId;

		ProductService.getProductType($scope.product).then(function(data) {
			
			$scope.productTypeVo = data.productTypeVo;


		})

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