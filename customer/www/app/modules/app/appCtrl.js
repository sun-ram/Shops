angular.module('aviate.controllers')
.controller("appCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT',
		 function($scope, $state, toastr, CONSTANT) {

       toastr.info(' APP CTRL');

}]);
