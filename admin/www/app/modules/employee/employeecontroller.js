aviateAdmin.controller("employeecontroller", ['$scope','$localStorage','$location','$state','$mdDialog','EmployeeService','toastr','CONSTANT','$rootScope',
                                              function($scope,$localStorage, $location,$state,$mdDialog,EmployeeService,toastr,CONSTANT, $rootScope) {

	if (angular.isDefined($localStorage.userDetails)) {
		$scope.user = $localStorage.userDetails;
	}	
	
	if($rootScope.user.role == 'STOREADMIN'){
		$scope.isStoreAdmin = true;
	}else{
		$scope.isStoreAdmin = false;
	}
	
	$scope.query = {
			limit: 5,
			page: 1
	};

	$scope.count = 3;
	$scope.srch = true;

	$scope.close = function () {
		$state.go('app.aviateemployees');
	};

	$scope.getEmployee = function() {
		$localStorage.employees = {};
		$scope.employee = {};
		$rootScope.user.storeId ? ($scope.employee.storeId = $rootScope.user.storeId) : '' ;
		$scope.shopList();
		EmployeeService.employeeList($scope.employee).then(function(data) {
			$scope.data = data;

		});
	};

	$scope.saveEmployee = function(employee) {
		if($rootScope.user.role=="STOREADMIN"){
		employee.storeId = $rootScope.user.storeId;
		}
		EmployeeService.saveEmployee(employee).then(function(data) {
			toastr.success("Employee Added Successfully");
			$state.go('app.aviateemployees');
		});
	}

	$scope.updateEmployee = function() {
		EmployeeService.updateEmployee($scope.user).then(function(data) {
			toastr.success(CONSTANT.UPDATEEMPLOYEE);
			$state.go('app.aviateemployees');
		});
	}

	$scope.deleteEmployee = function(customerId) {
		var confirm = $mdDialog.confirm()
	      .title('Would you like to delete Employee?')
	        .ok('Delete')
		       .cancel('Cancel');
		 $mdDialog.show(confirm).then(function() {
	
			$scope.customer={};
			$scope.customer.customerId = customerId;
			EmployeeService.deleteEmployee($scope.customer).then(function(data) {
			toastr.success("Employee Deleted Successfully");
					$scope.getEmployee();
				});
		  }, function() {
		  
		  });
					}


	$scope.addemployee = function(){
		$localStorage.user = {};
		$scope.user = {};
		$state.go('app.addemployee');

	},

	$scope.shopList = function(){
		EmployeeService.getShopList().then(function(data) {
			$localStorage.shops = {};
			$localStorage.shops = data.shoplist;
		});
		$scope.shops = {};
		$scope.shops = $localStorage.shops;

	},

	$scope.employeeDetails = function(employee){
		$localStorage.employees = employee;
		$state.go('app.employeedetailsview');

	},

	$scope.employee = $localStorage.employees;

	$scope.cancel = function(){
		$state.go('app.aviateemployees');

	},

	$scope.editEmployee = function(employee) {
		$scope.employee = employee;
		$scope.user = {};
		$scope.user.customerDetailsId = employee.customerDetails.customerDetailsId;
		$scope.user.customerId = employee.customerId;
		$scope.user.firstName = employee.address[0].firstName;
		$scope.user.lastName = employee.address[0].lastName;
		$scope.user.role = employee.role;
		$scope.user.storeId = employee.customerDetails.storeId;
		$scope.user.phoneNo = employee.customerDetails.phoneNo;
		$scope.user.emailId = employee.customerDetails.emailId;
		$scope.user.address = employee.address[0].address;
		$scope.user.city = employee.address[0].city;
		$scope.user.state = employee.address[0].state;
		$scope.user.status = employee.status;
		$scope.user.country =	 employee.address[0].country;
		$scope.user.postalCode =	 employee.address[0].postalCode;
		//$scope.user.createdBy =	 employee.customerDetails.createdBy;
		$localStorage.user = $scope.user;
		$state.go('app.addemployee');
	}
	$scope.user = $localStorage.user;
}
]);