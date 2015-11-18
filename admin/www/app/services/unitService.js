angular.module('aviateAdmin.services')
.service('UnitService',['$q','api','toastr', function($q, api, toastr) {
	
	this.UnitList = function(unit){
		var d = $q.defer();
		api.Unit.unitList(unit, function(err, result){
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
	
	this.saveUnit = function(uom){
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
	
	this.deleteUnit = function(unit){
		var d = $q.defer();
		api.Unit.Delete(unit, function(err, result){
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