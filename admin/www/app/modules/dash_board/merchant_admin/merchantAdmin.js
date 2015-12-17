aviateAdmin.controller("merchantDashboardCtrl", ['$scope', '$localStorage', '$location', '$state', '$mdDialog', 'EmployeeService', 'toastr', 'CONSTANT','myConfig', '$rootScope', 'CommonServices', 'StoreServices', 'api', '$http','$q',
    function ($scope, $localStorage, $location, $state, $mdDialog, EmployeeService, toastr, CONSTANT, myConfig, $rootScope, CommonServices, StoreServices, api, $http,$q) {
		var commonNodeURL = myConfig.node_server_url;
		var reportType = CONSTANT.DASHBOARD.DEFAULT_REPORT_TYPE;
		var commition = CONSTANT.DASHBOARD.COMMITION_PERCENTAGE;
		var deliveryTimeSpan = CONSTANT.DASHBOARD.DELIVERY_TIME_SPAN;
		var trafficTimeSpan = CONSTANT.DASHBOARD.ADJUSTABLE_TRAFIC_TIME_SPAN;
		var width = 300;
		var height = 300;
		var millisecondsPerday = 86400000;
		var today = new Date();
		var todayDateObj = new Date(today.toISOString().substring(0, 10));
		var yesterdayDateObj = new Date(today.toISOString().substring(0, 10));
		yesterdayDateObj.setTime(todayDateObj.getTime() - millisecondsPerday);
		$scope.salesOrders = {Books:[]};
		$scope.merchants;
		$scope.stores;
		$scope.salesOrderLines;
		$scope.yesterdayTotSale = 0;
		$scope.todayTotSale = 0;
		$scope.totalMerchants = 0;
		$scope.totalStores = 0;
		$scope.salesGrowthToday = 0;
		$scope.totalSalesRevenue = 0;

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
		
		function groupedBarChart (data){
					
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
			len = $scope.stores.Books.length;
			var tmpGroupObj = {};
			console.log("SALESORDER COUNT ==> ",$scope.salesOrders.Books.length);
			for(;i<len;i++){
				if($rootScope.user.merchantId == $scope.stores.Books[i].MERCHANT_ID){
					tmpArray = [];
					red=green=yellow=0.5;
					var j=0,solen = $scope.salesOrders.Books.length;
					for(;j<solen;j++){
						if($scope.salesOrders.Books[j].STORE_ID == $scope.stores.Books[i].STORE_ID && $scope.salesOrders.Books[j].DELIVERED_TIME){
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
						tmpGroupObj = {};
						tmpGroupObj.date = $scope.stores.Books[i].NAME;
						tmpGroupObj.Good = green;
						tmpGroupObj.Normal = yellow;
						tmpGroupObj.Bad = red;
						if(tmpGroupObj.Good >0 || tmpGroupObj.Normal > 0 || tmpGroupObj.Bad > 0){
							$scope.qualirtStatsRecords.push(angular.copy(tmpGroupObj));
						}
					}
				
			}
			console.log("$scope.qualirtStatsRecords",$scope.qualirtStatsRecords);
			groupedBarChart($scope.qualirtStatsRecords);
			
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

		function getStoreById(id) {
			for (var i = 0; i < $scope.stores.Books.length; i++) {
				if ($scope.stores.Books[i].STORE_ID == id) {
					return $scope.stores.Books[i].NAME;
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
		
		$scope.compSalesToday = function (data) {
			$scope.yesterdayTotSale = $scope.todayTotSale = 0;
			var len = data.Books.length;
			var i = 0,
				tempDateObj;
			for (; i < len; i++) {
				tempDateObj = new Date((data.Books[i].DELIVERY_DATE).substring(0, 10));
				tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					$scope.todayTotSale = $scope.todayTotSale + data.Books[i].NET_AMOUNT;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					$scope.yesterdayTotSale = $scope.yesterdayTotSale + data.Books[i].NET_AMOUNT;
				}
			}

			$scope.todayTotSale = (Math.round($scope.todayTotSale * 100) / 100);
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

		$scope.locateStores = function () {
			$scope.tempMarkers = [];
			var i = 0;
			var len = $scope.stores.Books.length;
			var locationObj = {};
			for (; i < len; i++) {
				if($scope.stores.Books[i].MERCHANT_ID == $rootScope.user.merchantId){
					var addressObj = $scope.getLatLongByUserId($scope.stores.Books[i].USER_ID);
					locationObj.latitude 	= 	parseFloat(addressObj.LATITUDE);
					locationObj.longitude 	= 	parseFloat(addressObj.LONGITUDE);
					locationObj.title 		= 	$scope.stores.Books[i].NAME;
					if (!$scope.tempMarkers || $scope.tempMarkers.length < 1) {
						locationObj.id = 1;
					} else {
						locationObj.id = $scope.tempMarkers[$scope.tempMarkers.length - 1].id + 1;
					}
					if (locationObj.latitude 	&& 	locationObj.longitude) {
						$scope.tempMarkers.push(angular.copy(locationObj));
					}
				}
			}
			$scope.randomMarkers = $scope.tempMarkers;
		};
 /*$scope.randomMarkers = [{
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
                }];*/
		
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
			$scope.totalSalesRevenue = (totalAmount > 0)? totalAmount : 0;
			$scope.salesOrders.Books = tempArray;
			var x = {};
			var storeIds = [];
			for (var i = 0; i < tempArray.length; ++i) {
				var obj = tempArray[i];
				if (x[obj.STORE_ID] === undefined && obj.STORE_ID) {
					x[obj.STORE_ID] = [getStoreById(obj.STORE_ID)];
					storeIds.push(obj.STORE_ID);
				}
				if (obj.STORE_ID)
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
					tmpAvgObj.y = totalAmount;
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
	
			console.log("$scope.historicalBarChart2--->",$scope.historicalBarChart2);
			len1 = $scope.stores.Books.length;
			$scope.historicalBarChart = [{key: "Cumulative Return",values: []}];
			var tempStore;
			for(var i=0; i < len1; i++){
				tempStore = $scope.stores.Books[i];
				count = 0;
				if(tempStore.MERCHANT_ID == $rootScope.user.merchantId){
					for(var j = 0; j < len ; j++){
						if(tempStore.STORE_ID == $scope.salesOrders.Books[j].STORE_ID){
							count = count +1;
						}
					}
					tmpAvgObj = {};
					tmpAvgObj.label = tempStore.NAME;
					tmpAvgObj.value = count;
					$scope.historicalBarChart[0].values.push(angular.copy(tmpAvgObj));
				}
			}
			function compare(a,b) {
  					if (a.value < b.value)
    					return 1;
					if (a.value > b.value)
    					return -1;
  					return 0;
				}

			$scope.historicalBarChart[0].values.sort(compare);
			console.log("$scope.historicalBarChart",$scope.historicalBarChart);
			$scope.drawBarChart();
			
			$scope.drawPiechart();
			$scope.drawReviewChart();
			$scope.drawBarChart2();
			$scope.compSalesToday(tempArray);
			getQualityStats();
			$scope.locateStores();
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
				/*tempDateObj.setTime(tempDateObj.getTime() + millisecondsPerday);*/
				if (tempDateObj.getTime() == todayDateObj.getTime()) {
					newMerchantsToday = newMerchantsToday + 1;
				} else if (tempDateObj.getTime() == yesterdayDateObj.getTime()) {
					newMerchantsLastDat = newMerchantsLastDat + 1;
				}
			}
			console.log("merchants Yesterday and to Today", newMerchantsLastDat, " & ", newMerchantsToday);
			$scope.merchantGrowthToday = (newMerchantsLastDat) ? (((newMerchantsToday - newMerchantsLastDat) / newMerchantsLastDat) * 100) : 0;
			$scope.merchantGrowthToday  = (Math.round($scope.merchantGrowthToday  * 100) / 100);
		};

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
			$scope.customerGrowthToday = (Math.round($scope.customerGrowthToday  * 100) / 100);
			/*callback();*/
		};
		
		function filteredStores (data){
			
			data.Books = _.reject(data.Books, function(book){ return book.MERCHANT_ID != $rootScope.user.merchantId; });
			$scope.totalStores = data.Books.length;
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
			console.log("Stores Yesterday and to Today", newStoresLastDay, " & ", newStoresToday);
			$scope.storeGrowthToday = (newStoresLastDay) ? (((newStoresToday - newStoresLastDay) / newStoresLastDay) * 100) : 0;
			$scope.storeGrowthToday = (Math.round($scope.storeGrowthToday  * 100) / 100);
			/*$scope.locateStores();*/
		};


		$scope.proceedSalesOrder = function (callback) {

			var anayed = [];
			$scope.testdata = [{
				key: "O",
				y: 0
            }];

			sendHttpRequest('salesOrder').then(function (data) {
				data.Books = _.reject(data.Books, function(book){ return (book.MERCHANT_ID != $rootScope.user.merchantId || book.ISACTIVE != 'Y'); });
				console.log("salesOrder Exected success case ", data);
				postSalesOrder(data);
			});
		};
		$scope.proceedSalesOrderLine = function (callback) {
			sendHttpRequest('salesOrderLine').then(function (data) {
				data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});
				$scope.salesOrderLines = data;
				console.log("Sales order Line =>", data);
			});

		};


		$scope.ProceedMerchant = function (callback) {
			//Merchants
			sendHttpRequest('merchant').then(function (data) {
				$scope.merchants = data;
				/*data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});
				console.log("merchant Line =>", data);*/
				/*postMerchant(data);*/
			});
		};
		$scope.proceedStore = function (callback) {			
			sendHttpRequest('store').then(function (data) {
				$scope.stores = data;
				var activeStores = angular.copy(data);
				activeStores.Books = _.reject(activeStores.Books, function(book){ return book.ISACTIVE != 'Y';});
				console.info("Store Received ", data);
				filteredStores(activeStores);
			});

		};
		$scope.proceedAddresses = function (callback) {		
			sendHttpRequest('address').then(function (data) {
				/*data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});*/
				$scope.addresses = data;
				console.log("proceedAddresses Line =>", data);
				/*$scope.locateStores(data);*/
			});
		};
		$scope.proceedUsers = function (callback) {
			sendHttpRequest('users').then(function (data) {
				/*data.Books = _.reject(data.Books, function(book){ return book.ISACTIVE != 'Y';});*/
				$scope.users = data;
				console.log("users --->", data);
			});
		};

		$scope.proceedCustomer = function (callback) {
			sendHttpRequest('customer').then(function (data) {				
				$scope.customers = data;
				var activeCustomers = angular.copy(data);
				activeCustomers.Books = _.reject(activeCustomers.Books, function(book){ return book.ISACTIVE != 'Y';});
				filteredCustomers(activeCustomers);
				console.info("Customers Received",data);
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
			$scope.proceedStore();
			$scope.proceedSalesOrder();
			
						
		};
		
		initiateAllMethods();
		
		


 }]);