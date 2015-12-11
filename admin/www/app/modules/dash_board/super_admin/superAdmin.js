aviateAdmin.controller("superDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT', '$rootScope', 'CommonServices', 'StoreServices', 'api', '$http','$q',
    function ($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, $rootScope, CommonServices, StoreServices, api, $http,$q) {
		var commonNodeURL = CONSTANT.DASHBOARD.NODE_SERVER_URL;
		var reportType = CONSTANT.DASHBOARD.DEFAULT_REPORT_TYPE;
		var commition = CONSTANT.DASHBOARD.COMMITION_PERCENTAGE;
		var width = 300;
		var height = 300;
		var millisecondsPerday = 86400000;
		var deliveryTimeSpan = 3600000;
		var trafficTimeSpan = 900000;
		var today = new Date();
		var todayDateObj = new Date(today.toISOString().substring(0, 10));
		var yesterdayDateObj = new Date(today.toISOString().substring(0, 10));
		yesterdayDateObj.setTime(todayDateObj.getTime() - millisecondsPerday);
		$scope.salesOrders = {Books:[]};
		$scope.merchants = [];
		$scope.stores;
		$scope.salesOrderLines;
		$scope.yesterdayTotSale = 0;
		$scope.todayTotSale = 0;
		$scope.totalMerchants = 0;
		$scope.salesGrowthToday = 0;
		$scope.qualirtStatsRecords = [];

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
				var n = 3, // number of layers
				m = $scope.merchants.Books.length, // number of samples per layer
				stack = d3.layout.stack(),
				layers = stack(d3.range(n).map(function() { return bumpLayer(m, .1); })),
				yGroupMax = d3.max(layers, function(layer) { return d3.max(layer, function(d) { return d.y; }); }),
				yStackMax = d3.max(layers, function(layer) { return d3.max(layer, function(d) { return d.y0 + d.y; }); });

			var margin = {top: 40, right: 10, bottom: 20, left: 10},
				width = 960 - margin.left - margin.right,
				height = 500 - margin.top - margin.bottom;

			var x = d3.scale.ordinal()
				.domain(d3.range(m))
				.rangeRoundBands([0, width], .08);

			var y = d3.scale.linear()
				.domain([0, yStackMax])
				.range([height, 0]);

			var color = d3.scale.linear()
				.domain([0, n - 1])
				.range(["#ff0000", "#32CD32"]);

			var xAxis = d3.svg.axis()
				.scale(x)
				.tickSize(0)
				.tickPadding(6)
				.orient("bottom");

			var svg = d3.select("#grouperBarChart").append("svg")
				.attr("width", width + margin.left + margin.right)
				.attr("height", height + margin.top + margin.bottom)
			  .append("g")
				.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

			var layer = svg.selectAll(".layer")
				.data(layers)
			  .enter().append("g")
				.attr("class", "layer")
				.style("fill", function(d, i) { return color(i); });

			var rect = layer.selectAll("rect")
				.data(function(d) { return d; })
			  .enter().append("rect")
				.attr("x", function(d) { return x(d.x); })
				.attr("y", height)
				.attr("width", x.rangeBand())
				.attr("height", 0);

			rect.transition()
				.delay(function(d, i) { return i * 10; })
				.attr("y", function(d) { return y(d.y0 + d.y); })
				.attr("height", function(d) { return y(d.y0) - y(d.y0 + d.y); });

			svg.append("g")
				.attr("class", "x axis")
				.attr("transform", "translate(0," + height + ")")
				.call(xAxis);

			d3.selectAll("input").on("change", change);

			var timeout = setTimeout(function() {
			  d3.select("input[value=\"grouped\"]").property("checked", true).each(change);
			}, 2000);

			function change() {
			  clearTimeout(timeout);
			  if (this.value === "grouped") transitionGrouped();
			  else transitionStacked();
			}

			function transitionGrouped() {
			  y.domain([0, yGroupMax]);

			  rect.transition()
				  .duration(500)
				  .delay(function(d, i) { return i * 10; })
				  .attr("x", function(d, i, j) { return x(d.x) + x.rangeBand() / n * j; })
				  .attr("width", x.rangeBand() / n)
				.transition()
				  .attr("y", function(d) { return y(d.y); })
				  .attr("height", function(d) { return height - y(d.y); });
			}

			function transitionStacked() {
			  y.domain([0, yStackMax]);

			  rect.transition()
				  .duration(500)
				  .delay(function(d, i) { return i * 10; })
				  .attr("y", function(d) { return y(d.y0 + d.y); })
				  .attr("height", function(d) { return y(d.y0) - y(d.y0 + d.y); })
				.transition()
				  .attr("x", function(d) { return x(d.x); })
				  .attr("width", x.rangeBand());
			}

			// Inspired by Lee Byron's test data generator.
			getQualityStats();
			function bumpLayer(n, o) {

			  function bump(a) {
				  
				  console.log("a--->");
				  
				var x = 5,
					y = 5,
					z = 5;
				  console.log("X,Y,Z ==>",x,'&',y,'&',z);
				for (var i = 0; i < n; i++) {
				  var w = (i / n - y) * z;
				  a[i] += x * Math.exp(-w * w);
				}
			  }

			  var a = [], i;
			  for (i = 0; i < n; ++i) a[i] = 1;
			  for (i = 0; i < 5; ++i) bump(a);
			  return a.map(function(d, i) { return {x: i, y: Math.max(0, d)}; });
			}
		};
		
		function getQualityStats (){
			var i=0,len=$scope.merchants.length;
			var tmpArray = [],red,green,yellow;
			var createdTime;
			var deliveredTime; 
			for(;i<len;i++){
				tmpArray = [];
				red=green=yellow=0;
				var j=0;soLen = $scope.salesOrders.Books.length;
				for(;j<solen;j++){
					if($scope.salesOrders.Books[j].MERCHANT_ID == $scope.merchants.Books[i].MERCHANT_ID && $scope.salesOrders.Books[j].DELIVERY_DATE){
						createdTime = new Date($scope.salesOrders.Books[j].CREATED);
						deliveredTime = new Date($scope.salesOrders.Books[j].DELIVERY_DATE);
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
				$scope.qualirtStatsRecords.push(tmpArray);
			}
		
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
		};
		
		function getCustomerNameById(id) {
			for (var i = 0; i < $scope.customers.Books.length; i++) {
				if ($scope.customers.Books[i].CUSTOMER_ID == id) {
					return $scope.customers.Books[i].NAME;
				}
			}
		};
		
		$scope.compSalesToday = function () {
			$scope.yesterdayTotSale = $scope.todayTotSale = 0;
			var len = $scope.salesOrders.Books.length;
			var i = 0,
				tempDateObj;
			for (; i < len; i++) {
				tempDateObj = new Date(($scope.salesOrders.Books[i].DELIVERY_DATE).substring(0, 10));
				tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					$scope.todayTotSale = $scope.todayTotSale + $scope.salesOrders.Books[i].NET_AMOUNT;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					$scope.yesterdayTotSale = $scope.yesterdayTotSale + $scope.salesOrders.Books[i].NET_AMOUNT;
				}
			}

			$scope.todayTotSale = (Math.round(((commition / 100) * $scope.todayTotSale) * 100) / 100);
			$scope.yesterdayTotSale = (commition / 100) * $scope.yesterdayTotSale;
			$scope.salesGrowthToday = ($scope.yesterdayTotSale > 0) ? ((($scope.todayTotSale - $scope.yesterdayTotSale) / $scope.yesterdayTotSale) * 100) : 0;
			$scope.salesGrowthToday = ($scope.salesGrowthToday > 0) ? (Math.round($scope.salesGrowthToday * 100) / 100) : $scope.todayTotSale;

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
				if (tempDateObj.getTime() > endDateObj.getTime()) {
					tempArray[index] = data.Books[i];
					index++;
					totalAmount = totalAmount + data.Books[i].NET_AMOUNT;
				}
			}
			$scope.salesOrders.Books = tempArray;
			var x = {};
			var storeIds = [];
			for (var i = 0; i < tempArray.length; ++i) {
				var obj = tempArray[i];
				if (x[obj.MERCHANT_ID] === undefined && obj.MERCHANT_ID) {
					x[obj.MERCHANT_ID] = [getMerchantById(obj.MERCHANT_ID)];
					storeIds.push(obj.MERCHANT_ID);
				}
				if (obj.MERCHANT_ID)
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
					tmpAvgObj.y = (commition / 100) * totalAmount;
					$scope.testdata.push(angular.copy(tmpAvgObj));

				}
			}
			x1 = {};
			storeIds1 = [];
			for (var i = 0; i < tempArray.length; ++i) {
				var obj = tempArray[i];
				if (x1[obj.CUSTOMER_ID] === undefined && obj.CUSTOMER_ID) {
					x1[obj.CUSTOMER_ID] = [getCustomerNameById(obj.CUSTOMER_ID)];
					storeIds1.push(obj.CUSTOMER_ID);
				}
				if (obj.CUSTOMER_ID)
					x1[obj.CUSTOMER_ID].push(obj.NET_AMOUNT);
			};
			
			tmpAvgObj = {};
			for (var j = 0; j < storeIds1.length; j++) {
				totalAmount = 0;
				for (var i = 1; i < x1[storeIds1[j]].length; i++) {
					totalAmount = totalAmount + x1[storeIds1[j]][i];
				}
				if (totalAmount > 0) {
					tmpAvgObj = {};
					tmpAvgObj.label =  x1[storeIds1[j]][0]+j;
					tmpAvgObj.value = Math.round((commition / 100) * totalAmount);
					$scope.historicalBarChart2[0].values.push(angular.copy(tmpAvgObj));

				}
			}
			console.log("$scope.historicalBarChart2--->",$scope.historicalBarChart2);
			
			$scope.drawPiechart();
			$scope.drawReviewChart();
			$scope.drawBarChart2();
			$scope.compSalesToday();
			/*callback();*/
		};

		function postMerchant(data) {
			$scope.totalMerchants = (data) ? data.Books.length : 0;
			var newMerchantsToday = 0,
				newMerchantsLastDat = 0;
			var tempDateObj;
			var len = data.Books.length;
			for (var i = 0; i < data.Books.length; i++) {
				tempDateObj = new Date((data.Books[i].CREATED).substring(0, 10));
				tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					newMerchantsToday = newMerchantsToday + 1;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					newMerchantsLastDat = newMerchantsLastDat + 1;
				}
			}
			console.log("merchants Yesterday and to Today", newMerchantsLastDat, " & ", newMerchantsToday);
			$scope.merchantGrowthToday = (newMerchantsLastDat) ? (((newMerchantsToday - newMerchantsLastDat) / newMerchantsLastDat) * 100) : 0;

		};

		function postCustomer(data) {
			$scope.totalCustomers = (data) ? data.Books.length : 0;
			var newCustomerToday = 0,
				newCustomerLastDat = 0;
			var tempDateObj;
			var len = data.Books.length;
			for (var i = 0; i < data.Books.length; i++) {
				tempDateObj = new Date((data.Books[i].CREATED).substring(0, 10));
				tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					newCustomerToday = newCustomerToday + 1;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					newCustomerLastDat = newCustomerLastDat + 1;
				}
			}
			$scope.customerGrowthToday = (newCustomerLastDat) ? (((newCustomerToday - newCustomerLastDat) / newCustomerLastDat) * 100) : 0;
			/*callback();*/
		};
		
		function postStore (data){
			$scope.totalStores = (data) ? data.Books.length : 0;
			var newStoresToday = 0,
				newStoresLastDay = 0;
			var tempDateObj;
			var tmpAvgObj = {};
			var len = data.Books.length;
			for (var i = 0; i < len; i++) {
				tempDateObj = new Date((data.Books[i].CREATED).substring(0, 10));
				tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					newStoresToday = newStoresToday + 1;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					newStoresLastDay = newStoresLastDay + 1;
				}
			}
			len1 = $scope.merchants.Books.length;
			var tempMerchant;
			for(var i=0; i < len1; i++){
				tempMerchant = $scope.merchants.Books[i];
				count = 0;
				for(var j = 0; j < len ; j++){
					if(tempMerchant.MERCHANT_ID == data.Books[j].MERCHANT_ID){
						count = count +1;
					}
				}
				tmpAvgObj = {};
				tmpAvgObj.label = tempMerchant.NAME;
				tmpAvgObj.value = count;
				$scope.historicalBarChart[0].values.push(angular.copy(tmpAvgObj));
			}
			console.log("$scope.historicalBarChart",$scope.historicalBarChart);
			$scope.drawBarChart();
			console.log("Stores Yesterday and to Today", newStoresLastDay, " & ", newStoresToday);
			$scope.storeGrowthToday = (newStoresLastDay) ? (((newStoresToday - newStoresLastDay) / newStoresLastDay) * 100) : 0;
		};


		$scope.proceedSalesOrder = function (callback) {

			var anayed = [];
			$scope.testdata = [{
				key: "O",
				y: 0
            }];

			sendHttpRequest('salesOrder').then(function (data) {
				console.log("salesOrder Exected success case ", data);
				postSalesOrder(data);
			});
		};
		$scope.proceedSalesOrderLine = function (callback) {
			sendHttpRequest('salesOrderLine').then(function (data) {
				$scope.salesOrderLines = data;
				console.log("Sales order Line =>", data);
			});

		};


		$scope.ProceedMerchant = function (callback) {
			//Merchants
			sendHttpRequest('merchant').then(function (data) {
				$scope.merchants = data;
				console.log("merchant Line =>", data);
				postMerchant(data);
			});
		};
		$scope.proceedStore = function (callback) {			
			sendHttpRequest('store').then(function (data) {
				$scope.stores = data;
				console.log("Store success case -- ", data);
				postStore(data);
				groupedBarChart();
			});

		};
		$scope.proceedAddresses = function (callback) {		
			sendHttpRequest('address').then(function (data) {
				$scope.addresses = data;
				console.log("proceedAddresses Line =>", data);
				$scope.locateMerchants(data);
			});
		};
		$scope.proceedUsers = function (callback) {
			sendHttpRequest('users').then(function (data) {
				$scope.users = data;
				console.log("users --->", data);
			});
		};

		$scope.proceedCustomer = function (callback) {
			sendHttpRequest('customer').then(function (data) {
				console.log("Customers--->",data);
				$scope.customers = data;
				postCustomer(data);
			});
		};
		
		function initiateAllMethods (){
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