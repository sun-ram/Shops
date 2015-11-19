angular.module('aviateAdmin.controllers')
.controller("taxEditCtrl", 
		['$scope', '$state','toastr','TaxServices',
		 function($scope, $state,  toastr, TaxServices) {

			$scope.getTax = function(){
				$scope.tax = TaxServices.getTaxObj();
				$scope.temp = localStorage.getItem('tax');
				if($scope.tax){
					localStorage.setItem('tax',JSON.stringify($scope.tax));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.tax = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('tax');
					$state.go('app.newtax');
				}
			};
			$scope.getTax();

			$scope.updateTax = function(){
				TaxServices.updateTax($scope.tax).then(function(data){
					localStorage.removeItem('tax');
					$state.go('app.tax');
				});
			};
			
			$scope.redirectToTax = function(tax){
				TaxServices.setTaxObj(tax);
				$state.go('app.tax');
			}

		}]);