angular.module('aviate.controllers')
.controller("orderHistoryCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$http', 'MyListServices', 'ipCookie','$rootScope','orderHistoryServices','$mdBottomSheet','myConfig','$q',
		 function($scope, $state, toastr, CONSTANT, $http, MyListServices, ipCookie,$rootScope,orderHistoryServices,$mdBottomSheet,myConfig,$q) {
             orderHistoryServices.getordersHistory().then(function(data){
                 $scope.data = data.salesOrderList;
                 console.log("data :: ", $scope.data );
             });
            $scope.showDetails = "";
            $scope.expand = false;
        $scope.showListBottomSheet = function(show) {
        	$scope.expand = true;
        	if($scope.showDetails === show){
        		$scope.showDetails = "";
        	}else{
        		$scope.showDetails = show;
        	}
            
        };
			 
		// Order tracking 
			 var commonNodeURL = myConfig.node_server_url;
		$scope.trackedOrders = [];
		$scope.trackedOrderDetails = [];
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
						console.log(service+" error case ", data);
					});
				return  defer.promise;
			}

		
		function postSalesOrder (data){
			console.log("User ==>",$rootScope.user);
			$scope.trackedOrders = [['highlight-index','none','none','none'],['highlight-index','highlight-index','none','none'],['highlight-index','highlight-index','none','none']];
			var i=0,len= ((data)?data.Books.length : 0);
			for(;i<len;i++){
				var tmpData = data.Books[i];
				var tmpArray = [];
				
				if(tmpData.DELIVERED_TIME){
					tmpArray = ['highlight-index','highlight-index','highlight-index','highlight-index'];
					$scope.trackedOrders.push(tmpArray);
					$scope.trackedOrderDetails.push(tmpData.NET_AMOUNT + " : "+ tmpData.ORDER_NO);
				}else if(tmpData.DELIVERY_START_TIME || tmpData.BACKER_ASSIGNED_TIME){
					tmpArray = ['highlight-index','highlight-index','highlight-index','none'];
					$scope.trackedOrders.push(tmpArray);
					$scope.trackedOrderDetails.push(tmpData.NET_AMOUNT + " : "+ tmpData.ORDER_NO);
				}else if(tmpData.PACKED_TIME){
					tmpArray = ['highlight-index','highlight-index','none','none'];
					$scope.trackedOrders.push(tmpArray);
					$scope.trackedOrderDetails.push(tmpData.NET_AMOUNT + " : "+ tmpData.ORDER_NO);
				}else if(tmpData.PICKUP_START_TIME || tmpData.SHOPPER_ASSIGNED_TIME){
					tmpArray = ['highlight-index','none','none','none'];
					$scope.trackedOrders.push(tmpArray);
					$scope.trackedOrderDetails.push(tmpData.NET_AMOUNT + " : "+ tmpData.ORDER_NO);
				}else{
					tmpArray = ['none','none','none','none'];
					$scope.trackedOrders.push(tmpArray);
					$scope.trackedOrderDetails.push(tmpData.NET_AMOUNT + " : "+ tmpData.ORDER_NO);
				}
				
			}
			console.info("$scope.trackedOrders---------------->",$scope.trackedOrders);
			
		};
		
		$scope.proceedSalesOrder = function (callback) {
			sendHttpRequest('salesOrder').then(function (data) {
				data.Books = _.reject(data.Books, function(book){ return book.CUSTOMER_ID != $rootScope.user.userId;});
				console.info("salesOrder Received ", data);
				postSalesOrder(data);
			});
		};
		$scope.proceedSalesOrder();
 
}]);
