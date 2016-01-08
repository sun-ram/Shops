angular.module('aviate.services')
.service('orderHistoryServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	this.getordersHistory = function(){
		var d = $q.defer();
        
        var orders ={
                    "customerId":$rootScope.user.userId
                    }
		api.ordersHistory.getordersHistory(orders, function(err, result){
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