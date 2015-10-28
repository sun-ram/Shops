angular.module('aviate.controllers')
.controller("productDetailsCtrl", ['$scope','$http','$sce','$stateParams','ProductService', 'MyListServices', '$rootScope','$state',
                                   function($scope,$http,$sce,$stateParams,ProductService, MyListServices, $rootScope,$state) {

	$scope.getProducts = function(){
		ProductService.getProductsByProductId({'productId':$stateParams.productId}).then(function(data){
			$scope.productDetails = data;
			console.info($scope.productDetails);
			for(var i=0;i<$scope.productDetails.productImages.length;i++){
				if($scope.productDetails.productImages[i].imagePosition == "ORIGINALFRONT"){
					$scope.changeimage($scope.productDetails.productImages[i].imageUrl);
				}
			}
			$scope.getProductsByProductTypeId($scope.productDetails.productTypeId);
		});
	}
	$scope.getProductsByProductTypeId = function(productTypeId){
		ProductService.getProductsByProductTypeId({'productTypeId':productTypeId}).then(function(data){
			$scope.relatedProductList = data;
		});
	}
	
	$scope.productDetail = function(products){ 
		$state.go('app.productsdetails',{productId:products.productId});
	}
	
	$scope.getProducts();

	/*	image zooom*/
	$scope.zoomLvl = 4;
	$scope.zoom ;
	$scope.showImageindescription = "http://54.68.134.166:8080/aviate/ImageServlet?imageName=e0603a8d69ca4b488e2ce866e07002fe";

	/*product desscription*/

	/*$scope.productDetail = function() {

		var menuJson = angular.toJson({
			"productId":"347"
		});

		$http({
			url: 'http://localhost:8080/aviate/json/product/getproductdetails',
			method: 'POST',
			data: menuJson,
			headers: {
				'Content-Type': 'application/json'
			}
		}).success(function(result, status,headers) {
			$scope.product = result;
			for(i=0;i<4;i++){
				if(result.productImages[i].imagePosition == "ORIGINALFRONT"){
					$scope.showImageindescription;
					$scope.showImageindescription = result.productImages[i].imageUrl;

					console.log("ashdgs :::", $scope.images);

				}
			}
		});
	}*/

	$scope.changeimage = function(imageurl){
		var someimage = document.getElementsByTagName('zoom')[0];
		var myimg1 = someimage.getElementsByTagName('img')[0];
		var myimg2 = someimage.getElementsByTagName('img')[1];
		myimg1.setAttribute("src",imageurl);
		myimg1.setAttribute("ng-src",imageurl);
		myimg2.setAttribute("src",imageurl);

		//document.getElementByTagName("zoom img").src = imageurl;
		//$scope.showImageindescription = imageurl;
	}


	$scope.addToMyList = function(product){
		if($rootScope.user == null || $rootScope.user == undefined){
			$scope.signInPopup();
		}else{
		MyListServices.addToMyList({"customerId":$rootScope.user.userId,"productId" : product.productId,"storeId" : $rootScope.store.storeId}).then(function(data){
			$scope.productDetails.isProductMyList = true;
			$scope.getMyList();
		});	
		}
	};


	$scope.getMyList = function(){
		MyListServices.getMyList({"customerId":$rootScope.user.userId, "storeId" : $rootScope.store.storeId}).then(function(data){
			$scope.myListProducts = data;
		});
	};

	$scope.removeFromMyList = function(product){
		MyListServices.removeMyList({"customerId":$rootScope.user.userId,"productId" : product.productId,"storeId" : $rootScope.store.storeId}).then(function(data){
			$scope.getMyList();
			$scope.productDetails.isProductMyList = false;
		});	
	};
}]);