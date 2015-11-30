aviateAdmin.controller("superDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT', '$rootScope', 'CommonServices', 'StoreServices', 'api', '$http',
    function($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, $rootScope, CommonServices, StoreServices, api, $http) {
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
                values: [
                    {
                        "label" : "A" ,
                        "value" : 29.765957771107
                    } 
                ]
            }
        ];
    
       /* $scope.map = { center: { latitude: 12.916292, longitude: 80.152379}, zoom: 15 };

        $scope.marker = {
            id: 0,
            coords: {
                latitude: 12.916292,
                longitude:  80.152379
            },
            options: { draggable: false },
            events: {
                dragend: function (marker, eventName, args) {
                    $log.log('marker dragend');
                    var lat = marker.getPosition().lat();
                    var lon = marker.getPosition().lng();
                    $log.log(lat);
                    $log.log(lon);

                    $scope.marker.options = {
                        draggable: true,
                        labelContent: "lat: " + $scope.marker.coords.latitude + ' ' + 'lon: ' + $scope.marker.coords.longitude,
                        labelAnchor: "100 0",
                        labelClass: "marker-labels"
                    };
                }
            }
        };*/
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

    $scope.populateCharts = function (){
        var width = 300;
        var height = 300;
        
        nv.addGraph(function() {
            var chart = nv.models.pie()
                    .x(function(d) { return d.key; })
                    .y(function(d) { return d.y; })
                    .width(width)
                    .height(height)
                    .labelType(function(d, i, values) {
                        return values.key + ':' + values.value;
                    })
                    ;

            d3.select("#test0")
                    .datum([$scope.testdata])
                    .transition().duration(1200)
                    .attr('width', width)
                    .attr('height', height)
                    .call(chart);

            return chart;
        });

        nv.addGraph(function() {
            var chart = nv.models.pie()
                    .x(function(d) { return d.key; })
                    .y(function(d) { return d.y; })
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


              var testdata1 = [
            { key: "Updated", y: 0 },
            { key: "Pending", y: 100 }
        ];

            var arcRadius1 = [
            { inner: 0.6, outer: 1 },
            { inner: 0.65, outer: 0.95 }
        ];

            var colors = ["green", "gray"];

            var testdata2 = [
            { key: "One", y: 1 },
            { key: "Two", y: 1 },
            { key: "Three", y: 1 },
            { key: "Four", y: 1 },
            { key: "Five", y: 1 },
            { key: "Six", y: 1 },
            { key: "Seven", y: 1 }
        ];

            var arcRadius2 = [
            { inner: 0.9, outer: 1 },
            { inner: 0.8, outer: 1 },
            { inner: 0.7, outer: 1 },
            { inner: 0.6, outer: 1 },
            { inner: 0.5, outer: 1 },
            { inner: 0.4, outer: 1 },
            { inner: 0.3, outer: 1 }
        ];

            var testdata3 = [
            { key: "Updated", y: 80 },
            { key: "Pending", y: 20 }
        ];

            var arcRadius3 = [
            { inner: 0, outer: 1 },
            { inner: 0, outer: 0.8 }
        ];

            var height = 350;
            var width = 350;

            nv.addGraph(function () {
                var chart = nv.models.pieChart()
                .x(function (d) { return d.key })
                .y(function (d) { return d.y })
                .donut(true)
                .showLabels(false)
                .color(colors)
                .width(width)
                .height(height)
                .growOnHover(false)
                .arcsRadius(arcRadius1)
                .id('donut1'); // allow custom CSS for this one svg

                chart.title("0%");

                d3.select("#test1")
                .datum(testdata1)
                .transition().duration(1200)
                .attr('width', width)
                .attr('height', height)
                .call(chart);
                
                d3.select("#test11")
                .datum(testdata1)
                .transition().duration(1200)
                .attr('width', width)
                .attr('height', height)
                .call(chart);

                d3.select("#test12")
                .datum(testdata1)
                .transition().duration(1200)
                .attr('width', width)
                .attr('height', height)
                .call(chart);

                // update chart data values randomly
                setInterval(function () {
                    if (testdata1[0].y < 100) {
                        testdata1[0].y = testdata1[0].y + 1;
                        testdata1[1].y = testdata1[1].y - 1;
                    }
                    else {
                        testdata1[0].y = 0;
                        testdata1[1].y = 100;
                    }
                    chart.title(testdata1[0].y + "%");
                    if(chart.update){ chart.update();  }
                }, 200);

                return chart;

            });

            nv.addGraph(function () {
                var chart = nv.models.pieChart()
                .x(function (d) { return d.key })
                .y(function (d) { return d.y })
                .donut(true)
                .width(width)
                .height(height)
                .arcsRadius(arcRadius2)
                .donutLabelsOutside(true)
                .labelSunbeamLayout(true)
                .id('donut2'); // allow custom CSS for this one svg

                d3.select("#test3")
                .datum(testdata2)
                .transition().duration(1200)
                .attr('width', width)
                .attr('height', height)
                .call(chart);

                return chart;

            });

            nv.addGraph(function () {
                var chart = nv.models.pieChart()
                .x(function (d) { return d.key })
                .y(function (d) { return d.y })
                .donut(true)
                .showLabels(true)
                .width(width)
                .height(height)
                .arcsRadius(arcRadius3)
                .donutLabelsOutside(true)
                .id('donut3'); // allow custom CSS for this one svg

                d3.select("#test4")
                .datum(testdata3)
                .transition().duration(1200)
                .attr('width', width)
                .attr('height', height)
                .call(chart);

                return chart;

            });

             var test_data = stream_layers(3,10+Math.random()*100,.1).map(function(data, i) {
            return {
                key: 'Stream' + i,
                values: data
            };
        });

        console.log('td',test_data);

        var negative_test_data = new d3.range(0,3).map(function(d,i) {
            return {
                key: 'Stream' + i,
                values: new d3.range(0,11).map( function(f,j) {
                    return {
                        y: 10 + Math.random()*100 * (Math.floor(Math.random()*100)%2 ? 1 : -1),
                        x: j
                    }
                })
            };
        });

       

        nv.addGraph(function() {
            var chart = nv.models.discreteBarChart()
                .x(function(d) { return d.label })
                .y(function(d) { return d.value })
                .staggerLabels(true)
                //.staggerLabels(historicalBarChart[0].values.length > 8)
                .showValues(true)
                .duration(250)
                ;

            d3.select('#chart1 svg')
                .datum($scope.historicalBarChart)
                .call(chart);

            nv.utils.windowResize(chart.update);
            return chart;
        });

          var chart2;
        var data2;

        var randomizeFillOpacity = function() {
            var rand = Math.random(0,1);
            for (var i = 0; i < 100; i++) { // modify sine amplitude
                data[4].values[i].y = Math.sin(i/(5 + rand)) * .4 * rand - .25;
            }
            data[4].fillOpacity = rand;
            chart.update();
        };

        /*nv.addGraph(function() {
            chart2 = nv.models.lineChart()
                .options({
                    transitionDuration: 300,
                    useInteractiveGuideline: true
                })
            ;

            // chart sub-models (ie. xAxis, yAxis, etc) when accessed directly, return themselves, not the parent chart, so need to chain separately
            chart2.xAxis
                .axisLabel("Time (s)")
                .tickFormat(d3.format(',.1f'))
                .staggerLabels(true)
            ;

            chart2.yAxis
                .axisLabel('Voltage (v)')
                .tickFormat(function(d) {
                    if (d == null) {
                        return 'N/A';
                    }
                    return d3.format(',.2f')(d);
                })
            ;

            data2 = sinAndCos();

            d3.select('#chart2').append('svg')
                .datum(data2)
                .call(chart2);

            nv.utils.windowResize(chart.update);

            return chart2;
        });*/

       /* function sinAndCos() {
            var sin = [],
                sin2 = [],
                cos = [],
                rand = [],
                rand2 = []
                ;

            for (var i = 0; i < 100; i++) {
                sin.push({x: i, y: i % 10 == 5 ? null : Math.sin(i/10) }); //the nulls are to show how defined works
                sin2.push({x: i, y: Math.sin(i/5) * 0.4 - 0.25});
                cos.push({x: i, y: .5 * Math.cos(i/10)});
                rand.push({x:i, y: Math.random() / 10});
                rand2.push({x: i, y: Math.cos(i/10) + Math.random() / 10 })
            }

            return [
                {
                    area: true,
                    values: sin,
                    key: "Sine Wave",
                    color: "#ff7f0e",
                    strokeWidth: 4,
                    classed: 'dashed'
                },
                {
                    values: cos,
                    key: "Cosine Wave",
                    color: "#2ca02c"
                },
                {
                    values: rand,
                    key: "Random Points",
                    color: "#2222ff"
                },
                {
                    values: rand2,
                    key: "Random Cosine",
                    color: "#667711",
                    strokeWidth: 3.5
                },
                {
                    area: true,
                    values: sin2,
                    key: "Fill opacity",
                    color: "#EF9CFB",
                    fillOpacity: .1
                }
            ];
        }*/
    }
    
    $scope.getInputData = function (){
            var reportType = 15;
            var anayed = [];
            $scope.testdata =[{key: "O", y:0}];
            
            $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/salesOrder'
                })
                .success(function (data, status) {
                    console.log("salesOrder Exected success case ", data);
                                    
                    var tempDateObj2 ;
                    var endDateObj = new Date("2015-11-30");
                    endDateObj.setTime(endDateObj.getTime()-(reportType*24*3600000));
                    var i= 0;len=data.Books.length;
                    var tempArray = [];
                    var index = 0;
                    var totalAmount = 0;
                    for(;i<len;i++){
                        tempDateObj = new Date((data.Books[i].delivery_date).substring(0, 10));
                        if(tempDateObj.getTime() > endDateObj.getTime()){
                            tempArray [index] = data.Books[i];
                            index++;
                            totalAmount = totalAmount + data.Books[i].order_amount;
                        }
                    }
                    console.info("Array Filtered= ",tempArray);
                    len = tempArray.length;
                    var tmpAvgObj = {},tmpAvgObj1 = [{}];
                    for(var j=0;j<reportType ; j++){
                        tempDateObj.setTime(endDateObj.getTime()+((j+1)*24*3600000));
                        totalAmount = 0;
                        for(var i=0;i<len;i++){
                            tempDateObj2=new Date((tempArray[i].delivery_date).substring(0, 10));
                            if(tempDateObj.getTime() == tempDateObj2.getTime()){
                                totalAmount = totalAmount + tempArray[i].order_amount;
                            }
                        }
                        if(totalAmount>0){
                            tmpAvgObj.key = month[tempDateObj.getMonth()] + (tempDateObj.getDate()+1).toString() + ' ₹';
                            tmpAvgObj.y = totalAmount;
                            $scope.testdata.push(angular.copy(tmpAvgObj)); 
                            tmpAvgObj = {};
                            tmpAvgObj.label = month[tempDateObj.getMonth()] + (tempDateObj.getDate()+1).toString();
                            tmpAvgObj.value = totalAmount;
                            $scope.historicalBarChart[0].values.push(angular.copy(tmpAvgObj));
                            
                        }
                    }
                   $scope.populateCharts();
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });
        
                //salesOrderLine 
                $http({
                    method: 'GET',
                    url: 'http://localhost:3000/shopsbacker/salesOrderLine'
                })
                .success(function (data, status) {
                    console.log("Sales order Line =>",data);
                })
                .error(function (data, status) {
                    console.log("salesOrder Exected error case ", data);
                });
        
             /*$http({
                    method: 'GET',
                    url: 'http://192.168.1.42:3000/aviate/json///salesOrderLine '
                })
                .success(function (data, status) {
                    console.log("salesOrderLine Exected success case ", data);
                })
                .error(function (data, status) {
                    console.log("salesOrderLine Exected error case ", data);
                });
        
             $http({
                    method: 'GET',
                    url: 'http://192.168.1.42:3000/aviate/json/merchant'
                })
                .success(function (data, status) {
                    console.log("merchant Exected success case ", data);
                })
                .error(function (data, status) {
                    console.log("merchant Exected error case ", data);
                });
        
            $http({
                    method: 'GET',
                    url: 'http://192.168.1.42:3000/aviate/json/store'
                })
                .success(function (data, status) {
                    console.log("Store Exected success case ", data);
                })
                .error(function (data, status) {
                    console.log("Store Exected error case ", data);
                });
		
 
    console.log("testdata = ",testdata);*/
    };
    $scope.getInputData();

   
 }]);