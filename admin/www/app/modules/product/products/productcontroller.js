angular.module('aviateAdmin.controllers')
.controller("productcontroller", ['$scope','$http','$rootScope','$localStorage','$location','$filter','$window','ngTableParams','$state','ProductService','toastr','ProductCategoryServices','myConfig','$mdDialog','DiscountService',
                                  function($scope, $http, $rootScope, $localStorage, $location, $filter,$window, ngTableParams,$state,ProductService,toastr, ProductCategoryServices,myConfig, $mdDialog,DiscountService) {
	/* ==========Get All Product Details========== */

	$scope.downloadExcel = myConfig.backend;

	$scope.excelUrl = $scope.downloadExcel +"product/exportExcelFile?merchantId="+ $rootScope.user.merchantId;

	$scope.query = {
			limit: 5,
			page: 1
	};

	$scope.count = 3;
	$scope.srch = true;

    $rootScope.setProductImage = function(image){
    	if($scope.imageType == 'original' && $scope.imageType != undefined){
    		$scope.image = image;
    	}else if($scope.imageType == 'uploaded' && $scope.imageType != undefined){
    		$scope.newImage = {};
    		$scope.newImage.image = image.originalFrontImage;
    		if(image.imageId != undefined){
    		  $scope.newImage.imageId = image.imageId;
    		}
    		$scope.uploadedImages[$scope.imageIndex] = $scope.newImage;
    	}
    }
	
	$scope.image={};
	$scope.image.originalFrontImage;

	$scope.images={};
	$scope.images.originalBackImage;


	$scope.productDetails = function(product){
		ProductService.setProductObj(product);
		$state.go('app.productdetailsview');
	};

	/*
	 * $scope.checkPrice = function(price, wasPrice){ if((price != undefined ||
	 * price !="") && (wasPrice != undefined || wasPrice != "")){ if(wasPrice <=
	 * price){ $scope.product.wasPrice = undefined; toastr.warning("WasPrice
	 * Should Be Greater Than To Price");
	 * 
	 * 
	 * }else{
	 * 
	 * return; } } }
	 */

	$rootScope.getAllProductList = function() {
		$scope.product = {};
		$scope.product.merchant = {
				"merchantId":$rootScope.user.merchantId
		}
		ProductService.getAllProductList($scope.product).then(function(data) {
			$rootScope.productList = data.products;
			$rootScope.originalList = $scope.productList;
			$rootScope.noOfRecords = $scope.productList.length;
		})
	};

	$scope.update = function(){
		if ($scope.product.price == 0) {
			toastr.warning("Product Price should be greater than Zero");
			return;
		} 
		if ($scope.product.wasPrice == 0) {
			toastr.warning("Product WasPrice should be greater than Zero");
			return;
		} 
		if(($scope.product.price != undefined || $scope.product.price !="") && ($scope.product.wasPrice != undefined || $scope.product.wasPrice != "")){
			if(parseInt($scope.product.wasPrice) <= parseInt($scope.product.price)){
				toastr.warning("WasPrice Should Be Greater Than To Price");
				return;

			}
		}

		if($scope.groupCount){
			if(parseInt($scope.product.groupCount)<2 || $scope.product.groupCount ==""){
				toastr.warning("BundleQty Should Be Greater Than One");
				return;
			}
		}

		if($scope.image.originalFrontImage != undefined ){
			$scope.product.image = $scope.splitProductType($scope.image.originalFrontImage);
		}
		$scope.product.images = [];
		if($scope.uploadedImages && $scope.uploadedImages.length > 0){
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
		$scope.product.userId = $rootScope.user.userName;
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
			toastr.success("Product details have been updated successfully!!!");
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

		if ($scope.product.price == 0) {
			toastr.warning("Product Price should be greater than Zero");
			return;
		} 
		if ($scope.product.wasPrice == 0) {
			toastr.warning("Product WasPrice should be greater than Zero");
			return;
		} 
		if(($scope.product.price != undefined || $scope.product.price !="") && ($scope.product.wasPrice != undefined || $scope.product.wasPrice != "")){
			if(parseInt($scope.product.wasPrice) <= parseInt($scope.product.price)){
				toastr.warning("WasPrice Should Be Greater Than To Price");
				return;

			}
		}
		if($scope.groupCount){
			if(parseInt($scope.product.groupCount)<2 || $scope.product.groupCount ==""){
				toastr.warning("BundleQty Should Be Greater Than One");
				return;
			}
		}
		if($scope.image.originalFrontImage != undefined ){
			$scope.product.image = $scope.splitProductType($scope.image.originalFrontImage);
		}else{
			toastr.warning("Please select originalFrontImage");
			return;
		}

		if($scope.uploadedImages && $scope.uploadedImages.length > 0){
			$scope.product.images =[];
			for(var i=0; i<$scope.uploadedImages.length; i++){
				if($scope.uploadedImages[i].image != undefined ){
					$scope.product.images.push($scope.splitProductType($scope.uploadedImages[i].image));
				}
			}
		}

		$scope.product.merchant = {};
		$scope.product.merchant.merchantId = $rootScope.user.merchantId;
		$scope.product.userId = $rootScope.user.userName;
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

	$scope.editproduct = function(products) {
		$localStorage.product = products;
		if(product.groupCount!=null)
			$scope.groupCount=true;
		else
			$scope.groupCount=false;

		$state.go('app.addproduct');
	}
	$scope.checkgroupCount = function(product)

	{
		if(product.groupCount)
			$scope.groupCount=true;
		else
			$scope.groupCount=false;
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
				$rootScope.getAllProductList();
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

	$scope.inLineEdit = function(product){
		$scope.productEdit = product;
	}

	$scope.cancelEdit = function(){
		scope.productEdit = null;
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


	$scope.uploadExcelFile = function (val1,val2){
		var id =$('#'+val2).val();
		var srs=id.replace("C:\\fakepath\\" ,"" );	
		$('#'+val1).html(srs);
	}
	$scope.excelFile={};
	$scope.uploadXmls = function(){
		// $scope.product.merchant.merchantId=$rootScope.user.merchantId
		$scope.product.productId="456"
			$scope.product.image ={};
		$scope.product.merchant={
				"merchantId":$rootScope.user.merchantId
		}
		$scope.product.image.image=$scope.excelFile.file.split(",")[1];
		$scope.product.image.type=$scope.excelFile.file ? ($scope.excelFile.file.substring(11).split(";")[0]) : "";
		if($scope.product.image.type=="ation/vnd.ms-excel" || $scope.product.image.type=="ation/vnd.openxmlformats-officedocument.spreadsheetml.sheet"){
			ProductService.uploadExcelFile($scope.product).then(function(data) {
				/* $state.go("app.products"); */
				$mdDialog.show({
					templateUrl: 'app/modules/modals/ProductFileUploadModal.html',
					parent: angular.element(document.body),
					clickOutsideToClose:false,
					controller: function($scope,$state,$mdDialog){
						$scope.newData=data.newData;
						$scope.existingData=data.existingData;
						$scope.rejectedData=data.rejectedData;
						$scope.addFiles=function(){
							$scope.addFilesList={};
							$scope.addFilesList.newData=$scope.newData;
							$scope.addFilesList.existingData=$scope.existingData;
							$scope.addFilesList.rejectedData=$scope.rejectedData;
							$scope.addFilesList.merchant={
									"merchantId":$rootScope.user.merchantId
							}
							ProductService.addProductListFiles($scope.addFilesList).then(function(data) {
								//$mdDialog.cancel();
								$rootScope.getAllProductList();
								if(data.productRejectedVo.length!=0){
									$mdDialog.show({
										templateUrl: 'app/modules/modals/ProductRejectedFileModal.html',
										parent: angular.element(document.body),
										clickOutsideToClose:false,
										controller: function($scope,$mdDialog){
											$scope.productRejectedVo=data.productRejectedVo;
											$scope.cancel1 = function() {
												$mdDialog.cancel();
											};
										}
									})
									.then(function(answer) {	
										$scope.status = 'You said the information was "' + answer + '".';
									}, function() {
										$scope.status = 'You cancelled the dialog.';
									});
								}
								else{
									$mdDialog.cancel();
								}
							});
						}
						$scope.productFileNew={
								limit: 5,
								page: 1
						};
						$scope.productFileExisting={
								limit: 5,
								page: 1
						};
						$scope.productFileRejected={
								limit: 5,
								page: 1
						};
						$scope.cancel = function() {
							$mdDialog.cancel();
						};
					}

				})
				.then(function(answer) {	
					$scope.status = 'You said the information was "' + answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
			})
		}else{
			toastr.error("Invalid Excel File");
		}
	}

	$scope.addNewImageToList = function(){
		if($scope.uploadedImages != undefined && $scope.uploadedImages.length > 0){
			if(!($scope.uploadedImages[$scope.uploadedImages.length-1].image || $scope.uploadedImages[$scope.uploadedImages.length-1].imageId)){
				return;
			}
		}else{
			$scope.uploadedImages = [];
		}
		$scope.uploadedImages.push({});
	}
	
	$scope.deleteProductImage = function(index, imgs){
		if(imgs.imageId){
			ProductService.deleteProductImage(imgs).then(function(data){
				$scope.uploadedImages.splice(index, 1);	
			})
			// TODO: jdfjs
		}else if (index > -1) {
			$scope.uploadedImages.splice(index, 1);
		}
	}

	$scope.getDiscount = function(){
		if($rootScope.user.storeId){
			$scope.store ={};
			$scope.store.storeId = $rootScope.user.storeId;

			DiscountService.storeDiscountList($scope.store).then(function(data) {
				$scope.discountList = data.discountVos;
			})

		}else{		

			$scope.merchant ={};
			$scope.merchant.merchantId = $rootScope.user.merchantId;

			DiscountService.merchantDiscountList($scope.merchant).then(function(data) {
				$scope.discountList = data.discountVos;
			})

		}	
	}

	$scope.uploadProductImage = function(imageType, index, imageId){
		$scope.imageType = imageType;
		$scope.imageIndex = index;
		
		$mdDialog.show({
			templateUrl: 'app/modules/modals/ProductImageUpload.html',
			parent: angular.element(document.body),
			clickOutsideToClose:false,
			controller: function($scope,$rootScope,$mdDialog,$state){
				
				$scope.uploadFile = function (val1,val2){
					var id =$('#'+val2).val();
					var srs=id.replace("C:\\fakepath\\" ,"" );	
					$('#'+val1).html(srs);
				}
				
				$scope.passImage = function(image){
					if(imageId != undefined){
						image.imageId = imageId;
					}
					$rootScope.setProductImage(image);
					$mdDialog.cancel();
				}
									
				$scope.cancel = function() {
					$mdDialog.cancel();
				};
			}
		})
		.then(function(answer) {	
			$scope.status = 'You said the information was "' + answer + '".';
		}, function() {
			$scope.status = 'You cancelled the dialog.';
		});

	}


}
]);