aviateAdmin.controller("discountController", ['$scope','$localStorage','$state','toastr','CONSTANT','$rootScope','CommonServices','StoreServices','DiscountService',
                                              function($scope,$localStorage,$state,toastr,CONSTANT, $rootScope, CommonServices, StoreServices,DiscountService) {
	
	
	
	$scope.count = 3;
	$scope.srch = true;
	
	var myDate = new Date();

	var previousDay = new Date(myDate);

	previousDay.setDate(myDate.getDate()-1);
	
	$scope.minDate = previousDay;
	
	  $scope.selection=[];
	  // toggle selection for a given employee by name
	  $scope.toggleSelection = function toggleSelection(storeId) {
	     var idx = $scope.selection.indexOf(storeId);
	     // is currently selected
	     if (idx > -1) {
	       $scope.selection.splice(idx, 1);
	     }else {
	         $scope.selection.push(storeId);
	       }

	  }  
	$scope.getDiscountByMerchant = function(){
		
		$scope.merchant ={};
		$scope.merchant.merchantId = $rootScope.user.merchantId;
		
		DiscountService.merchantDiscountList($scope.merchant).then(function(data) {
			$scope.discountList = data.discountVos;
		})
			
	}
	
	
	$scope.addDiscount = function(){
		$scope.discount ={};
		$state.go('app.adddiscount');
	}
	
	$scope.getStores = function(){
		$scope.merchant ={};
		$scope.merchant.merchantId = $rootScope.user.merchantId;
		
		StoreServices.getStore($scope.merchant).then(function(data) {
			$scope.storeList = data;
		})
	}
	 $scope.saveDiscount = function(discount){
		 
		 	$scope.discount = discount;
			$scope.discount.merchant ={};
			$scope.discount.merchant.merchantId = $rootScope.user.merchantId;
			$scope.discount.storeList =[];
			for(var i=0;i<$scope.selection.length;i++){
				$scope.discount.storeList.push({"storeId":$scope.selection[i]});
				}
			DiscountService.saveDiscount($scope.discount).then(function(data) {
				$scope.results = data;
				$scope.getDiscountByMerchant();
				$state.go('app.discount');
			})

	 }
	 
	 $scope.deleteDiscount = function(discountId){
		 $scope.discount = {};
		 $scope.discount.discountId = discountId;
			DiscountService.deleteDiscount($scope.discount).then(function(data) {
				$scope.getDiscountByMerchant();
				$state.go('app.discount');
			})
		 
	 }
	 
	 $scope.discountDetails =function(discount){
		 $rootScope.discountViews = discount;
		 $state.go('app.detaildiscount');
	 }
	 $scope.discountView = $rootScope.discountViews;
	 
		$scope.close = function () {
			$state.go('app.discount');
		};
		
		$scope.editDiscount = function(discount){
			 $rootScope.discountEdit = discount;
			$state.go('app.editdiscount');
		}
		$scope.discountEdit = $rootScope.discountEdit;
		
	$scope.updateDiscount = function(discount){
		
	 	$scope.discount = discount;
		DiscountService.saveDiscount($scope.discount).then(function(data) {
			$scope.results = data;
			$scope.getDiscountByMerchant();
			$state.go('app.discount');
		})
		
	}	
}
]);