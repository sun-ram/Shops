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
				errorCode: "UNKNOWN_ERROR"
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

		apiClass.User.forGetPassword = function (user, callback) {
			httpRequest("POST", "customer/forgetpassword", user, function (err, data) {
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
			httpRequest("POST", "product/getallproductsbycategory", product, function (err, data) {
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
			httpRequest("POST", "product/getproducts", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};		

		apiClass.Product.getTopCategory = function (product, callback) {
			httpRequest("POST", "product/gettopproducts", product, function (err, data) {
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
			httpRequest("POST", "product/getshoplist", location, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Location.getCity = function (location, callback) {
			httpRequest("POST", "product/getcity", location, function (err, data) {
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
			httpRequest("POST", "product/merchant/getcategories", storeId, function (err, data) {
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
			httpRequest("POST", "product/addtocart", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.MyCart.getCartList = function (user, callback) {
			httpRequest("POST", "product/getmycartlist", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.MyCart.removeCartProduct = function (user, callback) {
			httpRequest("POST", "product/deletefromcart", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*My List*/

		apiClass.MyList = {
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
		};
		
		/*Checkout services*/

		apiClass.CheckOut = {
				name: ""
		};
		
		apiClass.CheckOut.addAddress = function (address, callback) {
			httpRequest("POST", "checkout/updateaddress", address, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.CheckOut.getAddressList = function (address, callback) {
			httpRequest("POST", "checkout/getlistofaddress", address, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.CheckOut.removeAddress = function (address, callback) {
			httpRequest("POST", "checkout/deleteaddress", address, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.CheckOut.confirmOrder = function (order, callback) {
			httpRequest("POST", "checkout/deleteaddress", order, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		
		apiClass.CheckOut.payment = function (payment, callback) {
			httpRequest("POST", "checkout/deleteaddress", payment, function (err, data) {
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
