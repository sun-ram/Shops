angular.module('aviate.controllers')
.controller("productCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'ProductService','products', '$rootScope', 'ipCookie', 'MyCartFactory', 'MyCartServices','$mdDialog','MyListServices','$stateParams','homePageServices',
		 function($scope, $state, toastr, CONSTANT, ProductService, products,$rootScope,ipCookie, MyCartFactory, MyCartServices,$mdDialog,MyListServices,$stateParams,homePageServices) {

			$scope.rupeesSymbol = CONSTANT.RUPEESSYMBOL;
			
			if($stateParams.value=="bundleProducts"){
				$scope.product = {};
				$scope.product.merchant = {};
				$scope.product.merchant.merchantId = $rootScope.store.merchant.merchantId;
            homePageServices.isBundleProduct($scope.product).then(function(data){
            	$scope.productList = data;
            	$scope.comboOffers=false;
            })
			}
			else if($stateParams.value=="comboProducts"){
				$scope.product = {};
				$scope.product.merchant = {};
				$scope.product.merchant.merchantId = $rootScope.store.merchant.merchantId;
				         homePageServices.comboOffer($scope.product).then(function(data){
				        	 $scope.productList = data;	
				        	 $scope.comboOffers=true;
				        })
			}
			else if($stateParams.value=="topCategories"){
				$scope.product = {};
				$scope.product.merchant = {};
				$scope.product.merchant.merchantId = $rootScope.store.merchant.merchantId;
                homePageServices.topCategories($scope.product).then(function(data){
            	$scope.productList = data;
            	$scope.comboOffers=false;
            })
			}
			else{
				//need to write
			}
			
			$scope.addToMyList = function(product,index){
				if($rootScope.user == null || $rootScope.user == undefined){
					$rootScope.signInPopup();
				}else{
				MyListServices.addToMyList({"customerId":$rootScope.user.userId,"productId" : product.productId,"storeId" : $rootScope.store.storeId}).then(function(data){
					$scope.productList[index].isProductMyList = true;
					$scope.getProducts();
				});	
				}
			};
			
			$scope.removeFromMyList = function(product,index){
				MyListServices.removeMyList({"customerId":$rootScope.user.userId,"productId" : product.productId,"storeId" : $rootScope.store.storeId}).then(function(data){
					$scope.getMyList();
					$scope.productList[index].isProductMyList = false;
				});	
				$scope.getProducts();
			};
			
			$scope.getMyList = function(){
				MyListServices.getMyList({"customerId":$rootScope.user.userId, "storeId" : $rootScope.store.storeId}).then(function(data){
					$scope.myListProducts = data;
				});
			};
			
			$scope.getProducts = function(){
					MyCartFactory.checkMyListProductsList(products,function(data){
						$scope.productList = data;
						$scope.comboOffers=false;
						$scope.productTypes = [];
						for(var i=0;$scope.productList.length>=i;i++){
							
						var typeExist = $scope.checkType($scope.productList[i].productType.name);
						if(!typeExist){
							$scope.productTypes.push({
								"productTypeName":$scope.productList[i].productType.name,
								"productTypeId":$scope.productList[i].productType.productTypeId
							})
						  }
						}
					});
			};
			
			$scope.checkType = function(productTypeName){
				
				for(var i=0;i<$scope.productTypes.length;i++){
					
					if(productTypeName == $scope.productTypes[i].productTypeName){
							return true;
					}
				}
				
				return false;
				
			}
			
			$scope.activeprod = "prodactive others";
			
			$scope.setType = function(event,type){
				$scope.type =type;
				$(".others").removeClass("prodactive");
				$(event.currentTarget).addClass("prodactive");
			}
			$scope.getCartList = function(){
				if($rootScope.user){
					MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
						MyCartFactory.checkCartProductsQuantity($scope.productList,function(data){
							$scope.productList = data;
						});
					});
				}
			};
			
			$scope.getCartList();
			
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
		
			$scope.productDetailsCombo = function(ev,products){
				$rootScope.productDetails = products;
				$mdDialog.show({
					templateUrl: 'app/modules/products/productDetails/productDetailsCombo.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: "productDetailsCtrl"
				})
				.then(function() {
					
				}, function() {

				});
			}
			
			$rootScope.updateProductQuantity = function(item){
				angular.forEach($scope.productList,function(p){
					if(p.productId == item.product.productId){
						p.noOfQuantityInCart = 0;
					}
				});
			};

		}]);

