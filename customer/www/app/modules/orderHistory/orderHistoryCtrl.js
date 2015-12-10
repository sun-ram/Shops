angular.module('aviate.controllers')
.controller("orderHistoryCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$http', 'MyListServices', 'ipCookie','$rootScope',
		 function($scope, $state, toastr, CONSTANT, $http, MyListServices, ipCookie,$rootScope) {
             
             $scope.data = [{
    picture: "https://randomuser.me/api/portraits/med/women/60.jpg",
    firstname: "Georgia",
    lastname: "Flores",
    email: "georgia.flores55@example.com",
    birthday: "12/1/1982"
  }, {
    picture: "https://randomuser.me/api/portraits/med/women/5.jpg",
    firstname: "Avery",
    lastname: "Tucker",
    email: "avery.tucker67@example.com",
    birthday: "4/6/1983",
    data: ""
  }];
  
  $scope.columns = [{
    headerName: "",
    fieldName: "picture"
  }, {
    headerName: "Lastname",
    fieldName: "lastname"
  }, {
    headerName: "Firstname",
    fieldName: "firstname"
  }, {
    headerName: "Email",
    fieldName: "email"
  }, {
    headerName: "Birthday",
    fieldName: "birthday"
  }];
 
}]);
