angular.module('aviateAdmin.controllers')
.controller("orderNumberController",['$scope', '$rootScope','$state','$filter','$mdDialog','ngTableParams','OrderNumberServices','CONSTANT','toastr',
                                     function($scope, $rootScope ,$state, $filter,$mdDialog,ngTableParams,OrderNumberServices,CONSTANT,toastr) {


	/* $scope.query = {
					    limit: 5,
					    page: 1
					  };*/

	$scope.getOrderNumber = function(){
		$scope.store = {};
		$scope.store.storeId = $rootScope.user.storeId;
		/*$scope.store.storeId =2;*/
		OrderNumberServices.getOrderNumber($scope.store).then(function(data){
			$scope.orderNumber=data;
		});
	};

	$scope.redirectToEditOrderNumber = function(orderNumber){
		OrderNumberServices.setOrderNumberObj(orderNumber);
		$state.go('app.editordernumber');
	}

	$scope.deleteOrderNumber= function(orderNumber) {

		var confirm = $mdDialog.confirm()
		.title('Would you like to delete Order Number?')
		.ok('Delete')
		.cancel('Cancel');
		$mdDialog.show(confirm).then(function() {
			OrderNumberServices.deleteOrderNumber(orderNumber).then(function(data){
				$state.go('app.ordernumber');
				$scope.getOrderNumber();
			});

		}, function() {

		});				};

		$scope.redirectToCreate = function(){
			
			if($scope.orderNumber){
				toastr.error(CONSTANT.ORDERNUMBERERROR);
			}else{
				$state.go('app.newordernumber')
			}
		}

}]);
