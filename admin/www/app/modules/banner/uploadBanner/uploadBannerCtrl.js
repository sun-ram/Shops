angular.module('aviateAdmin.controllers')
	.controller("uploadBannerCtrl", 
	['$scope', '$rootScope', '$state','toastr','CommonServices','BannerServices',
	 function($scope, $rootScope, $state, toastr, CommonServices, BannerServices) {
				
		$scope.uploadFile = function (val1,val2){
			var id =$('#'+val2).val();
			var srs=id.replace("C:\\fakepath\\" ,"" );	
			$('#'+val1).html(srs);
		}
		
		$scope.addBanner = function(bannerDetail){
			if ($scope.bannerImage == "" || $scope.bannerImage==undefined) {
					toastr.warning("Please select banner Image");
					return;
				} 
				$scope.bannerDetail.merchant = {} ;
				$scope.bannerDetail.store = {};
				$scope.bannerDetail.image = {};
				$scope.bannerDetail.store.storeId = $rootScope.user.storeId;
				$scope.bannerDetail.merchant.merchantId = $rootScope.user.merchantId;
				$scope.bannerDetail.image.image=$scope.bannerImage.split(",")[1];
				$scope.bannerDetail.image.type=$scope.bannerImage ? ($scope.bannerImage.substring(11).split(";")[0]) : "";
				BannerServices.addNewBanner($scope.bannerDetail).then(function(data){
					$scope.bannerDetail = null;
					$state.go('app.banner');
				});
			}
						
	}]);