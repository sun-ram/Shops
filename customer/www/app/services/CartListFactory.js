angular.module('aviate.services')
.factory('CartPriceCalculationService',['$q','api','toastr','CONSTANT','ipCookie','$scope',
                                 function($q, api, toastr, CONSTANT, ipCookie, $scope) {

	this.myCartTotalPriceCalculation = function(){
		
		$scope.taxAmount = 0;
		$scope.grossAmount = 0;
		$scope.cartTotalAmount = 0;
		var totalAmount = 0;
		for(var i=0; i<$scope.cartItem.length; i++){
			var subTotal = 0;
			subTotal = $scope.cartItem[i].noOfQuantityInCart * $scope.cartItem[i].productDetails.productPrice.price;
			$scope.cartItem[i].subTotal = subTotal;
			totalAmount += subTotal;
		}

		$localStorage.localStoreCartlist = $scope.cartItem;
		$scope.cartTotalAmount = totalAmount;
		$localStorage.myCart.cartTotalAmount = $scope.cartTotalAmount;
		$scope.taxAmount = $scope.cartTotalAmount*($scope.tax/100);
		$localStorage.myCart.taxAmount = $scope.taxAmount;
		$scope.grossAmount = $scope.taxAmount+$scope.cartTotalAmount+$scope.shippingCharges;
		$localStorage.myCart.grossAmount =$scope.grossAmount;
		d.resolve();
		return d.promise;
	};
}]);
