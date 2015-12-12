angular.module('aviateAdmin.services')
.service('deliveryTimeSlotService',['$q','api','toastr','$rootScope', function($q, api, toastr, $rootScope) {
	
	this.getDeliveryTimeSlots = function(){
		var deliverTime = {
				"merchant":{"merchantId":$rootScope.user.merchantId}
				
		};
		var d = $q.defer();
		api.DeliveryTimeSlot.getList(deliverTime, function(err, result){
			d.resolve(result);
		})
		return d.promise;
	};
	
	this.UpdateDeliveryTimeSlot = function(deliveryTimeSlot){
		var d = $q.defer();
		api.DeliveryTimeSlot.save(deliveryTimeSlot, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.saveDeliveryTimeSlotService = function(deliveryTimeSlot){
		var d = $q.defer();
		api.DeliveryTimeSlot.save(deliveryTimeSlot, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.deleteDeliveryTimeSlot = function(deliveryTimeSlot){
		var d = $q.defer();
		api.DeliveryTimeSlot.deleteDeliveryTimeSlot(deliveryTimeSlot, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	
}
]);