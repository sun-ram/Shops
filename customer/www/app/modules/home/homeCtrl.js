angular.module('aviate.controllers')
.controller("homeCtrl",
		['$scope', '$state', '$interval', 'toastr', 'CONSTANT', 'ProductService','homePageServices','$rootScope','$mdDialog','$log','LocationService','ipCookie','$localStorage','$window',
		 function($scope, $state, $interval ,toastr, CONSTANT, ProductService, homePageServices, $rootScope,$mdDialog,$log,LocationService, ipCookie, $localStorage, $window) {

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
			
			//alert("djhasd");
			
			$scope.$watch(function(){
			       return $window.innerWidth;
			    }, function(value) {
			    	if(value < 450){
				    	   $rootScope.numLimit = 1;
				       }
				       if(value > 450 && value <= 700){
				    	   $rootScope.numLimit = 2;
				       }
				       if(value > 700 && value <= 960){
				    	   $rootScope.numLimit = 3;
				       }
				       if(value > 960 && value <= 1024){
				    	   $rootScope.numLimit = 4;
				       }
				       if(value > 1024 && value <= 1200){
				    	   $rootScope.numLimit = 5;
				       }
				       if(value > 1200 && value <= 1500){
				    	   $rootScope.numLimit = 6;
				       }
				       if(value > 1500){
				    	   
				    	   $rootScope.numLimit = 6;
				    	   
				    	   
				       }
			});
			
			$scope.remainOb = function(lengths){
				
				$scope.remain = 0;
				if($rootScope.numLimit > lengths){
					$scope.remain = $rootScope.numLimit - lengths;
				}
				return new Array($scope.remain);	
			};
			
			

			/*$rootScope.numLimit = 5;*/

			/*------------------------------------------------*/
						
																	
			$scope.getBannerList = function(){
				
				if($rootScope.store != null){
					$scope.banner = {};
					$scope.banner.store = {};
					$scope.banner.store.storeId = $rootScope.store.storeId;

					homePageServices.getBannerList($scope.banner).then(function(data){
						if(data.length != 0){
							$scope.images = data;
						}else{
							$scope.shopsBackerImages();
						}
					});
				}else{
					$scope.shopsBackerImages();
				}
			}
			
			$scope.shopsBackerImages = function(){
				$scope.banner = {};
				$scope.banner.isShopsbackerBanner = 'Y';

				homePageServices.getBannerList($scope.banner).then(function(data){
					$scope.images = data;
				});
				
			}

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

			$scope.productDetails = function(ev,products){
				$rootScope.productDetails = products;
				$mdDialog.show({
					templateUrl: 'app/modules/products/productDetails/productDetails.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: "productDetailsCtrl"
				})
				.then(function() {

				}, function() {

				});
			}

			$scope.getProductsByCategoryId = function(categoryId){
				if(categoryId){
					currentRootCatagoryIndex = -1;
					$scope.getRootCatagory($scope.categoryList,categoryId, null);
					$scope.optimizeData ($scope.categoryList, currentRootCatagoryIndex);
					$scope.findSubtree($scope.categoryList, categoryId,false);
					$state.go('app.products',{'categoryId': categoryId});
				}

			}

			$scope.getProducts = function(){

				$rootScope.getTopCategories();
				$rootScope.getOfferProduct();
				$rootScope.getAllCategoryWithProduct();
				$rootScope.shippingCharge();
			//	$rootScope.getStoreHolidays();
				$rootScope.getTax();
			}
			
			$scope.showOffers = function(productOffer){
				$rootScope.productOffer = productOffer;
				$state.go('app.productOffer');
			}

		}]);

