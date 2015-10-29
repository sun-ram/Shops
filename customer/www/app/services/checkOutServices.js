angular.module('aviate.services')
.service('CheckOutServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	
	this.timeLineStatus = {
            addressEntry: false,
            deliveryDate: false,
            verification: false,
            payment: false
        };
	
	this.currentOrder = {
            address:undefined,
            delivery: {
            	date:undefined,
            	time:undefined
            },
            contactNumber:undefined,
            isVerified:false
    };
	
	this.addAddress = function(user){
		var d = $q.defer();
		api.CheckOut.addAddress(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve($rootScope.myList);
				} else {
					toastr.error(CONSTANT.ERROR_CODE.VALIDADDRESS);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.getAddressList = function(user){
		var d = $q.defer();
		api.CheckOut.getAddressList(user, function(err, result){
			if(result){				
					ipCookie("myList",result);
					$rootScope.myList = ipCookie("myList");
					d.resolve($rootScope.myList);
				if(result.status === CONSTANT.STATUS.FAILURE)  {
					toastr.warning(CONSTANT.WARNING_CODE.ADDTHEADDERSS);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.removeAddress = function(user){
		var d = $q.defer();
		api.CheckOut.removeAddress(user, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
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
	
	this.confirmOrder = function(order){
		var d = $q.defer();
		api.CheckOut.confirmOrder(order, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.orderId);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
	
	this.payment = function(payment){
		var d = $q.defer();
		api.CheckOut.payment(payment, function(err, result){
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
