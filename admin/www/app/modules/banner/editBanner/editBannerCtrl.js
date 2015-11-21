angular.module('aviateAdmin.controllers')
.controller("editBannerCtrl", 
		['$scope', '$rootScope', '$state','toastr','CommonServices','BannerServices',
		 function($scope, $rootScope, $state, toastr, CommonServices, BannerServices) {

			$scope.bannerDetail = $rootScope.currentBanner;

			if($scope.bannerDetail != undefined){
				$scope.bannerImage = $scope.bannerDetail.image.url;
			}

			$scope.uploadFile = function (val1,val2){
				var id =$('#'+val2).val();
				var srs=id.replace("C:\\fakepath\\" ,"" );	
				$('#'+val1).html(srs);
			}

			$scope.updateBanner = function(bannerDetail){
				bannerDetail.image.image=$scope.bannerImage.split(",")[1];
				bannerDetail.image.type=$scope.bannerImage ? ($scope.bannerImage.substring(11).split(";")[0]) : "";
				BannerServices.updateBanner(bannerDetail).then(function(data){
					$scope.bannerDetail = null;
					$state.go('app.banner');
				});
			}

		}]);