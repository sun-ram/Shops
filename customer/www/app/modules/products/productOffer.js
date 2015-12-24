angular.module('aviate.controllers')
.controller("productOfferCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','$stateParams', '$rootScope', 'ipCookie', 'MyCartFactory', 'MyCartServices','$mdDialog','MyListServices',
		 function($scope, $state, toastr, CONSTANT, ProductService, $stateParams,$rootScope,ipCookie, MyCartFactory, MyCartServices,$mdDialog,MyListServices) {

			
			$scope.getProductOffer = function(){
				
				$scope.productOffer = {};
				$scope.productOffer.productOfferId = $stateParams.offerId;
				ProductService.getProductOffer($scope.productOffer).then(function(data){
					$scope.productOffer = data;
					
				});
			}
			
			
		}]);