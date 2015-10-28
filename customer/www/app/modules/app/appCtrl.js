angular.module('aviate.controllers')
.controller("appCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$rootScope', 'MyCartFactory', 'MyCartServices',
		 function($scope, $state, toastr, CONSTANT, $rootScope, MyCartFactory, MyCartServices) {

       
		$scope.checkOut = function() {
			if($rootScope.user != null){
				$state.go('app.checkout');
			}else{
				$rootScope.signInPopup();
			}
		};
		
		$scope.addToCart = function(product){
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
		
		$scope.removeFromMyCart = function(product, index) {
			MyCartFactory.removeFromCart(product, index);
		};
       

}]);
