angular.module('aviate.services')
.service('MyListServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                           function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {

	this.addToMyList = function(user){
		var d = $q.defer();
		api.MyList.addToMyList(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
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

	this.getMyList = function(user){
		var d = $q.defer();
		api.MyList.getMyList(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					$rootScope.myList = {};
					$rootScope.myList.listItem = [];
					for(var i = 0; i<result.customerMyList.length; i++){
						var noOfQuantityInCart = 0;
						for(var j = 0; j<$rootScope.myCart.cartItem.length; j++){
							if($rootScope.myCart.cartItem[j].productId == result.customerMyList[i].products.productId){
								noOfQuantityInCart = $rootScope.myCart.cartItem[j].quantity;
							}
						}
						result.customerMyList[i].products.noOfQuantityInCart = noOfQuantityInCart;
						$rootScope.myList.listItem.push(result.customerMyList[i]);
					}
					ipCookie('myList', $rootScope.myList);
					d.resolve($rootScope.myList);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};

	this.removeMyList = function(user){
		var d = $q.defer();
		api.MyList.removeMyList(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
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


}]);
