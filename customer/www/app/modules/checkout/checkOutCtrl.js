angular.module('aviate.controllers')
.controller("checkOutCtrl", ['$scope', '$state', 'toastr', 'CONSTANT', 'CheckOutServices', '$mdDialog', '$rootScope','MyCartFactory','$filter','CommonServices','$localStorage',
                             function($scope, $state, toastr, CONSTANT, CheckOutServices, $mdDialog, $rootScope, MyCartFactory,$filter,CommonServices,$localStorage) {

	MyCartFactory.myCartTotalPriceCalculation();

	$scope.addresses = [];
	$scope.delivery = {
			date: new Date()
	};
	$scope.currentOrder = CheckOutServices.currentOrder;
	$scope.timeLineStatus = CheckOutServices.timeLineStatus;
	
	$scope.selectAddress = function(addressId) {
		var seletecdAddress = _.filter($scope.addresses, function(add) {
			return add.selected == true;
		})
		_.each(seletecdAddress, function(add) {
			add.selected = false;
		});
		var address = _.findWhere($scope.addresses, {
			addressId: addressId
		});
		address.selected = true;
		$scope.currentOrder.address = address;
	};
	
	$scope.openAddressDialog = function(event) {
		$mdDialog.show({
			controller: addressDialogController,
			templateUrl: "app/modules/checkout/addAddress.html",
			clickOutsideToClose: true,
			targetEvent: event,
			locals: {
				address: undefined
			}
		});
	};

	$scope.editAddress = function(address, event) {
		$mdDialog.show({
			controller: addressDialogController,
			templateUrl: "app/modules/checkout/addAddress.html",
			clickOutsideToClose: true,
			targetEvent: event,
			locals: {
				address: address
			}
		});
	};

	var addAddress = function(address) {
		delete address.selected;
		//address.addressId = "";
		console.log("data", address);
		CheckOutServices.addAddress(address).then(function(data) {
			console.log("data", data);
			$scope.getAddressList({
				"customerId": $rootScope.user.userId
			});
		});


	};

	$scope.getAddressList = function(address) {

		CheckOutServices.getAddressList(address).then(function(data) {
			console.log("data.addressList", data.addressList);
			$scope.addresses = data.addressList;
		});


	};
	

	$scope.getTimeSlot = function(){
		CheckOutServices.getTimeSlot({'merchant':{'merchantId':$rootScope.store.merchant.merchantId}}).then(function(data) {
			$scope.deliveryTimeSlots = data;
			$scope.deliveryTime = $scope.deliveryTimeSlots[0];
			$scope.deliveryTime.index = 0;
		});
	}
	
	$scope.data = {
		      group1 : 'Cash on delivery',
		      group2 : 'Credit / Debit / Netbanking'
		    };
	
	$scope.confirmOrder = function() {
		var menuJson = {
				"customer":{
					"customerId":$rootScope.user.userId
					},
				"address":{
					"addressId":$scope.currentOrder.address.addressId
				},
				"store":{
					"storeId":$rootScope.store.storeId
				},
				"deliveryDate":$scope.currentOrder.delivery.date,
				"deliveryTimeSlot":$scope.currentOrder.delivery.fromTime +" "+ $scope.currentOrder.delivery.toTime
		}
		
		if($scope.data.group1=="Cash on delivery"){
			menuJson.paymentMethod="COD";
			menuJson.status="Initalized";
		}
		
		CheckOutServices.confirmOrder(menuJson).then(function(data) {
			console.log("OrderNo", data);
			$scope.orderNo = data.orderNo;
			$scope.salesOrderId = data.salesOrderId;
			if($scope.data.group1=="Cash on delivery"){
				$state.go('app.favourite',{'salesOrderId':$scope.salesOrderId});
				//$rootScope.myCart.cartItem = {};
			}else{
				$scope.payment();
			}
		});
	};

	$scope.cancel1=function(){
		//$rootScope.change=false;
		$state.go('app.home');
	}
	$scope.back=function()
	{
		//$rootScope.change=true;
		$state.go('app.cart');
	}
	
	$scope.payment = function() {
		var paymentJSON ={
				"amount":$rootScope.myCart.grossAmount
		};
		CheckOutServices.payment(paymentJSON).then(function(data) {
			$scope.timeStamp = data.timestamp;
			$scope.hash = data.hashMessage;
			$scope.TOTAL = $rootScope.myCart.grossAmount;
			$scope.shipping = ($rootScope.myCart.cartTotalAmount * .05);
			setTimeout(function() {
				$scope.$apply(); 
				document.forms["FirstData"].submit();
			}, 25);
			$rootScope.myCart.cartItem = {};
		});
	};

	var removeAddress = function(address) {
	
		CheckOutServices.removeAddress({"addressId":address.addressId}).then(function(data) {
			$scope.getAddressList({
				"customerId": $rootScope.user.userId
			});
		});


	};

	$scope.removeAddress = function(address) {
	
		var confirm = $mdDialog.confirm()
        .title('Would you like to delete Address?')
		        .ok('Delete')
		        .cancel('Cancel');
		  $mdDialog.show(confirm).then(function() {
	
			  CheckOutServices.removeAddress({"addressId":address.addressId}).then(function(data) {
					$scope.getAddressList({
						"customerId": $rootScope.user.userId
					});
				});

  }, function() {
			  
			  });		
		

	};

	function addressDialogController($scope, address) {
		$scope.address = _.clone(address) ;
		$scope.delete = false;
		if (address) {
			$scope.AddUpdate = "update";
			$scope.editName = true;
			$scope.delete = true;

		} else {
			$scope.editName = false;
			$scope.AddUpdate = "add";
		}
		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.addAddress = function(address) {
			$scope.address.country = {};
			$scope.address.state = {};
			$scope.cnt = JSON.parse($scope.country);
			$scope.address.country.countryId = $scope.cnt.countryId;
			$scope.address.country.name = $scope.cnt.name;
			$scope.sta = JSON.parse($scope.state);
			$scope.address.state.stateId = $scope.sta.stateId;
			$scope.address.state.name = $scope.sta.name;
			address.customer.customerId=$rootScope.user.userId;
			addAddress(address);
			$mdDialog.cancel();
		};

		$scope.removeAddress = function(address) {
			removeAddress(address);
			$mdDialog.cancel();
		};
		
		$scope.getState = function(country){
			$scope.cunt = JSON.parse(country);
			$scope.states = $scope.cunt.states;
		};
		
		$scope.getCountries = function(){
			if($localStorage.countries){
				$scope.countries=$localStorage.countries;
			}else{
				CommonServices.getCountries($scope.country).then(function(data){
					$scope.countries=data;
					$localStorage.countries = data;
				});
			}
		};
		
		$scope.getCountries();
		
	};

	//restoring checkout template based on timeline status 
	if($scope.timeLineStatus.deliveryDate){
		$scope.merchangetTemplate = "app/modules/checkout/verifyOrderDetails.html";
	}else if($scope.timeLineStatus.addressEntry){
		$scope.merchangetTemplate = "app/modules/checkout/deliverySchedule.html";
	}else{
		$scope.merchangetTemplate = "app/modules/checkout/address.html";
	}

	$scope.goNext = function(id) {
		switch (id) {
		case "address":
			if(!($scope.addresses)){
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.querySelector('#popupContainer')))
						.clickOutsideToClose(true)
						//.title('Alert')
						.content('Please Add delivery address')
						.ariaLabel('Alert Dialog Demo')
						.ok('Ok')
						.targetEvent()
				);
			}
			if($scope.addresses.length ==1){
				$scope.currentOrder.address = $scope.addresses[0];
			}
			if ($scope.currentOrder.address!= null) {
				$scope.timeLineStatus.addressEntry = true;
				$scope.merchangetTemplate = "app/modules/checkout/deliverySchedule.html";
			} else {
				if($scope.currentOrder.address == null)
					{
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.querySelector('#popupContainer')))
						.clickOutsideToClose(true)
						//.title('Alert')
						.content('Please choose delivery address')
						.ariaLabel('Alert Dialog Demo')
						.ok('Ok')
						.targetEvent()
				);
					}
				else{
					
					if($scope.currentOrder.contactNumber == null && $scope.currentOrder.address != null){
	
			$mdDialog.show(
					$mdDialog.alert()
					.parent(angular.element(document.querySelector('#popupContainer')))
					.clickOutsideToClose(true)
					//.title('Alert')
					.content('Please choose Contact Number')
					.ariaLabel('Alert Dialog Demo')
					.ok('Ok')
					.targetEvent()
			);
				}
										else
												{
												$mdDialog.show(
														$mdDialog.alert()
														.parent(angular.element(document.querySelector('#popupContainer')))
														.clickOutsideToClose(true)
														//.title('Alert')
														.content('Please choose Contact Number/address')
														.ariaLabel('Alert Dialog Demo')
														.ok('Ok')
														.targetEvent()
												);
												}
					}
			}
			break;
		case "deliverySchedule":
			//if ($scope.delivery.time && $scope.delivery.date) {
				$scope.currentOrder.delivery.fromTime = $scope.deliveryTime.fromTime;
				$scope.currentOrder.delivery.toTime = $scope.deliveryTime.toTime;
				$scope.currentOrder.delivery.date = $filter('date')($scope.delivery.date,'MM/dd/yyyy');//new Date($scope.delivery.date);				$scope.currentOrder.items = $rootScope.myCard;
 				$scope.currentOrder.items = $rootScope.myCard;
				$scope.timeLineStatus.deliveryDate = true;
				$scope.merchangetTemplate = "app/modules/checkout/verifyOrderDetails.html";
			/*} else {
				if($scope.delivery.date == null)
					{
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.querySelector('#popupContainer')))
						.clickOutsideToClose(true)
						//.title('Alert')
						.content('Please choose delivery date.')
						.ariaLabel('Alert Dialog Demo')
						.ok('Ok')
						.targetEvent()
				);
					}
				else
					{
					$mdDialog.show(
							$mdDialog.alert()
							.parent(angular.element(document.querySelector('#popupContainer')))
							.clickOutsideToClose(true)
							//.title('Alert')
							.content('Please choose delivery time.')
							.ariaLabel('Alert Dialog Demo')
							.ok('Ok')
							.targetEvent()
					);

					}
			}*/
			break;
		case "verification":
			$scope.timeLineStatus.verification = true;
			break;
		default:
			console.log("no option found");

		}
	};

	$scope.goPrevious = function(state,mode){
		switch(state){
		case 'address':
			$scope.merchangetTemplate = "app/modules/checkout/address.html";
			if(mode == 'edit'){
				$scope.editAddress($scope.currentOrder.address);
			}
			break;
		case 'deliverySchedule':
			$scope.merchangetTemplate = "app/modules/checkout/deliverySchedule.html";
		}
	}
	
	$scope.getAddressList({
		"customerId": $rootScope.user.userId
	});
	
	$scope.myDate = new Date();
    $scope.minDate = new Date(
        $scope.myDate.getFullYear(),
        $scope.myDate.getMonth(),
        $scope.myDate.getDate()
    );
    
    //displaying current date in delivery section
    $scope.choosedDate = $filter('date')(new Date(),'EEE,MMM,d,yyyy').split(',');
    console.log('formatter choosed date is ',$scope.choosedDate);
    $scope.formateSelectedDate = function(date){
    	$scope.choosedDate = $filter('date')(date,'EEE,MMM,d,yyyy').split(',');
    };
    
	console.log("card items",$rootScope.myCard);
	
	$scope.decrementTime = function(index){
		if(index < 0){
			return;
		}else{
			$scope.deliveryTime = $scope.deliveryTimeSlots[index];
		}
	};
	
	$scope.incrementTime = function(index){
		if($scope.deliveryTimeSlots.length == index){
			return;
		}else{
			$scope.deliveryTime = $scope.deliveryTimeSlots[index];
			$scope.deliveryTime.index = index;
		}
	};
}
]);