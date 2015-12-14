angular.module('aviateAdmin.services')
.service('EmployeeService',['$q', 'api', 'toastr', 'CONSTANT', function($q, api, toastr, CONSTANT) {
	this.employeeList = function(employee){
		var d = $q.defer();
		api.Employee.getList(employee, function(err, result){
			if (result.status == 'SUCCESS') {
				d.resolve(result.users);
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
				toastr.success("Employee Added Successfully");
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
				toastr.success(CONSTANT.UPDATEEMPLOYEE);
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
				toastr.success("Employee Deleted Successfully");
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
			if (result!= null && result.status == 'SUCCESS') {
				d.resolve(result);
			} else {
				toastr.error(result.errorString);
			}
		})
		return d.promise;
	};

}]);