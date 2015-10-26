angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products', '$rootScope', 'ipCookie', 'MyCartFactory', 'MyCartServices',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope,ipCookie, MyCartFactory, MyCartServices) {


			$scope.productList = products;

//			$rootScope.addToCart = function(product){
//				MyCartFactory.addToCart(product,$scope.productList,  function(data){
//					$scope.productList = data;
//					$scope.getCartList();
//				});
//			}
//
//			$scope.getCartList = function(){
//				if($rootScope.user){
//					MyCartServices.getCartList({"customerId" : $rootScope.user.userId, "storeId" : $rootScope.store.storeId},  function(data){
//						MyCartFactory.checkCartProductsQuantity($scope.productList,function(data){
//							$scope.productList = data;
//						});
//					});
//				}
//			}

			$scope.productDetails = function(products){
				$state.go('app.productsdetails',{productId:products.productId});
			}


		}]);

