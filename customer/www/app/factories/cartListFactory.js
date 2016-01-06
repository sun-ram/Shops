angular.module('aviate.factories')
.factory('MyCartFactory', function ($rootScope, ipCookie, MyCartServices,toastr,CONSTANT) {

	var _cart, 
	_isExistInCart, 
	factory = {};

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
		//ipCookie("myCart",$rootScope.myCart);
		callback(_productList);
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
		_totalAmount = Math.ceil(_totalAmount * 10) / 10;
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
				$rootScope.rate = Math.ceil($rootScope.rate * 10)/10;
				$rootScope.myCart.taxs.push({
					"name":$rootScope.name,
					"taxPercentage":$rootScope.taxPercentage,
					"rate":$rootScope.rate
				});
				$rootScope.myCart.taxAmount = $rootScope.myCart.taxAmount + $rootScope.rate;
			}
		}
		//cartTotalAmount*(2.5/100)
		$rootScope.myCart.grossAmount = $rootScope.myCart.taxAmount+$rootScope.myCart.cartTotalAmount+$rootScope.myCart.shippingCharges;
		//ipCookie("myCart",$rootScope.myCart);
		$rootScope.myCart.grossAmount = Math.ceil($rootScope.myCart.grossAmount * 10) / 10;
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

	return factory;
});