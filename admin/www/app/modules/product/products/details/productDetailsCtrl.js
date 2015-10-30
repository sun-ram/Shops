angular.module('aviateAdmin.controllers').controller("productDetailController", 
		['$scope', '$http', '$rootScope','$localStorage','$state','$filter','$window', 'ngTableParams',
		 function($scope, $http, $rootScope, $localStorage, $state, $filter,$window, ngTableParams) {



			$scope.getProductDetails = function (){

				console.log($localStorage.productDetails);
				$scope.productDetail = $localStorage.productDetails;
				console.log($scope.productDetail);
			};

			$scope.editproduct = function(products){
				$scope.addshow=false;
				$localStorage.product ={};
				$localStorage.product.productId = products.productId;
				$localStorage.product.productName = products.productName;
				$localStorage.product.measurement =products.measurement;
				$localStorage.product.type = products.type;
				$localStorage.product.productPrice ={};
				$localStorage.product.productPrice.price=products.productPrice.price;
				$localStorage.product.productTypeId = products.productTypeId;
				$localStorage.product.productUnitOfMeasure = products.productUnitOfMeasure;
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







		}]);