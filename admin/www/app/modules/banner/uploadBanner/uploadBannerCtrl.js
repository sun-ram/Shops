angular.module('aviateAdmin.controllers')
.controller("uploadBannerCtrl", 
		['$scope', '$rootScope', '$state','toastr','CommonServices','BannerServices','$localStorage',
		 function($scope, $rootScope, $state, toastr, CommonServices, BannerServices, $localStorage) {
			var flag=false;
			$scope.uploadFile = function (val1,val2){
				var id =$('#'+val2).val();
				var srs=id.replace("C:\\fakepath\\" ,"" );	
				$('#'+val1).html(srs);
			}

			$scope.validateTitleBold = function(title){
			
				if(title != undefined){
					var len = $localStorage.banners.length;

					for(var i=0;i<len;i++){
						var oldTitle = $localStorage.banners[i].tabTitleBold;
						if(title.toLowerCase() == oldTitle.toLowerCase()){
							$scope.bannerDetail.tabTitleBold = undefined;
							toastr.warning("Title For Bold Already Exist");
							return;
						}
					}
				}
			}

			$scope.validateTitleSmall = function(title){

				if(title != undefined){
					var len = $localStorage.banners.length;

					for(var i=0;i<len;i++){
						var oldTitle = $localStorage.banners[i].tabTitleSmall;
						if(title.toLowerCase() == oldTitle.toLowerCase() ){
							$scope.bannerDetail.tabTitleSmall = undefined;
							toastr.warning("Title For Regular Already Exist");
							return;
						}
					}
				}
			}

			$scope.addBanner = function(bannerDetail){
				if(!flag){
					if ($scope.bannerDetail == undefined || $scope.bannerDetail.tabTitleBold == "" || $scope.bannerDetail.tabTitleBold==undefined) {
						toastr.warning("Please Enter Title For Bold");
						return;
					}else if ($scope.bannerDetail.tabTitleSmall == "" || $scope.bannerDetail.tabTitleSmall==undefined) {
						toastr.warning("Please Enter Title For Regular");	
						return;
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
					flag=true;
					$scope.bannerDetail.image.image = $scope.bannerImage.split(",")[1];
					$scope.bannerDetail.image.type = $scope.bannerImage ? ($scope.bannerImage.substring(11).split(";")[0]) : "";
					BannerServices.addNewBanner($scope.bannerDetail).then(function(data){
						flag=false;
						$scope.bannerDetail = null;
						$state.go('app.banner');
					});
				}
			}

		}]);