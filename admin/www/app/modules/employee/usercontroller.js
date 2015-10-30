angular.module('aviateAdmin.controllers')
.controller("usercontroller", ['$scope','$http','$rootScope','$state','$filter','ngTableParams','ipCookie',
		               function($scope, $http, $rootScope, $state, $filter, ngTableParams, ipCookie) {
		                	   $scope.addUser = function() {
		                		   $state.go('app.adduser');
		                	   }
		                	   $scope.getUser = function() {
		                		   var menuJson = angular.toJson({
		                			   "filterType": "ALL"
		                		   });
		                		   $http({
		                			   url: serviceUrl + 'aviate/json/product/getshoplist',
		                			   method: 'POST',
		                			   data: menuJson,
		                			   headers: {
		                				   'Content-Type': 'application/json'
		                			   }
		                		   })
		                		   .success(function(result, status, headers) {
		                					   var data = result.shoplist;
		                					   $scope.tableParams = new ngTableParams({
		                						   page: 1,
		                						   count: 10
		                					   }, {
		                						   total: data.length,
		                						   getData: function(
		                								   $defer,
		                								   params) {
		                							   var orderedData = params
		                							   .sorting() ? $filter(
		                							   'orderBy')
		                							   (
		                									   data,
		                									   params
		                									   .orderBy()) : data;
		                									   $defer
		                									   .resolve(orderedData
		                											   .slice(
		                													   (params
		                															   .page() - 1) * params
		                															   .count(),
		                															   params
		                															   .page()
		                															   .count()));
		                						   }
		                					   });
		                				   })
		                				   $scope.editUser = function(user) {
		                			   $rootScope.adduser = user;
		                			   $location.path("/adduser");
		                		   }

		                		   $rootScope.addUserFunc = function(adduser) {
		                			   var menuJson = angular.toJson({
		                				   "storeId": adduser.storeId,
		                				   "storeName": adduser.storeName,
		                				   "address": adduser.address,
		                				   "city": adduser.city,
		                				   "state": adduser.state,
		                				   "country": adduser.country,
		                				   "postalCode": adduser.postalCode,
		                				   "phoneNo": adduser.phone
		                			   });
		                			   $http({
		                				   url: serviceUrl + 'aviate/json/update/product/adduser',
		                				   method: 'POST',
		                				   data: menuJson,
		                				   headers: {
		                					   'Content-Type': 'application/json'
		                				   }
		                			   })
		                			   .success(
		                					   function(result, status,
		                							   headers) {
		                						   if (result.status == "SUCCESS") {
		                							   alert("User added successfully");
		                							   $scope.store = {};
		                						   } else {
		                							   alert(errorString);
		                						   }
		                					   })
		                		   }
		                	   }
		                   }
		                   ]);