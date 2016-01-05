angular.module('aviate.controllers')
.controller("orderHistoryCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$http', 'MyListServices', 'ipCookie','$rootScope','orderHistoryServices','$mdBottomSheet','myConfig','$q',
		 function($scope, $state, toastr, CONSTANT, $http, MyListServices, ipCookie,$rootScope,orderHistoryServices,$mdBottomSheet,myConfig,$q) {
             orderHistoryServices.getordersHistory().then(function(data){
                 $scope.data = data.salesOrderList;
                 postSalesOrder($scope.data);
				 console.log("data ", $scope.data );
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
			 
		function postSalesOrder (data){
			var i=0,len= ((data)?data.length : 0);
			for(;i<len;i++){
				var tmpData = data[i];
				var tmpArray = [];
				 data[i].tracked = [];
				if(tmpData.deliveredTime){
					tmpArray = ['highlight-index','highlight-index','highlight-index','highlight-index'];
					/*$scope.trackedOrderDetails.push(tmpData.netAmount + " : "+ tmpData.orderNo);*/
				}else if(tmpData.deliveryStartTime || tmpData.backerAssignedTime){
					tmpArray = ['highlight-index','highlight-index','highlight-index','none'];
				}else if(tmpData.packedTime){
					tmpArray = ['highlight-index','highlight-index','none','none'];
				}else if(tmpData.pickupStartTime || tmpData.shopperAssignedTime){
					tmpArray = ['highlight-index','none','none','none'];
				}else{
					tmpArray = ['none','none','none','none'];
				}
				$scope.data[i].tracked = angular.copy(tmpArray);
				
			}			
		};		
 
}]);
