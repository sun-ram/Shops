angular.module('aviate.directives')
.directive('mainNav', ['$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdUtil','$mdSidenav','$log','$mdDialog','MyCartFactory','toastr','MyCartServices', 'MyListServices','CheckOutServices', 'FavouriteServices',
                       function($rootScope, $document, $state, ipCookie, $timeout, $mdUtil, $mdSidenav, $log ,$mdDialog, MyCartFactory,toastr, MyCartServices, MyListServices,CheckOutServices, FavouriteServices) {

	return {
		// scope: false,
		restrict: 'E',
		templateUrl: './app/modules/main/nav-main.html',
		replace: true,
		link: function($scope, iElm, iAttrs, controller, AuthServices, CONSTANT,toastr){
			$scope.bar= true;
			$scope.back=false;
			
			$scope.toggleLeft = buildToggler('left');
			$scope.toggleRight = buildCartToggler('right');
			
			function buildToggler(navID) {
				var debounceFn =  $mdUtil.debounce(function(){
				/*if($rootScope.newclass == "navigate"){
					$rootScope.newclass = "remove";
					$rootScope.topbar = "toptoolrem";
				}else{
					$rootScope.newclass = "navigate";
					$rootScope.topbar = "toptool";	
				}*/
					$mdSidenav(navID).toggle().then(function () {
						$log.debug("toggle " + navID + " is done");
					});
				},200);
				return debounceFn;
			};
			

			function buildCartToggler(navID) {
				var debounceFn =  $mdUtil.debounce(function(){
					 if($rootScope.navsides == "navigate-right"){
							$rootScope.navsides = "remove-right";
                            $rootScope.numLimit = 5;
							}else{
								$rootScope.navsides = "navigate-right";
                                 $rootScope.numLimit = 3;
							}
					$mdSidenav(navID).toggle().then(function () {
						$log.debug("toggle " + navID + " is done");
					});
				},200);
				return debounceFn;
			};
			
//			console.info('cart---------------',$rootScope.myCart);
			$scope.signUpPopup = function(ev){
				$rootScope.isSignUp = true;
				$rootScope.signInSignUpOptions(ev);
			}
			
			$rootScope.signInPopup = function(ev){
				$rootScope.isSignUp = false;
				$rootScope.signInSignUpOptions(ev);
			}

             $scope.orderHistory = function(){
                
                $state.go("app.orderHistory");
            }
            
			$scope.removeFromMyCart = function(item, index) {
				MyCartFactory.removeFromCart(item.product.productId, index,function(data){
					if($rootScope.updateProductQuantity)
						$rootScope.updateProductQuantity(item);
				});
			};

			/*			$scope.checkOutPage = function() {
				if($rootScope.user != null){
					MyCartFactory.myCartTotalPriceCalculation();
					$state.go('app.checkout');
				}else{
					toast.info('need to login first');
				}
			};*/
			
			$rootScope.signInSignUpOptions = function(ev){
				$mdDialog.show({
					templateUrl: 'app/modules/auth/signIn.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: function($scope, AuthServices, toastr, CONSTANT,ipCookie){
						$scope.isSignUp = $rootScope.isSignUp;
						$scope.isSignIn=!$rootScope.isSignUp;
						$scope.title = 'SIGN IN';
						$scope.forgetPass = false;
						$scope.user = {}
						var authInfo = ipCookie('auth_info');
						
						if(authInfo != undefined || authInfo != null){
							$scope.user.email = authInfo;
							$scope.rememberme = true;
							/*if(authInfo.rememberme){
							$scope.user.email=authInfo.email;
							$scope.user.password = authInfo.password;*/
						}else{
							$scope.rememberme = false;
						}
						
						$scope.saveauth = function() {
							if($scope.rememberme){
								if($scope.user.email!=undefined){
									ipCookie('auth_info', $scope.user.email);
								}
							 }else{
								 	ipCookie.remove('auth_info');
							 }
							}
						
						
						/*$scope.validatePhoneNo = function(name) {
							var number="/^\d+$/";
							if(number.test(name))
								return true;
							else
								return false;
						}
						
						$scope.validateEmail = function(name) {
							var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
							if( emailReg.test(name))
								return true;
							else
								return false;
						}*/
						
						$scope.redirectToUrl = function(){
							if($rootScope.redirectUrl != undefined || $rootScope.redirectUrl != null){
								var redirectUrl = $rootScope.redirectUrl;
								$rootScope.redirectUrl = null;
								$state.go(redirectUrl);
							}
						};
						
						$scope.addProductsToCart = function(callback){
							$scope.myCart = JSON.parse(localStorage.getItem('myCart')); //ipCookie('myCart');
							if($scope.myCart != undefined || $scope.myCart != null){

								var addToCartRequest = {
										customerId: $rootScope.user.userId,
										storeId: $rootScope.store.storeId
										products:[]
								}
								
								for(var i=0;i<$scope.myCart.cartItem.length;i++){
									var myCartItem = $scope.myCart.cartItem[i];
									var product = {
										productId: myCartItem.product.productId,
										qty: myCartItem.product.noOfQuantityInCart
									}
									
									addToCartRequest.products.push(product);

								}
								
								MyCartServices.addProductsToCart(addToCartRequest).then(function(data){
									console.log('Products added successfully to user cart');
									callback();
								});
							}else{
								callback();
							}
						}
						
						$scope.signIn = function(user) {
							AuthServices.signIn(user).then(function(data){
								$scope.saveauth();
								$scope.cancel();
								toastr.success(CONSTANT.SUCCESS_CODE.SIGNINSUCCESS);
								$scope.addProductsToCart(function(){
									MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
										MyCartFactory.myCartTotalPriceCalculation();
										console.log('get MyCartlist success in Main Nav');
									});
									
									FavouriteServices.getFavourite().then(function(datas){
										$rootScope.favouriteList = datas;
										
									});
									
									$scope.redirectToUrl();
								});

							});
						};

						/*$scope.forGetPassword = function(user) {
						$scope.forgetPass = false;
						if(!user.emailId){
							toastr.warning(CONSTANT.WARNING_CODE.FORGETPASSWORDNEEDMAILID);
							return;
						}
						AuthServices.forGetPassword(user).then(function(data){

						});
					};*/
					   	
					$scope.forGetPassword = function(user) {
						$scope.forgetPass = false;
						var req = {"user":user, "passwordResetUrl": window.location.origin + "/#/resetpassword/", "userType": "customer"};
						AuthServices.forGetPassword(req).then(function(data){
							//$scope.user ="";
							//$state.go('login');
						});
					};
					
					$scope.resetpass = function(user) {
						if(user.password === $scope.confirmPassword){
							var req = {"tokenId": $scope.tokenId, "user": user};
							AuthService.resetpass(req).then(function(data){
								$scope.user ="";
								$state.go('login');
							});
						}else{
							toastr.error(CONSTANT.PASSWORDNOTMATCH);
						}
					};
					

					$scope.signUp = function(user) {
						//user.role = CONSTANT.SUCCESS_CODE.ROLE;
						if(user.password !== $scope.confirmPassword){
							toastr.warning(CONSTANT.WARNING_CODE.MISSMATCHPASSWORD);
							return;
						}
						AuthServices.signUp(user).then(function(data){
							$scope.cancel();
							toastr.success(CONSTANT.SUCCESS_CODE.SIGNUPSUCCESS);
							$scope.addProductsToCart(function(){
								$scope.redirectToUrl();
							});
						});
					};
					
					$scope.clearFormValues = function(){
						var email = $scope.user.email;
						$scope.user = {};
						$scope.user.email = email;
					}

						$scope.cancel = function() {
							$mdDialog.cancel();
						};

					}
				})
				.then(function() {

				}, function() {

				});
			}

			$scope.logout = function() {
				$rootScope.user = null;
				ipCookie('user', null);
				$rootScope.myCart = {};
				$rootScope.categoryList();
				$rootScope.myCart.cartItem = [];
				//ipCookie('myCart', $rootScope.myCart);
				localStorage.setItem('myCart',JSON.stringify($rootScope.myCart));
				$state.go('app.home');
			};

			$scope.changeStore = function() {
				$rootScope.newclass = "remove";
				$rootScope.topbar = "toptoolrem";
				$mdSidenav('left').close();
				if($rootScope.latLong.support==true){
					$rootScope.showLocationDialog();
				}else{
					$rootScope.showLocationDialog(true);
				}
			};
			
			$scope.addFavouriteToCart = function(favouriteId){
		   		$scope.favourite ={};
				$scope.favourite.customerId = $rootScope.user.userId;
				$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
				$scope.favourite.storeId = $rootScope.store.storeId;
				$scope.favourite.favouriteId = favouriteId;
				FavouriteServices.addFavouriteToCart($scope.favourite).then(function(data){
					MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
						MyCartFactory.myCartTotalPriceCalculation();
						console.log('get MyCartlist success in Main Nav');
						$state.go('app.favouriteList');
					});
					
				});	
		   	}
			
			$scope.viewProductInFavourite = function(favouriteId){
				
				$scope.favourite ={};
				$scope.favourite.customerId = $rootScope.user.userId;
				$scope.favourite.merchantId = $rootScope.store.merchant.merchantId;
				$scope.favourite.storeId = $rootScope.store.storeId;
				$scope.favourite.favouriteId = favouriteId
				FavouriteServices.viewProductInFavourite($scope.favourite).then(function(data){
					$scope.productInFavourite = {};
					$scope.productInFavourite.productList = data.myCart;
					$scope.productInFavourite.favouriteId = favouriteId;
					$state.go('app.favouriteList');

				});	
			}
			
		}

	};
}
]);
