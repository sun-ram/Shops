angular.module('aviate.services')
.factory('CartPriceCalculationService',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                                 function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {

	this.myCartTotalPriceCalculation = function(){
		
		$rootScope.taxAmount = 0;
		$rootScope.grossAmount = 0;
		$rootScope.cartTotalAmount = 0;
		var totalAmount = 0;
		for(var i=0; i<$rootScope.cartItem.length; i++){
			var subTotal = 0;
			subTotal = $rootScope.cartItem[i].noOfQuantityInCart * $rootScope.cartItem[i].productDetails.productPrice.price;
			$rootScope.cartItem[i].subTotal = subTotal;
			totalAmount += subTotal;
		}

		$localStorage.localStoreCartlist = $rootScope.cartItem;
		$rootScope.cartTotalAmount = totalAmount;
		$localStorage.myCart.cartTotalAmount = $rootScope.cartTotalAmount;
		$rootScope.taxAmount = $rootScope.cartTotalAmount*($rootScope.tax/100);
		$localStorage.myCart.taxAmount = $rootScope.taxAmount;
		$rootScope.grossAmount = $rootScope.taxAmount+$rootScope.cartTotalAmount+$rootScope.shippingCharges;
		$localStorage.myCart.grossAmount =$rootScope.grossAmount;
		d.resolve();
		return d.promise;
	};
}]);
