angular.module('aviate.controllers')
.controller("orderHistoryCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', '$http', 'MyListServices', 'ipCookie','$rootScope','orderHistoryServices','$mdBottomSheet',
		 function($scope, $state, toastr, CONSTANT, $http, MyListServices, ipCookie,$rootScope,orderHistoryServices,$mdBottomSheet) {
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
 
}]);
