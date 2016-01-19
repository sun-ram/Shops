angular.module('aviate.factories')
.factory('MyCartFactory', function ($rootScope, ipCookie, MyCartServices,toastr,CONSTANT) {

	var _cart, 
	_isExistInCart, 
	factory = {};
	
/*	factory.productOffer = function(product){
			
			
			for(var i=0;i<product.productOfferLines.length;i++){
				var offer = product.productOfferLines[i].productOfferVo;
				for(var j=0;j<offer.productOfferLinesVo.length;j++){
					var productvo = offer.productOfferLinesVo[j].productVo;
					
					if($rootScope.myCart.cartItem.length >0){
						for(var k=0;k<$rootScope.myCart.cartItem.length;k++){
							//Check is offer product
							if(myCart.cartItem[k].product.productOffer){
								continue;
							}else if(myCart.cartItem[k].product.productOfferLine){
								continue;
							}else if($rootScope.myCart.cartItem[k].product.productId == productvo.productId){
								
							}
						}
				}
			}
		}
	}*/
	
	factory.checkProductOffer = function(){
		
		for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
			if($rootScope.myCart.cartItem[i].product.productOffers.length > 0 && $rootScope.myCart.cartItem.length > 1){
				
				
			}
		}
		
	}
	
	function checkInCart(product) {
	    return _.find($rootScope.myCart.cartItem, function(goal) {
	        return goal.product.productId == product.productId;
	    });
	}
	
	
	factory.checkComboOffer = function(){
		
		for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
			if($rootScope.myCart.cartItem[i].product.isChild && $rootScope.myCart.cartItem.length > 1){
				var productOfferLines = $rootScope.myCart.cartItem[i].product.productOfferLines; 	
				
				for(var j = 0; j<productOfferLines.length; j++){
					
					var offer = productOfferLines[j].productOfferVo;
					var offerLines = offer.productOfferLinesVo;
					
					var mappedProductCount = 0;
					var products = [];
/*					if($rootScope.myCart.length <= offerLines.length){
						continue;
					}*/
					if(offerLines.length > 1){
					for(var k = 0; k<offerLines.length; k++){
						for(var m = 0; m<$rootScope.myCart.cartItem.length; m++){
							if($rootScope.myCart.cartItem[m].product.productId == offerLines[k].productVo.productId){
							mappedProductCount++;
							products.push({"product" : $rootScope.myCart.cartItem[m].product,"index" : m,"qty": $rootScope.myCart.cartItem[m].qty});
						}
						}
					}
					}
					if(mappedProductCount == offerLines.length){
						for(var l=0; l<products.length;l++){
							if(products[l].product,products[l].qty == 1){
								
								
								if($rootScope.user && $rootScope.user.userId){
									var cartDetails = {
											customer : {customerId : $rootScope.user.userId}, 
											store : {storeId : $rootScope.store.storeId}, 
											product : {productId : products[l].product.productId}
									};
									MyCartServices.removeCartProduct(cartDetails).then(function(data){
										console.log('get Mylist success in Main Nav');
										$rootScope.myCart.cartItem.splice(products[l].index - l, 1);
									});
									
								}else{
									$rootScope.myCart.cartItem.splice(products[l].index - l, 1);
									factory.myCartTotalPriceCalculation();
								}
								
								
								checkQuantity(products[l].product,products[l].index - l);
							}else{
								var offers = {};
								offers.product = products[l].product;
								offers.product.noOfQuantityInCart = offers.product.noOfQuantityInCart - 1;
								factory.addToCartForCombo(offers.product);
							}
							
						}
						var productList = [];
						var isCheck = checkInCart(offer.productVo);
						if(isCheck){
							offer.productVo.noOfQuantityInCart = isCheck.qty + 1;
						}else{
							offer.productVo.noOfQuantityInCart = 1;
						}
						
						factory.addToCartForCombo(offer.productVo);
						if($rootScope.user && $rootScope.user.userId){
						MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
							factory.myCartTotalPriceCalculation();
						});
						return;
						}
						
					}
				}
				
			}
			
			
		}
		
	}
	
	factory.addToCart = function (_product, _productList , callback) {
		_isExistInCart = false;
		if(_product.noOfQuantityInCart > 0){
			if($rootScope.user && $rootScope.user.userId){
				var cartDetails = {
						customer : {customerId : $rootScope.user.userId}, 
						store : {storeId : $rootScope.store.storeId}, 
						product : {productId : _product.productId}, 
						qty : _product.noOfQuantityInCart
				}
				if(_product.discount != null){
					cartDetails.discountId = _product.discount.discountId;
				}
				//factory.myCartTotalPriceCalculation();
				MyCartServices.addToCart(cartDetails).then(function(data){
					console.log('Add To My Cart in factory');
					factory.myCartTotalPriceCalculation();
					MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
						factory.myCartTotalPriceCalculation();
						console.log('Get To My Cart in factory');
						factory.checkComboOffer();
					});
				})
			}else{
				for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
					if($rootScope.myCart.cartItem[i].product.productId == _product.productId){
						$rootScope.myCart.cartItem[i].qty = _product.noOfQuantityInCart;
						$rootScope.myCart.cartItem[i].product = _product;
						_isExistInCart = true;
					}
				}
				if(!_isExistInCart){
					if(_product.discount){
						if(_product.discount.discountPercentage){
							var discountPrice = _product.price - (_product.price * _product.discount.discountPercentage/100);
						}
						if(_product.discount.discountAmount){
							var discountPrice = _product.price - _product.discount.discountAmount;
						}
					}else{
						var discountPrice = _product.price;
					}	
					$rootScope.myCart.cartItem.push({
						product:_product,
						qty:_product.noOfQuantityInCart,
						discountPrice : discountPrice
					});
				}
				factory.myCartTotalPriceCalculation();
				factory.checkComboOffer();
			}
		}else if(_product.noOfQuantityInCart == 0){
			for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
				if($rootScope.myCart.cartItem[i].product.productId == _product.productId){
					factory.removeFromCart(_product.productId, i);
				}
			}
			factory.checkComboOffer();
		}
		//ipCookie("myCart",$rootScope.myCart);
		callback(_productList);
		
	}
	
	
	
	factory.addToCartForCombo = function (_product) {
		_isExistInCart = false;
		if(_product.noOfQuantityInCart > 0){
			if($rootScope.user && $rootScope.user.userId){
				var cartDetails = {
						customer : {customerId : $rootScope.user.userId}, 
						store : {storeId : $rootScope.store.storeId}, 
						product : {productId : _product.productId}, 
						qty : _product.noOfQuantityInCart
				}
				if(_product.discount != null){
					cartDetails.discountId = _product.discount.discountId;
				}
				//factory.myCartTotalPriceCalculation();
				MyCartServices.addToCart(cartDetails).then(function(data){
					console.log('Add To My Cart in factory');
					factory.myCartTotalPriceCalculation();
					MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
						factory.myCartTotalPriceCalculation();
						console.log('Get To My Cart in factory');
					});
				})
			}else{
				for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
					if($rootScope.myCart.cartItem[i].product.productId == _product.productId){
						$rootScope.myCart.cartItem[i].qty = _product.noOfQuantityInCart;
						$rootScope.myCart.cartItem[i].product = _product;
						_isExistInCart = true;
					}
				}
				if(!_isExistInCart){
					if(_product.discount){
						if(_product.discount.discountPercentage){
							var discountPrice = _product.price - (_product.price * _product.discount.discountPercentage/100);
						}
						if(_product.discount.discountAmount){
							var discountPrice = _product.price - _product.discount.discountAmount;
						}
					}else{
						var discountPrice = _product.price;
					}	
					$rootScope.myCart.cartItem.push({
						product:_product,
						qty:_product.noOfQuantityInCart,
						discountPrice : discountPrice
					});
				}
				factory.myCartTotalPriceCalculation();
			}
		}else if(_product.noOfQuantityInCart == 0){
			for(var i = 0; i<$rootScope.myCart.cartItem.length; i++){
				if($rootScope.myCart.cartItem[i].product.productId == _product.productId){
					factory.removeFromCart(_product.productId, i);
				}
			}
		}
		checkComboQuantity(_product);
		//ipCookie("myCart",$rootScope.myCart);
		//callback(_productList);
	}


	factory.removeFromCart = function (productId,_index,callback) {
		if($rootScope.user && $rootScope.user.userId){
			var cartDetails = {
					customer : {customerId : $rootScope.user.userId}, 
					store : {storeId : $rootScope.store.storeId}, 
					product : {productId : productId}
			};
			MyCartServices.removeCartProduct(cartDetails).then(function(data){
				console.log('get Mylist success in Main Nav');
				$rootScope.myCart.cartItem.splice(_index, 1);
				MyCartServices.getCartList({"customer" : {"customerId" : $rootScope.user.userId},"store" : {"storeId" : $rootScope.store.storeId}}).then(function(data){
					factory.myCartTotalPriceCalculation();
					console.log('get Cart in factory');
				});
			});
			
		}else{
			$rootScope.myCart.cartItem.splice(_index, 1);
			factory.myCartTotalPriceCalculation();
			//ipCookie("myCart",$rootScope.myCart);
		}
		if(callback){
			callback();
		}
	}

	factory.myCartTotalPriceCalculation = function () {
		var  _totalAmount = 0;
		if($rootScope.myCart && $rootScope.myCart.cartItem){
		for(var i=0; i<$rootScope.myCart.cartItem.length; i++){
			var _subTotal = 0;
			_subTotal = $rootScope.myCart.cartItem[i].qty * $rootScope.myCart.cartItem[i].discountPrice;
			$rootScope.myCart.cartItem[i].product.subTotal = _subTotal;
			_totalAmount += _subTotal;
		}
		}
		_totalAmount = Math.ceil(_totalAmount * 100) / 100;
		$rootScope.myCart.cartTotalAmount = _totalAmount;
		
		//shipping charge
		if(_totalAmount != 0 && $rootScope.shippingCharges != undefined && $rootScope.shippingCharges.length !=0){
		for(var i =0;i<$rootScope.shippingCharges.length;i++){
				if(_totalAmount <= $rootScope.shippingCharges[i].amountRange){
				$rootScope.myCart.shippingCharges = $rootScope.shippingCharges[i].chargingAmount;
				i = $rootScope.shippingCharges.length;
				}	
			}
		}else{
			$rootScope.myCart.shippingCharges =0;
		}
		if(_totalAmount != 0 && $rootScope.shippingCharges && $rootScope.shippingCharges.length == 1){
			$rootScope.myCart.shippingCharges = $rootScope.shippingCharges[0].chargingAmount;
		}
		//Tax Charge
		
		$rootScope.taxList
		$rootScope.myCart.taxs = [];
		$rootScope.myCart.taxAmount =0;
		if($rootScope.taxList != undefined && $rootScope.taxList.length !=0){
			for(var i=0;i<$rootScope.taxList.length;i++){
				$rootScope.name = $rootScope.taxList[i].name;
				$rootScope.taxPercentage = $rootScope.taxList[i].taxPercentage;
				$rootScope.rate = $rootScope.myCart.cartTotalAmount*($rootScope.taxPercentage/100);
				$rootScope.rate = Math.ceil($rootScope.rate * 100)/100;
				$rootScope.myCart.taxs.push({
					"name":$rootScope.name,
					"taxPercentage":$rootScope.taxPercentage,
					"rate":$rootScope.rate
				});
				$rootScope.myCart.taxAmount = $rootScope.myCart.taxAmount + $rootScope.rate;
			}
			$rootScope.myCart.taxAmount = Math.ceil($rootScope.myCart.taxAmount * 100) / 100;
		}
		//cartTotalAmount*(2.5/100)
		$rootScope.myCart.grossAmount = $rootScope.myCart.taxAmount+$rootScope.myCart.cartTotalAmount+$rootScope.myCart.shippingCharges;
		//ipCookie("myCart",$rootScope.myCart);
		$rootScope.myCart.grossAmount = Math.ceil($rootScope.myCart.grossAmount * 100) / 100;
		localStorage.setItem('myCart',JSON.stringify($rootScope.myCart));
	}


	factory.checkCartProductsQuantity = function (_productList , callback) {
		if($rootScope.myCart.cartItem && $rootScope.myCart.cartItem.length > 0){
			for(var i = 0; $rootScope.myCart.cartItem.length > i; i++){
				for(var j = 0; _productList.length > j; j++){
					if(_productList[j].productId == $rootScope.myCart.cartItem[i].product.productId){
						_productList[j].noOfQuantityInCart = $rootScope.myCart.cartItem[i].qty;
					}	
				}
			}
		}
		callback(_productList);
	}

	factory.checkSingleProductsinCart = function (_product , callback) {
		if($rootScope.myCart.cartItem.length > 0){
			for(var i = 0; $rootScope.myCart.cartItem.length > i; i++){
				if(_product.productId == $rootScope.myCart.cartItem[i].product.productId){
					_product.noOfQuantityInCart = $rootScope.myCart.cartItem[i].qty;
				}
			}
		}
		callback(_product);
	}
	
	factory.checkProductInMyList = function (_product , callback) {
		var _isProductMyList = false;
		if($rootScope.myList.listItem.length > 0){
			for(var i = 0; $rootScope.myList.listItem.length > i; i++){
				if(_product.productId == $rootScope.myList.listItem[i].product.productId){
					_isProductMyList = true;
				}
			}
		}
		_product.isProductMyList = _isProductMyList
		callback(_product);
	}
	

	factory.checkMyListProductsList = function (_productList , callback) {
		if($rootScope.myList.listItem){
		if($rootScope.myList.listItem.length > 0){
			for(var i = 0; $rootScope.myList.listItem.length > i; i++){
				for(var j = 0; _productList.length > j; j++){
					if(_productList[j].productId == $rootScope.myList.listItem[i].product.productId){
						_productList[j].isProductMyList = true;
					}	
				}
			}
		}
		}
		callback(_productList);
	
	}
	
	function checkQuantity(item, index) {
			if($rootScope.updateProductQuantity)
				$rootScope.updateProductQuantity(item);
			if($rootScope.topcategories){
				angular.forEach($rootScope.topcategories,function(p){
					if(p.productId == item.productId){
						p.noOfQuantityInCart = 0;
					}
				});
			}
			
			if($rootScope.isBundleProducts){
				angular.forEach($rootScope.isBundleProducts,function(p){
					if(p.productId == item.productId){
						p.noOfQuantityInCart = 0;
					}
				});
			}
			
			if($rootScope.comboOffer){
				angular.forEach($rootScope.comboOffer,function(p){
					if(p.productId == item.productId){
						p.noOfQuantityInCart = 0;
					}
				});
			}
			
			//$rootScope.getAllCategoryWithProduct();	
			if($rootScope.categoriesWithProduct){
				angular.forEach($rootScope.categoriesWithProduct,function(p){
					angular.forEach(p.products,function(s){
						if(s.productId == item.productId){
							s.noOfQuantityInCart = 0;
						}
					});	
				});
				}
	};
	
	function checkComboQuantity(product){
		angular.forEach($rootScope.comboOffer,function(p){
			if(p.productId == product.productId){
				p.noOfQuantityInCart = product.noOfQuantityInCart;
			}
		});
		
	}

	return factory;
});

