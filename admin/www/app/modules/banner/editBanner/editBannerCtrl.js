angular.module('aviateAdmin.controllers')
.controller("editBannerCtrl", 
		['$scope', '$state','toastr','CommonServices','BannerServices','$localStorage',
		 function($scope, $state, toastr, CommonServices, BannerServices, $localStorage) {

			$scope.bannerDetail = BannerServices.getBannerObj();

			if($scope.bannerDetail != undefined){
				$scope.bannerImage = $scope.bannerDetail.image.url;
			}

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
						var id = $localStorage.banners[i].bannerId;
						if(title.toLowerCase() == oldTitle.toLowerCase() && id != $scope.bannerDetail.bannerId){
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
						var id = $localStorage.banners[i].bannerId;
						if(title.toLowerCase() == oldTitle.toLowerCase() && id != $scope.bannerDetail.bannerId){
							$scope.bannerDetail.tabTitleSmall = undefined;
							toastr.warning("Title For Regular Already Exist");
							return;
						}
					}
				}
			}
			
			$scope.updateBanner = function(bannerDetail){
				if (bannerDetail == undefined || bannerDetail.tabTitleBold == "" || bannerDetail.tabTitleBold==undefined) {
					toastr.warning("Please Enter Title For Bold");
					return;
				}else if (bannerDetail.tabTitleSmall == "" || bannerDetail.tabTitleSmall==undefined) {
					toastr.warning("Please Enter Title For Regular");			
					return;
				}
				bannerDetail.image.image=$scope.bannerImage.split(",")[1];
				bannerDetail.image.type=$scope.bannerImage ? ($scope.bannerImage.substring(11).split(";")[0]) : "";
				BannerServices.updateBanner(bannerDetail).then(function(data){
					$scope.bannerDetail = null;
					$state.go('app.banner');
				});
			}

		}]);