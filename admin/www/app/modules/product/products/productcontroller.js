angular.module('aviateAdmin.controllers')
.controller("productcontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices) {
	/*==========Get All Product Details==========*/



	$scope.query = {
			limit: 5,
			page: 1
	};

	$scope.count = 3;
	$scope.srch = true;




	$scope.getProductList = function() {

		if($localStorage.totalProductType){

			$scope.product ={};

			$scope.product.categoryId = $localStorage.productcatId;

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
		$scope.category = {'categoryId':$localStorage.categoryId};
		ProductService.productType($scope.category).then(function(data){
			console.log(data);
			$scope.productTypes = data.productType;

		})
	};


	$scope.getMeasurementUnit = function (){

		$scope.products ={'merchantId':$rootScope.user.merchantId};
		ProductService.getMeasurementUnit($scope.products).then(function(data) {
			$scope.measuredUnits = data.units;
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

		if ($scope.OriginalFrontImage == "" || $scope.OriginalFrontImage==undefined) {
			toastr.warning("Please select Original Front Image");
			return;
		}  if ($scope.OriginalBackImage == "" || $scope.OriginalBackImage == undefined) {
			toastr.warning("Please Select Original Back Image");
			return;
		} if ($scope.smallFrontImage == "" || $scope.smallFrontImage == undefined) {
			toastr.warning("Please Select Thumbnail Front Image");
			return;
		}
		if ($scope.smallBackImage == "" || $scope.smallBackImage == undefined) {
			toastr.warning("Please Select Thumbnail Back Image");
			return;
		}
		else {

			console.log($localStorage.product);


			console.log($scope.product);

			$localStorage.products.merchantId = $rootScope.user.merchantId;
			$localStorage.products.productId  =  $scope.product.productId;
			$localStorage.products.productTypeId = $scope.product.productTypeId;
			$localStorage.products.productName= $scope.product.productName;
			$localStorage.products.description =  $scope.product.productUnitOfMeasure.description;
			$localStorage.products.type =  $scope.product.type;
			/*  Small Front Image base64 Start*/
			$localStorage.products.smallFrontImage =   $scope.smallFrontImage ? (($scope.smallFrontImage.substring(11).split(";")[0].length < 4) ? $scope.smallFrontImage.substring(22) : 
				$scope.smallFrontImage.substring(23)) : "";

			/*  Small Front Image base64 End*/

			/* Small Front Small Image Type Start*/

			$localStorage.products.smallFrontImageType = $scope.smallFrontImage ? ($scope.smallFrontImage.substring(11).split(";")[0]) : "";

			/* Small Front Small Image Type End*/

			/*  Small Back Image base64 Start*/

			$localStorage.products.smallBackImage =  $scope.smallBackImage ? (($scope.smallBackImage.substring(11).split(";")[0].length < 4) ? $scope.smallBackImage.substring(22) : 
				$scope.smallBackImage.substring(23)) : "";

			/*  Small Back Image base64 End*/		

			/* Small Small Back Image Type Start*/

			$localStorage.products.smallBackImageType = $scope.smallBackImage ? ($scope.smallBackImage.substring(11).split(";")[0]) : "";

			/* Small Small Back Image Type End*/

			/* Orginal Front Image base64 Start*/

			$localStorage.products.originalFrontImage =  $scope.OriginalFrontImage ? (($scope.OriginalFrontImage.substring(11).split(";")[0].length < 4) ? $scope.OriginalFrontImage.substring(22) :
				$scope.OriginalFrontImage.substring(23)) : "";

			/* Orginal Front Image base64 End*/

			/* Orginal Front Image Type Start*/
			$localStorage.products.originalFrontImageType= $scope.OriginalFrontImage ? ($scope.OriginalFrontImage.substring(11).split(";")[0]) : "",

					/* Orginal Front Image Type End*/

					/* Orginal Back Image base64 Start*/

					$localStorage.products.originalBackImage = $scope.OriginalBackImage ? (($scope.OriginalBackImage.substring(11).split(";")[0].length < 4) ? $scope.OriginalBackImage.substring(22) :
						$scope.OriginalBackImage.substring(23)) : "";

					/* Orginal Back Image base64 End*/

					/* Orginal Back Image Type Start*/

					$localStorage.products.originalBackImageType =  $scope.OriginalBackImage ? ($scope.OriginalBackImage.substring(11).split(";")[0]) : "";

					/* Orginal Back Image Type End*/


					$localStorage.products.price = $scope.product.productPrice.price;
					$localStorage.products.measurement=$scope.product.measurement;

					$localStorage.products.productTypeId =  $scope.product.productTypeId;

					$localStorage.products.abbreviation =  $scope.product.productUnitOfMeasure.abbreviation;

					$scope.product = $localStorage.products;


					$localStorage.orgfrontimage = $scope.OriginalFrontImage;


					$localStorage.orgbackimage = $scope.OriginalBackImage ;
					$localStorage.smallfrntimage =$scope.smallFrontImage;
					$localStorage.smallbckimage  = $scope.smallBackImage;

					ProductService.addProduct($scope.product).then(function(data) {
						$scope.product = $localStorage.product;

						console.log($localStorage.products);

						if($scope.product.productId){

							toastr.success("product details have been updated successfully!!!");
							$localStorage.product={};
							$state.go("app.producttype");

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

		$state.go('app.editproduct');

	}



	$scope.deleteProduct= function(productId){

		$scope.product= {};

		$scope.product.productId = productId;

		ProductService.deleteProduct($scope.product).then(function(data) {

			$scope.getProductList();

			toastr.success("product details have been deleted successfully!!!");


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



}
]);