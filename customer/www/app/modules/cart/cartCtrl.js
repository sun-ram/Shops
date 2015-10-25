angular.module('aviate.controllers')
.controller("cartCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT','$http','$rootScope',
		 function($scope, $state, toastr, CONSTANT,$http,$rootScope) {

			$rootScope.addToCartFun = function(product){
				MyCartFactory.addToCart(product,$scope.productList,  function(data){
					$scope.productList = data;
				});
			};
			
			$scope.removeFromMyCart = function(product, index) {
				MyCartFactory.removeFromCart(product, index);
			};

		}]);
