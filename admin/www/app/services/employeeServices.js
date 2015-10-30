angular.module('aviateAdmin.services')
.service('EmployeeService',['$q','api','toastr', function($q, api, toastr) {
	this.employeeList = function(employee){
		var d = $q.defer();
		api.Employee.getList(employee, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result.customerList);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.saveEmployee = function(employee){
		var d = $q.defer();
		api.Employee.save(employee, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};	
	
	this.updateEmployee = function(employee){
		var d = $q.defer();
		api.Employee.update(employee, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.deleteEmployee = function(employee){
		var d = $q.defer();
		api.Employee.deleteEmployee(employee, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};
	
	this.getShopList = function(){
		var d = $q.defer();
		var store ={};
		api.Employee.shopList(store,function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};

}]);