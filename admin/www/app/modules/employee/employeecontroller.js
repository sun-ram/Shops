aviateAdmin.controller("employeecontroller", ['$scope','$localStorage','$location','$state','$mdDialog','EmployeeService','toastr','CONSTANT','$rootScope','CommonServices','StoreServices','$timeout',
                                              function($scope,$localStorage, $location,$state,$mdDialog,EmployeeService,toastr,CONSTANT, $rootScope, CommonServices, StoreServices,$timeout) {

	if (angular.isDefined($localStorage.userDetails)) {
		$scope.user = $localStorage.userDetails;
	}
	
	$scope.getStores=function(){
		StoreServices.getStore({'merchantId':$rootScope.user.merchantId}).then(function(data){
			$scope.stores=data;
			console.info($scope.stores);
		});
	};
	
	$scope.getEmployee = function() {
		if($rootScope.user.role=="MERCHANTADMIN"){
			$scope.employee = {
					"merchantId":$rootScope.user.merchantId
			};
		} else if($rootScope.user.role=="STOREADMIN"){
			
			$scope.employee = {
				"storeId":$rootScope.user.storeId
			};
		}
		//$localStorage.employees = {};
		//$rootScope.user.storeId ? ($scope.employee.storeId = $rootScope.user.storeId) : '' ;
		//$scope.shopList();
		EmployeeService.employeeList($scope.employee).then(function(data) {
			$scope.data = data;

		});
	};
	
	$scope.getStoreList = function(){
		if($rootScope.user.role == 'MERCHANTADMIN'){
			$scope.isMerchantAdmin = true;
			$scope.getStores();
		}else{
			$scope.isMerchantAdmin = false;
		}
	}

	$scope.count = 3;
	$scope.srch = true;

	$scope.close = function () {
		$state.go('app.aviateemployees');
	};

	$scope.getState = function(country){
		$scope.states = country.states;
	}
	
	$scope.saveEmployee = function() {
		$scope.user.address.country = {};
		$scope.cnt = $scope.country;
		$scope.user.address.country.countryId = $scope.cnt.countryId;
		$scope.user.address.country.name = $scope.cnt.name;
		$scope.sta = $scope.state;
		$scope.user.address.state = $scope.sta;
		if($rootScope.user.role=="STOREADMIN"){
			$scope.user.store = {};
			$scope.user.store.storeId = $rootScope.user.storeId
		}
			$scope.user.merchant = {};
			$scope.user.merchant.merchantId = $rootScope.user.merchantId
		
		EmployeeService.saveEmployee($scope.user).then(function(data) {
			$state.go('app.aviateemployees');
		});
	}

	$scope.updateEmployee = function() {
		$scope.user.address.country = {};
		$scope.cnt = $scope.country;
		$scope.user.address.country.countryId = $scope.cnt.countryId;
		$scope.user.address.country.name = $scope.cnt.name;
		$scope.sta = $scope.state;
		$scope.user.address.state = $scope.sta;
		if($rootScope.user.role=="STOREADMIN"){
			$scope.user.store = {};
			$scope.user.store.storeId = $rootScope.user.storeId
		}
		$scope.user.merchant = {};
		$scope.user.merchant.merchantId = $rootScope.user.merchantId
		EmployeeService.updateEmployee($scope.user).then(function(data) {
			$state.go('app.aviateemployees');
		});
	}

	$scope.deleteEmployee = function(userId) {
		var confirm = $mdDialog.confirm().title('Would you like to delete Employee?')
		.ok('Delete')
		.cancel('Cancel');
		$mdDialog.show(confirm).then(function() {
			$scope.customer={};
			$scope.customer.userId = userId;
			EmployeeService.deleteEmployee($scope.customer).then(function(data) {
				$scope.getEmployee();
			});
		}, function() {

		});
	}


	$scope.addemployee = function(){
		$localStorage.user = {};
		$scope.user = {};
		$state.go('app.addemployee');

	};

	$scope.shopList = function(){
		EmployeeService.getShopList().then(function(data) {
			$localStorage.shops = {};
			$localStorage.shops = data.shoplist;
		});
		$scope.shops = {};
		$scope.shops = $localStorage.shops;

	};

	$scope.employeeDetails = function(employee){
		$localStorage.employees = employee;
		$state.go('app.employeedetailsview');

	};

	var populateEmployee = function(){
		if(!$scope.countries){
			$timeout(function(){populateEmployee()},2000);
		}else{
			$scope.user = $localStorage.user;
			if ($scope.user && $scope.user.address) {
				$scope.country = $scope.user.address.country;
				$scope.states = _.findWhere($scope.countries,{countryId:$scope.country.countryId}).states;
				$scope.state = $scope.user.address.state;
			}	
		}
	}
	
	if($scope.countries){
		populateEmployee();
	}else{
		$timeout(function(){populateEmployee()},3000)
	}

	$scope.employee = $localStorage.employees;
	
	$scope.redirectToEmployeeDetails = function(){
		if($rootScope.fromDetailsPage == true){
			$state.go('app.employeedetailsview');
			$rootScope.fromDetailsPage = false;
		} else {
			$state.go('app.aviateemployees');
		}
	}

	$scope.editEmployee = function(user,detail) {
		/*$scope.employee = employee;
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
		$scope.user.postalCode =	 employee.address[0].postalCode;*/
		//$scope.user.createdBy =	 employee.customerDetails.createdBy;
		
		$localStorage.user = user;
		if(detail == true)
			$rootScope.fromDetailsPage = true;
		$state.go('app.addemployee');
	}
}
]);