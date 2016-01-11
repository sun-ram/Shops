angular.module('aviate.services')
.service('MyCartServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	this.addToCart = function(user){
		var d = $q.defer();
		//{"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId, "productId" : productId, "price" : subTotal, "quantity" : quantity}
		api.MyCart.addToCart(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					/*ipCookie("myCart",result);
					$rootScope.myCart = ipCookie("myCart");*/
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.addProductsToCart = function(cartdetails){
		var d = $q.defer();
		//{"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId, "productId" : productId, "price" : subTotal, "quantity" : quantity}
		api.MyCart.addProductsToCart(cartdetails, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					/*ipCookie("myCart",result);
					$rootScope.myCart = ipCookie("myCart");*/
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	}
	
	this.getCartList = function(user){
		var d = $q.defer();
		//{"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId}
		api.MyCart.getCartList(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					$rootScope.myCart.cartItem = result.myCart;
					//ipCookie("myCart", $rootScope.myCart);
					localStorage.setItem('myCart',JSON.stringify($rootScope.myCart));
					d.resolve($rootScope.myCart );
				} else {
					/*toastr.error(result.errorString);*/
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.removeCartProduct = function(user){
		var d = $q.defer();
		//{"customerId" : $localStorage.userId, "storeId" : $rootScope.superMarketId, "productId" : productId}
		api.MyCart.removeCartProduct(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result);
				//	toastr.success(CONSTANT.SUCCESS_CODE.REMOVEPRODUCT);
				} else {
				//	toastr.error(result.errorString);
				}
			}else{
			//	toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};

}]);
