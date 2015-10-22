angular.module('aviate.controllers')
.controller("signInCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT',
		 function($scope, $state, toastr, CONSTANT) {

       toastr.info('SIGN IN CTRL');

}]);
