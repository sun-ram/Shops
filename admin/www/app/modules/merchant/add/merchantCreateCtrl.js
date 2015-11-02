angular.module('aviateAdmin.controllers')
	.controller("createMerchantCtrl", 
	['$scope', '$state','toastr','MerchantServices',
	 function($scope, $state, toastr, MerchantServices) {
		$scope.addMerchant = function(){
			if ($scope.merchantImage == "" || $scope.merchantImage==undefined) {
				toastr.warning("Please select merchant logo");
				return;
			} 
			$scope.merchantDetail.merchantImage=$scope.merchantImage ? (($scope.merchantImage.substring(11).split(";")[0].length < 4) ? $scope.merchantImage.substring(22) : 
				$scope.merchantImage.substring(23)) : "";
			$scope.merchantDetail.merchantImageType=$scope.merchantImage ? ($scope.merchantImage.substring(11).split(";")[0]) : "";
			MerchantServices.addNewMerchant($scope.merchantDetail).then(function(data){
				$scope.merchantImage=null;
				$scope.merchantDetail = null;
			});
		};
	
		$scope.uploadFile = function (val1,val2){
			var id =$('#'+val2).val();
			var srs=id.replace("C:\\fakepath\\" ,"" );	
			$('#'+val1).html(srs);
		}
	}]);