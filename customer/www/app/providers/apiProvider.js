angular.module('aviate.providers').provider('api', function ApiProvider() {

	var _apiUrl = null;
	this.setApiUrl = function (url) {
		_apiUrl = url;

	};

	var _apiHeaders = {};
	this.setApiHeaders = function (headers) {
		_apiHeaders = headers;
	};

	var http = null;
	var httpRequest = function (method, path, data, callback) {
		if (http == null) callback({
			error: true,
			errorCode: "HTTP_NULL"
		}, null);

		_apiHeaders['Content-Type'] = 'application/json';
		http({
			method: method,
			url: _apiUrl + path,
			headers: _apiHeaders,
			data: data
		})
		.success(function (data, status, headers, config) {
			if (data.error) {
				callback(data, null);
			} else {
				callback(null, data);
			}
		})
		.error(function (data, status, headers, config) {
			callback({
				error: true,
				errorCode: "CONNECTION_ERROR"
			}, null);
		});
	};



	this.$get = ['$http', function ($http) {
		http = $http;

		var apiClass = {}

		apiClass.User = {
				name: ""
		};

		apiClass.User.signUp = function (user, callback) {
			httpRequest("POST", "customer/signup", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.User.signIn = function (user, callback) {
			httpRequest("POST", "customer/login", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.User.forGetPassword = function (req, callback) {
			httpRequest("POST", "common/forgetpassword", req, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.User.verifyToken = function (user, callback) {
			httpRequest("POST", "common/verifytoken", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.User.resetPassword = function (user, callback) {
			httpRequest("POST", "common/resetpassword", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		// Products Api

		apiClass.Product = {
				name: ""
		};

		apiClass.Product.getProductsFromCategory = function (product, callback) {
			httpRequest("POST", "product/productbycategory", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Product.getFutureProducts = function (product, callback) {
			httpRequest("POST", "product/getfutureproducts", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		
		apiClass.Product.getProductsByProductId = function (product, callback) {
			httpRequest("POST", "product/getproductdetails", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.Product.getProductsByProductTypeId = function (product, callback) {
			httpRequest("POST", "product/productbytype", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};		

		apiClass.Product.getTopCategory = function (product, callback) {
			httpRequest("POST", "product/gettopproduct", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.Product.allCategoriesWithProduct = function (product, callback) {
			httpRequest("POST", "productcategory/getallleafcategorylistWithProducts", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};		

		/*Location Api*/

		apiClass.Location = {
				name: ""
		};

		apiClass.Location.getStoreByLocation = function (location, callback) {
			httpRequest("POST", "store/getshoplist", location, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Location.getCity = function (location, callback) {
			httpRequest("POST", "store/getcity", location, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		/*Product Category*/

		apiClass.Category = {
				name: ""
		};

		apiClass.Category.getProductCategory = function (storeId, callback) {
			httpRequest("POST", "productcategory/store/getallcategorylist", storeId, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*My Cart*/

		apiClass.MyCart = {
				name: ""
		};

		apiClass.MyCart.addToCart = function (user, callback) {
			httpRequest("POST", "mycart/addtocart", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.MyCart.getCartList = function (user, callback) {
			httpRequest("POST", "mycart/getMyCartList", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.MyCart.removeCartProduct = function (user, callback) {
			httpRequest("POST", "mycart/removefromcart", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
						
		/*My List*/
		/*apiClass.MyList = {
				name: ""
		};
		
		apiClass.MyList.addToMyList = function (user, callback) {
			httpRequest("POST", "customer/addmylist", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.MyList.getMyList = function (user, callback) {
			httpRequest("POST", "customer/getmylist", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.MyList.removeMyList = function (user, callback) {
			httpRequest("POST", "customer/removemylist", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/
		
		/*Checkout services*/

		apiClass.CheckOut = {
				name: ""
		};
		
		apiClass.CheckOut.addAddress = function (address, callback) {
			httpRequest("POST", "customer/addAddress", address, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.CheckOut.getAddressList = function (address, callback) {
			httpRequest("POST", "customer/getaddress", address, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.CheckOut.removeAddress = function (address, callback) {
			httpRequest("POST", "customer/deleteaddress", address, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.CheckOut.confirmOrder = function (order, callback) {
			httpRequest("POST", "sales/confirmorder", order, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.CheckOut.getTimeSlot = function (store, callback) {
			httpRequest("POST", "deliverytimeslot/get", store, function (err, data) {
				if (err) {
		
					callback(err, null);
		
				} else {
		
					callback(null, data);
				}
			});
		};
		/*Favourite*/
		apiClass.CheckOut.addFavourite = function (favourite, callback) {
			httpRequest("POST", "favourite/addfavourite", favourite, function (err, data) {
				if (err) {
		
					callback(err, null);
		
				} else {
		
					callback(null, data);
				}
			});
		};
		
		apiClass.CheckOut.getFavourite = function (favourite, callback) {
			httpRequest("POST", "favourite/getfavourite", favourite, function (err, data) {
				if (err) {
		
					callback(err, null);
		
				} else {
		
					callback(null, data);
				}
			});
		};
		
		apiClass.CheckOut.addFavouriteToCart = function (favourite, callback) {
			httpRequest("POST", "favourite/addFavouriteToCart", favourite, function (err, data) {
				if (err) {
		
					callback(err, null);
		
				} else {
		
					callback(null, data);
				}
			});
		};
		apiClass.CheckOut.viewProductInFavourite = function (favourite, callback) {
			httpRequest("POST", "favourite/getProductInFavourite", favourite, function (err, data) {
				if (err) {
		
					callback(err, null);
		
				} else {
		
					callback(null, data);
				}
			});
		};
		apiClass.CheckOut.favouriteToCheckout = function (favourite, callback) {
			httpRequest("POST", "favourite/favouriteToCheckout", favourite, function (err, data) {
				if (err) {
		
					callback(err, null);
		
				} else {
		
					callback(null, data);
				}
			});
		};
		
		apiClass.CheckOut.payment = function (payment, callback) {
			httpRequest("POST", "payment/gatewaydetails", payment, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		/*Shipping Charge*/
		
		apiClass.shippingCharge = {
				name: ""
		};

		apiClass.shippingCharge.getShippingCharge = function (merchant, callback) {
			httpRequest("POST", "shippingcharges/getshippingcharges", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		/*Tax Charge*/
		
		apiClass.tax = {
				name: ""
		};

		apiClass.tax.getTax = function (merchant, callback) {
			httpRequest("POST", "tax/gettax", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		/*Home Page Banners*/
				
				apiClass.Banner = {
						name: ""
				};
		
				apiClass.Banner.getBannerList = function (banner, callback) {
					httpRequest("POST", "banner/getbannerlist", banner, function (err, data) {
						if (err) {
		
							callback(err, null);
		
						} else {
		
							callback(null, data);
		
						}
					});
				};
				

				/*Country and State List */
							apiClass.Region = {
									name: ""
							};

							apiClass.Region.getCountries = function (country, callback) {
								httpRequest("POST", "common/country", country, function (err, data) {
									if (err) {

										callback(err, null);

									} else {

										callback(null, data);

									}
								});
							};

		return apiClass;
	}]

});
