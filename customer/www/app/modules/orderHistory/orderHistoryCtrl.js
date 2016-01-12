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
					tmpArray = [true,true,true,true];
				}else if(tmpData.deliveryStartTime || tmpData.backerAssignedTime){
					tmpArray = [true,true,true,false];
				}else if(tmpData.packedTime){
					tmpArray = [true,true,false,false];
				}else if(tmpData.pickupStartTime || tmpData.shopperAssignedTime){
					tmpArray = [true,false,false,false];
				}else{
					tmpArray = [false,false,false,false];
				}
				$scope.data[i].tracked = angular.copy(tmpArray);
				
			}			
		};		
		
		$scope.stringToDate = function(dateTime){
			return new Date(dateTime);
		}
 
}]);
