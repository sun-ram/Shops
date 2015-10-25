angular.module('aviate.controllers')
.controller("homeCtrl",
		['$scope', '$state', '$interval', 'toastr', 'CONSTANT', 'ProductService','homePageServices','$rootScope','$mdDialog','$log','LocationService',
		 function($scope, $state, $interval ,toastr, CONSTANT, ProductService, homePageServices, $rootScope,$mdDialog,$log,LocationService) {

			$scope.images = [
			                 {
			                	 src: "assets/images/banner1.png",
			                	 alt: "image 1"
			                 },
			                 {
			                	 src: "assets/images/higgidy_2.jpg",
			                	 alt: "image 2"
			                 },
			                 {
			                	 src: "assets/images/higgidy_3.jpg",
			                	 alt: "image 3"
			                 },
			                 {
			                	 src: "assets/images/higgidy_3.jpg",
			                	 alt: "image 3"
			                 }
			                 ]

			$scope.topCategoryList = [
			                          { title: 'One', content: "Tabs will become paginated if there isn't enough room for them."},
			                          { title: 'Two', content: "You can swipe left and right on a mobile device to change tabs."},
			                          { title: 'Three', content: "You can bind the selected tab via the selected attribute on the md-tabs element."},
			                          { title: 'Four', content: "If you set the selected tab binding to -1, it will leave no tab selected."},
			                          { title: 'Five', content: "If you remove a tab, it will try to select a new one."},
			                          { title: 'Six', content: "There's an ink bar that follows the selected tab, you can turn it off if you want."},
			                          { title: 'Seven', content: "If you set ng-disabled on a tab, it becomes unselectable. If the currently selected tab becomes disabled, it will try to select the next tab."},
			                          { title: 'Eight', content: "If you look at the source, you're using tabs to look at a demo for tabs. Recursion!"},
			                          { title: 'Nine', content: "If you set md-theme=\"green\" on the md-tabs element, you'll get green tabs."},
			                          { title: 'Ten', content: "If you're still reading this, you should just go check out the API docs for tabs!"}
			                          ]


			/*------------------------------------------------*/
			
			var timeout;
			$scope.carousel = {
					current: 0,
					max: 0
			};
			$scope.setMax = function() {
				if ($scope.images){
					$scope.carousel.max = $scope.images.length;
				} else {
					$scope.carousel.max = 1;
				}
			};
			$scope.show = function(i) {
				$scope.resetTimeout();
				$scope.carousel.current = i;
			};
			$scope.moveOn = function() {
				$scope.carousel.current++;
				if ($scope.carousel.current >= $scope.carousel.max) {
					$scope.carousel.current = 0;
				}
			};
			$scope.initTimeout = function() {
				timeout = $interval($scope.moveOn, $scope.carousel.timeout);
			};
			$scope.resetTimeout = function() {
				$interval.cancel(timeout);
				$scope.initTimeout();
			};
			$scope.$watch('carousel.timeout', $scope.initTimeout);
			$scope.$watch('images', $scope.setMax);

             
             
             
             
             
             /* Location Service*/
			$rootScope.geoLocation ={}

			$rootScope.showLocationDialog = function(ev) {
				$mdDialog.show({
					templateUrl: 'app/modules/home/locationdialog.tmpl.html',
					parent: angular.element(document.body),
					clickOutsideToClose:true,
					controller: function($scope, $mdDialog,LocationService,$rootScope,$log,toastr,$state,ipCookie){
						$scope.checked=ev;
						$scope.hide = function() {
							$mdDialog.hide();
						};
						
						$scope.cancel = function() {
							$mdDialog.cancel();
						};
						
						$scope.answer = function(answer) {
							$mdDialog.hide(answer);
						};

						$scope.selectedcity={};
						
						$scope.selectedIndex = 2;

						var location ={
								latitude:$rootScope.geoLocation.latitude,
								longitude:$rootScope.geoLocation.longitude
						}
						
						if(location.latitude!=undefined && location.longitude!=undefined){
						LocationService.getStoreByLocation(location).then(function(data) {
							$scope.storeList = data;
						});
						}

						var optionLocation=null;

						LocationService.getCity(optionLocation).then(function(data) {
							$scope.allStoreList=data;
						});

						$scope.searchByCity = function(selectedcity){
							$scope.selectedcity=selectedcity;
							var request ={
									city:selectedcity
							}
							LocationService.getStoreByLocation(request).then(function(data) {
								$scope.addressList = data;
							});
						}

						$scope.searchByAddress = function(selectedaddress){
							var request ={area:selectedaddress,city:$scope.selectedcity}
							LocationService.getStoreByLocation(request).then(function(data) {
								$scope.filterStoreList = data;
								$log.debug(data);
							});
						}

						$scope.changeStore = function(storedetails){
							$rootScope.store = storedetails;
							ipCookie("store", storedetails);
							$rootScope.getFutureProducts();
							$rootScope.getTopCategories(); 
							$log.debug(storedetails);
							$state.go('app.products');
							$mdDialog.cancel();
						}

					}
				})	
				.then(function(answer) {
					$scope.status = 'You said the information was "' + answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
			};

			var settings = {
					enableHighAccuracy:true
			}
			
			var geoLocation = function(){
				if($rootScope.findlocation){
					if (navigator.geolocation) {
						navigator.geolocation.getCurrentPosition(showPosition, showError,settings);
					} else { 
						$log.debug("Geolocation is not supported by this browser");
					}
					}
			};

			var showPosition = function(position) {
				$rootScope.geoLocation.latitude = position.coords.latitude;
				$rootScope.geoLocation.longitude = position.coords.longitude;
				$rootScope.showLocationDialog();
			}
			
			var showError = function(error) {
				switch(error.code) {
				case error.PERMISSION_DENIED:
					$log.debug("User denied the request for Geolocation.");
					var checked=true;
					$rootScope.showLocationDialog(checked);
					break;
				case error.POSITION_UNAVAILABLE:
					$log.debug("Location information is unavailable.");
					var checked=true;
					$rootScope.showLocationDialog(checked);
					break;
				case error.TIMEOUT:
					$log.debug("The request to get user location timed out.");
					break;
				case error.UNKNOWN_ERROR:
					$log.debug("An unknown error occurred.")
					break;
				}
			}
			geoLocation();
			
			$rootScope.getFutureProducts = function(){
			homePageServices.futureProducts($rootScope.store.storeId).then(function(data){
                $scope.futureProducts = data;
            });
			}
            
			$rootScope.getTopCategories = function(){
            homePageServices.topCategories($rootScope.store.storeId).then(function(data){
                $scope.topcategories = data;
            })
			}
		}]);

