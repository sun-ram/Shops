angular.module('aviate.controllers')
.controller("myListCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT',
		 function($scope, $state, toastr, CONSTANT) {

       toastr.info('MY LIST CTRL');

}]);
