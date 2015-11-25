angular.module('aviateAdmin.services')
.service('deliveryTimeSlotService',['$q','api','toastr', function($q, api, toastr) {
	
	this.getDeliveryTimeSlots = function(uom){
		var d = $q.defer();
		api.DeliveryTimeSlot.getList(uom, function(err, result){
			d.resolve(result);
		})
		return d.promise;
	};
	
	this.UpdateUnit = function(uom){
		var d = $q.defer();
		api.Unit.Update(uom, function(err, result){
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
	
	this.deleteUnit = function(uom){
		var d = $q.defer();
		api.Unit.Delete(uom, function(err, result){
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