angular.module('aviateAdmin.controllers')
	.controller("createMerchantCtrl", 
	['$scope', '$state','toastr','MerchantServices','CommonServices',
	 function($scope, $state, toastr, MerchantServices, CommonServices) {

		$scope.addMerchant = function(){
			if ($scope.merchantLogo == "" || $scope.merchantLogo==undefined) {
				toastr.warning("Please select merchant logo");
				return;
			} 
			
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
				$state.go('app.merchant');
			});
		};
	
		$scope.uploadFile = function (val1,val2){
			var id =$('#'+val2).val();
			var srs=id.replace("C:\\fakepath\\" ,"" );	
			$('#'+val1).html(srs);
		}
	}]);