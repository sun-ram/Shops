angular.module('aviateAdmin.controllers')
	.controller("createMerchantCtrl", 
	['$scope','$rootScope','$state','toastr','MerchantServices','CommonServices',
	 function($scope,$rootScope,$state, toastr, MerchantServices, CommonServices) {

		$scope.addMerchant = function(){
			if ($scope.merchantLogo == "" || $scope.merchantLogo==undefined) {
				toastr.warning("Please select merchant logo");
				return;
			} 
			
			$scope.cnt = $scope.country;
			$scope.merchantDetail.user.address.country = {};
			$scope.merchantDetail.user.address.state = {};
			$scope.merchantDetail.user.address.city = {};
			$scope.merchantDetail.user.address.country.countryId = $scope.cnt.countryId;
			$scope.merchantDetail.user.address.country.name = $scope.cnt.name;
			$scope.merchantDetail.user.address.state.stateId = $scope.state.stateId;
			$scope.merchantDetail.user.address.state.name = $scope.state.name;
			$scope.merchantDetail.user.address.city.cityId=$scope.cty.cityId;
			$scope.merchantDetail.user.address.city.name=$scope.cty.name;
			$scope.merchantDetail.userId=$rootScope.user.userName;
			$scope.merchantDetail.logo = {};
			$scope.merchantDetail.logo.image=$scope.merchantLogo.split(",")[1];
			$scope.merchantDetail.logo.type=$scope.merchantLogo ? ($scope.merchantLogo.substring(11).split(";")[0]) : "";
			MerchantServices.addNewMerchant($scope.merchantDetail).then(function(data){
				$scope.merchantLogo=null;
				$scope.merchantDetail = null;
				$state.go('app.merchants');
			});
		};
	
		$scope.uploadFile = function (val1,val2){
			$scope.showtext = true;
			var id =$('#'+val2).val();
			var srs=id.replace("C:\\fakepath\\" ,"" );	
			$('#'+val1).html(srs);
		}
	}]);