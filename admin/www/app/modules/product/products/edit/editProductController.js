angular.module('aviateAdmin.controllers')
.controller("editproductcontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr',
                                      function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr) {



	$scope.query = {
			limit: 5,
			page: 1
	};

	$scope.count = 3;
	$scope.srch = true;


	$scope.getProducts = function(){
		$scope.productDetails = ProductService.getProductObj();
		$scope.temp = localStorage.getItem('productDetails');
		if($scope.productDetails){
			localStorage.setItem('productDetails',JSON.stringify($scope.productDetails));
		}else if($scope.temp && $scope.temp != 'undefined'){
			$scope.productDetails = JSON.parse($scope.temp);
		}else{
			localStorage.removeItem('productDetails');
			//$state.go('app.newmerchant');
		}
		console.log($scope.productDetails);
	};

	$scope.getProducts();


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

		$scope.category = {'merchantId':$rootScope.user.merchantId};
		ProductService.productType($scope.category).then(function(data){
			console.log(data);
			$scope.productTypes = data.productTypes;

		})
	};


	$scope.getMeasurementUnit = function (){

		$scope.products ={'merchantId':$rootScope.user.merchantId};
		ProductService.getMeasurementUnit($scope.products).then(function(data) {
			$scope.measuredUnits = data.units;
		})
	}

	$scope.editproduct = function(products) {
		$scope.product = {};

		if($scope.smallFrontImage && $scope.smallFrontImage != undefined){

			$scope.product.smallFrontImage =   $scope.smallFrontImage ? (($scope.smallFrontImage.substring(11).split(";")[0].length < 4) ? $scope.smallFrontImage.substring(22) : 
				$scope.smallFrontImage.substring(23)) : "";

			$scope.product.smallFrontImageType = $scope.smallFrontImage ? ($scope.smallFrontImage.substring(11).split(";")[0]) : "";
		}
		$scope.product.smallBackImage =  $scope.smallBackImage ? (($scope.smallBackImage.substring(11).split(";")[0].length < 4) ? $scope.smallBackImage.substring(22) : 
			$scope.smallBackImage.substring(23)) : "";

		$scope.product.smallBackImageType = $scope.smallBackImage ? ($scope.smallBackImage.substring(11).split(";")[0]) : "";

		$scope.product.originalFrontImage =  $scope.originalFrontImage ? (($scope.originalFrontImage.substring(11).split(";")[0].length < 4) ? $scope.originalFrontImage.substring(22) :
			$scope.originalFrontImage.substring(23)) : "";

		$scope.product.originalFrontImageType= $scope.originalFrontImage ? ($scope.originalFrontImage.substring(11).split(";")[0]) : "",

				$scope.product.originalBackImage = $scope.originalBackImage ? (($scope.originalBackImage.substring(11).split(";")[0].length < 4) ? $scope.originalBackImage.substring(22) :
					$scope.originalBackImage.substring(23)) : "";

				$scope.product.originalBackImageType =  $scope.originalBackImage ? ($scope.originalBackImage.substring(11).split(";")[0]) : "";

				$scope.product.merchantId = $rootScope.user.merchantId;
				$scope.product.storeId = $rootScope.user.storeId;
				$scope.product.productId  =  products.productId;
				$scope.product.productTypeId = products.productTypeId;
				$scope.product.productName= products.productName;
				$scope.product.description =  products.productUnitOfMeasure.description;
				$scope.product.type =  products.type;
				$scope.product.price = products.productPrice.price;
				$scope.product.priceId = products.productPrice.priceId;
				$scope.product.measurement=products.measurement;
				$scope.product.measureId = products.productUnitOfMeasure.unitOfMeasureId;
				$scope.product.abbreviation = products.productUnitOfMeasure.abbreviation;

				console.log($scope.product);

				ProductService.editProduct($scope.product).then(function(data) {
					$state.go("app.producttype");
				})


	}



}



]);