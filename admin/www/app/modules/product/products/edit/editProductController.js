angular.module('aviateAdmin.controllers')
.controller("editproductcontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr',
                                      function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr) {



	$scope.query = {
			limit: 5,
			page: 1
	};

	$scope.count = 3;
	$scope.srch = true;



	$scope.typeList = [{
		Id:"Veg",Name:"Veg"
	},{
		Id:"Non-Veg",Name:"Non-Veg",
	},{
		Id:"Non-Eatable",Name:"Non-Eatable"
	}];


	$scope.orginalfrontimage =$localStorage.orginalfrontimage;
	$scope.orginalbackimage = $localStorage.orginalbackimage;
	$scope.smallfrontimage = $localStorage.smallfrontimage;
	$scope.smallbackimage  = $localStorage.smallbackimage;




	$scope.getproductTypeList = function (){


		$scope.showorgfront = false;
		$scope.showorgback = false;
		$scope.showsmallfront = false;
		$scope.showsmallback = false;


		$scope.product = $localStorage.product;

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

	$scope.editproduct = function() {

		/*		var image = $('#file-name1').val();

	console.log(image?((image.substring(11).split(";")[0].length < 4) ? image.substring(22) : 
		image.substring(23)) : "");*/



		if ($scope.OriginalFrontImage == "" || $scope.OriginalFrontImage==undefined && $scope.orginalfrontimage == "" || $scope.orginalfrontimage==undefined) {
			toastr.warning("Please select Original Front Image");
			return;
		}  if ($scope.OriginalBackImage == "" || $scope.OriginalBackImage == undefined && $scope.orginalbackimage == "" || $scope.orginalbackimage == undefined) {
			toastr.warning("Please Select Original Back Image");
			return;
		} if ($scope.smallFrontImage == "" || $scope.smallFrontImage == undefined && $scope.smallfrontimage == "" || $scope.smallfrontimage == undefined) {
			toastr.warning("Please Select Thumbnail Front Image");
			return;
		}
		if ($scope.smallBackImage == "" || $scope.smallBackImage == undefined && $scope.smallbackimage == "" || $scope.smallbackimage == undefined) {
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


			/*		$localStorage.orgfrontimage = $scope.OriginalFrontImage;

		$localStorage.orgbackimage = $scope.OriginalBackImage;
		$localStorage.smallfrontimage = $scope.smallFrontImage;
		$localStorage.smallbackimage  = $scope.smallBackImage;	*/


			if($scope.OriginalFrontImage=="" || $scope.OriginalFrontImage == undefined){


				$scope.OriginalFrontImage =  $localStorage.orgfrontimage;
			}

			if($scope.OriginalBackImage == "" || $scope.OriginalBackImage == undefined ){

				$scope.OriginalBackImage = $localStorage.orgbackimage;
			}

			if($scope.smallFrontImage == "" || $scope.smallFrontImage == undefined ){

				$scope.smallFrontImage = $localStorage.smallfrntimage;

			}

			if($scope.smallBackImage == "" || $scope.smallBackImage == undefined){

				$scope.smallBackImage = $localStorage.smallbckimage;

			}





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
					$localStorage.products.priceId = $scope.product.productPrice.priceId;
					$localStorage.products.measurement=$scope.product.measurement;
					$localStorage.products.measureId = $scope.product.productUnitOfMeasure.unitOfMeasureId;

					$localStorage.products.productTypeId =  $scope.product.productTypeId;

					$localStorage.products.abbreviation =  $scope.product.productUnitOfMeasure.abbreviation;

					$scope.product = $localStorage.products;

					console.log($scope.product);

					ProductService.editProduct($scope.product).then(function(data) {
						$scope.product = $localStorage.product;

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



}



]);