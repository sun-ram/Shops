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
					$rootScope.myCart.cartItem.push({
						product:_product,
						qty:_product.noOfQuantityInCart
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
			_subTotal = $rootScope.myCart.cartItem[i].qty * $rootScope.myCart.cartItem[i].product.price;
			$rootScope.myCart.cartItem[i].product.subTotal = _subTotal;
			_totalAmount += _subTotal;
		}
		}
		$rootScope.myCart.cartTotalAmount = _totalAmount;
		$rootScope.myCart.shippingCharges = 100;
		$rootScope.myCart.taxAmount = $rootScope.myCart.cartTotalAmount*(12.5/100);
		$rootScope.myCart.serviceTax = $rootScope.myCart.cartTotalAmount*(2.5/100);
		//cartTotalAmount*(2.5/100)
		$rootScope.myCart.grossAmount = $rootScope.myCart.taxAmount+$rootScope.myCart.cartTotalAmount+$rootScope.myCart.shippingCharges+$rootScope.myCart.serviceTax;
		//ipCookie("myCart",$rootScope.myCart);
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