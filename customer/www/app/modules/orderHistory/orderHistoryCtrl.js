angular.module('aviate.controllers')
.controller("orderHistoryCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$http', 'MyListServices', 'ipCookie','$rootScope','orderHistoryServices',
		 function($scope, $state, toastr, CONSTANT, $http, MyListServices, ipCookie,$rootScope,orderHistoryServices) {
             orderHistoryServices.getordersHistory().then(function(data){
                 $scope.data = data.salesOrderList;
                 console.log("data :: ", $scope.data );
             });
             
                 
             
     
  
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
