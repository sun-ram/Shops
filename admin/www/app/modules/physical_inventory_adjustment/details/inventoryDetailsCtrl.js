angular.module('aviateAdmin.controllers')
.controller("inventoryDetailsCtrl", 
		['$scope', '$rootScope','$state','$filter','$window', 'ngTableParams','inventory',
		 function($scope, $rootScope, $state, $filter,$window, ngTableParams,inventory) {
			
			$scope.inventory = inventory;
			

		}]);