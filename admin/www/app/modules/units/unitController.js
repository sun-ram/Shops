aviateAdmin.controller("unitController", ['$scope','$http','$localStorage','$location','$state','$rootScope','toastr','CONSTANT','UnitService',
                                              function($scope,$http,$localStorage, $location,$state,$rootScope,toastr,CONSTANT,UnitService) {

	 /* $scope.query = {
			    limit: 5,
			    page: 1
			  };*/
	  
		$scope.count = 3;
		$scope.srch = true;
		$scope.uom = $localStorage.uom;

		$scope.getmeasurementunit = function() {
			
			var uom ={
			}
			UnitService.UnitList(uom).then(function(data) {
				$scope.uoms =[];
				$scope.uoms = data.uom;
			});
		};
	
		$scope.updateUnit = function(uom) {
			UnitService.UpdateUnit(uom).then(function(data) {
				toastr.success(CONSTANT.UPDATEUNIT);
				$state.go('app.units');
			});
		};		
		
		$scope.saveUnit = function(uom) {
			//unit.merchantId = $rootScope.user.merchantId
			UnitService.saveUnit(uom).then(function(data) {
				toastr.success(CONSTANT.ADDUNIT);
				$state.go('app.units');
			});
		};
		
		$scope.deleteUnit = function(uom) {
				UnitService.deleteUnit(uom).then(function(data) {
					$scope.getmeasurementunit();
				toastr.success(CONSTANT.DELETEUNIT);
			});
		}		
			
		
	$scope.unitDetails = function(uom){
		$localStorage.uom = uom;
		$state.go('app.detailsUnit');
		
	},
	
	$scope.editUnit = function(uom) {
		$localStorage.uom = uom;
		$scope.uom = $localStorage.uom;
		$state.go('app.createUnit');
	},
	
	$scope.addUnit = function(){
		$scope.uom = {};
		$localStorage.uom = {};
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