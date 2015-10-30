angular.module('aviateAdmin.controllers')
.controller("merchantEditCtrl", 
		['$scope', '$rootScope','$state','toastr','MerchantServices',
		 function($scope,$rootScope, $state,  toastr, MerchantServices) {

			$scope.getMerchant = function(){
				$scope.merchantDetail = MerchantServices.getMerchantObj();
				$scope.temp = localStorage.getItem('merchantDetails');
				if($scope.merchantDetail){
					localStorage.setItem('merchantDetails',JSON.stringify($scope.merchantDetail));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.merchantDetail = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('merchantDetails');
					$state.go('app.newmerchant');
				}
			};
			$scope.getMerchant();

			$scope.updateMerchant = function(){
				if ($scope.merchantImage != "" && $scope.merchantImage!=undefined && $scope.merchantImage!=null) {
					$scope.merchantDetail.merchantImage=$scope.merchantImage ? (($scope.merchantImage.substring(11).split(";")[0].length < 4) ? $scope.merchantImage.substring(22) : 
						$scope.merchantImage.substring(23)) : "";
					$scope.merchantDetail.merchantImageType=$scope.merchantImage ? ($scope.merchantImage.substring(11).split(";")[0]) : "";
				} 
				
				MerchantServices.addNewMerchant($scope.merchantDetail).then(function(data){
					localStorage.removeItem('merchantDetails');
					$rootScope.merchantImage="";
					$state.go('app.merchants');
				});
			};
			
			$scope.redirectToMerchantDetails = function(merchant){
				MerchantServices.setMerchantObj(merchant);
				$state.go('app.merchantdetails');
			}

		}]);