aviateAdmin.controller("unitController", ['$scope','$http','$localStorage','$location','$state','$rootScope','toastr','CONSTANT','UnitService',
                                              function($scope,$http,$localStorage, $location,$state,$rootScope,toastr,CONSTANT,UnitService) {

	  $scope.query = {
			    limit: 5,
			    page: 1
			  };
	  
		$scope.count = 3;
		$scope.srch = true;
		$scope.unit = $localStorage.unit;

		$scope.getmeasurementunit = function() {
			
			$localStorage.unit = {};
			var unit ={
					merchantId:$rootScope.user.merchantId
			}
			UnitService.UnitList(unit).then(function(data) {
				$scope.measureType =[];
				$scope.measureType = data.units;
			});
		};
	
		$scope.updateUnit = function(unit) {
			UnitService.UpdateUnit(unit).then(function(data) {
				toastr.success(CONSTANT.UPDATEUNIT);
				$state.go('app.units');
			});
		};		
		
		$scope.saveUnit = function(unit) {
			unit.merchantId = $rootScope.user.merchantId
			UnitService.saveUnit(unit).then(function(data) {
				toastr.success(CONSTANT.ADDUNIT);
				$state.go('app.units');
			});
		};
		
		$scope.deleteUnit = function(unit) {
				UnitService.deleteUnit(unit).then(function(data) {
					$scope.getmeasurementunit();
				toastr.success(CONSTANT.DELETEUNIT);
			});
		}		
			
		
	$scope.unitDetails = function(unit){
		$localStorage.unit = unit;
		$state.go('app.detailsUnit');
		
	},
	
	$scope.editUnit = function(unit) {
		$localStorage.unit = unit;
		$scope.unit = $localStorage.unit;
		$state.go('app.createUnit');
	},
	
	$scope.addUnit = function(){
		$scope.unit = {};
		$localStorage.unit = {};
		$state.go('app.createUnit');
		
	}
	$scope.cancelEdit = function(){
		$state.go('app.units');
		
	}
	$scope.cancel = function(){
		$state.go('app.units');
	}
}
]);