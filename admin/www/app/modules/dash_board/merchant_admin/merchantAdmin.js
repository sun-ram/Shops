aviateAdmin.controller("merchantDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT', 'myConfig', '$rootScope', 'CommonServices', 'StoreServices', 'api', '$http', '$q', '$timeout',
    function ($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, myConfig, $rootScope, CommonServices, StoreServices, api, $http, $q, $timeout) {
        var commonNodeURL = myConfig.node_server_url;
        var reportType = CONSTANT.DASHBOARD.DEFAULT_REPORT_TYPE;
        var commition = CONSTANT.DASHBOARD.COMMITION_PERCENTAGE;
        var deliveryTimeSpan = CONSTANT.DASHBOARD.DELIVERY_TIME_SPAN;
        var trafficTimeSpan = CONSTANT.DASHBOARD.ADJUSTABLE_TRAFIC_TIME_SPAN;
        var growthRatioChartSpan = 91;
        var width = 300;
        var height = 300;
        var index;
        var suspendMoveOperation = false;
        var dragstarted = false;
        var millisecondsPerday = 86400000;
        var today = new Date();
        var todayDateObj = new Date(angular.copy(today.toISOString().substring(0, 10)));
        var yesterdayDateObj = new Date(angular.copy(today.toISOString().substring(0, 10)));
        yesterdayDateObj.setTime(angular.copy(todayDateObj.getTime() - millisecondsPerday));
        var tomorrowDateObj = new Date(angular.copy(today.toISOString().substring(0, 10)));
        tomorrowDateObj.setTime(angular.copy(todayDateObj.getTime() + millisecondsPerday));
        $scope.salesOrders = {
            Books: []
        };
        $scope.merchants = [];
        $scope.stores;
        $scope.salesOrderLines;
        $scope.yesterdayTotSale = 0;
        $scope.todayTotSale = 0;
        $scope.totalMerchants = 0;
        $scope.totalStores = 0;
        $scope.totalSales = 0;
        $scope.totalCustomers = 0;
        $scope.salesOrdersCount = 0;
        $scope.salesGrowthToday = 0;
        $scope.qualirtStatsRecords = [];
        $scope.merchantsRaised = 0;
        $scope.storesRaised = 0;
        $scope.customersRaised = 0;
        $scope.salesOrdersRaised = 0;
        $scope.merchatsRaisedPrevWeek = 0;
        $scope.storesRaisedprevWeek = 0;
        $scope.customersRaisedPrevWeek = 0;
        $scope.salesOrderRaisedPreWeek = 0;
        $scope.receivedResponceCount = 0;
        $scope.d3LineData = [];
        var growthYcount = 10;
        var growthXcount = growthRatioChartSpan / 7;
        $scope.raisedTableTitle = "Half Year Report";


        $scope.historicalBarChart = [
            {
                key: "Cumulative Return",
                values: []
            }
        ];

        $scope.historicalBarChart2 = [
            {
                key: "Cumulative Return1",
                values: []
            }
        ];
        $scope.reportSelector = [{
            name: "Today",
            value: 1
		 }, {
            name: "Weekly",
            value: 7
        }, {
            name: "Half Month",
            value: 15
        }, {
            name: "Monthly",
            value: 30
        }];
        $scope.onReportChanged = function (reportValue) {
                reportType = reportValue;
                console.log("onReportChanged", reportValue);
                initiateAllMethods();
            }
            /*$scope.map = {
            	center: {
            		latitude: 12.916292,
            		longitude: 80.152379
            	},
            	zoom: 15,
            	bounds: {}
            };*/
        $scope.options = {
            scrollwheel: true
        };


        $scope.onMarkerClicked = function (marker, eventName, model) {
            model.show = !model.show;
        };

        //zoomedlinechart
        $scope.drawZoomedlinechart = function () {
            var data = [];
            var bandPos = [-1, -1];
            var pos;
            var xdomain = growthXcount;
            var ydomain = growthYcount;
            var colors = ["green", "red", "darkorange"];

            var margin = {
                top: 40,
                right: 40,
                bottom: 50,
                left: 60
            }
            var width = 1011 - margin.left - margin.right;
            var height = 450 - margin.top - margin.bottom;
            var zoomArea = {
                x1: 0,
                y1: 0,
                x2: xdomain,
                y2: ydomain
            };
            var drag = d3.behavior.drag();

            data = angular.copy($scope.d3LineData);
            /*data.push(d2);
            data.push(d31);*/
            var svg = d3.select("#graph").append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


            var x = d3.scale.linear()
                .range([0, width]).domain([0, xdomain]);

            var y = d3.scale.linear()
                .range([height, 0]).domain([0, ydomain]);

            var xAxis = d3.svg.axis()
                .scale(x)
                .orient("bottom");

            var yAxis = d3.svg.axis()
                .scale(y)
                .orient("left");

            var line = d3.svg.line()
                .interpolate("basis")
                .x(function (d) {
                    return x(d[0]);
                })
                .y(function (d) {
                    return y(d[1]);
                });
            var tip = d3.tip()
                .attr('class', 'd3-tip')
                .offset([0, 0])
                .html(function (d) {
                    return "<strong>growth:</strong> <span style='color:red'>" + data[idx] + "</span>";
                });

            svg.call(tip);

            var band = svg.append("rect")
                .attr("width", 0)
                .attr("height", 0)
                .attr("x", 0)
                .attr("id", "bandrect")
                .attr("y", 0)
                .attr("class", "band");

            svg.append("g")
                .attr("class", "x axis")
                .call(xAxis)
                .attr("transform", "translate(0," + height + ")");

            svg.append("g")
                .attr("class", "y axis")
                .call(yAxis)

            svg.append("clipPath")
                .attr("id", "clip")
                .append("rect")
                .attr("width", width)
                .attr("height", height);

            for (idx in data) {
                svg.append("path")
                    .attr("class", "line line" + idx)
                    .attr("clip-path", "url(#clip)")
                    .attr("d", line(data[idx]))
                    .attr("style", "z-index:999")
                    .on('mouseover', tip.show)
                    .on('mouseleave', tip.hide);
            }

            var zoomOverlay = svg.append("rect")
                .attr("width", width - 10)
                .attr("height", height)
                .attr("class", "zoomOverlay")
                .attr("id", "outerRect")
                /*.attr("style","display:none")*/
                .call(drag);

            var zoomout = svg.append("g");

            zoomout.append("rect")
                .attr("class", "zoomOut")
                .attr("width", 75)
                .attr("height", 40)
                .attr("x", -12)
                .attr("style", "display:none")
                .attr("y", height + (margin.bottom - 20))
                .on("click", function () {
                    zoomOut();
                });

            zoomout.append("text")
                .attr("class", "zoomOutText")
                .attr("width", 75)
                .attr("height", 30)
                .attr("x", -10)
                .attr("style", "display:none")
                .attr("y", height + (margin.bottom - 5))
                .text("Zoom Out");

            zoom();

            var divPos = {};
            var offset = $("rect").offset();
            $("#outerRect").mouseleave(function (e) {
                document.getElementById("bandrect").style.display = "none";
            });

            $("#outerRect").mouseenter(function (e) {
                document.getElementById("bandrect").style.display = "block";
            });

            $("rect").mousemove(function (e) {
                if (!dragstarted && !suspendMoveOperation) {
                    bandPos = [-1, -1];
                    divPos = {
                        left: e.pageX - offset.left,
                        top: e.pageY - offset.top
                    };
                    var rectWidth = (width / (growthRatioChartSpan / 7));
                    divPos.left = divPos.left - (divPos.left % rectWidth);
                    /*divPos.left = divPos.left - 35;*/
                    if (divPos.left < 0) divPos.left = 0;

                    for (var i = 0; i < rectWidth; i++) {

                        divPos.left++;
                        var pos = [divPos.left, 0];

                        if (pos[0] < bandPos[0]) {
                            d3.select(".band").
                            attr("transform", "translate(" + (pos[0]) + "," + bandPos[1] + ")");
                        }
                        if (pos[1] < bandPos[1]) {
                            d3.select(".band").
                            attr("transform", "translate(" + (pos[0]) + "," + pos[1] + ")");
                        }
                        if (pos[1] < bandPos[1] && pos[0] > bandPos[0]) {
                            d3.select(".band").
                            attr("transform", "translate(" + (bandPos[0]) + "," + pos[1] + ")");
                        }

                        //set new position of band when user initializes drag
                        if (bandPos[0] == -1) {
                            bandPos = pos;
                            bandPos[1] = 0;
                            d3.select(".band").attr("transform", "translate(" + bandPos[0] + "," + bandPos[1] + ")");
                        }

                        d3.select(".band").transition().duration(1)
                            .attr("width", Math.abs(bandPos[0] - pos[0]))
                            .attr("height", Math.abs(bandPos[1] - pos[1]));
                    }
                    var x1 = x.invert(bandPos[0]);
                    var x2 = x.invert(pos[0]);
                    x2 = Math.round(x2);
                    if (x2 < x1) {
                        var tmp = x2;
                        x2 = x1;
                        x1 = tmp;
                    } else {
                        x1 = Math.round(x1);
                    }

                    if (x1 < x2) {
                        zoomArea.x1 = x1;
                        zoomArea.x2 = x2;
                    } else {
                        zoomArea.x1 = x2;
                        zoomArea.x2 = x1;
                    }

                    //calling custom method to update values in html table
                    updategrowthTable(x1, x2);
                }
            });
            $('rect').on("mousedown", function (event) {
                suspendMoveOperation = false;
                d3.select(".band").transition()
                    .attr("width", 0)
                    .attr("height", 0)
                    .attr("x", -1)
                    .attr("y", -1);
            });

            drag.on("dragend", function () {
                dragstarted = false;

                var pos = d3.mouse(this);
                var x1 = x.invert(bandPos[0]);
                var x2 = x.invert(pos[0]);
                x2 = Math.round(x2);
                if (x2 < x1) {
                    var tmp = x2;
                    x2 = x1;
                    x1 = tmp;
                }
                if (x1 <= 0) {
                    //						x1 = x2-1;/* clickToDrag(pos);*/
                    //					    d3.select(".band").transition()
                    //						.attr("width", 0)
                    //						.attr("height", 0)
                    //						.attr("x", bandPos[0])
                    //						.attr("y", bandPos[1]) ;
                } else {
                    x1 = Math.round(x1);
                }

                if (x1 < x2) {
                    zoomArea.x1 = x1;
                    zoomArea.x2 = x2;
                } else {
                    zoomArea.x1 = x2;
                    zoomArea.x2 = x1;
                }

                //calling custom method to update values in html table
                updategrowthTable(x1, x2);


                var y1 = y.invert(pos[1]);
                var y2 = y.invert(bandPos[1]);

                if (x1 < x2) {
                    zoomArea.y1 = y1;
                    zoomArea.y2 = y2;
                } else {
                    zoomArea.y1 = y2;
                    zoomArea.y2 = y1;
                }

                bandPos = [-1, -1];

                /*d3.select(".band").transition()
                	.attr("width", 0)
                	.attr("height", 0)
                	.attr("x", bandPos[0])
                	.attr("y", bandPos[1]) ;*/

                /*zoom();*/


            });

            function updategrowthTable(start, end) {
                $scope.raisedTableTitle = "Weeks between " + Math.round(start) + " - " + Math.round(end);
                $scope.storesRaised = 0;
                $scope.customersRaised = 0;
                $scope.storesRaisedprevWeek = 0;
                $scope.customersRaisedPrevWeek = 0;
                $scope.salesOrderRaisedPreWeek = 0;
                $scope.salesOrdersRaised = 0;
                for (var i = end; i > start; i--) {
                    try {

                        $scope.storesRaised = $scope.storesRaised + $scope.d3LineData[0][i][1];
                        $scope.customersRaised = $scope.customersRaised + $scope.d3LineData[1][i][1];
                        $scope.salesOrdersRaised = $scope.salesOrdersRaised + $scope.d3LineData[2][i][1];
                    } catch (e) {}
                }
                if (start > 0) {
                    for (var i = (start); i < (end); i++) {
                        try {
                            $scope.storesRaisedprevWeek = $scope.storesRaisedprevWeek + $scope.d3LineData[0][i][1];
                            $scope.customersRaisedPrevWeek = $scope.customersRaisedPrevWeek + $scope.d3LineData[1][i][1];
                            $scope.salesOrderRaisedPreWeek = $scope.salesOrderRaisedPreWeek + $scope.d3LineData[2][i][1]

                        } catch (e) {
                            console.log("Exception ");
                        }
                    }
                    $scope.storesRaisedprevWeek = innercalculation($scope.storesRaisedprevWeek, $scope.storesRaised);
                    $scope.customersRaisedPrevWeek = innercalculation($scope.customersRaisedPrevWeek, $scope.customersRaised);
                    $scope.salesOrderRaisedPreWeek = innercalculation($scope.salesOrderRaisedPreWeek, $scope.salesOrdersRaised);

                    $scope.storesRaisedprevWeek = (Math.round($scope.storesRaisedprevWeek * 100)) / 100;
                    $scope.customersRaisedPrevWeek = (Math.round($scope.customersRaisedPrevWeek * 100)) / 100;
                    $scope.salesOrderRaisedPreWeek = (Math.round($scope.salesOrderRaisedPreWeek * 100)) / 100;
                } else {
                    $scope.storesRaisedprevWeek = 0;
                    $scope.customersRaisedPrevWeek = 0;
                    $scope.salesOrderRaisedPreWeek = 0;
                }
                $scope.$apply();
            }
            document.onmouseup = function (e) {
                cursorX = e.pageX;
                cursorY = e.pageY;
            }


            function innercalculation(val1, val2) {
                if (val1 == 0 && val2 == 0) {
                    return 0;
                } else if (val2 != 0 && val1 == 0) {
                    return 100;
                } else if (val2 == 0 && val1 != 0) {
                    return -100;
                } else {
                    return ((val2 - val1) / val1) * 100;
                }
            }

            function clickToDrag(mouseProp) {

                var pos = [cursorX, cursorY];
                bandPos = angular.copy(pos);
                bandPos[0] = bandPos[0] - 35;
                bandPos[1] = bandPos[1] - 35;

                if (pos[0] < bandPos[0]) {
                    d3.select(".band").
                    attr("transform", "translate(" + (pos[0]) + "," + bandPos[1] + ")");
                }
                if (pos[1] < bandPos[1]) {
                    d3.select(".band").
                    attr("transform", "translate(" + (pos[0]) + "," + pos[1] + ")");
                }
                if (pos[1] < bandPos[1] && pos[0] > bandPos[0]) {
                    d3.select(".band").
                    attr("transform", "translate(" + (bandPos[0]) + "," + pos[1] + ")");
                }

                //set new position of band when user initializes drag
                if (bandPos[0] == -1) {
                    bandPos = pos;
                    bandPos[1] = 0;
                    d3.select(".band").attr("transform", "translate(" + bandPos[0] + "," + bandPos[1] + ")");
                }

                d3.select(".band").transition().duration(1)
                    .attr("width", Math.abs(bandPos[0] - pos[0]))
                    .attr("height", Math.abs(bandPos[1] - pos[1]));

            }

            drag.on("drag", function () {
                dragstarted = true;
                suspendMoveOperation = true;
                var pos = d3.mouse(this);

                if (pos[0] < bandPos[0]) {
                    d3.select(".band").
                    attr("transform", "translate(" + (pos[0]) + "," + bandPos[1] + ")");
                }
                if (pos[1] < bandPos[1]) {
                    d3.select(".band").
                    attr("transform", "translate(" + (pos[0]) + "," + pos[1] + ")");
                }
                if (pos[1] < bandPos[1] && pos[0] > bandPos[0]) {
                    d3.select(".band").
                    attr("transform", "translate(" + (bandPos[0]) + "," + pos[1] + ")");
                }

                //set new position of band when user initializes drag
                if (bandPos[0] == -1) {
                    bandPos = pos;
                    bandPos[1] = 0;
                    d3.select(".band").attr("transform", "translate(" + bandPos[0] + "," + bandPos[1] + ")");
                }

                d3.select(".band").transition().duration(1)
                    .attr("width", Math.abs(bandPos[0] - pos[0]))
                    .attr("height", Math.abs(bandPos[1] - pos[1]));
            });

            function zoom() {
                //recalculate domains
                if (zoomArea.x1 > zoomArea.x2) {
                    x.domain([zoomArea.x2, zoomArea.x1]);
                } else {
                    x.domain([zoomArea.x1, zoomArea.x2]);
                }

                if (zoomArea.y1 > zoomArea.y2) {
                    y.domain([zoomArea.y2, zoomArea.y1]);
                } else {
                    y.domain([zoomArea.y1, zoomArea.y2]);
                }

                //update axis and redraw lines
                var t = svg.transition().duration(750);
                t.select(".x.axis").call(xAxis);
                t.select(".y.axis").call(yAxis);

                for (idx in data) {
                    t.select(".line" + idx).attr("d", line(data[idx]));
                    t.select(".line" + idx).style("stroke", colors[idx]);

                }

            }

            var zoomOut = function () {
                x.domain([0, xdomain]);
                y.domain([0, ydomain]);

                var t = svg.transition().duration(750);
                t.select(".x.axis").call(xAxis);
                t.select(".y.axis").call(yAxis);

                for (idx in data) {
                    t.select(".line" + idx).attr("d", line(data[idx]));
                    t.select(".line" + idx).style("stroke", colors[idx]);


                }
            }

        };

        /*Today sales order value chart*/
        $scope.drawTodaySalesChart = function () {
            $timeout(function () {
                drawTodaySalesChartTest();
            }, 5000)
        }
        var drawTodaySalesChartTest = function () {
            var todayTotalSalesOrder = $scope.salesOrders.Books ? $scope.salesOrders.Books.length : 0,
                todaySalesOrders = $scope.salesOrders.Books,
                salesOrderByStore = [],
                salesOrderByCOD = [],
                merchantTotalSalesValue = 0,
                orders = [];

            $scope.todaySalesMerchant = {
                merchantId: $rootScope.user.merchantId
            }
            orders = _.filter(todaySalesOrders, function (order) {
                return order.MERCHANT_ID == $scope.todaySalesMerchant.merchantId
            });
            _.each(orders, function (order) {
                merchantTotalSalesValue = merchantTotalSalesValue + order.NET_AMOUNT
            });

            $scope.todaySalesMerchant.salesOrders = orders;
            $scope.todaySalesMerchant.salesValue = merchantTotalSalesValue;

            function calculateSalesOrderByStore(merchant) {
                var orders = [];
                merchant.stores = [];
                orders = _.groupBy(merchant.salesOrders, function (order) {
                    return order.STORE_ID;
                });
                _.each(Object.keys(orders), function (storeId) {
                    var storeName = _.findWhere($scope.stores.Books, {
                            STORE_ID: storeId
                        }).NAME || '',
                        storeTotalSalesValue = 0;
                    _.each(orders[storeId], function (order) {
                        storeTotalSalesValue = storeTotalSalesValue + order.NET_AMOUNT;
                    });
                    merchant.stores.push({
                        storeId: storeId,
                        storeName: storeName,
                        salesOrders: orders[storeId],
                        salesPercent: (orders[storeId].length / merchant.salesOrders.length) * 100,
                        salesValue: storeTotalSalesValue
                    });
                });
                /*$scope.$apply(function () {
    $scope.todaySalesByMerchant = merchant
})*/
            };

            calculateSalesOrderByStore($scope.todaySalesMerchant);

            function processSelectedStore(store) {
                console.log('element over method is calling ', store);
                $scope.currentSalesStore = store;
                $scope.currentSalesStore.codOrders = _.filter(store.salesOrders, {
                    PAYMENT_METHOD: "COD"
                });

                var drawRadialProgressOfCODSales = function (id, label, value) {
                    console.log('selected dom div ', $(id)[0]);
                    radialProgress($(id)[0])
                        .maxValue($scope.currentSalesStore.salesOrders.length)
                        .diameter(150)
                        .label(label)
                        .value(value)
                        .render();
                }
                drawRadialProgressOfCODSales('#cod-sales', 'COD Sales', $scope.currentSalesStore.codOrders.length);
                drawRadialProgressOfCODSales('#cardPay-sales', 'Card Sales', ($scope.currentSalesStore.salesOrders.length - $scope.currentSalesStore.codOrders.length));
            }

            var widthOfBar = 40,
                height = 500,
                width = $scope.todaySalesMerchant.stores.legnth * widthOfBar; //dynamically calculate width of chart depends on number of stores in merchant
            nv.addGraph(function () {
                var chart = nv.models.discreteBarChart()
                    .x(function (d) {
                        return d.storeName
                    })
                    .y(function (d) {
                        return d.salesValue
                    })
                    .staggerLabels(true)
                    .showValues(true);

                console.log('created chart object ', chart);

                if ($scope.todaySalesMerchant.stores.legnth > 20) {
                    chart.width(width);
                    width = width + "px"
                } else {
                    width = "100%"
                }
                chart.height(height);
                chart.discretebar.dispatch.on('elementMouseover', function (store) {
                    processSelectedStore(store.data);
                });

                d3.select('#todaySalesRevenue svg')
                    .datum([{
                        key: "Today Salesvalue Report",
                        values: $scope.todaySalesMerchant.stores
                    }])
                    .transition().duration(500)
                    .call(chart)
                    .style({
                        height: height + "px",
                        width: width
                    })

                nv.utils.windowResize(chart.update);

                return chart;
            });


            $scope.drawStoreTodaySalesChart = function (store) {
                $timeout(function () {
                    radialProgress(document.getElementById(store.storeId))
                        .diameter(150)
                        .value(store.salesPercent)
                        .render();
                }, 1000)
            };

        }


        /*//Pie chart
		$scope.drawPiechart = function () {
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
		$scope.drawReviewChart = function () {
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
*/
        //Bar Chart
        $scope.drawBarChart = function () {
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
                    .valueFormat(d3.format('d'))
                    .duration(250);

                d3.select('#chart1 svg')
                    .datum($scope.historicalBarChart)
                    .call(chart);

                nv.utils.windowResize(chart.update);
                return chart;
            });
        }
        $scope.drawBarChart2 = function () {
            nv.addGraph(function () {
                var chart = nv.models.discreteBarChart()
                    .x(function (d) {
                        return d.label
                    })
                    .y(function (d) {
                        return Math.round(d.value)
                    })
                    .staggerLabels(true)
                    //.staggerLabels(historicalBarChart[0].values.length > 8)
                    .showValues(true)
                    .duration(250)
                    .valueFormat(d3.format('d'));

                d3.select('#barChart2 svg')
                    .datum($scope.historicalBarChart2)
                    .call(chart);

                nv.utils.windowResize(chart.update);
                return chart;
            });
        }

        function groupedBarChart() {
            console.log("$scope.qualirtStatsRecords", $scope.qualirtStatsRecords);

            function dashboard(id, fData) {
                var barColor = 'steelblue';

                function segColor(c) {
                    return {
                        Bad: "red",
                        Fair: "#e08214",
                        Good: "#317f43"
                    }[c];
                }

                // compute total for each state.
                fData.forEach(function (d) {
                    d.total = d.freq.Bad + d.freq.Fair + d.freq.Good;
                });

                // function to handle histogram.
                function histoGram(fD) {
                    var hG = {},
                        hGDim = {
                            t: 60,
                            r: 0,
                            b: 30,
                            l: 0
                        };
                    hGDim.w = 500 - hGDim.l - hGDim.r,
                        hGDim.h = 300 - hGDim.t - hGDim.b;

                    //create svg for histogram.
                    var hGsvg = d3.select(id).append("svg")
                        .attr("width", hGDim.w + hGDim.l + hGDim.r)
                        .attr("height", hGDim.h + hGDim.t + hGDim.b).append("g")
                        .attr("transform", "translate(" + hGDim.l + "," + hGDim.t + ")");

                    // create function for x-axis mapping.
                    var x = d3.scale.ordinal().rangeRoundBands([0, hGDim.w], 0.1)
                        .domain(fD.map(function (d) {
                            return d[0];
                        }));

                    // Add x-axis to the histogram svg.
                    hGsvg.append("g").attr("class", "x axis")
                        .attr("transform", "translate(0," + hGDim.h + ")")
                        .call(d3.svg.axis().scale(x).orient("bottom"));

                    // Create function for y-axis map.
                    var y = d3.scale.linear().range([hGDim.h, 0])
                        .domain([0, d3.max(fD, function (d) {
                            return d[1];
                        })]);

                    // Create bars for histogram to contain rectangles and freq labels.
                    var bars = hGsvg.selectAll(".bar").data(fD).enter()
                        .append("g").attr("class", "bar");

                    //create the rectangles.
                    bars.append("rect")
                        .attr("x", function (d) {
                            return x(d[0]);
                        })
                        .attr("y", function (d) {
                            return y(d[1]);
                        })
                        .attr("width", x.rangeBand())
                        .attr("height", function (d) {
                            return hGDim.h - y(d[1]);
                        })
                        .attr('fill', barColor)
                        .on("mouseover", mouseover) // mouseover is defined below.
                        .on("mouseout", mouseout); // mouseout is defined below.

                    //Create the frequency labels above the rectangles.
                    bars.append("text").text(function (d) {
                            return d3.format(",")(d[1])
                        })
                        .attr("x", function (d) {
                            return x(d[0]) + x.rangeBand() / 2;
                        })
                        .attr("y", function (d) {
                            return y(d[1]) - 5;
                        })
                        .attr("text-anchor", "middle");

                    function mouseover(d) { // utility function to be called on mouseover.
                        // filter for selected state.
                        var st = fData.filter(function (s) {
                                return s.State == d[0];
                            })[0],
                            nD = d3.keys(st.freq).map(function (s) {
                                return {
                                    type: s,
                                    freq: st.freq[s]
                                };
                            });

                        // call update functions of pie-chart and legend.    
                        pC.update(nD);
                        leg.update(nD);
                    }

                    function mouseout(d) { // utility function to be called on mouseout.
                        // reset the pie-chart and legend.    
                        pC.update(tF);
                        leg.update(tF);
                    }

                    // create function to update the bars. This will be used by pie-chart.
                    hG.update = function (nD, color) {
                        // update the domain of the y-axis map to reflect change in frequencies.
                        y.domain([0, d3.max(nD, function (d) {
                            return d[1];
                        })]);

                        // Attach the new data to the bars.
                        var bars = hGsvg.selectAll(".bar").data(nD);

                        // transition the height and color of rectangles.
                        bars.select("rect").transition().duration(500)
                            .attr("y", function (d) {
                                return y(d[1]);
                            })
                            .attr("height", function (d) {
                                return hGDim.h - y(d[1]);
                            })
                            .attr("fill", color);

                        // transition the frequency labels location and change value.
                        bars.select("text").transition().duration(500)
                            .text(function (d) {
                                return d3.format(",")(d[1])
                            })
                            .attr("y", function (d) {
                                return y(d[1]) - 5;
                            });
                    }
                    return hG;
                }

                // function to handle pieChart.
                function pieChart(pD) {
                    var pC = {},
                        pieDim = {
                            w: 200,
                            h: 200
                        };
                    pieDim.r = Math.min(pieDim.w, pieDim.h) / 2;

                    // create svg for pie chart.
                    var piesvg = d3.select(id).append("svg")
                        .attr("width", pieDim.w).attr("height", pieDim.h).append("g")
                        .attr("transform", "translate(" + pieDim.w / 2 + "," + pieDim.h / 2 + ")");

                    // create function to draw the arcs of the pie slices.
                    var arc = d3.svg.arc().outerRadius(pieDim.r - 10).innerRadius(0);

                    // create a function to compute the pie slice angles.
                    var pie = d3.layout.pie().sort(null).value(function (d) {
                        return d.freq;
                    });

                    // Draw the pie slices.
                    piesvg.selectAll("path").data(pie(pD)).enter().append("path").attr("d", arc)
                        .each(function (d) {
                            this._current = d;
                        })
                        .style("fill", function (d) {
                            return segColor(d.data.type);
                        })
                        .on("mouseover", mouseover).on("mouseout", mouseout);

                    // create function to update pie-chart. This will be used by histogram.
                    pC.update = function (nD) {
                            piesvg.selectAll("path").data(pie(nD)).transition().duration(500)
                                .attrTween("d", arcTween);
                        }
                        // Utility function to be called on mouseover a pie slice.
                    function mouseover(d) {
                        // call the update function of histogram with new data.
                        hG.update(fData.map(function (v) {
                            return [v.State, v.freq[d.data.type]];
                        }), segColor(d.data.type));
                    }
                    //Utility function to be called on mouseout a pie slice.
                    function mouseout(d) {
                        // call the update function of histogram with all data.
                        hG.update(fData.map(function (v) {
                            return [v.State, v.total];
                        }), barColor);
                    }
                    // Animating the pie-slice requiring a custom function which specifies
                    // how the intermediate paths should be drawn.
                    function arcTween(a) {
                        var i = d3.interpolate(this._current, a);
                        this._current = i(0);
                        return function (t) {
                            return arc(i(t));
                        };
                    }
                    return pC;
                }

                // function to handle legend.
                function legend(lD) {
                    var leg = {};

                    // create table for legend.
                    var legend = d3.select(id).append("table").attr('class', 'legend');

                    // create one row per segment.
                    var tr = legend.append("tbody").selectAll("tr").data(lD).enter().append("tr");

                    // create the first column for each segment.
                    tr.append("td").append("svg").attr("width", '16').attr("height", '16').append("rect")
                        .attr("width", '16').attr("height", '16')
                        .attr("fill", function (d) {
                            return segColor(d.type);
                        });

                    // create the second column for each segment.
                    tr.append("td").text(function (d) {
                        return d.type;
                    });

                    // create the third column for each segment.
                    tr.append("td").attr("class", 'legendFreq')
                        .text(function (d) {
                            return d3.format(",")(d.freq);
                        });

                    // create the fourth column for each segment.
                    tr.append("td").attr("class", 'legendPerc')
                        .text(function (d) {
                            return getLegend(d, lD);
                        });

                    // Utility function to be used to update the legend.
                    leg.update = function (nD) {
                        // update the data attached to the row elements.
                        var l = legend.select("tbody").selectAll("tr").data(nD);

                        // update the frequencies.
                        l.select(".legendFreq").text(function (d) {
                            return d3.format(",")(d.freq);
                        });

                        // update the percentage column.
                        l.select(".legendPerc").text(function (d) {
                            return getLegend(d, nD);
                        });
                    }

                    function getLegend(d, aD) { // Utility function to compute percentage.
                        return d3.format("%")(d.freq / d3.sum(aD.map(function (v) {
                            return v.freq;
                        })));
                    }

                    return leg;
                }

                // calculate total frequency by segment for all state.
                var tF = ['Bad', 'Fair', 'Good'].map(function (d) {
                    return {
                        type: d,
                        freq: d3.sum(fData.map(function (t) {
                            return t.freq[d];
                        }))
                    };
                });

                // calculate total frequency by state for all segment.
                var sF = fData.map(function (d) {
                    return [d.State, d.total];
                });

                var hG = histoGram(sF), // create the histogram.
                    pC = pieChart(tF), // create the pie-chart.
                    leg = legend(tF); // create the legend.
            }
            dashboard('#grouperBarChart', $scope.qualirtStatsRecords);
        };


        function getQualityStats(times) {
            $scope.qualirtStatsRecords = [];
            var i = 0,
                len;
            var tmpArray = [],
                red, green, yellow;
            var createdTime;
            var deliveredTime;
            console.info("Quality stats review ");
            for (;
                ($scope.stores.Books && i < $scope.stores.Books.length); i++) {
                tmpArray = {};
                red = green = yellow = 0;
                var j = 0,
                    solen = $scope.salesOrders.Books.length;
                for (; j < solen; j++) {
                    if ($scope.salesOrders.Books[j].STORE_ID == $scope.stores.Books[i].STORE_ID && $scope.salesOrders.Books[j].DELIVERY_DATE) {
                        createdTime = new Date($scope.salesOrders.Books[j].CREATED);
                        deliveredTime = new Date($scope.salesOrders.Books[j].DELIVERED_TIME);
                        if ((deliveredTime.getTime() - createdTime.getTime()) <= deliveryTimeSpan) {
                            green++;
                        } else if ((deliveredTime.getTime() - createdTime.getTime()) <= (deliveryTimeSpan + trafficTimeSpan)) {
                            yellow++;
                        } else {
                            red++;
                        }
                    }
                }
                tmpArray.State = $scope.stores.Books[i].NAME;
                tmpArray.freq = {};
                tmpArray.freq.Bad = red;
                tmpArray.freq.Fair = yellow;
                tmpArray.freq.Good = red;
                $scope.qualirtStatsRecords.push(tmpArray);
            }
            /*$scope.qualirtStatsRecords=[
            	{State:'AL',freq:{Bad:4786, Fair:1319, Good:249}}
            	,{State:'AZ',freq:{Bad:1101, Fair:412, Good:674}}
            	,{State:'CT',freq:{Bad:932, Fair:2149, Good:418}}
            	,{State:'DE',freq:{Bad:832, Fair:1152, Good:1862}}
            	
            	];*/
            console.log("$scope.qualirtStatsRecords", $scope.qualirtStatsRecords);
            groupedBarChart();

        };

        function sendHttpRequest(service) {
            var defer = $q.defer();
            $http({
                    method: 'GET',
                    url: commonNodeURL + service
                })
                .success(function (data, status) {
                    defer.resolve(data);
                })
                .error(function (data, status) {
                    defer.reject(data);
                    console.log("merchant error case ", data);
                });
            return defer.promise;
        }

        function getStoreById(id) {
            for (var i = 0; i < $scope.stores.Books.length; i++) {
                if ($scope.stores.Books[i].STORE_ID == id) {
                    return $scope.stores.Books[i].NAME;
                }
            }
            console.info("Failed to get Merchant Name By Id of : ", id);
        };

        function getCustomerNameById(id) {
            for (var i = 0; i < $scope.customers.Books.length; i++) {
                if ($scope.customers.Books[i].CUSTOMER_ID == id) {
                    return $scope.customers.Books[i].NAME;
                }
            }
        };

        $scope.compSalesToday = function (data) {
            $scope.yesterdayTotSale = $scope.todayTotSale = 0;
            $scope.totalSales = 0;
            var len = data.Books.length;
            var i = 0,
                tempDateObj;
            for (; i < len; i++) {
                tempDateObj = new Date((data.Books[i].DELIVERY_DATE).substring(0, 10));
                tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);
                $scope.totalSales = Math.round($scope.totalSales + data.Books[i].NET_AMOUNT);
                if (tempDateObj.getTime() == todayDateObj.getTime()) {
                    $scope.todayTotSale = $scope.todayTotSale + data.Books[i].NET_AMOUNT;
                } else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
                    $scope.yesterdayTotSale = $scope.yesterdayTotSale + data.Books[i].NET_AMOUNT;
                }
            }
            console.log("Yesterday sales ==>", $scope.yesterdayTotSale, ": Today Sales ==>", $scope.todayTotSale);
            $scope.salesGrowthToday = ($scope.yesterdayTotSale > 0) ? ((($scope.todayTotSale - $scope.yesterdayTotSale) / $scope.yesterdayTotSale) * 100) : 0;
            $scope.salesGrowthToday = ($scope.salesGrowthToday > 0) ? (Math.round($scope.salesGrowthToday * 100) / 100) : 0;

        };
        $scope.getAddressIdByUserId = function (userID) {
            var i = 0;
            l = $scope.users.Books.length;
            for (; i < l; i++) {
                if ($scope.users.Books[i].USER_ID == userID) {
                    return $scope.users.Books[i].ADDRESS_ID;
                }
            }
        };
        $scope.getLatLongByUserId = function (userId) {
            var addressId = $scope.getAddressIdByUserId(userId);
            var i = 0;
            l = $scope.addresses.Books.length;
            for (; i < l; i++) {
                if ($scope.addresses.Books[i].ADDRESS_ID == addressId) {
                    return $scope.addresses.Books[i];
                }
            }
        };


        function postSalesOrder(data) {

            var tempDateObj;
            var endDateObj = new Date(today.toISOString().substring(0, 10));
            endDateObj.setTime(endDateObj.getTime() - (reportType * 24 * 3600000));
            var i = 0;
            len = data.Books.length;
            var tempArray = [];
            var index = 0;
            var totalAmount = 0;
            for (; i < len; i++) {
                tempDateObj = new Date((data.Books[i].DELIVERY_DATE).substring(0, 10));
                tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);
                if (tempDateObj.getTime() >= endDateObj.getTime() && tempDateObj.getTime() < tomorrowDateObj.getTime()) {
                    tempArray[index] = data.Books[i];
                    index++;
                    totalAmount = totalAmount + data.Books[i].NET_AMOUNT;
                }
            }
            $scope.salesOrdersCount = index;
            $scope.salesOrders.Books = tempArray;
            var x = {};
            var storeIds = [];
            var storeName = "";
            for (var i = 0; i < tempArray.length; ++i) {
                var obj = tempArray[i];
                if (x[obj.STORE_ID] === undefined && obj.STORE_ID) {
                    storeName = getStoreById(obj.STORE_ID);
                    if (storeName) {
                        x[obj.STORE_ID] = [storeName];
                        storeIds.push(obj.STORE_ID);
                    }
                }
                if (obj.STORE_ID && x[obj.STORE_ID])
                    x[obj.STORE_ID].push(obj.NET_AMOUNT);
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
                    tmpAvgObj.key = x[storeIds[j]][0] + ' ₹';
                    tmpAvgObj.y = Math.round(totalAmount);
                    $scope.testdata.push(angular.copy(tmpAvgObj));

                }
            }
            $scope.historicalBarChart2 = [{
                key: "Cumulative Return1",
                values: []
            }];
            tmpAvgObj = {};
            var billMinestone = [0, 0, 0, 0, 0, 0];
            for (var i = 0; i < tempArray.length; i++) {
                tempArray[i].NET_AMOUNT
                if (tempArray[i].NET_AMOUNT > 10000) {
                    billMinestone[5]++;
                } else if (tempArray[i].NET_AMOUNT >= 5000) {
                    billMinestone[4]++;
                } else if (tempArray[i].NET_AMOUNT >= 1000) {
                    billMinestone[3]++;
                } else if (tempArray[i].NET_AMOUNT >= 500) {
                    billMinestone[2]++;
                } else if (tempArray[i].NET_AMOUNT >= 100) {
                    billMinestone[1]++;
                } else if (tempArray[i].NET_AMOUNT < 100) {
                    billMinestone[0]++;
                }
            }
            for (i = billMinestone.length - 1; i >= 0; i--) {

                if (totalAmount > 0) {
                    tmpAvgObj = {};
                    switch (i) {
                    case 0:
                        tmpAvgObj.label = 'Bill to 100';
                        break;
                    case 1:
                        tmpAvgObj.label = '100 to 500';
                        break;
                    case 2:
                        tmpAvgObj.label = '500 to 1000';
                        break;
                    case 3:
                        tmpAvgObj.label = '1000 to 5000';
                        break;
                    case 4:
                        tmpAvgObj.label = '5000 to 10000';
                        break;
                    case 5:
                        tmpAvgObj.label = '10000 to Bill';
                        break;
                    default:
                        console.log("Something went wrong in sales milestone count calculation");
                    }
                    tmpAvgObj.value = parseInt(billMinestone[i]);
                    $scope.historicalBarChart2[0].values.push(angular.copy(tmpAvgObj));

                }
            }


            var filteredSalesOrder = angular.copy($scope.salesOrders);
            filteredSalesOrder.Books = _.reject(filteredSalesOrder.Books, function (book) {
                return book.ISACTIVE != 'Y';
            });
            $scope.drawBarChart2();
            $scope.compSalesToday(filteredSalesOrder);
            getQualityStats();
            /*$scope.locateMerchants();*/
            /*callback();*/
        };

        //		function filteredMerchants(data) {
        //			$scope.totalMerchants = (data) ? data.Books.length : 0;
        //			var newMerchantsToday = 0,
        //				newMerchantsLastDat = 0;
        //			var tempDateObj;
        //			var len = data.Books.length;
        //			for (var i = 0; i < data.Books.length; i++) {
        //				tempDateObj = new Date((data.Books[i].CREATED).substring(0, 10));
        //				/*tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);*/
        //				if (tempDateObj.getTime() == todayDateObj.getTime()) {
        //					newMerchantsToday = newMerchantsToday + 1;
        //				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
        //					newMerchantsLastDat = newMerchantsLastDat + 1;
        //				}
        //			}
        //			$scope.merchantGrowthToday = (newMerchantsLastDat) ? (((newMerchantsToday - newMerchantsLastDat) / newMerchantsLastDat) * 100) : 0;
        //			$scope.merchantGrowthToday = (Math.round($scope.merchantGrowthToday*100))/100;
        //			
        //		};

        function calcGrowthRatio(data) {
            var tempDateObj = angular.copy(todayDateObj);
            var tmpLen = data.Books.length;
            $scope.d3LineLeafs = [];
            var d3TmpArray = [];

            //for merchants 
            var k = growthXcount;
            d3TmpArray = [k, 0];
            for (var i = 0; i < (growthRatioChartSpan + 7); i++) {
                tempDateObj.setTime(tempDateObj.getTime() - millisecondsPerday);

                for (var j = 0; j < tmpLen; j++) {
                    createdDateObj = new Date((data.Books[j].CREATED).substring(0, 10));
                    if (createdDateObj.getTime() == tempDateObj.getTime()) {
                        d3TmpArray[1]++;
                    }
                }
                if (tempDateObj.getDay() == 0) {
                    if (d3TmpArray[1] > growthYcount) {
                        growthYcount = d3TmpArray[1];
                    }
                    k--;
                    $scope.d3LineLeafs.push(angular.copy(d3TmpArray));
                    d3TmpArray = [k, 0];
                }
            }
            if (d3TmpArray[1] > 0) {
                k--;
                $scope.d3LineLeafs.push(angular.copy(d3TmpArray));
                d3TmpArray = [k, 0];
            }

            $scope.d3LineLeafs.reverse();
            $scope.d3LineData.push(angular.copy($scope.d3LineLeafs));

        }

        function orgGrowthRation() {
            calcGrowthRatio($scope.stores);
            calcGrowthRatio($scope.customers);
            calcGrowthRatio($scope.salesOrders);
            $scope.drawZoomedlinechart();
            console.info("d3LineData=", $scope.d3LineData);
        }

        function filteredCustomers(data) {
            /*$scope.totalCustomers = (data) ? data.Books.length : 0;*/
            var newCustomerToday = 0,
                newCustomerLastDat = 0;
            var tempDateObj;
            var len = data.Books.length;
            for (var i = 0; i < data.Books.length; i++) {
                tempDateObj = new Date((data.Books[i].CREATED).substring(0, 10));
                /*tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);*/
                if (tempDateObj.getTime() == todayDateObj.getTime()) {
                    newCustomerToday = newCustomerToday + 1;
                } else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
                    newCustomerLastDat = newCustomerLastDat + 1;
                }
            }
            $scope.customerGrowthToday = (newCustomerLastDat) ? (((newCustomerToday - newCustomerLastDat) / newCustomerLastDat) * 100) : 0;
            $scope.customerGrowthToday = Math.round($scope.customerGrowthToday * 100) / 100;
            /*callback();*/
        };

        function filteredStores(data) {
            $scope.totalStores = (data) ? data.Books.length : 0;
            var newStoresToday = 0,
                newStoresLastDay = 0;
            var tempDateObj;
            var tmpAvgObj = {};
            var len = data.Books.length;
            for (var i = 0; i < len; i++) {
                tempDateObj = new Date((data.Books[i].CREATED).substring(0, 10));
                /*tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);*/
                if (tempDateObj.getTime() == todayDateObj.getTime()) {
                    newStoresToday = newStoresToday + 1;
                } else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
                    newStoresLastDay = newStoresLastDay + 1;
                }
            }
            $scope.storeGrowthToday = (newStoresLastDay) ? (((newStoresToday - newStoresLastDay) / newStoresLastDay) * 100) : 0;
            $scope.storeGrowthToday = Math.round($scope.storeGrowthToday * 100) / 100;

        }

        function postStore(data) {

            console.log("$scope.historicalBarChart2--->", $scope.historicalBarChart2);
            len1 = $scope.stores.Books.length;
            len = $scope.salesOrders.Books.length;
            $scope.historicalBarChart = [{
                key: "Cumulative Return",
                values: []
            }];
            var tempStore;
            for (var i = 0; i < len1; i++) {
                tempStore = $scope.stores.Books[i];
                count = 0;
                if (tempStore.MERCHANT_ID == $rootScope.user.merchantId) {
                    for (var j = 0; j < len; j++) {
                        if (tempStore.STORE_ID == $scope.salesOrders.Books[j].STORE_ID) {
                            count = count + 1;
                        }
                    }
                    tmpAvgObj = {};
                    tmpAvgObj.label = tempStore.NAME;
                    tmpAvgObj.value = count;
                    $scope.historicalBarChart[0].values.push(angular.copy(tmpAvgObj));
                }
            }

            function compare(a, b) {
                if (a.value < b.value)
                    return 1;
                if (a.value > b.value)
                    return -1;
                return 0;
            }

            $scope.historicalBarChart[0].values.sort(compare);
            $scope.drawBarChart();
            orgGrowthRation();
        };


        $scope.proceedSalesOrder = function (data) {

            var anayed = [];
            $scope.testdata = [{
                key: "O",
                y: 0
            }];


            data.Books = _.reject(data.Books, function (book) {
                return (book.ISACTIVE != 'Y' || book.STATUS != 'Delivered');
            });
            console.info("salesOrder Received ", data);
            postSalesOrder(data);

        };
        $scope.proceedSalesOrderLine = function (data) {


        };

        $scope.proceedStore = function (data) {
            var activeStores = angular.copy(data);
            activeStores.Books = _.reject(activeStores.Books, function (book) {
                return book.ISACTIVE != 'Y';
            });
            console.info("Store Received ", data);
            filteredStores(activeStores);
            postStore(data);

        };

        $scope.proceedAddresses = function (callback) {

        };
        $scope.proceedUsers = function (callback) {

        };

        $scope.proceedCustomer = function (data) { // Need to filter customers by utilized by stores of merchat
            var activeCustomers = angular.copy(data);
            activeCustomers.Books = _.reject(activeCustomers.Books, function (book) {
                return book.ISACTIVE != 'Y';
            });
            filteredCustomers(activeCustomers);
        };

        var callAllwebservices = function () {
            $scope.receivedResponceCount = 0;
            sendHttpRequest('customer').then(function (data) {
                console.info("customer responce : ", data);
                $scope.customers = data;
                $scope.receivedResponceCount++;
            });
            sendHttpRequest('users').then(function (data) {
                console.info("users responce : ", data);
                $scope.users = data;
                $scope.receivedResponceCount++;
            });

            sendHttpRequest('address').then(function (data) {
                console.info("address responce : ", data);
                $scope.addresses = data;
                $scope.receivedResponceCount++;
            });
            sendHttpRequest('store').then(function (data) {
                data.Books = _.reject(data.Books, function (book) {
                    return (book.MERCHANT_ID != $rootScope.user.merchantId);
                });
                console.info("store responce : ", data);
                $scope.stores = data;
                $scope.receivedResponceCount++;
            });

            sendHttpRequest('salesOrderLine').then(function (data) {
                data.Books = _.reject(data.Books, function (book) {
                    return (book.MERCHANT_ID != $rootScope.user.merchantId);
                });
                console.info("salesOrderLine responce : ", data);
                $scope.salesOrderLines = data;
                $scope.receivedResponceCount++;
            });

            sendHttpRequest('salesOrder').then(function (data) {
                console.info("salesOrder responce : ", data);
                data.Books = _.reject(data.Books, function (book) {
                    return (book.STATUS != 'Delivered' || book.MERCHANT_ID != $rootScope.user.merchantId);
                });
                $scope.salesOrdersCount = data.Books.length;
                $scope.salesOrders = data;
                $scope.receivedResponceCount++;
            });

        }

        $scope.loadRelatedCustomersAlone = function () {
            data = $scope.salesOrders;
            var storeCustomer = [];
            var idFound = false;
            for (var i = 0; i < $scope.salesOrders.Books.length; ++i) {
                idFound = false;
                var obj = $scope.salesOrders.Books[i];
                for (var j = 0; j < storeCustomer.length; j++) {
                    if (storeCustomer[j] == obj.CUSTOMER_ID) {
                        idFound = true;
                    }
                }
                if (!idFound) {
                    storeCustomer.push($scope.salesOrders.Books[i].CUSTOMER_ID);
                }
            }
            $scope.totalCustomers = storeCustomer.length;
            $scope.customers.Books = _.reject($scope.customers.Books, function (book) {
                idFound = false;
                for (var i = 0; i < storeCustomer.length; i++) {
                    if (book.CUSTOMER_ID == storeCustomer[i]) {
                        idFound = true;
                    }
                }
                return !idFound;
            });
        }

        $scope.$watch('receivedResponceCount', function (newValue, oldValue) {
            if (newValue >= 6) {
                $scope.loadRelatedCustomersAlone();
                $scope.proceedCustomer($scope.customers);
                $scope.proceedStore($scope.stores);
                $scope.proceedSalesOrder($scope.salesOrders);
                console.log("Got all responces from node server");

            }
        });

        function initiateAllMethods() {
            document.getElementById('grouperBarChart').innerHTML = "";
            document.getElementById('graph').innerHTML = "";
            //call all methods by preority
            callAllwebservices();

        };

        initiateAllMethods();


 }]);