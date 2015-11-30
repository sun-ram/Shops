aviateAdmin.controller("merchantDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT', '$rootScope', 'CommonServices', 'StoreServices',
    function($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, $rootScope, CommonServices, StoreServices) {

   
    var testdata = [
        {key: "One", y: 5},
        {key: "Two", y: 2},
        {key: "Three", y: 9},
        {key: "Four", y: 7},
        {key: "Five", y: 4},
        {key: "Six", y: 3},
        {key: "Seven", y: 0.5}
    ];

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
                .datum([testdata])
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
                .datum([testdata])
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
                chart.update();
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

   historicalBarChart = [
        {
            key: "Cumulative Return",
            values: [
                {
                    "label" : "A" ,
                    "value" : 29.765957771107
                } ,
                {
                    "label" : "B" ,
                    "value" : 0
                } ,
                {
                    "label" : "C" ,
                    "value" : 32.807804682612
                } ,
                {
                    "label" : "D" ,
                    "value" : 196.45946739256
                } ,
                {
                    "label" : "E" ,
                    "value" : 0.19434030906893
                } ,
                {
                    "label" : "F" ,
                    "value" : 98.079782601442
                } ,
                {
                    "label" : "G" ,
                    "value" : 13.925743130903
                } ,
                {
                    "label" : "H" ,
                    "value" : 5.1387322875705
                }
            ]
        }
    ];

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
            .datum(historicalBarChart)
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

    nv.addGraph(function() {
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
    });

    function sinAndCos() {
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
    }
 
        
   
 }]);