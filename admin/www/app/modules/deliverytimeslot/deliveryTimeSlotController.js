aviateAdmin.controller("deliveryTimeSlot", ['$scope','$http','$localStorage','$location','$state','$rootScope','$mdDialog','toastr','CONSTANT','deliveryTimeSlotService','$filter','deliverTimes',
                                            function($scope,$http,$localStorage, $location,$state,$rootScope,$mdDialog,toastr,CONSTANT,deliveryTimeSlotService,$filter,deliverTimes) {

	$scope.deliveryTimeSlot = deliverTimes.deliveryTimeSlots;
		
	$scope.count = 3;
	$scope.srch = true;
	$scope.uom = $localStorage.uom;
	$scope.time = new Date();
	
	$scope.getDeliveryTimeSlots = function() {
		if($scope.deliveryTimeSlot.length !=0){
			$scope.fromDeliveryTime = $scope.deliveryTimeSlot[0].fromTime;
			$scope.toDeliveryTime = $scope.deliveryTimeSlot[0].toTime;
			$scope.deliveryTimeSlot = $scope.deliveryTimeSlot[0];
			$scope.deliveryTimeSlot.fromTime = $scope.convertToTime($scope.deliveryTimeSlot.fromTime);
			$scope.deliveryTimeSlot.toTime = $scope.convertToTime($scope.deliveryTimeSlot.toTime);
			if($scope.deliveryTimeSlot.holidayDates){
				$scope.deliveryTimeSlot.holidayDates.forEach(function(date){
					$scope.holidayDates.push(new Date(date));
				});
			}
			$scope.holidayReasons = JSON.parse($scope.deliveryTimeSlot.holidayReasons);
		}else{
			$scope.deliveryTimeSlot = {fromTime:new Date(),toTime:new Date()};
		}
	};
		
	$scope.updateDeliveryTimeSlot = function(deliveryTimeSlot) {
		if(!deliveryTimeSlot.fromTime || !deliveryTimeSlot.toTime){
			toastr.error('Please select delivery time slot');
			return;
		}
		var reasons = {};
		$scope.holidayDates.forEach(function(date){
			var dateKey = $filter('date')(date,"yyyy-M-d");
			reasons[dateKey] = $scope.holidayReasons[dateKey] || " ";
		});
		deliveryTimeSlot.storeId=$rootScope.user.storeId;
		deliveryTimeSlot.holidayDates = $scope.holidayDates;
		deliveryTimeSlot.holidayReasons = JSON.stringify(reasons);
		deliveryTimeSlot.userId = $rootScope.user.userName;

		if(deliveryTimeSlot.fromTime.getTime() >= deliveryTimeSlot.toTime.getTime()){
			var confirm = $mdDialog.confirm()
			.title('Are You Delivering In Night Shift?')
			.ok('Ok')
			.cancel('Cancel');
			$mdDialog.show(confirm).then(function() {
				deliveryTimeSlotService.UpdateDeliveryTimeSlot(deliveryTimeSlot).then(function(data) {
					toastr.success(CONSTANT.UPDATEDELIVERYTIMESLOT);
					$state.go('app.addDeliveryTimeSlot',{},{reload: true});
				});
			}, function() {

			});	
		}else{
			deliveryTimeSlotService.UpdateDeliveryTimeSlot(deliveryTimeSlot).then(function(data) {
				toastr.success(CONSTANT.UPDATEDELIVERYTIMESLOT);
				$state.go('app.addDeliveryTimeSlot',{},{reload: true});
			});	
		}
	};		

	$scope.saveDeliveryTimeSlot = function(deliveryTimeSlot) {
		//unit.merchantId = $rootScope.user.merchantId
		deliveryTimeSlot.merchant={};
		if(!deliveryTimeSlot.fromTime || !deliveryTimeSlot.toTime){
			toastr.error('Please select delivery time slot');
			return;
		}
		var reasons = {};
		$scope.holidayDates.forEach(function(date){
			var dateKey = $filter('date')(date,"yyyy-M-d");
			reasons[dateKey] = $scope.holidayReasons[dateKey] || "";
		});
		deliveryTimeSlot.holidayDates = $scope.holidayDates;
		deliveryTimeSlot.holidayReasons = JSON.stringify(reasons);
		deliveryTimeSlot.userId = $rootScope.user.userName;

		if(deliveryTimeSlot.fromTime.getTime() >= deliveryTimeSlot.toTime.getTime()){
			var confirm = $mdDialog.confirm()
			.title('Are You Delivering In Night Shift?')
			.ok('Ok')
			.cancel('Cancel');
			$mdDialog.show(confirm).then(function() {
				deliveryTimeSlot.merchant.merchantId = $rootScope.user.merchantId;
				deliveryTimeSlot.storeId=$rootScope.user.storeId;

				deliveryTimeSlotService.saveDeliveryTimeSlotService(deliveryTimeSlot).then(function(data) {
					toastr.success(CONSTANT.ADDDELIVERYTIMESLOT);
					$state.go('app.addDeliveryTimeSlot',{},{reload: true});
				});
			}, function() {

			});
		}else{
			deliveryTimeSlot.storeId=$rootScope.user.storeId;
			deliveryTimeSlot.merchant.merchantId = $rootScope.user.merchantId;
			deliveryTimeSlotService.saveDeliveryTimeSlotService(deliveryTimeSlot).then(function(data) {
				toastr.success(CONSTANT.ADDDELIVERYTIMESLOT);
				$state.go('app.addDeliveryTimeSlot',{},{reload: true});
			});
		}
	};
	
	$scope.convertToTime = function(timeString){
		var timeTokens = timeString.split(':');
		return new Date(1970,0,1, timeTokens[0], timeTokens[1], timeTokens[2]);
	}
	
	 $scope.filter12HrTime = function(time){
	        var temp = time.split(':'),hours = temp[0],
	            ampm = hours >= 12 ? 'PM' : 'AM';
	        hours = hours % 12;
	        temp[0] = hours ? hours : 12;
	        return  temp[0]+':'+temp[1]+" "+ampm;
	    };
	
	/*
	if($scope.deliveryTimeSlot && $scope.deliveryTimeSlot.fromTime) 
		parseDeliverySlot($scope.deliveryTimeSlot);
	else
		$scope.deliveryTimeSlot = {fromTime:new Date(),toTime:new Date()};*/

	/*$scope.deleteDeliveryTimeSlot = function(deliveryTimeSlot) {
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


		};	*/	


	/*$scope.deliveryTimeSlotDetails = function(deliveryTimeSlot){
		$localStorage.deliveryTimeSlot = deliveryTimeSlot;
		$state.go('app.deliveryTimeSlotDetails');

	};*/

	/*var parseDeliverySlot = function(deliverySlot){
		if(!deliverySlot.hasOwnProperty('fromTime')) return;
		if(Date.parse(deliverySlot.fromTime) && Date.parse(deliverySlot.toTime)) return;
		var temp = deliverySlot.fromTime.split(':');
		deliverySlot.fromTime = new Date(new Date().setHours(temp[0],temp[1],temp[2]));
		var temp = deliverySlot.toTime.split(':');
		deliverySlot.toTime = new Date(new Date().setHours(temp[0],temp[1],temp[2]));
	}
*/
	/*$scope.deliveryTimeSlot=$localStorage.deliveryTimeSlot;*/



	/*$scope.editDeliveryTimeSlot = function(deliveryTimeSlot) {
		$localStorage.deliveryTimeSlot=deliveryTimeSlot;
//		$localStorage.deliveryTimeSlot.fromTime=$scope.convertToTime(deliveryTimeSlot.fromTime);
//		$localStorage.deliveryTimeSlot.toTime=$scope.convertToTime(deliveryTimeSlot.toTime);
		$state.go('app.addDeliveryTimeSlot');
	},*/
	//$scope.deliveryTimeSlot = JSON.parse(localStorage.getItem('deliveryTimeSlot'));

	
	/*$scope.addDeliveryTimeSlot = function(){
		$scope.deliveryTimeSlot = {};
		$localStorage.deliveryTimeSlot = {};
		$state.go('app.addDeliveryTimeSlot');

	}
   */
	/*$scope.cancelEdit = function(){
		$state.go('app.deliveryTimeSlot');

	}
	$scope.cancel = function(){
		$state.go('app.deliveryTimeSlot');
	}*/
	
    /*calendar initialization part*/
	$scope.selectedDate = null;
    $scope.firstDayOfWeek = 0;
    $scope.direction = "horizontal";
   
    $scope.holidayDates = [];
    $scope.holidayReasons = {};
    $scope.setDirection = function (direction) {
        $scope.direction = direction;
    };
    
    Date.prototype.withoutTime = function () {
        var d = new Date(this);
        d.setHours(0, 0, 0, 0, 0);
        return d
    }
    
    
    $scope.nextMonth = function(){
    	
    }
    $scope.prevMonth = function(){
    	
    }
    $scope.dayClick = function(){
    	
    }
    
    /*calendar initilization work complete*/
    $scope.getDeliveryTimeSlots();
}
]);


