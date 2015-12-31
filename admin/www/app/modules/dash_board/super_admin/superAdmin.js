aviateAdmin.controller("superDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT','myConfig', '$rootScope', 'CommonServices', 'StoreServices', 'api', '$http','$q',
    function ($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, myConfig, $rootScope, CommonServices, StoreServices, api, $http,$q) {
		var commonNodeURL = myConfig.node_server_url;
		var reportType = CONSTANT.DASHBOARD.DEFAULT_REPORT_TYPE;
		var commition = CONSTANT.DASHBOARD.COMMITION_PERCENTAGE;
		var deliveryTimeSpan = CONSTANT.DASHBOARD.DELIVERY_TIME_SPAN;
		var trafficTimeSpan = CONSTANT.DASHBOARD.ADJUSTABLE_TRAFIC_TIME_SPAN;
		var growthRatioChartSpan = 182;
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
		$scope.salesOrders = {Books:[]};
		$scope.merchants = [];
		$scope.stores;
		$scope.salesOrderLines;
		$scope.yesterdayTotSale = 0;
		$scope.todayTotSale = 0;
		$scope.totalMerchants = 0;
		$scope.totalStores = 0;
		$scope.totalSales =0;
		$scope.totalCustomers = 0;
		$scope.salesGrowthToday = 0;
		$scope.qualirtStatsRecords = [];
		$scope.merchantsRaised = 0;
		$scope.storesRaised = 0;
		$scope.customersRaised = 0;
		$scope.d3LineData = [];
		var growthYcount = 10;
		var growthXcount = 26;
		$scope.raisedTableTitle = "Complete Details of Growth";
		

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
            value:1
		 },{
            name: "Weekly",
            value:7
        }, {
            name: "Half Month",
            value:15
        }, {
            name: "Monthly",
            value:30
        }];
		$scope.onReportChanged = function(reportValue){
			reportType = reportValue;
            console.log("onReportChanged",reportValue);
			initiateAllMethods();
        }
		$scope.map = {
			center: {
				latitude: 12.916292,
				longitude: 80.152379
			},
			zoom: 15,
			bounds: {}
		};
		$scope.options = {
			scrollwheel: true
		};


		$scope.onMarkerClicked = function (marker, eventName, model) {
			console.log("Clicked!", marker);
			model.show = !model.show;
		};
		
		//zoomedlinechart
		$scope.drawZoomedlinechart = function (){
				var data = [];
				var bandPos = [-1, -1];
				var pos;
				var xdomain = growthXcount;
				var ydomain = growthYcount;
				var colors = ["steelblue", "green", "red"];

				var margin = {
					top: 40,
					right: 40,
					bottom: 50,
					left: 60
				}
				var width = 1000 - margin.left - margin.right;
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
				  .html(function(d) {
					return "<strong>growth:</strong> <span style='color:red'>" +data[idx]+ "</span>";
				  });
			
				svg.call(tip);

				var band = svg.append("rect")
					.attr("width", 0)
					.attr("height", 0)
					.attr("x", 0)
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

				for(idx in data) {   
					svg.append("path")
						.attr("class", "line line" + idx)
						.attr("clip-path", "url(#clip)")
						.attr("d", line(data[idx]))
						.attr("style","z-index:999")
					    .on('mouseover', tip.show)
						.on('mouseleave', tip.hide);
				}

				var zoomOverlay = svg.append("rect")
					.attr("width", width - 10)
					.attr("height", height)
					.attr("class", "zoomOverlay")
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
			
				$("rect").mousemove(function(e){
					if(!dragstarted && !suspendMoveOperation){
						bandPos = [-1,-1];
						divPos = {
								left: e.pageX - offset.left,
								top: e.pageY - offset.top
							};
						divPos.left = divPos.left - (divPos.left % 35);
						/*divPos.left = divPos.left - 35;*/
						if(divPos.left < 0) divPos.left = 0;
						
						for(var i=0;i<35;i++){

							divPos.left++;
							var pos = [divPos.left,0];

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
							console.log("Rect Position ==",divPos);
						}
						var x1 = x.invert(bandPos[0]);
						var x2 = x.invert(pos[0]);
						x2=Math.round(x2);
						if(x2 < x1) {
							var tmp = x2;
							x2 = x1;
							x1 = tmp;
						}
						else {x1=Math.round(x1);}

						if (x1 < x2) {
							zoomArea.x1 = x1;
							zoomArea.x2 = x2;
						} else {
							zoomArea.x1 = x2;
							zoomArea.x2 = x1;
						}

						//calling custom method to update values in html table
						updategrowthTable(x1,x2);
					}
				});
				$('rect').on("mousedown", function(event) {
 					suspendMoveOperation = false;
					 d3.select(".band").transition()
						.attr("width", 0)
						.attr("height", 0)
						.attr("x", -1)
						.attr("y", -1) ;
				});
			
				drag.on("dragend", function () {
					dragstarted = false;
					
					var pos = d3.mouse(this);
					console.log("clicked!!!!!!!!!!!!!!2 bandPos",bandPos, "& pos= ",pos);
					var x1 = x.invert(bandPos[0]);
					var x2 = x.invert(pos[0]);
					x2=Math.round(x2);
					if(x2 < x1) {
						var tmp = x2;
						x2 = x1;
						x1 = tmp;
					}
					if(x1<=0){
//						x1 = x2-1;/* clickToDrag(pos);*/
//					    d3.select(".band").transition()
//						.attr("width", 0)
//						.attr("height", 0)
//						.attr("x", bandPos[0])
//						.attr("y", bandPos[1]) ;
					}
					else {x1=Math.round(x1);}
					
					if (x1 < x2) {
						zoomArea.x1 = x1;
						zoomArea.x2 = x2;
					} else {
						zoomArea.x1 = x2;
						zoomArea.x2 = x1;
					}
					
					//calling custom method to update values in html table
					updategrowthTable(x1,x2);
						
								
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
			
				function updategrowthTable (start, end) {
					$scope.raisedTableTitle = "Growth between "+Math.round(start)+" to "+ Math.round(end) + " Weeks";
					
					$scope.merchantsRaised = 0;
					$scope.storesRaised = 0;
					$scope.customersRaised = 0;
					for(var i=start ; i<end ; i++){
						try{
							$scope.merchantsRaised = $scope.merchantsRaised + $scope.d3LineData[0][i][1];
							$scope.storesRaised = $scope.storesRaised + $scope.d3LineData[1][i][1];
							$scope.customersRaised = $scope.customersRaised + $scope.d3LineData[2][i][1];
						}catch(e){console.error("Exception ");}
					}
					$scope.$apply();
				}
				document.onmouseup = function(e){
    				cursorX = e.pageX;
    				cursorY = e.pageY;
				}
				
	
				
				function clickToDrag (mouseProp) {
					
					var pos = [cursorX,cursorY];
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
					console.log(bandPos);
				});

				function zoom() {
					//recalculate domains
					if(zoomArea.x1 > zoomArea.x2) {
					  x.domain([zoomArea.x2, zoomArea.x1]);
					} else {
					  x.domain([zoomArea.x1, zoomArea.x2]);
					}

					if(zoomArea.y1 > zoomArea.y2) {
					  y.domain([zoomArea.y2, zoomArea.y1]);
					} else {
					  y.domain([zoomArea.y1, zoomArea.y2]);
					}

					//update axis and redraw lines
					var t = svg.transition().duration(750);
					t.select(".x.axis").call(xAxis);
					t.select(".y.axis").call(yAxis);

					for(idx in data) {
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

					for(idx in data) {
						t.select(".line" + idx).attr("d", line(data[idx]));
						t.select(".line" + idx).style("stroke", colors[idx]);
						
      					
					}
				}

		};
		
		//Pie chart
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
						return d.value
					})
					.staggerLabels(true)
					//.staggerLabels(historicalBarChart[0].values.length > 8)
					.showValues(true)
					.duration(250);

				d3.select('#barChart2 svg')
					.datum($scope.historicalBarChart2)
					.call(chart);

				nv.utils.windowResize(chart.update);
				return chart;
			});
		}
		
		function groupedBarChart (){
			var data = [];
			var i=0;len=$scope.qualirtStatsRecords.length;
			var tmpGroupObj = {};
			for(;i<len;i++){
				tmpGroupObj = {};
				tmpGroupObj.date = $scope.merchants.Books[i].NAME;
				tmpGroupObj.Good = $scope.qualirtStatsRecords[i][0];
				tmpGroupObj.Normal = $scope.qualirtStatsRecords[i][1];
				tmpGroupObj.Bad = $scope.qualirtStatsRecords[i][2];
				if(tmpGroupObj.Good >0 || tmpGroupObj.Normal > 0 || tmpGroupObj.Bad > 0){
					data.push(angular.copy(tmpGroupObj));
				}
			}
			// Set our margins
			var margin = {
				top: 20,
				right: 20,
				bottom: 30,
				left: 60
			},
			width = 700 - margin.left - margin.right,
			height = 350 - margin.top - margin.bottom;

			var x0 = d3.scale.ordinal()
				.rangeRoundBands([0, width], .1);

			var x1 = d3.scale.ordinal();

			var y = d3.scale.linear()
				.range([height, 0]);

			var color = d3.scale.ordinal()
				.range(["#4eaf4c", "#ffff00", "#ff0000"]);

			var xAxis = d3.svg.axis()
				.scale(x0)
				.orient("bottom");

			var yAxis = d3.svg.axis()
				.scale(y)
				.orient("left")
				.tickFormat(d3.format(".2s"));

			
			// Add our chart to the #chart div
			var svg = d3.select("#grouperBarChart").append("svg")
				.attr("width", width + margin.left + margin.right)
				.attr("height", height + margin.top + margin.bottom)
				.append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

		
			//array of keys except date
			var keys = d3.keys(data[0]).filter(function(key) { return key !== "date"; });

			data.forEach(function(d) {
				d.key = keys.map(function(name) { 
				  console.log("printing the name: "+name+ " "+d[name])
				  return {name: name, value: +d[name]}; });
			});

			x0.domain(data.map(function(d) { return d.date; }));
			x1.domain(keys).rangeRoundBands([0, x0.rangeBand()]);
			y.domain([0, d3.max(data, function(d) { return d3.max(d.key, function(d) { return d.value; }); })]);

			  svg.append("g")
				  .attr("class", "x axis")
				  .attr("transform", "translate(0," + height + ")")
				  .call(xAxis);

			  svg.append("g")
				  .attr("class", "y axis")
				  .call(yAxis)
				.append("text")
				  .attr("transform", "rotate(-90)")
				  .attr("y", 6)
				  .attr("dy", ".71em")
				  .style("text-anchor", "end")
				  .text("count");

			  var date = svg.selectAll(".date")
				  .data(data)
				  .enter().append("g")
				  .attr("class", "g")
				  .attr("transform", function(d) { return "translate(" + x0(d.date) + ",0)"; });

			  date.selectAll("rect")
				  .data(function(d) { return d.key; })
				  .enter().append("rect")
				  .attr("width", x1.rangeBand())
				  .attr("x", function(d) { return x1(d.name); })
				  .attr("y", function(d) { return y(d.value); })
				  .attr("height", function(d) { return height - y(d.value); })
				  .style("fill", function(d) { return color(d.name); });

			//for showing the legend
			var legend = svg.selectAll(".legend")
				.data(color.domain().slice().reverse())
				.enter().append("g")
				.attr("class", "legend")
				.attr("transform", function (d, i) {
				return "translate(0," + i * 20 + ")";
			});

			legend.append("rect")
				.attr("x", width - 18)
				.attr("width", 18)
				.attr("height", 18)
				.style("fill", color);

			legend.append("text")
				.attr("x", width - 24)
				.attr("y", 9)
				.attr("dy", ".35em")
				.style("text-anchor", "end")
				.text(function (d) {
				return d;
			});
		};
		
		function getQualityStats (){
			$scope.qualirtStatsRecords = [];
			var i=0,len;
			var tmpArray = [],red,green,yellow;
			var createdTime;
			var deliveredTime; 
			len = $scope.merchants.Books.length;
			for(;i<len;i++){
				tmpArray = [];
				red=green=yellow=0.5;
				var j=0,solen = $scope.salesOrders.Books.length;
				for(;j<solen;j++){
					if($scope.salesOrders.Books[j].MERCHANT_ID == $scope.merchants.Books[i].MERCHANT_ID && $scope.salesOrders.Books[j].DELIVERY_DATE){
						createdTime = new Date($scope.salesOrders.Books[j].CREATED);
						deliveredTime = new Date($scope.salesOrders.Books[j].DELIVERED_TIME);
						if((deliveredTime.getTime() - createdTime.getTime()) <= deliveryTimeSpan){
							green++;
						}else if((deliveredTime.getTime() - createdTime.getTime()) <= (deliveryTimeSpan + trafficTimeSpan) ){
							yellow++;
						}else{
							red++;
						}
					}
				}
				tmpArray.push(green);
				tmpArray.push(yellow);
				tmpArray.push(red);	
				/*console.log("tmpArray =",tmpArray);*/
				$scope.qualirtStatsRecords.push(tmpArray);
			}
			console.log("$scope.qualirtStatsRecords",$scope.qualirtStatsRecords);
			groupedBarChart();
			
		};
		
		function sendHttpRequest (service){
			var defer = $q.defer();
			$http({
					method: 'GET',
					url: commonNodeURL+service
				})
				.success(function (data, status) {
					defer.resolve(data);
				})
				.error(function (data, status) {
					defer.reject(data);
					console.log("merchant error case ", data);
				});
			return  defer.promise;
		}

		function getMerchantById(id) {
			for (var i = 0; i < $scope.merchants.Books.length; i++) {
				if ($scope.merchants.Books[i].MERCHANT_ID == id) {
					return $scope.merchants.Books[i].NAME;
				}
			}
			console.info("Failed to get Merchant Name By Id of : ",id);
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
				$scope.totalSales = $scope.totalSales + data.Books[i].NET_AMOUNT; 
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					$scope.todayTotSale = $scope.todayTotSale + data.Books[i].NET_AMOUNT;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					$scope.yesterdayTotSale = $scope.yesterdayTotSale + data.Books[i].NET_AMOUNT;
				}
			}
			console.log("Yesterday sales ==>",$scope.yesterdayTotSale,": Today Sales ==>",$scope.todayTotSale);
			$scope.totalSales = (Math.round(((commition / 100) * $scope.totalSales) * 100) / 100);
			$scope.todayTotSale = (Math.round(((commition / 100) * $scope.todayTotSale) * 100) / 100);
			$scope.yesterdayTotSale = (commition / 100) * $scope.yesterdayTotSale;
			$scope.salesGrowthToday = ($scope.yesterdayTotSale > 0) ? ((($scope.todayTotSale - $scope.yesterdayTotSale) / $scope.yesterdayTotSale) * 100) : 0;
			$scope.salesGrowthToday = ($scope.salesGrowthToday > 0) ? (Math.round($scope.salesGrowthToday * 100) / 100) :  0;

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

		$scope.locateMerchants = function () {
			$scope.tempMarkers = [];
			var i = 0;
			var len = $scope.merchants.Books.length;
			var locationObj = {};
			for (; i < len; i++) {
				var addressObj = $scope.getLatLongByUserId($scope.merchants.Books[i].USER_ID);
				locationObj.latitude 	= 	parseFloat(addressObj.LATITUDE);
				locationObj.longitude 	= 	parseFloat(addressObj.LONGITUDE);
				locationObj.title 		= 	$scope.merchants.Books[i].NAME;
				if (!$scope.tempMarkers || $scope.tempMarkers.length < 1) {
                    locationObj.id = 1;
                } else {
                    locationObj.id = $scope.tempMarkers[$scope.tempMarkers.length - 1].id + 1;
                }
				if (locationObj.latitude 	&& 	locationObj.longitude) {
					$scope.tempMarkers.push(angular.copy(locationObj));
				}
			}
			$scope.randomMarkers = $scope.tempMarkers;
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
			$scope.salesOrders.Books = tempArray;
			var x = {};
			var storeIds = [];
			var merchantName = "";
			for (var i = 0; i < tempArray.length; ++i) {
				var obj = tempArray[i];
				if (x[obj.MERCHANT_ID] === undefined && obj.MERCHANT_ID) {
					merchantName = getMerchantById(obj.MERCHANT_ID);
					if(merchantName){
						x[obj.MERCHANT_ID] = [merchantName];
						storeIds.push(obj.MERCHANT_ID);
					}
				}
				if (obj.MERCHANT_ID && x[obj.MERCHANT_ID])
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
					tmpAvgObj.key = x[storeIds[j]][0] + ' ₹';
					tmpAvgObj.y = Math.round((commition / 100) * totalAmount *100)/100;
					$scope.testdata.push(angular.copy(tmpAvgObj));

				}
			}			
			$scope.historicalBarChart2 = [{key: "Cumulative Return1",values: []}];
			tmpAvgObj = {};
			var billMinestone = [0,0,0,0,0,0];
			for(var i=0;i < tempArray.length; i++){
				tempArray[i].NET_AMOUNT
				if(tempArray[i].NET_AMOUNT > 10000){
					billMinestone[5]++;
				}else if(tempArray[i].NET_AMOUNT >= 5000){
					billMinestone[4]++;
				}else if(tempArray[i].NET_AMOUNT >= 1000){
					billMinestone[3]++;
				}else if(tempArray[i].NET_AMOUNT >= 500 ){
					billMinestone[2]++;
				}else if(tempArray[i].NET_AMOUNT >= 100 ){
					billMinestone[1]++;
				}else if(tempArray[i].NET_AMOUNT < 100){
					billMinestone[0]++;
				}
			}
			for(i=billMinestone.length-1; i >= 0 ; i--){
			
				if (totalAmount > 0) {
					tmpAvgObj = {};
					switch (i) {
						case 0:
							tmpAvgObj.label = 'Bill < 100';
							break;
						case 1:
							tmpAvgObj.label = '100< Bill <=500';
							break;
						case 2:
							tmpAvgObj.label = '500< Bill <=1000';
							break;
						case 3:
							tmpAvgObj.label = '1000< Bill <=5000';
							break;
						case 4:
							tmpAvgObj.label = '5000< Bill <=10000';
							break;
						case 5:
							tmpAvgObj.label = '10000 < Bill';
							break;
						default:
							console.log("Something went wrong in sales milestone count calculation");
					}
					tmpAvgObj.value = parseInt(billMinestone[i]);
					$scope.historicalBarChart2[0].values.push(angular.copy(tmpAvgObj));

				}
			}
			
			
			var filteredSalesOrder = angular.copy($scope.salesOrders);
			filteredSalesOrder.Books = _.reject(filteredSalesOrder.Books, function(book){ return book.ISACTIVE != 'Y';});
			$scope.drawPiechart();
			$scope.drawReviewChart();
			$scope.drawBarChart2();
			$scope.compSalesToday(filteredSalesOrder);
			getQualityStats();
			$scope.locateMerchants();
			/*callback();*/
		};

		function filteredMerchants(data) {
			$scope.totalMerchants = (data) ? data.Books.length : 0;
			var newMerchantsToday = 0,
				newMerchantsLastDat = 0;
			var tempDateObj;
			var len = data.Books.length;
			for (var i = 0; i < data.Books.length; i++) {
				tempDateObj = new Date((data.Books[i].CREATED).substring(0, 10));
				/*tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);*/
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					newMerchantsToday = newMerchantsToday + 1;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					newMerchantsLastDat = newMerchantsLastDat + 1;
				}
			}
			$scope.merchantGrowthToday = (newMerchantsLastDat) ? (((newMerchantsToday - newMerchantsLastDat) / newMerchantsLastDat) * 100) : 0;
			$scope.merchantGrowthToday = (Math.round($scope.merchantGrowthToday*100))/100;
			
		};

		function calcGrowthRatio(data){
			var tempDateObj = angular.copy(todayDateObj); 
			var tmpLen = data.Books.length;
			$scope.d3LineLeafs = [];
			var d3TmpArray = [];
			growthYcount = 0;
			//for merchants 
			var k=0;
			d3TmpArray = [k,0];
			for(var i=0; i< growthRatioChartSpan ; i++ ){
				tempDateObj.setTime(tempDateObj.getTime() - millisecondsPerday);
				 
				for (var j = 0; j < tmpLen; j++) {
					createdDateObj = new Date((data.Books[j].CREATED).substring(0, 10));
					if(createdDateObj.getTime() == tempDateObj.getTime()){
						d3TmpArray[1]++;
					}
				}
				if(tempDateObj.getDay() == 0){
					if(d3TmpArray[1] > growthYcount){growthYcount = d3TmpArray[1];}
					k++;
					$scope.d3LineLeafs.push(angular.copy(d3TmpArray));
					d3TmpArray = [k,0];
				}
			}
			if(d3TmpArray[1] > 0){
					k++;
					$scope.d3LineLeafs.push(angular.copy(d3TmpArray));
					d3TmpArray = [k,0];
			}

			$scope.d3LineData.push(angular.copy($scope.d3LineLeafs));
		}
		
		function orgGrowthRation (){
			calcGrowthRatio($scope.merchants);
			calcGrowthRatio($scope.stores);
			calcGrowthRatio($scope.customers);
			
			$scope.drawZoomedlinechart();
		}
		
		function filteredCustomers(data) {
			$scope.totalCustomers = (data) ? data.Books.length : 0;
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
			$scope.customerGrowthToday = Math.round($scope.customerGrowthToday * 100)/100;
			/*callback();*/
		};
		
		function filteredStores (data) {
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
			$scope.storeGrowthToday = Math.round($scope.storeGrowthToday * 100)/100;
		
		}
		
		function postStore (data){
			
			var len1 = $scope.merchants.Books.length;
			var tempMerchant;
			for(var i=0; i < len1; i++){
				tempMerchant = $scope.merchants.Books[i];
				count = 0;
				for(var j = 0; j < len1 ; j++){
					if(tempMerchant.MERCHANT_ID == data.Books[j].MERCHANT_ID){
						count = count +1;
					}
				}
				tmpAvgObj = {};
				tmpAvgObj.label = tempMerchant.NAME;
				tmpAvgObj.value = count;
				$scope.historicalBarChart[0].values.push(angular.copy(tmpAvgObj));
				
			}
			function compare(a,b) {
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


		$scope.proceedSalesOrder = function (callback) {

			var anayed = [];
			$scope.testdata = [{
				key: "O",
				y: 0
            }];

			sendHttpRequest('salesOrder').then(function (data) {
				data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});
				console.info("salesOrder Received ", data);
				postSalesOrder(data);
			});
		};
		$scope.proceedSalesOrderLine = function (callback) {
			sendHttpRequest('salesOrderLine').then(function (data) {
				data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});
				$scope.salesOrderLines = data;
				console.info("Sales order line Received", data);
			});

		};


		$scope.ProceedMerchant = function (callback) {
			//Merchants
			sendHttpRequest('merchant').then(function (data) {
				$scope.merchants = data;
				var activeMerchants = angular.copy(data);
				activeMerchants.Books = _.reject(activeMerchants.Books, function(book){ return book.ISACTIVE != 'Y';});
				console.info("Merchant Received ", data);
				filteredMerchants(data);
			});
		};
		$scope.proceedStore = function (callback) {			
			sendHttpRequest('store').then(function (data) {
				$scope.stores = data;
				var activeStores = angular.copy(data);
				activeStores.Books = _.reject(activeStores.Books, function(book){ return book.ISACTIVE != 'Y';});
				console.info("Store Received ", data);
				filteredStores(activeStores);
				postStore(data);
			});

		};
		$scope.proceedAddresses = function (callback) {		
			sendHttpRequest('address').then(function (data) {
				/*data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});*/
				$scope.addresses = data;
				console.info("Addresses Received =>");
			});
		};
		$scope.proceedUsers = function (callback) {
			sendHttpRequest('users').then(function (data) {
				/*data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});*/
				$scope.users = data;
				console.info("users Received", data);
			});
		};

		$scope.proceedCustomer = function (callback) {
			sendHttpRequest('customer').then(function (data) {
				$scope.customers = data;
				var activeCustomers = angular.copy(data);
				activeCustomers.Books = _.reject(activeCustomers.Books, function(book){ return book.ISACTIVE != 'Y';});
				filteredCustomers(activeCustomers);
				console.info("Customers Received",data);
				
				/*postCustomer(data);*/
			});
		};
		
		function initiateAllMethods (){
			document.getElementById('grouperBarChart').innerHTML = "";
			//call all methods by preority
			$scope.proceedCustomer();
			$scope.ProceedMerchant();
			$scope.proceedUsers();
			$scope.proceedAddresses();
			$scope.proceedSalesOrderLine();
			$scope.proceedSalesOrder();
			$scope.proceedStore();
		};
		
		initiateAllMethods();
		
		


 }]);