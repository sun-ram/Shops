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
                
        return apiClass;
    }]

});
