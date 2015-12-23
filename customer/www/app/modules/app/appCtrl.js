angular.module('aviate.controllers')
.controller("appCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$rootScope', 'MyCartFactory', 'MyCartServices','homePageServices','$mdDialog','FavouriteServices','$localStorage',
		 function($scope, $state, toastr, CONSTANT, $rootScope, MyCartFactory, MyCartServices, homePageServices,$mdDialog,FavouriteServices,$localStorage) {
			
			var storeHoliday = $localStorage.storeHoliday; //ipCookie('myCart');
			if (storeHoliday !== undefined || storeHoliday !== null) {
				$scope.storeHoliday = null;
				$scope.storeHoliday = storeHoliday;
			}else{
				$scope.storeHoliday = {};
				$scope.storeHoliday.storeHoliday = [];
			}
			var isTodayHoliday = $localStorage.isTodayHoliday;  
			if (isTodayHoliday !== undefined || isTodayHoliday !== null) {
				$rootScope.isTodayHoliday = null;
				$rootScope.isTodayHoliday = isTodayHoliday;
			}else{
				$rootScope.isTodayHoliday = {};
			}
			
			var holidayReasons = $localStorage.holidayReasons; //ipCookie('myCart');
			if (holidayReasons !== undefined || holidayReasons !== null) {
				$rootScope.holidayReasons = null;
				$rootScope.holidayReasons = holidayReasons;
			}else{
				$rootScope.holidayReasons = {};
			}
			
			var nextWorkingDate = $localStorage.nextWorkingDate; //ipCookie('myCart');
			if (nextWorkingDate !== undefined || nextWorkingDate !== null) {
				$rootScope.nextWorkingDate = null;
				$rootScope.nextWorkingDate = nextWorkingDate;
			}else{
				$rootScope.nextWorkingDate = {};
			}
			
			
		$scope.checkOut = function() {
			if($rootScope.user != null){
				$rootScope.change=false;
				$state.go('app.checkout');
			}else{
				$rootScope.redirectUrl = 'app.checkout';
				$rootScope.signInPopup();
			}
		};
		
		$scope.getFavourite = function(){
			FavouriteServices.getFavourite($scope.favourite).then(function(data){
				$scope.favouriteList = data;
			});	
	   	};
	   	
	   	if($rootScope.user != undefined){
	   		$scope.getFavourite();
	   	}
		
		$scope.checkOutfromcart = function() {
					if($rootScope.user != null){
						$rootScope.change=true;
			 				$state.go('app.checkout');
			 			}else{
			 				$rootScope.redirectUrl = 'app.checkout';
			 				$rootScope.signInPopup();
			 			}
		};
		
		var timeOut;
		$scope.addToCart = function(product){
				if(timeOut)
					clearTimeout(timeOut);
				timeOut = setTimeout(function() {
					MyCartFactory.addToCart(product,$scope.productList,  function(data){
						console.log("product added to cart");
						$scope.productList = data;
						$scope.getCartList();
					});
				}, 500);
		}

		$scope.getCartList = function(){
			if($rootScope.user){
				MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}},  function(data){
					MyCartFactory.checkCartProductsQuantity($scope.productList,function(data){
							MyCartFactory.checkMyListProductsList(dataInList,function(productList){
									$scope.productList = productList;
								});
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
					escapeToClose : false,
					controller: function($scope, $mdDialog,LocationService,$rootScope,$log,toastr,$state,ipCookie, $filter){
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

//						$scope.searchByCity = function(selectedcity){
//							$scope.selectedcity=selectedcity.name;
//							var request ={
//									city:$scope.selectedcity
//							}
//							LocationService.getStoreByLocation(request).then(function(data) {
//								$scope.addressList = data;
//							});
//						}
						
							
						$scope.searchAreaByCity = function(selectedcity){
							$scope.selectedcity=selectedcity;
							var request ={
									city:$scope.selectedcity
							}
							LocationService.getAreaByCity(request).then(function(data) {
								$scope.areaList = data;
							});
						}
						
						$scope.searchByArea = function(area){
							LocationService.getStoreByArea(area).then(function(data) {
								$scope.filterStoreList = data;
								$log.debug(data);
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
						//	$rootScope.getFutureProducts();
							$rootScope.getTopCategories(); 
							$rootScope.getAllCategoryWithProduct();
							$rootScope.getOfferProduct();
							$rootScope.categoryList();
							$rootScope.shippingCharge();
							$rootScope.getTax();
							$scope.getStoreHolidays();
							$log.debug(storedetails);
							$mdDialog.cancel();
							$state.go('app.home',{},{reload: true});
						}
						
						$scope.getStoreHolidays = function(){
				            homePageServices.getStoreHolidays({"storeId":$rootScope.store.storeId}).then(function(data){
				            	$scope.holidayDates = [];
				                $scope.holidayReasons = {};
				            	if(data.holidays){
				    				data.holidays.forEach(function(date){
				    					$scope.holidayDates.push(new Date(date));
				    				});
				    			}
				    			$rootScope.holidayReasons = JSON.parse(data.reason);
				    			
				            	$localStorage.storeHoliday = $scope.holidayDates;
				                $scope.storeHoliday = $scope.holidayDates;
				                $localStorage.holidayReasons = undefined;
				                $localStorage.isTodayHoliday = undefined;
				                
						 		var storeHoliday =$localStorage.storeHoliday;
						 		storeHoliday.forEach(function(date){
						 			var d=new Date();
						 			d.setHours(0, 0, 0, 0, 0);
						 			if(date.getTime() == d.getTime()){
						 				if($rootScope.holidayReasons){
						 					var convertedDate=$filter('date')(date, "yyyy-MM-dd");
							 				var reason=$rootScope.holidayReasons[convertedDate];
							 				$localStorage.holidayReasons = reason;
							 				$rootScope.holidayReasons =reason;
						 				}
						 				$rootScope.isTodayHoliday= true;
						 				$localStorage.isTodayHoliday = true;
						 				var tomorrow = new Date();
						 				tomorrow.setHours(0, 0, 0, 0, 0);
						 				tomorrow.setDate(d.getDate()+1);
						 				var count=1;
						 				$scope.isTmwHoliday = false;
						 				while(count==1){ 
						 				$scope.isTomorrowHoliday(tomorrow,storeHoliday);
						 				if(!$scope.isTmwHoliday){
						 					$rootScope.nextWorkingDate = $filter('date')(tomorrow, "dd-MM-yyyy");
						 					$localStorage.nextWorkingDate=$rootScope.nextWorkingDate;
						 					count++;
						 				}else{
						 					$scope.isTmwHoliday = false;
						 					tomorrow.setDate(tomorrow.getDate()+1);
						 				}
						 			}
						 			}
						 		});
						 		if(!$localStorage.isTodayHoliday){
						 			$rootScope.isTodayHoliday= false;
					 				$localStorage.isTodayHoliday = false;
						 		}
				            })
						}
						
						$scope.isTomorrowHoliday=function(date,storeHoliday){
							storeHoliday.forEach(function(date1){
								if(date1.getTime() == date.getTime()){
									$scope.isTmwHoliday = true;
								}
							})
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
					//toastr.error("User denied the request for Geolocation.");
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
			
			$rootScope.getOfferProduct = function(){
				$scope.product = {};
				$scope.product.merchantVo = {};
				$scope.product.merchant = {};
				$scope.product.merchant.merchantId = $rootScope.store.merchant.merchantId;
            homePageServices.offerProduct($scope.product).then(function(data){
             //   $rootScope.topcategories = data;
            	 $rootScope.offerProducts = data;
            })
			}
            
            
			$rootScope.getTopCategories = function(){
				$scope.product = {};
				$scope.product.merchant = {};
				$scope.product.merchant.merchantId = $rootScope.store.merchant.merchantId;
            homePageServices.topCategories($scope.product).then(function(data){
             //   $rootScope.topcategories = data;
            	 $rootScope.topcategories = $rootScope.comboOffer(data);
            })
			}
			
			
			$rootScope.comboOffer = function(productList){
				$scope.productList = productList;
				for(var i =0;i<$scope.productList.length;i++){
					$scope.productOffers = [];
					for(var j =0;j<$scope.productList[i].productOffer.length;j++){
						$scope.productOffer = {};
						$scope.productOffer.name = $scope.productList[i].productOffer[j].name;
						$scope.productOffer.description = $scope.productList[i].productOffer[j].description;
						$scope.productOffer.productOfferId = $scope.productList[i].productOffer[j].productOfferId;
						$scope.productOffer.offerLines = $scope.productOfferLines($scope.productList[i].productOffer[j].productOfferLinesVo);
						$scope.productOffers.push({"name":$scope.productOffer.name,"description":$scope.productOffer.description,"productOfferId":$scope.productOffer.productOfferId,
							"productOfferLines":$scope.productOffer.offerLines});
					}
					$scope.productList[i].productOffers = angular.copy($scope.productOffers) || [];
				}
				return $scope.productList;
			}
			
			$scope.productOfferLines = function(productOfferLines){
				$scope.offerLines =[];
				for(var i =0;i<productOfferLines.length;i++){
					
					$scope.productOfferLineId = productOfferLines[i].productOfferLineId;
					$scope.discountPercentage = productOfferLines[i].discountPercentage;
					$scope.discountAmount = productOfferLines[i].discountAmount;	
					$scope.product ={};
					$scope.product.productId = productOfferLines[i].productVo.productId;
					$scope.product.price = productOfferLines[i].productVo.price;
					$scope.product.name = productOfferLines[i].productVo.name;
					$scope.offerLines.push({"productOfferLineId":$scope.productOfferLineId,"discountPercentage":$scope.discountPercentage,
											"discountAmount":$scope.discountAmount,"product":$scope.product})
									
				}
				return $scope.offerLines;
			}
						
			
			$rootScope.getAllCategoryWithProduct = function() {
				$scope.product = {};
				$scope.product.merchant = {};
				$scope.product.merchant.merchantId = $rootScope.store.merchant.merchantId;
            homePageServices.allCategoriesWithProduct($scope.product).then(function(data){
                $rootScope.categoriesWithProduct = data.productCategories;
            })
			}
			
			$rootScope.shippingCharge = function(){
				$scope.shippingCharges ={};
				$scope.merchant = $rootScope.store.merchant;
	            homePageServices.getShippingCharge($scope.merchant).then(function(data){
	            	$localStorage.shippingCharges =data;
	                $rootScope.shippingCharges = data;
	            })
			}
			
			$rootScope.getTax =function(){
				$scope.tax ={};
				$scope.merchant = $rootScope.store.merchant;
				homePageServices.getTax($scope.merchant).then(function(data){
					$localStorage.taxList =data.taxList;	
					$rootScope.taxList = data.taxList;
				})
								
			}
			
			$scope.deleteFavourite = function(favouriteId){
				
				$scope.favourite ={};
				$scope.favourite.favouriteId = favouriteId
				FavouriteServices.deleteFavourite($scope.favourite).then(function(data){
					FavouriteServices.getFavourite().then(function(datas){
						$scope.favouriteList = datas;
						
					});
					
				});	
			}
			
			if ($rootScope.store == undefined || $rootScope.store == null) {
				setTimeout(function() {
					/*alert("check");*/
					$rootScope.geoLocation();
				}, 500);
			}
}]);
