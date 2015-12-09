aviateAdmin.controller("merchantDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT', '$rootScope', 'CommonServices', 'StoreServices',  'api', '$http',
    function ($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, $rootScope, CommonServices, StoreServices, api, $http) {
      var width = 300;
        var height = 300;
        var reportType = 15;
        var month = new Array();
        month[0] = "Jan";
        month[1] = "Feb";
        month[2] = "Mar";
        month[3] = "Apr";
        month[4] = "May";
        month[5] = "Jun";
        month[6] = "Jul";
        month[7] = "Aug";
        month[8] = "Sep";
        month[9] = "Oct";
        month[10] = "Nov";
        month[11] = "Dec";
       $scope.salesOrders;
	   $scope.merchants;
	   $scope.stores;
	   $scope.salesOrderLines;
	   $scope.yesterdayTotSale = 0;
	   $scope.todayTotSale = 0;
	   $scope.totalMerchants = 0;
       $scope.salesGrowthToday = 0;

        $scope.historicalBarChart = [
            {
                key: "Cumulative Return",
                values: []
            }
        ];

        $scope.map = {
            center: {
                latitude: 12.916292,
                longitude: 80.152379
            },
            zoom: 15,
            bounds: {}
        };
        $scope.options = {
            scrollwheel: false
        };

        //Random Markers
        
        //Jayam SuperMarket 12.916149, 80.152353
        //AGS SuperMArket   12.915115, 80.153115
        //Coffee Day        12.922847, 80.151881

         //Pie chart
            $scope.drawPiechart = function (){
                nv.addGraph(function () {
                    var chart = nv.models.pie()
                        .x(function (d) {
                            return d.key;
                        })
                        .y(function (d) {
                            return d.y;
                        })
                        .width(width)
                        .height(height)
                        .labelType(function (d, i, values) {
                            return values.key + ':' + values.value;
                        });

                    d3.select("#test0")
                        .datum([$scope.testdata])
                        .transition().duration(1200)
                        .attr('width', width)
                        .attr('height', height)
                        .call(chart);

                    return chart;
                });
            };
        
         //Revenue chart
         $scope.drawReviewChart = function (){
            nv.addGraph(function () {
                var chart = nv.models.pie()
                    .x(function (d) {
                        return d.key;
                    })
                    .y(function (d) {
                        return d.y;
                    })
                    .width(width)
                    .height(height)
                    .labelType('percent')
                    .valueFormat(d3.format('%'))
                    .donut(true);

                d3.select("#test2")
                    .datum([$scope.testdata])
                    .transition().duration(1200)
                    .attr('width', width)
                    .attr('height', height)
                    .call(chart);

                return chart;
            });   
        };
        
        //Bar Chart
        $scope.drawBarChart = function(){
            nv.addGraph(function () {
                var chart = nv.models.discreteBarChart()
                    .x(function (d) {
                        return d.label
                    })
                    .y(function (d) {
                        return d.value
                    })
                    .staggerLabels(true)
                    //.staggerLabels(historicalBarChart[0].values.length > 8)
                    .showValues(true)
                    .duration(250);

                d3.select('#chart1 svg')
                    .datum($scope.historicalBarChart)
                    .call(chart);

                nv.utils.windowResize(chart.update);
                return chart;
            });
        }
        
        function getMerchantById(id){
            for (var i=0; i < $scope.merchants.Books.length; i++) {
                if ($scope.merchants.Books[i].MERCHANT_ID == id) {
                    return $scope.merchants.Books[i].NAME;
                }
            }
        };

        $scope.proceedSalesOrder = function (callback) {
          
            var anayed = [];
            $scope.testdata = [{
                key: "O",
                y: 0
            }];

            $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/salesOrder'
                })
                .success(function (data, status) {
					console.log("$rootScope.user",$rootScope.user);
				/*	data.Books[data.Books.length - 1].MERCHANT_ID = $rootScope.user.merchantId;*/
                    console.log("salesOrder Exected success case before", data);
				 	data.Books = _.reject(data.Books, function(book){ return book.MERCHANT_ID != $rootScope.user.merchantId; });
					console.log("salesOrder Exected success case before", data);
					$scope.salesOrders = data;
                    var tempDateObj2;
                    var today = new Date();
			        var endDateObj = new Date(today.toISOString().substring(0, 10));
                    endDateObj.setTime(endDateObj.getTime() - (reportType * 24 * 3600000));
                    var i = 0;
                    len = data.Books.length;
                    var tempArray = [];
                    var index = 0;
                    var totalAmount = 0;
                    for (; i < len; i++) {
                        tempDateObj = new Date((data.Books[i].DELIVERY_DATE).substring(0, 10));
                        if (tempDateObj.getTime() > endDateObj.getTime()) {
                            tempArray[index] = data.Books[i];
                            index++;
                            totalAmount = totalAmount + data.Books[i].NET_AMOUNT;
                        }
                    }
                    var x = {};
                    var storeIds=[];
                    for (var i = 0; i < tempArray.length; ++i) {
                        var obj = tempArray[i];
                        if (x[obj.MERCHANT_ID] === undefined && obj.MERCHANT_ID){
                                x[obj.MERCHANT_ID] = [getMerchantById(obj.MERCHANT_ID)]; 
                                storeIds.push(obj.MERCHANT_ID);
                        }
                        if(obj.MERCHANT_ID)
                                x[obj.MERCHANT_ID].push(obj.NET_AMOUNT);
                    }
                    len = tempArray.length;
                    var tmpAvgObj = {},
                        tmpAvgObj1 = [{}];
                    for (var j = 0; j < storeIds.length; j++) {
                        totalAmount = 0;
                        for (var i = 1; i < x[storeIds[j]].length; i++) {
                                totalAmount = totalAmount + x[storeIds[j]][i];
                        }
                        if (totalAmount > 0) {
                            tmpAvgObj.key = x[storeIds[j]][0]+ ' ₹';
                            tmpAvgObj.y = totalAmount;
                            $scope.testdata.push(angular.copy(tmpAvgObj));

                        }
                        tmpAvgObj = {};
                        tmpAvgObj.label = x[storeIds[j]][0]+"-"+storeIds[j];
                        tmpAvgObj.value = totalAmount;
                        $scope.historicalBarChart[0].values.push(angular.copy(tmpAvgObj));
                    }
                    $scope.drawPiechart();
                    $scope.drawReviewChart();
                    $scope.drawBarChart();
                    $scope.compSalesToday();
                    /*callback();*/
                
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });

           
           

        };
        $scope.proceedSalesOrderLine = function (callback){
            $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/salesOrderLine'
                })
                .success(function (data, status) {
                    $scope.salesOrderLines = data;
                    console.log("Sales order Line =>", data);
                    /*callback();*/
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });

        };
        
        $scope.ProceedMerchant = function (callback) {
             //Merchants
            $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/merchant'
                })
                .success(function (data, status) {
                
                    $scope.merchants = data;
                    console.log("merchant Line =>", data);
                    if (data && data.Books) {
                        $scope.totalMerchants = data.Books.length;
                    }
                    
                })
                .error(function (data, status) {
                    console.log("merchant error case ", data);
                });
        };
         $scope.proceedStore = function (callback){
            $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/store'
                })
                .success(function (data, status) {
                    $scope.stores = data;
                    console.log("Sales order Line =>", data);
                    /*callback();*/
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });

        };
		$scope.proceedAddresses = function (callback){
            $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/address'
                })
                .success(function (data, status) {
                    $scope.addresses = data;
                    console.log("proceedAddresses Line =>", data);
					$scope.locateMerchants();
                    /*callback();*/
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });

        };
		$scope.proceedUsers = function (callback){
            $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/users'
                })
                .success(function (data, status) {
                    $scope.users = data;
                    console.log("users  =>", data);
                    /*callback();*/
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });

        };
    $scope.compSalesToday = function() {
            var today = new Date();
            var todayDateObj = new Date(today.toISOString().substring(0, 10));
            var yesterdayDateObj = new Date(today.toISOString().substring(0, 10));
            yesterdayDateObj.setTime(todayDateObj.getTime() - 86400000);
            var len = $scope.salesOrders.Books.length;
            var i = 0,
                tempDateObj;
            for (; i < len; i++) {
                tempDateObj = new Date(($scope.salesOrders.Books[i].DELIVERY_DATE).substring(0, 10));
                tempDateObj.setTime(tempDateObj.getTime() + 86400000);
                if (tempDateObj.getTime() == todayDateObj.getTime()) {
                    $scope.todayTotSale = $scope.todayTotSale + $scope.salesOrders.Books[i].NET_AMOUNT;
                } else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
                    $scope.yesterdayTotSale = $scope.yesterdayTotSale + $scope.salesOrders.Books[i].NET_AMOUNT;
                }
            }
            $scope.todayTotSale =   $scope.todayTotSale;
            $scope.yesterdayTotSale = $scope.yesterdayTotSale;
            $scope.salesGrowthToday = ($scope.salesGrowthToday > 0)? ((($scope.todayTotSale - $scope.yesterdayTotSale)/$scope.yesterdayTotSale)*100) : 0;
            $scope.salesGrowthToday = ($scope.salesGrowthToday > 0)? (Math.round($scope.salesGrowthToday * 100) / 100) : 0;
            
	};
		$scope.getAddressIdByUserId = function (userID){
			var i=0;l = $scope.users.Books.length;
			for(;i<l;i++){
				if($scope.users.Books[i].USER_ID == userID){
					return $scope.users.Books[i].ADDRESS_ID;
				}
			}
		};
		$scope.getLatLongByUserId = function (userId){
			var addressId =  $scope.getAddressIdByUserId(userId);
			var i=0;l = $scope.addresses.Books.length;
			for(;i<l;i++){
				if($scope.addresses.Books[i].ADDRESS_ID == addressId){
					return $scope.addresses.Books[i];
				}
			}
		};
		
		$scope.locateMerchants = function() {
			$scope.tempMarkers = [{
			}];
			var i = 0;
			var len = $scope.stores.Books.length;
			var locationObj = {};
			for (; i < len; i++) {
				var addressObj= $scope.getLatLongByUserId($scope.stores.Books[i].USER_ID); 
				locationObj.latitude = parseFloat(addressObj.LATITUDE);
				locationObj.longitude = parseFloat(addressObj.LONGITUDE);
				locationObj.title = $scope.stores.Books[i].NAME;
				locationObj.id = $scope.stores.Books[i].MERCHANT_ID;
				if (locationObj.latitude && locationObj.longitude) {
					$scope.tempMarkers.push(angular.copy(locationObj));
				}
			}
			$scope.randomMarkers = $scope.tempMarkers;
		}
	
		$scope.proceedStore();
        $scope.ProceedMerchant();
		$scope.proceedUsers();
		$scope.proceedAddresses();
        $scope.proceedSalesOrderLine();
        $scope.proceedSalesOrder();
        

 }]);