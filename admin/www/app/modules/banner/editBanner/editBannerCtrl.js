angular.module('aviateAdmin.controllers')
.controller("editBannerCtrl", 
		['$scope','$rootScope','$state','toastr','CommonServices','BannerServices','$localStorage',
		 function($scope,$rootScope,$state, toastr, CommonServices, BannerServices, $localStorage) {

			$scope.bannerDetail = BannerServices.getBannerObj();
			
			$scope.isTitleBoldExist = false;
			$scope.isTitleSmallExist = false;

			if($scope.bannerDetail != undefined){
				$scope.bannerImage = $scope.bannerDetail.image.url;
			}

			$scope.uploadFile = function (val1,val2){
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
						var id = $localStorage.banners[i].bannerId;
						if(title.toLowerCase() == oldTitle.toLowerCase() && id != $scope.bannerDetail.bannerId){
							$scope.bannerDetail.tabTitleBold = undefined;
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
						var id = $localStorage.banners[i].bannerId;
						if(title.toLowerCase() == oldTitle.toLowerCase() && id != $scope.bannerDetail.bannerId){
							$scope.bannerDetail.tabTitleSmall = undefined;
							toastr.error("Title For Regular Already Exist");
							return;
						}
					}
				}
			}
			
			$scope.updateBanner = function(bannerDetail){
				if(!$scope.isTitleSmallExist && !$scope.isTitleBoldExist){
				if (bannerDetail == undefined || bannerDetail.tabTitleBold == "" || bannerDetail.tabTitleBold==undefined) {
					toastr.error("Please Enter Title For Bold");
					return;
				}else if (bannerDetail.tabTitleSmall == "" || bannerDetail.tabTitleSmall==undefined) {
					toastr.error("Please Enter Title For Regular");			
					return;
				}
				bannerDetail.image.image=$scope.bannerImage.split(",")[1];
				bannerDetail.image.type=$scope.bannerImage ? ($scope.bannerImage.substring(11).split(";")[0]) : "";
				$scope.bannerDetail.userId = $rootScope.user.userId;
				BannerServices.updateBanner(bannerDetail).then(function(data){
					$scope.bannerDetail = null;
					$scope.isTitleSmallExist = false;
					$scope.isTitleBoldExist = false;
					$state.go('app.banner');
				});
			}
		}

		}]);