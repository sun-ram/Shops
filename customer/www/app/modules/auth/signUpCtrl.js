angular.module('aviate.controllers')
.controller("signUpCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT',
		 function($scope, $state, toastr, CONSTANT) {

       toastr.info('SIGN UP CTRL');

}]);
