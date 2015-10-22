angular.module('aviate.controllers')
.controller("checkOutCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT',
		 function($scope, $state, toastr, CONSTANT) {

       toastr.info('CHECK OUT CTRL');

}]);
