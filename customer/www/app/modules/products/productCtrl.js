angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products', '$rootScope', 'ipCookie', 'MyCartFactory', 'MyCartServices','$mdDialog',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope,ipCookie, MyCartFactory, MyCartServices,$mdDialog) {


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

/*			$scope.productDetails = function(products){
				$state.go('app.productsdetails',{productId:products.productId});
			}*/
			$scope.productDetails = function(ev,products){
				$rootScope.productDetails = products;
				$mdDialog.show({
					templateUrl: 'app/modules/products/productDetails/productDetails.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: "productDetailsCtrl"
				})
				.then(function() {
					
				}, function() {

				});
			}

		}]);

