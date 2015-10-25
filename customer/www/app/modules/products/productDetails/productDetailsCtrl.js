angular.module('aviate.controllers')
.controller("productDetailsCtrl", ['$scope','$http','$sce',
                                   function($scope,$http,$sce) {


	/*	image zooom*/
	$scope.zoomLvl = 4;
	$scope.zoom ;


	/*product desscription*/

	$scope.productDetail = function() {

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
	}

	$scope.changeimage = function(imageurl){

		$scope.zoom.showImageindescription = imageurl;
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