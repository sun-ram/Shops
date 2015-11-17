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
			
			$scope.getCountries = function(){
				CommonServices.getCountries($scope.country).then(function(data){
					$scope.countries=data;
				});
			}
			
			$scope.getCountries();

			$scope.updateMerchant = function(){
				

				$scope.cnt = JSON.parse($scope.country);
				$scope.st = JSON.parse($scope.state);
				$scope.merchantDetail.user.address.country = {};
				$scope.merchantDetail.user.address.state = {};
				$scope.merchantDetail.user.address.country.countryId = $scope.cnt.countryId;
				$scope.merchantDetail.user.address.country.name = $scope.cnt.name;
				$scope.merchantDetail.user.address.state.stateId = $scope.st.stateId;
				$scope.merchantDetail.user.address.state.name = $scope.st.name;
				
				$scope.merchantDetail.logo = {};
				$scope.merchantDetail.logo.image=$scope.merchantLogo.split(",")[1];
				$scope.merchantDetail.logo.type=$scope.merchantLogo ? ($scope.merchantLogo.substring(11).split(";")[0]) : "";
				MerchantServices.addNewMerchant($scope.merchantDetail).then(function(data){
					$scope.merchantLogo=null;
					$scope.merchantDetail = null;
					$state.go('app.merchantdetails');
				});
			
				
				if ($scope.merchantLogo != "" && $scope.merchantLogo!=undefined && $scope.merchantLogo!=null) {
					$scope.merchantDetail.logo = {};
					$scope.merchantDetail.logo.image=$scope.merchantLogo.split(",")[1];
					$scope.merchantDetail.logo.type=$scope.merchantLogo ? ($scope.merchantLogo.substring(11).split(";")[0]) : "";
				} 
				
				MerchantServices.addNewMerchant($scope.merchantDetail).then(function(data){
					localStorage.removeItem('merchantDetails');
					$rootScope.merchantLogo="";
					$state.go('app.merchants');
				});
			};
			
			$scope.redirectToMerchantDetails = function(merchant){
				MerchantServices.setMerchantObj(merchant);
				$state.go('app.merchantdetails');
			}

		}]);