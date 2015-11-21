angular.module('aviateAdmin.controllers')
.controller("bannerCtrl", 
		['$scope', '$rootScope', '$state','toastr','CommonServices','$mdDialog','BannerServices',
		 function($scope, $rootScope, $state, toastr, CommonServices, $mdDialog, BannerServices) {

			$scope.banner = {};

			$scope.getBannerList = function(){
				var store = {};
				store.storeId = $rootScope.user.storeId;
				BannerServices.getBanner(store).then(function(data){
					$scope.bannerData=data;
				});
			}

			$scope.removeBanner = function(bannerId) {

				var confirm = $mdDialog.confirm()
				.title('Would you like to delete Banner?')
				.ok('Delete')
				.cancel('Cancel');
				$mdDialog.show(confirm).then(function() {
					$scope.banner.bannerId = bannerId;

					BannerServices.deleteBanner($scope.banner).then(function(data){
						$scope.getBannerList();
					});
				}, function() {

				});		
			}

			$scope.editBanner = function(banner){
				$rootScope.currentBanner = banner;
				$state.go('app.editbanner')
			}
		}]);