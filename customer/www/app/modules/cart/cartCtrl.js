angular.module('aviate.controllers')
.controller("cartCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT','$http','$rootScope','MyCartFactory','MyCartServices',
		 function($scope, $state, toastr, CONSTANT,$http,$rootScope, MyCartFactory,MyCartServices) {
			MyCartFactory.myCartTotalPriceCalculation();
			$rootScope.addToCart = function(product){
				MyCartFactory.addToCart(product,$scope.productList,  function(data){
					$scope.productList = data;
					$scope.getCartList();
				});
			}

			$scope.getCartList = function(){
				if($rootScope.user){
					MyCartServices.getCartList({"customerId" : $rootScope.user.userId, "storeId" : $rootScope.store.storeId},  function(data){
						MyCartFactory.checkCartProductsQuantity($scope.productList,function(data){
							$scope.productList = data;
						});
					});
				}
			}
			
			$rootScope.checkOut = function() {
				if($rootScope.user != null){
					$state.go('app.checkout');
				}else{
					toast.info('need to login first');
				}
			};
			
			$scope.removeFromMyCart = function(product, index) {
				MyCartFactory.removeFromCart(product, index);
			};

	}]);
