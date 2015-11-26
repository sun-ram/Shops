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
				if ($scope.bannerDetail.tabTitleBold == "" || $scope.bannerDetail.tabTitleBold==undefined) {
					toastr.warning("Please Enter Banner Title 1");
					return;
				}else if ($scope.bannerDetail.tabTitleSmall == "" || $scope.bannerDetail.tabTitleSmall==undefined) {
					toastr.warning("Please Enter Banner Title 2");					return;
				}else if ($scope.bannerImage == "" || $scope.bannerImage==undefined) {
					toastr.warning("Please select banner Image");
					return;
				} 
				$scope.bannerDetail.merchant = {} ;
				$scope.bannerDetail.store = {};
				$scope.bannerDetail.image = {};
				if($rootScope.user.role =="SUPERADMIN"){
					$scope.bannerDetail.isShopsbackerBanner = 'Y';
				}else{
					$scope.bannerDetail.isShopsbackerBanner = 'N';
					$scope.bannerDetail.store.storeId = $rootScope.user.storeId;
					$scope.bannerDetail.merchant.merchantId = $rootScope.user.merchantId;
				}
				/*if($rootScope.user.storeId != null || $rootScope.user.storeId != undefined){
				   $scope.bannerDetail.store.storeId = $rootScope.user.storeId;
					$scope.bannerDetail.store.storeId = 2;
				}
				if($rootScope.user.merchantId != null || $rootScope.user.merchantId != undefined){
					$scope.bannerDetail.merchant.merchantId = $rootScope.user.merchantId;
					$scope.bannerDetail.merchant.merchantId = 1;
				}*/
				$scope.bannerDetail.image.image = $scope.bannerImage.split(",")[1];
				$scope.bannerDetail.image.type = $scope.bannerImage ? ($scope.bannerImage.substring(11).split(";")[0]) : "";
				BannerServices.addNewBanner($scope.bannerDetail).then(function(data){
					$scope.bannerDetail = null;
					$state.go('app.banner');
				});
			}

		}]);