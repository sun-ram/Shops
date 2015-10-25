angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products','$rootScope','ipCookie','MyCartFactory',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope,ipCookie, MyCartFactory) {


			$scope.productList = products;

			$rootScope.addToCart = function(product){
				MyCartFactory.addToCart(product,$scope.productList,  function(data){
					$scope.productList = data;
				});
			}
			
			$scope.productDetails = function(products){
				$state.go('app.productsdetails',{productId:products.productId});
			}


		}]);

