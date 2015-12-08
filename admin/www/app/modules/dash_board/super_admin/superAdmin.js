aviateAdmin.controller("superDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT', '$rootScope', 'CommonServices', 'StoreServices', 'api', '$http',
    function ($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, $rootScope, CommonServices, StoreServices, api, $http) {
        var width = 300;
        var height = 300;
        var reportType = 15;
        var commition = 3;
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
        $scope.randomMarkers = [{
            "latitude": 12.916149,
            "longitude": 80.152353,
            "title": "Jayam SuperMarket",
            "id": 0
        }, {
            "latitude": 12.915115,
            "longitude": 80.153115,
            "title": "AGS SuperMarket",
            "id": 1
        }, {
            "latitude": 12.922847,
            "longitude": 80.151881,
            "title": "Coffee Day",
            "id": 2
        }];
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

        $scope.proceedSalesOrder = function (callback) {
          
            var anayed = [];
            $scope.testdata = [{
                key: "O",
                y: 0
            }];

            $http({
                    method: 'GET',
                    url: 'http://192.168.1.100:3000/shopsbacker/salesOrder'
                })
                .success(function (data, status) {
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
                        tempDateObj = new Date((data.Books[i].delivery_date).substring(0, 10));
                        if (tempDateObj.getTime() > endDateObj.getTime()) {
                            tempArray[index] = data.Books[i];
                            index++;
                            totalAmount = totalAmount + data.Books[i].order_amount;
                        }
                    }
                    len = tempArray.length;
                    var tmpAvgObj = {},
                        tmpAvgObj1 = [{}];
                    for (var j = 0; j < reportType; j++) {
                        tempDateObj.setTime(endDateObj.getTime() + (j * 24 * 3600000));
                        totalAmount = 0;
                        for (var i = 0; i < len; i++) {
                            tempDateObj2 = new Date((tempArray[i].delivery_date).substring(0, 10));
                            if (tempDateObj.getTime() == tempDateObj2.getTime()) {
                                totalAmount = totalAmount + tempArray[i].order_amount;
                            }
                        }
                        if (totalAmount > 0) {
                            tmpAvgObj.key = month[tempDateObj.getMonth()] + (tempDateObj.getDate() + 1).toString() + ' ₹';
                            tmpAvgObj.y = totalAmount;
                            $scope.testdata.push(angular.copy(tmpAvgObj));

                        }
                        tmpAvgObj = {};
                        tmpAvgObj.label = month[tempDateObj.getMonth()] + (tempDateObj.getDate() + 1).toString();
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
                    url: 'http://192.168.1.100:3000/shopsbacker/salesOrderLine'
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
                    url: 'http://192.168.1.100:3000/shopsbacker/merchant'
                })
                .success(function (data, status) {
                
                    $scope.merchants = data;
                    console.log("merchant Line =>", data);
                    if (data && data.Books) {
                        $scope.totalMerchants = data.Books.length;
                    }
                    $scope.locateMerchants();
                })
                .error(function (data, status) {
                    console.log("merchant error case ", data);
                });
        };
         $scope.proceedStore = function (callback){
            $http({
                    method: 'GET',
                    url: 'http://192.168.1.100:3000/shopsbacker/store'
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
    $scope.compSalesToday = function() {
            var today = new Date();
            var todayDateObj = new Date(today.toISOString().substring(0, 10));
            var yesterdayDateObj = new Date(today.toISOString().substring(0, 10));
            yesterdayDateObj.setTime(todayDateObj.getTime() - 86400000);
            var len = $scope.salesOrders.Books.length;
            var i = 0,
                tempDateObj;
            for (; i < len; i++) {
                tempDateObj = new Date(($scope.salesOrders.Books[i].delivery_date).substring(0, 10));
                tempDateObj.setTime(tempDateObj.getTime() + 86400000);
                if (tempDateObj.getTime() == todayDateObj.getTime()) {
                    $scope.todayTotSale = $scope.todayTotSale + $scope.salesOrders.Books[i].order_amount;
                } else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
                    $scope.yesterdayTotSale = $scope.yesterdayTotSale + $scope.salesOrders.Books[i].order_amount;
                }
            }
            $scope.todayTotSale =   (commition/100) * $scope.todayTotSale;
            $scope.yesterdayTotSale = (commition/100) * $scope.yesterdayTotSale;
            $scope.salesGrowthToday = (($scope.todayTotSale - $scope.yesterdayTotSale)/$scope.yesterdayTotSale)*100;
            $scope.salesGrowthToday = Math.round($scope.salesGrowthToday * 100) / 100;
            
	};
	$scope.locateMerchants = function() {
		var i = 0;
		var len = $scope.merchants.Books.length;
		var locationObj = {};
		for (; i < len; i++) {
			locationObj.latitude = $scope.merchants.Books[i].latitude;
			locationObj.longtitude = $scope.merchants.Books[i].longtitude;
			locationObj.title = $scope.merchants.Books[i].merchant_name;
			locationObj.id = $scope.randomMarkers[$scope.randomMarkers.length - 1] + 1;
            if(locationObj.latitude && locationObj.longtitude){
			     $scope.randomMarkers.push(angular.copy(locationObj));
            }
		}
	}

        $scope.proceedSalesOrder();
        $scope.ProceedMerchant();
        $scope.proceedSalesOrderLine();
        $scope.proceedStore();


 }]);