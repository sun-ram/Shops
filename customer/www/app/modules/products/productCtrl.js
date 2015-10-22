angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products',
		 function($scope, $state, toastr, CONSTANT, ProductService, products) {
				

			$scope.productList = products;




		}]);

