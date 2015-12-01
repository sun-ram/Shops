aviateAdmin.controller("merchantDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT', '$rootScope', 'CommonServices', 'StoreServices',
    function ($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, $rootScope, CommonServices, StoreServices, api, $http) {
        var width = 300;
        var height = 300;
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
            var reportType = 15;
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
                    console.log("salesOrder Exected success case ", data);

                    var tempDateObj2;
                    var endDateObj = new Date("2015-11-30");
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
                    console.info("Array Filtered= ", tempArray);
                    len = tempArray.length;
                    var tmpAvgObj = {},
                        tmpAvgObj1 = [{}];
                    for (var j = 0; j < reportType; j++) {
                        tempDateObj.setTime(endDateObj.getTime() + ((j + 1) * 24 * 3600000));
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
                    console.log("Sales order Line =>", data);
                    /*callback();*/
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });

        };
        $scope.proceedSalesOrder();
        $scope.proceedSalesOrderLine();
        $scope.proceedStore();


 }]);