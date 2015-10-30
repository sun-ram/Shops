angular.module('aviateAdmin.controllers').controller("employeecontroller", 
		['$scope', '$http', '$rootScope','$localStorage','$state','$filter','$window', 'ngTableParams',
		 function($scope, $http, $rootScope, $localStorage, $state, $filter,$window, ngTableParams) {
			
			$scope.createEmployee = function() {
				
				alert("hiii");
			}
		}]);