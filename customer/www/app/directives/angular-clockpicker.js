
angular.module('aviate.directives')
.directive('clockpicker', function() {
	return {
		restrict: "EA",
		replace: true,
		templateUrl: "app/modules/checkout/clockpicker.html",
		scope: {
			datetime: "=ngModel"
		},
		controller: function ($scope,$rootScope,$mdDialog,toastr,$localStorage,AppServices) {
			$rootScope.hidenext=true;
			$rootScope.textDesign=false;
			$scope.hourOptions = [12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];
			$scope.minuteOptions = ['00', '05', '10', '15', '20', '25', '30', '35', '40', '45', '50', '55'];
			$scope.periodOptions = ['am', 'pm'];
			$scope.selectionMode = true;

			var timeMatches = /(\d{2}):(\d{2}):(\d{2})/.exec($scope.datetime.toString());
			
			var temp1 = timeMatches[0].split(':'),hours = temp1[0],
			ampm = hours >= 12 ? 'pm' : 'am';
			hours = hours % 12;
			temp1.splice(2);
			temp1[0] = hours ? hours : 12;
			$scope.period=ampm;
			
			$scope.hour = timeMatches[1];
			$scope.minute = timeMatches[2];

			if ($scope.hour > 12) {
				$scope.hour = parseInt($scope.hour - 12);
				$scope.period = "pm";
			}

			var toggleOnSelection = false;
			var currentIndex = function () {
				if ($scope.selectionMode) {
					for (var i = 0; i < $scope.hourOptions.length; i++) {
						if ($scope.hourOptions[i] == $scope.hour) return i;
					}
				}
				else {
					for (var j = 0; j < $scope.hourOptions.length; j++) {
						if ($scope.minuteOptions[j] == $scope.minute) return j;
					}
				}
			};
			$scope.selectValue = function (value) {
				$rootScope.timeSelected = true;
				$scope.selectionMode ? $scope.hour = value : $scope.minute = value;
				if (toggleOnSelection) {
					$scope.selectionMode = !$scope.selectionMode;
				}
			};
			$scope.selectPeriod = function (value) {
				$scope.period = value;
			};
			$scope.togglePeriod = function () {
				$scope.selectPeriod($scope.period == "am" ? "pm" : "am");
			};
			$scope.lineStyle = function () {
				var angle = "rotate(" + (currentIndex() * 30 - 180) + "deg)";
				return "transform: " + angle + "; -webkit-transform: " + angle;
			};
			$scope.$watch("selectionMode", function (value) {
				$scope.options = value ? $scope.hourOptions : $scope.minuteOptions;
			});
			$scope.$watch("hour + period", function () {
				if(angular.isString($scope.hour)){
					if($scope.hour != 12){
						$scope.datetime.setHours($scope.period == "pm" ? parseInt($scope.hour) + 12 : $scope.hour);	
					}else{
						$scope.datetime.setHours($scope.period == "am" ? parseInt($scope.hour) + 12 : $scope.hour);
					}
					
				}else{
					if($scope.hour != 12){
						$scope.datetime.setHours($scope.period == "pm" ? $scope.hour + 12 : $scope.hour);	
					}else{
						$scope.datetime.setHours($scope.period == "am" ? $scope.hour + 12 : $scope.hour);
					}
				}
				$rootScope.totalHours=$scope.datetime.getTime();
				$rootScope.hour = $scope.datetime.getHours();
				$rootScope.period = $scope.period;
				
				/*------------------------------------*/
				$rootScope.deliveryTimeValidation();
				/*--------------------------------------*/
			});
			$scope.$watch("minute", function (value) {
				$scope.datetime.setMinutes(value);
				$rootScope.minute = value;
				
				
				/*Checking For minutes*/
				$rootScope.deliveryTimeValidation();
				/*End*/
			});
			$rootScope.totalHours=$scope.datetime.getTime();
			$rootScope.hour = $scope.datetime.getHours();
			$rootScope.minute = $scope.minute;
			$rootScope.period = $scope.period;
			
			
			//Manual Method
			 $scope.filter12HrTime = function(time){
			        var temp = time.split(':'),hours = temp[0],
			            ampm = hours >= 12 ? 'PM' : 'AM';
			        hours = hours % 12;
			        temp.splice(2);
			        temp[0] = hours ? hours : 12;
			        return  temp.join(':') +" "+ampm;
			    };
			    
			    $rootScope.deliveryTimeValidation=function(){
			    	 $scope.isHoliday = false;
			    	
			    	 var storeHoliday = localStorage.getItem("holidays");
			    	 storeHoliday = storeHoliday.split(",");
			    	 
			    	 var holiday = AppServices.covertStringToDate(storeHoliday);
			    	 
			    	 var selectedDate = new Date($rootScope.delivery.date);
			    	 selectedDate.setHours(0, 0, 0, 0, 0);
			    	
			    	 holiday.forEach(function(date1){
							if(date1.getTime() == selectedDate.getTime()){
								$scope.isHoliday = true; 
								$rootScope.hidenext = true;
								$rootScope.textDesign = false;
								toastr.error("Selected Date is Holiday");
								return;
							}
					 })
					 
					 if(!$scope.isHoliday){
					 selectedDate.setHours($rootScope.hour);
			         selectedDate.setMinutes($rootScope.minute);
		             var selectedDateValue=selectedDate.getTime();
		             
		             var fromDate=new Date(($rootScope.delivery.date).toString());
		                fromDate.setHours($rootScope.deliveryTime.fromTime.substring(0, 2));
		                fromDate.setMinutes($rootScope.deliveryTime.fromTime.substring(3, 5));
		                var fromDateValue=fromDate.getTime();
		                
		                var toDate=new Date(($rootScope.delivery.date).toString());
		                toDate.setHours($rootScope.deliveryTime.toTime.substring(0, 2));
		                toDate.setMinutes($rootScope.deliveryTime.toTime.substring(3, 5));
		                var toDateValue=toDate.getTime();
		                
		                var currentdate = new Date();
		                var d=currentdate.toString().substring(16,24);
		                
		                if(selectedDateValue>=fromDate && selectedDateValue<=toDate){
		                	if(currentdate.getTime()<=selectedDateValue){
		                		currentdate.setMinutes(currentdate.getMinutes()+60);
		                		if(currentdate.getTime()<=selectedDateValue){
		                			var check = $scope.filter12HrTime(selectedDate.toString().substring(16,24));
		                			$rootScope.expectedTime=check;
		                			$rootScope.hidenext=false;
		                			$rootScope.textDesign=true;
		                		}
		                		else{
		                			var check= $scope.filter12HrTime(currentdate.toString().substring(16,24));
		                			$rootScope.expectedTime=check;
		                			$rootScope.hidenext=false;
		                			$rootScope.textDesign=true;
		                		}
		                	}
		                	else{
		                		if(currentdate.getTime()>=toDateValue){
			                		$rootScope.hidenext=true;
			                		$rootScope.textDesign=false;
			                    	/*$mdDialog.show(
			        						$mdDialog.alert()
			        						.parent(angular.element(document.querySelector('#popupContainer')))
			        						.clickOutsideToClose(true)
			        						//.title('Alert')
			        						.content('Please choose Future delivery time '+$scope.filter12HrTime($rootScope.deliveryTime.fromTime)+' to '+$scope.filter12HrTime($rootScope.deliveryTime.toTime)+'.')
			        						.ariaLabel('Alert Dialog Demo')
			        						.ok('Ok')
			        						.targetEvent()
			        				);*/
			                		toastr.error("Please choose Future delivery time "+$scope.filter12HrTime($rootScope.deliveryTime.fromTime)+" to "+$scope.filter12HrTime($rootScope.deliveryTime.toTime)+".");
		                		}
		                		else{
			                		$rootScope.hidenext=true;
			                		$rootScope.textDesign=false;
			                    	/*$mdDialog.show(
			        						$mdDialog.alert()
			        						.parent(angular.element(document.querySelector('#popupContainer')))
			        						.clickOutsideToClose(true)
			        						//.title('Alert')
			        						.content('Please choose future delivery time '+$scope.filter12HrTime(d)+' to '+$scope.filter12HrTime($rootScope.deliveryTime.toTime)+'.')
			        						.ariaLabel('Alert Dialog Demo')
			        						.ok('Ok')
			        						.targetEvent()
			        				);*/
			                		toastr.error("Please choose Future delivery time "+$scope.filter12HrTime(d)+" to "+$scope.filter12HrTime($rootScope.deliveryTime.toTime)+".");
		                		}
		                	}
		                	
		                }
		                else{
		                	$rootScope.hidenext=true;
		                	$rootScope.textDesign=false;
		                	/*$mdDialog.show(
		    						$mdDialog.alert()
		    						.parent(angular.element(document.querySelector('#popupContainer')))
		    						.clickOutsideToClose(true)
		    						//.title('Alert')
		    						.content('Please choose delivery time '+$scope.filter12HrTime($rootScope.deliveryTime.fromTime)+' to '+$scope.filter12HrTime($rootScope.deliveryTime.toTime)+'.')
		    						.ariaLabel('Alert Dialog Demo')
		    						.ok('Ok')
		    						.targetEvent()
		    				);*/
		                	toastr.error("Please choose Valid delivery time "+$scope.filter12HrTime($rootScope.deliveryTime.fromTime)+" to "+$scope.filter12HrTime($rootScope.deliveryTime.toTime)+".");
		                }
					 }
			    };
		}
	};
})
/*.run(["$templateCache", function ($templateCache) {
	$templateCache.put("template/clockpicker.html",
		"\n" +
		"<div class='ui-clockpicker'>\n" +
		"  <div class='ui-clockpicker-selection'>\n" +
		"    <a ng-click='selectionMode = true' ng-class='{selected: selectionMode}'>{{hour}}</a>:" +
		"<a ng-click='selectionMode = false' ng-class='{selected: !selectionMode}'>{{minute}}</a> " +
		"<a ng-click='togglePeriod()'>{{period}}</a>\n" +
		"  </div>\n" +
		"  <div class='ui-clockpicker-selector' ng-class='{minute: !selectionMode}'>\n" +
		"     <div class='ui-clockpicker-line' style='{{lineStyle()}}'></div>" +
		"     <ol class='ui-clockpicker-time'>\n" +
		"       <li ng-repeat='option in options' " +
		"         ng-class='{selected: selectionMode ? hour == option : minute == option }' " +
		"         ng-click='selectValue(option)'>{{option}}</li>\n" +
		"     </ol>\n" +
		"     <ol class='ui-clockpicker-period'>\n" +
		"       <li ng-repeat='periodOption in periodOptions' " +
		"         ng-class='{selected: period == periodOption }' " +
		"         ng-click='selectPeriod(periodOption)'>{{periodOption}}</li>\n" +
		"     </ol>\n" +
		"  </div>\n" +
		"</div>\n" +
		"");
}]);*/