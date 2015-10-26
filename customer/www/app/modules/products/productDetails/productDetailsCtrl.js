angular.module('aviate.controllers')
.controller("productDetailsCtrl", ['$scope','$http','$sce','$stateParams','ProductService',
                                   function($scope,$http,$sce,$stateParams,ProductService) {

	$scope.getProducts = function(){
		ProductService.getProductsByProductId({'productId':$stateParams.productId}).then(function(data){
			$scope.productDetails = data;
			console.info($scope.productDetails);
		});
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


	$scope.addToMyList = function(){
		MyListServices.addToMyList().then(function(data){
			$scope.productDetail.isProductMyList = !$scope.productDetail.isProductMyList;
		});	
	};

	$scope.getMyList = function(){
		MyListServices.getMyList().then(function(data){

		});
	};

	$scope.removeFromMyList = function(){
		MyListServices.removeMyList().then(function(data){
		});	
	};

	/*	$scope.productDescriptionFun = function(productObj){
		$rootScope.addListShow = true;
		var showImageindescription = '';
		if($rootScope.myLists){
			for(var i = 0;i<$rootScope.myLists.length;i++)
				if(true){
					$rootScope.addListShow = false;
				}
		}
		for(var i = 0; i < productObj.productDetails.productImages.length; i++){
			if(productObj.productDetails.productImages[i].imagePosition == 'ORIGINALFRONT'){
				showImageindescription = productObj.productDetails.productImages[i].imageUrl;
			}
		}if(productObj.productDetails.productImages.length == 0){
			showImageindescription = 'http://182.74.202.178:8181/aviate/ImageServlet?imageName=1eb789bde1fa452e92';
		}
		productObj.addListShow = $rootScope.addListShow;
		$rootScope.productDetail = productObj;
		$rootScope.showImageindescription = showImageindescription;
		$localStorage.localStorageProductDetail = $rootScope.productDetail;
	}*/
}
]);