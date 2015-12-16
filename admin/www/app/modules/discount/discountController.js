aviateAdmin.controller("discountController", ['$scope','$localStorage','$state','toastr','CONSTANT','$rootScope','CommonServices','StoreServices','DiscountService',
                                              function($scope,$localStorage,$state,toastr,CONSTANT, $rootScope, CommonServices, StoreServices,DiscountService) {
	
	
	$scope.getDiscountByMerchant = function(){
		
		$scope.merchant ={};
		$scope.merchant.merchantId = $rootScope.user.merchantId;
		
		DiscountService.merchantDiscountList($scope.merchant).then(function(data) {
			$scope.discountList = data;
		})
		
		
	}
}
]);