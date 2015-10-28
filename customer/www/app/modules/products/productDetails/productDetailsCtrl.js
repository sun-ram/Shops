angular.module('aviate.controllers')
.controller("productDetailsCtrl", ['$scope','$rootScope','$http','$sce','$stateParams','ProductService', 'MyListServices', '$rootScope','$state',
                                   function($scope,$rootScope,$http,$sce,$stateParams,ProductService, MyListServices, $rootScope,$state) {

	$scope.getProducts = function(){
			$scope.productDetails = $rootScope.productDetails;
			for(var i=0;i<$scope.productDetails.productImages.length;i++){
				if($scope.productDetails.productImages[i].imagePosition == "ORIGINALFRONT"){
					$scope.image = $scope.productDetails.productImages[i].imageUrl;
					//$scope.changeimage($rootScope.productDetails.productImages[i].imageUrl);
				}
			}
	}
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
	  
	$scope.getProducts();

	/*	image zooom*/
	$scope.zoomLvl = 4;

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