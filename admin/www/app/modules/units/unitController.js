aviateAdmin.controller("unitController", ['$scope','$http','$localStorage','$location','$state','$rootScope','toastr','CONSTANT','UnitService','$mdDialog',
                                          function($scope,$http,$localStorage, $location,$state,$rootScope,toastr,CONSTANT,UnitService, $mdDialog) {

	/* $scope.query = {
			    limit: 5,
			    page: 1
			  };*/

	$scope.srch = true;
	$scope.uom = $localStorage.uom;
  
    $scope.getmeasurementunit = function() {

		var uom ={
		}
		UnitService.UnitList(uom).then(function(data) {
			$scope.uoms =[];
			$scope.uoms = data.uom;
			$scope.originalList = $scope.uoms;
			$scope.noOfRecords = $scope.uoms.length;
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
		var confirm = $mdDialog.confirm()
		.title('Would you like to delete Unit?')
		.ok('Delete')
		.cancel('Cancel');
		$mdDialog.show(confirm).then(function() {
			UnitService.deleteUnit(uom).then(function(data) {
				$scope.getmeasurementunit();
				toastr.success(CONSTANT.DELETEUNIT);
			});
		}, function() {

		});
	};



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