aviateAdmin.controller("deliveryTimeSlot", ['$scope','$http','$localStorage','$location','$state','$rootScope','toastr','CONSTANT','deliveryTimeSlotService',
                                              function($scope,$http,$localStorage, $location,$state,$rootScope,toastr,CONSTANT,deliveryTimeSlotService) {

	 /* $scope.query = {
			    limit: 5,
			    page: 1
			  };*/
	  
		$scope.count = 3;
		$scope.srch = true;
		$scope.uom = $localStorage.uom;

		$scope.getDeliveryTimeSlots = function() {
			
			$scope.deliveryTimeSlot ={};
			$scope.deliveryTimeSlot.merchant={};
			$scope.deliveryTimeSlot.merchant.merchantId = $rootScope.user.merchantId;
			
			deliveryTimeSlotService.getDeliveryTimeSlots($scope.deliveryTimeSlot).then(function(data) {
				$scope.listOfDeliveryTimeSlot =[];
				$scope.listOfDeliveryTimeSlot = data.deliveryTimeSlots;
			});
		};
	
		$scope.updateDeliveryTimeSlot = function(deliveryTimeSlot) {
			deliveryTimeSlotService.UpdateUnit(deliveryTimeSlot).then(function(data) {
				toastr.success(CONSTANT.UPDATEUNIT);
				$state.go('app.deliveryTimeSlot');
			});
		};		
		
		$scope.saveDeliveryTimeSlot = function(deliveryTimeSlot) {
			//unit.merchantId = $rootScope.user.merchantId
			deliveryTimeSlot.merchant={};
			deliveryTimeSlot.merchant.merchantId = $rootScope.user.merchantId;
			deliveryTimeSlotService.saveDeliveryTimeSlotService(deliveryTimeSlot).then(function(data) {
				toastr.success(CONSTANT.ADDUNIT);
				$state.go('app.deliveryTimeSlot');
			});
		};
		
		$scope.deleteDeliveryTimeSlot = function(uom) {
				UnitService.deleteUnit(uom).then(function(data) {
					$scope.getmeasurementunit();
				toastr.success(CONSTANT.DELETEUNIT);
			});
		}		
			
		
	$scope.deliveryTimeSlotDetails = function(deliveryTimeSlot){
		$localStorage.deliveryTimeSlot = deliveryTimeSlot;
		$state.go('app.deliveryTimeSlotDetails');
		
	},
	//$scope.setDeliveryTimeSlot = function(){
	$scope.deliveryTimeSlot=$localStorage.deliveryTimeSlot;
	//}
	
	$scope.editDeliveryTimeSlot = function(deliveryTimeSlot) {
		$localStorage.deliveryTimeSlot=deliveryTimeSlot;
		$localStorage.deliveryTimeSlot.fromTime=$scope.convertToTime(deliveryTimeSlot.fromTime);
		$localStorage.deliveryTimeSlot.toTime=$scope.convertToTime(deliveryTimeSlot.toTime);
		$state.go('app.addDeliveryTimeSlot');
	},
	//$scope.deliveryTimeSlot = JSON.parse(localStorage.getItem('deliveryTimeSlot'));
	
	 $scope.convertToTime = function(timeString){
	    var timeTokens = timeString.split(':');
	    return new Date(1970,0,1, timeTokens[0], timeTokens[1], timeTokens[2]);
	}
	
	$scope.addDeliveryTimeSlot = function(){
		$scope.deliveryTimeSlot = {};
		$localStorage.deliveryTimeSlot = {};
		$state.go('app.addDeliveryTimeSlot');
		
	}
	$scope.cancelEdit = function(){
		$state.go('app.deliveryTimeSlot');
		
	}
	$scope.cancel = function(){
		$state.go('app.deliveryTimeSlot');
	}
}
]);