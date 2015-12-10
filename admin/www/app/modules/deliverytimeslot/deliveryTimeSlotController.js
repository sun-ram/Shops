aviateAdmin.controller("deliveryTimeSlot", ['$scope','$http','$localStorage','$location','$state','$rootScope','$mdDialog','toastr','CONSTANT','deliveryTimeSlotService','$filter',
                                              function($scope,$http,$localStorage, $location,$state,$rootScope,$mdDialog,toastr,CONSTANT,deliveryTimeSlotService,$filter) {

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
            if(!deliveryTimeSlot.fromTime || !deliveryTimeSlot.toTime){
                toastr.error('Please select delivery time slot');
                return;
            }
            if(deliveryTimeSlot.fromTime.getTime() >= deliveryTimeSlot.toTime.getTime()){
                toastr.error('To-Time cannot less than From-Time');
                return;
            }
			deliveryTimeSlotService.UpdateDeliveryTimeSlot(deliveryTimeSlot).then(function(data) {
				toastr.success(CONSTANT.UPDATEDELIVERYTIMESLOT);
				$state.go('app.deliveryTimeSlot');
			});
		};		
		
		$scope.saveDeliveryTimeSlot = function(deliveryTimeSlot) {
			//unit.merchantId = $rootScope.user.merchantId
			deliveryTimeSlot.merchant={};
            if(!deliveryTimeSlot.fromTime || !deliveryTimeSlot.toTime){
                toastr.error('Please select delivery time slot');
                return;
            }
            if(deliveryTimeSlot.fromTime.getTime() >= deliveryTimeSlot.toTime.getTime()){
                toastr.error('To-Time cannot less than From-Time');
                return;
            }
			deliveryTimeSlot.merchant.merchantId = $rootScope.user.merchantId;
			deliveryTimeSlotService.saveDeliveryTimeSlotService(deliveryTimeSlot).then(function(data) {
				toastr.success(CONSTANT.ADDDELIVERYTIMESLOT);
				$state.go('app.deliveryTimeSlot');
			});
		};
		
		$scope.deleteDeliveryTimeSlot = function(deliveryTimeSlot) {
			var confirm = $mdDialog.confirm()
	        .title('Would you like to delete Delivery Time Slot?')
			        .ok('Delete')
			        .cancel('Cancel');
			  $mdDialog.show(confirm).then(function() {
		
				  deliveryTimeSlotService.deleteDeliveryTimeSlot({'deliveryTimeSlotId':deliveryTimeSlot.deliveryTimeSlotId}).then(function(data) {
						$scope.getDeliveryTimeSlots();
						toastr.success(CONSTANT.DELETEDELIVERYTIMESLOT);
					});
				
	  }, function() {
				  
				  });		
			  
			
		};		
			
		
	$scope.deliveryTimeSlotDetails = function(deliveryTimeSlot){
		$localStorage.deliveryTimeSlot = deliveryTimeSlot;
		$state.go('app.deliveryTimeSlotDetails');
		
	};
                                                  
    var parseDeliverySlot = function(deliverySlot){
        if(!deliverySlot.hasOwnProperty('fromTime')) return;
        if(Date.parse(deliverySlot.fromTime) && Date.parse(deliverySlot.toTime)) return;
        var temp = deliverySlot.fromTime.split(':');
        deliverySlot.fromTime = new Date(new Date().setHours(temp[0],temp[1],temp[2]));
        var temp = deliverySlot.toTime.split(':');
        deliverySlot.toTime = new Date(new Date().setHours(temp[0],temp[1],temp[2]));
    }
                                                  
	$scope.deliveryTimeSlot=$localStorage.deliveryTimeSlot;
    
    if($scope.deliveryTimeSlot && $scope.deliveryTimeSlot.fromTime) 
        parseDeliverySlot($scope.deliveryTimeSlot);
    else
         $scope.deliveryTimeSlot = {fromTime:new Date(),toTime:new Date()};
   
	$scope.editDeliveryTimeSlot = function(deliveryTimeSlot) {
		$localStorage.deliveryTimeSlot=deliveryTimeSlot;
//		$localStorage.deliveryTimeSlot.fromTime=$scope.convertToTime(deliveryTimeSlot.fromTime);
//		$localStorage.deliveryTimeSlot.toTime=$scope.convertToTime(deliveryTimeSlot.toTime);
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
    $scope.filter12HrTime = function(time){
        var temp = time.split(':'),hours = temp[0],
            ampm = hours >= 12 ? 'PM' : 'AM';
        hours = hours % 12;
        temp[0] = hours ? hours : 12;
        return  temp.join(':') +" "+ampm;
    };
	$scope.cancelEdit = function(){
		$state.go('app.deliveryTimeSlot');
		
	}
	$scope.cancel = function(){
		$state.go('app.deliveryTimeSlot');
	}
}
]);


