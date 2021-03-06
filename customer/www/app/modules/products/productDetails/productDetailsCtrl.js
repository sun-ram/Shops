angular.module('aviate.controllers')
.controller("productDetailsCtrl", ['$scope','$http','$stateParams','ProductService', 'MyListServices', '$rootScope','$state','MyCartFactory', 'MyCartServices','$mdDialog','$localStorage',
                                   function($scope,$http,$stateParams,ProductService, MyListServices, $rootScope,$state, MyCartFactory, MyCartServices, $mdDialog,$localStorage) {

	$scope.getProducts = function(){
			$scope.productDetails = $rootScope.productDetails;
			/*for(var i=0;i<$scope.productDetails.productImages.length;i++){
				if($scope.productDetails.productImages[i].imagePosition == "ORIGINALFRONT"){
					$scope.image = $scope.productDetails.image.url;
					//$scope.changeimage($rootScope.productDetails.productImages[i].imageUrl);
				}
			}*/
		$scope.image = $scope.productDetails.image.url;
	}
	
	
	  $scope.changeimage = function(imageurl){
			/*var someimage = document.getElementsByTagName('zoom')[0];
			var myimg1 = someimage.getElementsByTagName('img')[0];
			var myimg2 = someimage.getElementsByTagName('img')[1];
			myimg1.setAttribute("src",imageurl);
			myimg1.setAttribute("ng-src",imageurl);
			myimg2.setAttribute("src",imageurl);*/
			$scope.image = imageurl;

			//document.getElementByTagName("zoom img").src = imageurl;
			//$scope.showImageindescription = imageurl;
		}
	  
	$scope.getProducts();

	/*	image zooom*/
	$scope.zoomLvl = 4;

	$scope.addToMyList = function(product){
		if($rootScope.user == null || $rootScope.user == undefined){
			$rootScope.signInPopup();
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
	
	$scope.addToCart = function(product){
		MyCartFactory.addToCart(product,$scope.productList,  function(data){
			$scope.productList = data;
			$scope.getCartList();
		});
	}

	$scope.getCartList = function(){
		if($rootScope.user){
			MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}},  function(data){
				MyCartFactory.checkCartProductsQuantity($scope.productList,function(data){
					$scope.productList = data;
				});
			});
		}
	}
	
	$scope.removeFromMyCart = function(product, index) {
		MyCartFactory.removeFromCart(product, index);
	};
	
	$scope.cancel = function() {
		$mdDialog.cancel();
	};
}]);