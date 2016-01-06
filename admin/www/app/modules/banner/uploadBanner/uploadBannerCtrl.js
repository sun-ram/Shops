angular.module('aviateAdmin.controllers')
.controller("uploadBannerCtrl", 
		['$scope', '$rootScope', '$state','toastr','CommonServices','BannerServices','$localStorage',
		 function($scope, $rootScope, $state, toastr, CommonServices, BannerServices, $localStorage) {
			var flag=false;
			$scope.isTitleBoldExist = false;
			$scope.isTitleSmallExist = false;
			
			$scope.uploadFile = function (val1,val2){
				$scope.showtextbannner = true;
				var id =$('#'+val2).val();
				var srs=id.replace("C:\\fakepath\\" ,"" );	
				$('#'+val1).html(srs);
			}

			$scope.validateTitleBold = function(title){
				$scope.isTitleBoldExist = false;
				
				if(title != undefined){
					var len = $localStorage.banners.length;

					for(var i=0;i<len;i++){
						var oldTitle = $localStorage.banners[i].tabTitleBold;
						if(title.toLowerCase() == oldTitle.toLowerCase()){
							$scope.bannerDetail.tabTitleBold = undefined;
							$scope.isTitleBoldExist = true;
							toastr.error("Title For Bold Already Exist");
							return;
						}
					}
				}
			}

			$scope.validateTitleSmall = function(title){
				$scope.isTitleSmallExist = false;

				if(title != undefined){
					var len = $localStorage.banners.length;

					for(var i=0;i<len;i++){
						var oldTitle = $localStorage.banners[i].tabTitleSmall;
						if(title.toLowerCase() == oldTitle.toLowerCase() ){
							$scope.bannerDetail.tabTitleSmall = undefined;
							$scope.isTitleSmallExist = true;
							toastr.error("Title For Regular Already Exist");
							return;
						}
					}
				}
			}

			$scope.addBanner = function(bannerDetail){
				if(!flag && !$scope.isTitleSmallExist && !$scope.isTitleBoldExist){
					if ($scope.bannerDetail == undefined || $scope.bannerDetail.tabTitleBold == "" || $scope.bannerDetail.tabTitleBold==undefined) {
						toastr.error("Please Enter Title For Bold");
						return;
					}else if ($scope.bannerDetail.tabTitleSmall == "" || $scope.bannerDetail.tabTitleSmall==undefined) {
						toastr.error("Please Enter Title For Regular");	
						return;
					}else if ($scope.bannerImage == "" || $scope.bannerImage==undefined) {
						toastr.error("Please select banner Image");
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
					$scope.bannerDetail.userId = $rootScope.user.userId;
					BannerServices.addNewBanner($scope.bannerDetail).then(function(data){
						flag=false;
						$scope.isTitleSmallExist = false;
						$scope.isTitleBoldExist = false;
						$scope.bannerDetail = null;
						$state.go('app.banner');
					});
				}
			}

		}]);