angular.module('aviate.controllers')
.controller("appCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$rootScope', 'MyCartFactory', 'MyCartServices','homePageServices','$mdDialog',
		 function($scope, $state, toastr, CONSTANT, $rootScope, MyCartFactory, MyCartServices, homePageServices,$mdDialog) {
			
		
		$scope.checkOut = function() {
			if($rootScope.user != null){
				$state.go('app.checkout');
			}else{
				$rootScope.signInPopup();
			}
		};
		
		$scope.addToCart = function(product){
			MyCartFactory.addToCart(product,$scope.productList,  function(data){
				$scope.productList = data;
				$scope.getCartList();
			});
		}

		$scope.getCartList = function(){
			if($rootScope.user){
				MyCartServices.getCartList({"customerId" : $rootScope.user.userId, "storeId" : $rootScope.store.storeId},  function(data){
					MyCartFactory.checkCartProductsQuantity($scope.productList,function(data){
						$scope.productList = data;
					});
				});
			}
		}
		
		$scope.removeFromMyCart = function(product, index) {
			MyCartFactory.removeFromCart(product, index);
		};
		
		 //Location Service
			
		$rootScope.showLocationDialog = function(ev) {
				$mdDialog.show({
					templateUrl: 'app/modules/home/locationdialog.tmpl.html',
					parent: angular.element(document.body),
					clickOutsideToClose:false,
					controller: function($scope, $mdDialog,LocationService,$rootScope,$log,toastr,$state,ipCookie){
						$scope.checked=ev;
						$scope.hide = function() {
							$mdDialog.hide();
						};
						
						$scope.cancel = function() {
							if($rootScope.store != undefined || $rootScope.store != null){
								$mdDialog.cancel();
							}else{
								toastr.error("Please Select Store");
							}
							
						};
						
						$scope.answer = function(answer) {
							$mdDialog.hide(answer);
						};

						$scope.selectedcity={};
						
						$scope.selectedIndex = 2;

						var location ={
								latitude:$rootScope.latLong.latitude,
								longitude:$rootScope.latLong.longitude
						}
						
						if(location.latitude!=undefined && location.longitude!=undefined){
							if($rootScope.nearByStoreList == undefined || $rootScope.nearByStoreList == null){
								LocationService.getStoreByLocation(location).then(function(data) {
									$scope.storeList = data;
									ipCookie("storeList", data);
									$rootScope.nearByStoreList = data;
								});
							}else{
								$scope.storeList = $rootScope.nearByStoreList;
							}
						}else{
							$scope.storeList = ipCookie("storeList");
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
							if(storedetails.merchantLogo!= undefined && storedetails.merchantLogo!=null){
							$rootScope.logoadd="merch-marg";
							$rootScope.logoadd1="nav-menu1";
							}
							else{
								$rootScope.logoadd="merch-logo";
								$rootScope.logoadd1="nav-menu";
							}
							$rootScope.myCart = {};
							$rootScope.myCart.cartItem = [];
							//ipCookie("myCart", $rootScope.myCart);
							localStorage.setItem('myCart',JSON.stringify($rootScope.myCart));
							$rootScope.store = storedetails;
							ipCookie("store", storedetails);
							$rootScope.getFutureProducts();
							$rootScope.getTopCategories(); 
							$rootScope.categoryList();
							$log.debug(storedetails);
							$mdDialog.cancel();
							$state.go('app.home',{},{reload: true});
						}

					}
				})	
				.then(function(answer) {
				}, function() {
					console.log('You cancelled the dialog.');
				});
			};
			
			
			var settings = {
					enableHighAccuracy:true
			}
			
			$rootScope.geoLocation = function(){
				if($rootScope.latLong.findlocation){
					if (navigator.geolocation) {
						navigator.geolocation.getCurrentPosition(showPosition, showError,settings);
					} else { 
						$rootScope.latLong.support=false;
						toastr.error("Geolocation is not supported by this browser");
					}
					}
			};

			var showPosition = function(position) {
				$rootScope.latLong.latitude = position.coords.latitude;
				$rootScope.latLong.longitude = position.coords.longitude;
				$rootScope.latLong.support=true;
				$rootScope.showLocationDialog();
			}
			
			var showError = function(error) {
				switch(error.code) {
				case error.PERMISSION_DENIED:
					toastr.error("User denied the request for Geolocation.");
					$rootScope.latLong.support=false;
					$rootScope.showLocationDialog(true);
					break;
				case error.POSITION_UNAVAILABLE:
					toastr.error("Location information is unavailable.");
					$rootScope.latLong.support=false;
					$rootScope.showLocationDialog(true);
					break;
				case error.TIMEOUT:
					toastr.error("The request to get user location timed out.");
					break;
				case error.UNKNOWN_ERROR:
					toastr.error("An unknown error occurred.")
					break;
				}
			}
			
			
			$rootScope.getFutureProducts = function(){
			homePageServices.futureProducts($rootScope.store.storeId).then(function(data){
                $rootScope.futureProducts = data;
            });
			}
            
			$rootScope.getTopCategories = function(){
            homePageServices.topCategories($rootScope.store.storeId).then(function(data){
                $rootScope.topcategories = data;
            })
			}

}]);
