aviateAdmin.controller("discountController", ['$scope','$localStorage','$state','toastr','CONSTANT','$rootScope','CommonServices','StoreServices','DiscountService','$stateParams','ProductService',
                                              function($scope,$localStorage,$state,toastr,CONSTANT, $rootScope, CommonServices, StoreServices,DiscountService,$stateParams,ProductService) {

	$scope.srch = true;

	var myDate = new Date();

	var previousDay = new Date(myDate);

	previousDay.setDate(myDate.getDate()-1);
	$scope.addNew=false;
	$scope.lineEdit=true;
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
	$scope.getDiscountList = function(){
		if($rootScope.user.storeId){
			$scope.store ={};
			$scope.store.storeId = $rootScope.user.storeId;

			DiscountService.storeDiscountList($scope.store).then(function(data) {
				$scope.discountList = data.discountVos;
				$scope.originalList = $scope.discountList;
				$scope.noOfRecords = $scope.discountList.length;
			})

		}else{		

			$scope.merchant ={};
			$scope.merchant.merchantId = $rootScope.user.merchantId;

			DiscountService.merchantDiscountList($scope.merchant).then(function(data) {
				$scope.discountList = data.discountVos;
				$scope.originalList = $scope.discountList;
				$scope.noOfRecords = $scope.discountList.length;
			})
		}	
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
		$scope.discount.userId = $rootScope.user.userId;
		$scope.discount.storeList =[];

		if($rootScope.user.storeId){
			$scope.discount.storeList.push({"storeId":$rootScope.user.storeId});
		}else{
			for(var i=0;i<$scope.selection.length;i++){
				$scope.discount.storeList.push({"storeId":$scope.selection[i]});
			}
		}
		if($scope.discount.storeList.length!=0){
			DiscountService.saveDiscount($scope.discount).then(function(data) {
				$scope.results = data;
				$localStorage.discountList = data.discountVos;
				$state.go('app.productdiscount',{'discountId':data.discountVos[0].discountId});
			})}else{
				toastr.error("Select Any Store");
			}

	}

	$scope.deleteDiscount = function(discountId){
		$scope.discount = {};
		$scope.discount.discountId = discountId;
		DiscountService.deleteDiscount($scope.discount).then(function(data) {
			$scope.getDiscountList();
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
		if(!(_.isDate(discount.startTime))){
			if(discount.startTime !=null ){
				discount.startTime = timeFormat(discount.startTime);
				discount.endTime = timeFormat(discount.endTime);
			}
			$rootScope.discountEdit = discount;
			$state.go('app.editdiscount');
		}
	}
	$scope.discountEdit = $rootScope.discountEdit;

	var timeFormat = function(time){ 
		if(time){
			var hh = time.split(":");
			$scope.newTime = new Date(1970, 0, 1, hh[0], hh[1], 0);
			console.log($scope.startTime);
			return $scope.newTime;
		}
	}

	$scope.updateDiscount = function(discount){
		$scope.discount = discount;
		$scope.discount.userId = $rootScope.user.userId;
		DiscountService.saveDiscount($scope.discount).then(function(data) {
			$scope.results = data;
			$scope.getDiscountList();
			$state.go('app.discount');
		})

	}
	$scope.saveProductDiscount = function(discount){
		$scope.productId = discount.product.productId;
		$scope.newproductDiscount.merchant = {};
		$scope.newproductDiscount.discount = {};
		$scope.newproductDiscount.product = {};
		$scope.newproductDiscount.merchant.merchantId = $rootScope.user.merchantId;
		$scope.newproductDiscount.discount.discountId = $stateParams.discountId;
		$scope.newproductDiscount.product.productId = $scope.productId;
		$scope.newproductDiscount.userId = $rootScope.user.userId;
		if($localStorage.discountList==null){
			$scope.newproductDiscount.discountList = [];
			$scope.newproductDiscount.discountList.push($scope.newproductDiscount.discount);
		}else{
			$scope.newproductDiscount.discountList = $localStorage.discountList;
		}
		DiscountService.saveProductDiscount($scope.newproductDiscount).then(function(data) {
			$scope.results = data;
			$localStorage.discountList=null;
			$scope.getProductDiscount();
		})
	}

	$scope.offerProductRedirect = function(discount){
		$state.go('app.productdiscount',{'discountId':discount.discountId});
	}

	$scope.getProductDiscount = function(){
		$scope.productDiscount={};
		$scope.productDiscount.discount={};
		$scope.productDiscount.discount.discountId =  $stateParams.discountId;

		DiscountService.getProductDicountList($scope.productDiscount).then(function(data) {
			$scope.discountProductList = data.discountProductList;
		})

	}

	$scope.getAllProductList = function() {
		$scope.product = {};
		$scope.product.merchant = {
				"merchantId":$rootScope.user.merchantId
		}
		ProductService.getAllProductList($scope.product).then(function(data) {
			$scope.productList = data.products;
		})
	};


	$scope.deleteProductDiscount = function(discountProduct){
		$scope.discountProduct = {};
		$scope.discountProduct.discountProductId = discountProduct;
		DiscountService.deleteProductDiscount($scope.discountProduct).then(function(data) {
			$scope.getProductDiscount();
		})

	}

	$scope.updateProductDiscount = function(discountProduct){
		$scope.productDiscountLine={};
		$scope.productDiscountLine.product={};
		$scope.productDiscountLine.product.productId=discountProduct.product.productId;
		$scope.productDiscountLine.discountProductId = discountProduct.discountProductId;
		$scope.productDiscountLine.userId = $rootScope.user.userId;
		DiscountService.updateProductDiscount($scope.productDiscountLine).then(function(data) {
			$scope.getProductDiscount();
		})

	}

	$scope.validateTime = function(discount){
		if(discount.startTime!=null && discount.endTime!=null && 
				discount.startTime.getHours()==discount.endTime.getHours()){
			$scope.endTime = true;
			$scope.discountForm.$invalid=true;
		}else{
			$scope.endTime = false;
		}
	}
	
	$scope.filter12HrTime = function(time){
		var temp = time.split(':'),hours = temp[0],
		ampm = hours >= 12 ? 'PM' : 'AM';
		hours = hours % 12;
		temp.splice(2);
		temp[0] = hours ? hours : 12;
		return  temp.join(':') +" "+ampm;
	};
}
]);
