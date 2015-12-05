angular.module('aviateAdmin.controllers')
.controller("productcontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices','myConfig','$mdDialog',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices,myConfig, $mdDialog) {
	/*==========Get All Product Details==========*/

	$scope.downloadExcel = myConfig.backend;

	$scope.excelUrl = $scope.downloadExcel +"product/exportExcelFile?merchantId="+ $rootScope.user.merchantId;

	$scope.query = {
			limit: 5,
			page: 1
	};

	$scope.count = 3;
	$scope.srch = true;

	$scope.image={};
	$scope.image.originalFrontImage;

	$scope.images={};
	$scope.images.originalBackImage;


	$scope.productDetails = function(product){
		ProductService.setProductObj(product);
		$state.go('app.productdetailsview');
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

	$scope.update = function(){
		if($scope.image.originalFrontImage != undefined ){
			$scope.product.image = $scope.splitProductType($scope.image.originalFrontImage);
		}
		$scope.product.images = [];
		if($scope.uploadedImages.length > 0){
			for(var i=0; i<$scope.uploadedImages.length; i++){
				if($scope.uploadedImages[i].image != undefined && $scope.uploadedImages[i].image != null ){
					$scope.productimg = $scope.splitProductType($scope.uploadedImages[i].image);
					$scope.uploadedImages[i].image = $scope.productimg.image;
					$scope.uploadedImages[i].type = $scope.productimg.type;
					$scope.product.images.push($scope.uploadedImages[i]);
				}else {
					$scope.product.images.push($scope.uploadedImages[i]);
				}
			}
		}
		$scope.product.merchant = {};
		$scope.product.merchant.merchantId = $rootScope.user.merchantId;
		ProductService.addProduct($scope.product).then(function(data) {
			$scope.product = $localStorage.product;

			if($scope.product.productId){

				toastr.success("Product details have been updated successfully!!!");
				$localStorage.product={};
				$state.go("app.products");

			}

			else{

				toastr.success("Product details have been added successfully!!!");
				$localStorage.product={};
				$state.go("app.products");
			}

		})
	}

	$scope.updateProduct = function(product) {
		$scope.product = product;
		$scope.product.merchant = {};
		$scope.product.merchant.merchantId = $rootScope.user.merchantId;
		ProductService.addProduct($scope.product).then(function(data) {
			$scope.product = $localStorage.product;
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

	$scope.splitProductType = function(img){
		$scope.reImg = {};
		$scope.reImg.image =img.split(",")[1];
		$scope.reImg.type = img ? (img.substring(11).split(";")[0]) : "";
		return $scope.reImg;
	}

	$scope.addproduct = function() {
		if($scope.image.originalFrontImage != undefined ){
			$scope.product.image = $scope.splitProductType($scope.image.originalFrontImage);
		}else{
			toastr.warning("Please select originalFrontImage");
			return;
		}

		if($scope.uploadedImages.length > 0){
			$scope.product.images =[];
			for(var i=0; i<$scope.uploadedImages.length; i++){
				if($scope.uploadedImages[i].image != undefined ){
					$scope.product.images.push($scope.splitProductType($scope.uploadedImages[i].image));
				}
			}
		}

		$scope.product.merchant = {};
		$scope.product.merchant.merchantId = $rootScope.user.merchantId;
		ProductService.addProduct($scope.product).then(function(data) {
			$scope.product = $localStorage.product;
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

	$scope.editproduct = function(products) {
		$localStorage.product = products;
		$state.go('app.addproduct');
	}

	if($localStorage.product){
		$scope.product = $localStorage.product;
		$scope.uploadedImages = $scope.product.images;
	}

	$scope.deleteProduct= function(productId){
		var confirm = $mdDialog.confirm()
		.title('Would you like to delete Product?')
		.ok('Delete')
		.cancel('Cancel');
		$mdDialog.show(confirm).then(function() {
			$scope.product= {};
			$scope.product.productId = productId;
			ProductService.deleteProduct($scope.product).then(function(data) {
				$scope.getAllProductList();
				toastr.success("Product details have been deleted successfully!!!");
			})
		}, function() {

		});		

	}

	$scope.getproductCategory = function(){
		$scope.products = {};
		$scope.products.merchant = {};
		$scope.products.merchant.merchantId = $rootScope.user.merchantId;

		ProductService.getProductCategory($scope.products).then(function(data) {

			$scope.productCategoryVo = data.productCategories;
		})

	}

	$scope.getProductType = function(productCategoryId){
		$scope.productType = {};
		$scope.productType.productCategory = {};
		$scope.productType.productCategory.productCategoryId = productCategoryId;

		ProductService.getProductType($scope.productType).then(function(data) {
			$scope.productTypeVo = data.productTypeVo;
		})

	}
	
	$scope.deleteProductImage = function(index, imgs){
		if(imgs.imageId){
			ProductService.deleteProductImage(imgs).then(function(data){
				$scope.uploadedImages.splice(index, 1);	
			})
			//TODO: jdfjs
		}else if (index > -1) {
			$scope.uploadedImages.splice(index, 1);
		}
		
		
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

		})
	}


	$scope.uploadExcel = function (val1,val2){

		var id =$('#'+val2).val();

		var srs=id.replace("C:\\fakepath\\" ,"" );	

		$('#'+val1).html(srs);

		$scope.uploadXmls();

	}

	$scope.uploadXmls = function(){
		//$scope.product.merchant.merchantId=$rootScope.user.merchantId
		$scope.product.productId="456"
			$scope.product.image ={};
		$scope.product.image.image=$scope.excelFile.split(",")[1];
		$scope.product.image.type=$scope.excelFile ? ($scope.excelFile.substring(11).split(";")[0]) : "";
		ProductService.uploadExcelFile($scope.product).then(function(data) {
		})
	}

	$scope.addNewImageToList = function(){
		if($scope.uploadedImages != undefined && $scope.uploadedImages.length > 0){
			if(!$scope.uploadedImages[$scope.uploadedImages.length-1].image){
				return;
			}
		}else{
			$scope.uploadedImages = [];
		}
		$scope.uploadedImages.push({});
	}

}
]);