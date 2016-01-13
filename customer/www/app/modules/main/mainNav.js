angular.module('aviate.directives')
.directive('mainNav', ['$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdUtil','$mdSidenav','$log','$mdDialog','MyCartFactory','toastr','MyCartServices', 'MyListServices','CheckOutServices', 'FavouriteServices', '$window','SocketServices',
                       function($rootScope, $document, $state, ipCookie, $timeout, $mdUtil, $mdSidenav, $log ,$mdDialog, MyCartFactory,toastr, MyCartServices, MyListServices,CheckOutServices, FavouriteServices, $window, SocketServices) {

	return {
		// scope: false,
		restrict: 'E',
		templateUrl: './app/modules/main/nav-main.html',
		replace: true,
		link: function($scope, iElm, iAttrs, controller, AuthServices, CONSTANT,toastr){
			$scope.bar= true;
			$scope.back=false;
			$scope.toggleLeft = buildToggler('left');
			
			
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
							
                            //$rootScope.numLimit = 5;
							
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
							
							}else{
								
                                // $rootScope.numLimit = 3;
								
								$scope.$watch(function(){
								       return $window.innerWidth;
								    }, function(value) {
								    	if(value < 450){
									    	   $rootScope.numLimit = 1;
									       }
									       if(value > 450 && value <= 700){
									    	   $rootScope.numLimit = 1;
									       }
									       if(value > 700 && value <= 960){
									    	   $rootScope.numLimit = 1;
									       }
									       if(value > 960 && value <= 1024){
									    	   $rootScope.numLimit = 1;
									       }
									       if(value > 1024 && value <= 1200){
									    	   $rootScope.numLimit = 2;
									       }
									       if(value > 1200 && value <= 1500){
									    	   $rootScope.numLimit = 3;
									       }
									       if(value > 1500){
									    	   
									    	   $rootScope.numLimit = 4;
									       }
								});
							       
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
					if($rootScope.topcategories){
						angular.forEach($rootScope.topcategories,function(p){
							if(p.productId == item.product.productId){
								p.noOfQuantityInCart = 0;
							}
						});
					}
					
					if($rootScope.isBundleProducts){
						angular.forEach($rootScope.isBundleProducts,function(p){
							if(p.productId == item.product.productId){
								p.noOfQuantityInCart = 0;
							}
						});
					}
					
					if($rootScope.comboOffer){
						angular.forEach($rootScope.comboOffer,function(p){
							if(p.productId == item.product.productId){
								p.noOfQuantityInCart = 0;
							}
						});
					}
					
					//$rootScope.getAllCategoryWithProduct();	
					if($rootScope.categoriesWithProduct){
						angular.forEach($rootScope.categoriesWithProduct,function(p){
							angular.forEach(p.products,function(s){
								if(s.productId == item.product.productId){
									s.noOfQuantityInCart = 0;
								}
							});	
						});
						}
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
										storeId: $rootScope.store.storeId,
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
						};
						
						$scope.signIn = function(user) {
							AuthServices.signIn(user).then(function(data){
								$scope.saveauth();
								$scope.cancel();
								toastr.success(CONSTANT.SUCCESS_CODE.SIGNINSUCCESS);
								SocketServices.getSocket($rootScope.user);
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
						
					$scope.isForPassSubBtnDisabled = false;
					   	
					$scope.forGetPassword = function(user) {
						$scope.isForPassSubBtnDisabled = true;
						var req = {"user":user, "passwordResetUrl": window.location.origin + window.location.pathname + "#/resetpassword/", "userType": "customer"};
						AuthServices.forGetPassword(req).then(function(data){
							//$scope.user ="";
							//$state.go('login');
							if (data.status === CONSTANT.STATUS.SUCCESS) {
								toastr.success(CONSTANT.SUCCESS_CODE.FORGETPASSWORDCONFIRMATION);
								$scope.forgetPass = false;
								$scope.isSignIn = true;
								$scope.title = 'SIGN IN'
							} else {
								toastr.error(data.errorString);
							}
							
							$scope.isForPassSubBtnDisabled = false;
							
						});
					};
					
					$scope.checkValue = function(email, phone){
				   		if((email == undefined || email == "") && (phone == undefined || phone == "")){
				   			$scope.emailReq = true;
				   			$scope.phoneReq = true;
				   		}else if((email == undefined || email == "") && (phone != undefined || phone != "")){
				   			$scope.emailReq = false;
				   		}else if((email != undefined || email != "") && (phone == undefined || phone == "")){
				   			$scope.phoneReq = false;
				   		}
				   	}
				 
					$scope.signUp = function(user, confirmPassword) {
						//user.role = CONSTANT.SUCCESS_CODE.ROLE;
						if((user.email == undefined || user.email == "") && (user.phoneNo == undefined || user.phoneNo == "")){
							toastr.error("Please Enter Email or Phone Number");
							return;
						}
						if(user.password !== confirmPassword){
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
						$rootScope.favouriteList=null;
					};
					
					$scope.clearFormValues = function(){
						var email = $scope.user.email;
						$scope.user = {};
						$scope.user.email = email;
					}

						$scope.cancel = function() {
							$mdDialog.cancel();
						};

						$scope.$watch(function(){
						       return $window.innerWidth;
						    }, function(value) {
						       $scope.windowsize = value;
						   });          
    $scope.isSignIn = true;
    $scope.goSignup = function () {
        $scope.isSignup = true;
        $scope.isSignIn = false;
        $scope.isForgetPassword = false;
        $('#textbox').animate({
            'marginLeft': "0" //moves left
        });
        $('.toplam').animate({
            'marginLeft': "100%" //moves right
        });
    };
    $scope.goForgetPassword = function () {
        $scope.isSignup = false;
        $scope.isSignIn = false;
        $scope.isForgetPassword = true;
        $('#textbox').animate({
            'marginLeft': "0" //moves left
        });
        $('.toplam').animate({
            'marginLeft': "100%" //moves right
        });
    };

    $scope.goGuest = function () {
        $scope.isSignup = false;
        $scope.isSignIn = false;
        $scope.isGuest = true;
        $('#textbox').animate({
            'marginLeft': "0" //moves left
        });
        $('.toplam').animate({
            'marginLeft': "100%" //moves right
        });
    };
    $scope.goSignIn = function () {
        $scope.isSignup = false;
        $scope.isForgetPassword = false;
        $scope.isGuest = false;
        $scope.isSignIn = true;
        
        if($scope.windowsize < 855){        	
        	$('#textbox').animate({
	            'marginLeft': "0%" //moves right
	        });
	        $('.toplam').animate({
	            'marginLeft': "0" //moves right
	        });
		}else{			
			$('#textbox').animate({
	            'marginLeft': "50%" //moves right
	        });

	        $('.toplam').animate({
	            'marginLeft': "0" //moves right
	        });
		}        
    }
                        
                        
                        
					}
				})
				.then(function() {

				}, function() {

				});
			}

			$scope.logout = function() {
				$rootScope.favouriteList=null;	
				$rootScope.user = null;
				ipCookie('user', null);
				$rootScope.myCart = {};
				$rootScope.categoryList();
				$rootScope.myCart.cartItem = [];
				if($rootScope.websocket){
	       			 $rootScope.websocket.close();
	       		 	}
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
					$scope.productInFavourite.flag=false;
					$scope.productInFavourite.productList = data.myCart;
					for(var i=0;i<$scope.productInFavourite.productList.length;i++){
						if($scope.productInFavourite.productList[i].qty > $scope.productInFavourite.productList[i].product.productInventory.availableQty){
							$scope.productInFavourite.flag=true;
							$scope.productInFavourite.message="Out Of Stock for "+$scope.productInFavourite.productList[i].product.name;
							break;
						}
					}
					$scope.productInFavourite.favouriteId = favouriteId;
					$state.go('app.favouriteList');

				});	
			}
			
		}

	};
}
]);
