angular.module('aviate.directives')
.directive('mainNav', ['$rootScope', '$document', '$state', 'ipCookie', '$timeout','$mdUtil','$mdSidenav','$log','$mdDialog','MyCartFactory',
                       function($rootScope, $document, $state, ipCookie, $timeout, $mdUtil, $mdSidenav, $log ,$mdDialog, MyCartFactory) {

	return {
		// scope: false,
		restrict: 'E',
		templateUrl: './app/modules/main/nav-main.html',
		replace: true,
		link: function($scope, iElm, iAttrs, controller, AuthServices, CONSTANT){

			$scope.toggleSidenav = buildToggler('left');

			function buildToggler(navID) {
				var debounceFn =  $mdUtil.debounce(function(){
					$mdSidenav(navID).toggle().then(function () {
						$log.debug("toggle " + navID + " is done");
					});
				},200);
				return debounceFn;
			};

			$scope.signUpPopup = function(ev){
				$mdDialog.show({
					templateUrl: 'app/modules/auth/signUp.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: function($scope, AuthServices, toastr, CONSTANT){
						$scope.isSignUp = true;
						$scope.signUp = function(user) {
							user.role = CONSTANT.SUCCESS_CODE.ROLE;
							if(user.password !== $scope.confirmPassword){
								toastr.warning(CONSTANT.WARNING_CODE.MISSMATCHPASSWORD);
								return;
							}
							AuthServices.signUp(user).then(function(data){
								$scope.cancel();
								toastr.success(CONSTANT.SUCCESS_CODE.SIGNUPSUCCESS);
							});
						};

						$scope.cancel = function() {
							$mdDialog.cancel();
						};

					}
				})
				.then(function() {

				}, function() {

				});
			}

			$scope.removeFromMyCart = function(product, index) {
				MyCartFactory.removeFromCart(product, index);
			};
			$scope.signInPopup = function(ev){
				$mdDialog.show({
					templateUrl: 'app/modules/auth/signIn.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: function($scope, AuthServices, toastr, CONSTANT){
						$scope.isSignUp = false;
						$scope.signIn = function(user) {
							AuthServices.signIn(user).then(function(data){
								$scope.cancel();
								toastr.success(CONSTANT.SUCCESS_CODE.SIGNINSUCCESS);

								$scope.myCart = ipCookie('myCart');
								if($scope.myCart != undefined || $scope.myCart != null){

									for(var i=0;i<$scope.myCart.cartItem.length;i++){
										$scope.cartDetails = {
												"customerId" : $rootScope.user.userId, 
												"storeId" : $rootScope.store.storeId, 
												"productId" : $scope.myCart.cartItem[i].productDetails.productId, 
												"price" : $scope.myCart.cartItem[i].productDetails.productPrice.price, 
												"quantity" : $scope.myCart.cartItem[i].noOfQuantityInCart
										}
										MyCartServices.addToCart($scope.cartDetails).then(function(data){
											console.log('get Mylist success in Main Nav');
										});


										$rootScope.addToCartDB($rootScope.cartItem[i].productDetails.productId,$rootScope.cartItem[i].noOfQuantityInCart
												,$rootScope.cartItem[i].productDetails.productPrice.price);
									}
									/*$rootScope.cartItem = [];
									$rootScope.cartTotalAmount = 0;*/


								}

								MyCartServices.getCartList(userDetails).then(function(data){
									console.log('get Mylist success in Main Nav');
								});

								MyCartServices.getCartList(userDetails).then(function(data){
									console.log('get cartlist success in Main Nav');
								});

							});
						};

						$scope.forGetPassword = function(user) {
							if(!user.emailId){
								toastr.warning(CONSTANT.WARNING_CODE.FORGETPASSWORDNEEDMAILID);
								return;
							}
							AuthServices.forGetPassword(user).then(function(data){

							});
						};

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
			};
		}

	};
}
]);
