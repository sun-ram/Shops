angular.module('aviate.controllers')
.controller("checkOutCtrl", ['$scope', '$state', 'toastr', 'CONSTANT', 'CheckOutServices', '$mdDialog', '$rootScope','MyCartFactory','$filter','CommonServices','$localStorage',
                             function($scope, $state, toastr, CONSTANT, CheckOutServices, $mdDialog, $rootScope, MyCartFactory,$filter,CommonServices,$localStorage) {

	MyCartFactory.myCartTotalPriceCalculation();

	$scope.rupeesSymbol = CONSTANT.RUPEESSYMBOL;

	$scope.time = new Date();
	$scope.time.setMinutes($scope.time.getMinutes()+60);
	$scope.addresses = [];
	$scope.delivery = {
			date: new Date()
	};
	$rootScope.delivery = {
			date: new Date()
	};
	$scope.currentOrder = CheckOutServices.currentOrder;
	$scope.timeLineStatus = CheckOutServices.timeLineStatus;

	$scope.selectAddress = function(addressId) {
		var seletecdAddress = _.filter($scope.addresses, function(add) {
			return add.selected == true;
		});
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
		CheckOutServices.getTimeSlot({'merchant':{'merchantId':$rootScope.store.merchant.merchantId},'storeId':$rootScope.store.storeId}).then(function(data) {
			$scope.deliveryTimeSlots = data;
			$scope.deliveryTime = $scope.deliveryTimeSlots[0];
			$rootScope.deliveryTime = $scope.deliveryTimeSlots[0];
			$scope.deliveryTime.index = 0;
		});
	};

	$scope.transactions = [{
		'key':'COD',
		'name':'Cash on delivery'
	},
	{
		'key':'WT',
		'name':'Credit / Debit / Netbanking'
	}];
	$scope.transactionType = "COD";

	$scope.confirmOrder = function(ty) {
		$scope.orderBtn = true;
		//$scope.$apply();
		$scope.transactionType = ty;
		$scope.orderBtn = true;
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
				"deliveryTimeSlot":$scope.currentOrder.delivery.fromTime,
				"userId":$rootScope.user.userId
		};

		if($scope.transactionType == "COD"){
			menuJson.paymentMethod = "COD";
			menuJson.status = "Initalized";
		}else if($scope.transactionType == "WT"){
			menuJson.paymentMethod = "WT";
			menuJson.status = "Initalized";
		}
			CheckOutServices.confirmOrder(menuJson).then(function(data) {
				$scope.orderBtn = false;
				console.log("OrderNo", data);
				$scope.orderNo = data.orderNo;
				$scope.salesOrderId = data.salesOrderId;
				if(data.paymentMethod == "COD"){
					$scope.getCartList();
					$state.go('app.favourite',{'salesOrderId':$scope.salesOrderId});
					//$rootScope.myCart.cartItem = {};
				}else if(data.paymentMethod == "WT") {
					$scope.transactionDetails = data.transactionDatas;
					$scope.transactionDetails.amount = data.transactionDatas.amount.toFixed(2);
					setTimeout(function() {
						$scope.$apply(); 
						document.forms["frmTransaction"].submit();
					}, 25);
				}
			});
		};
	

	$scope.cancel1=function() {
		//$rootScope.change=false;
		$state.go('app.home');
	};
	$scope.back=function() {
		//$rootScope.change=true;
		$state.go('app.cart');
	};

	/*$scope.payment = function() {
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
	};*/

	var removeAddress = function(address) {

		CheckOutServices.removeAddress({"addressId":address.addressId}).then(function(data) {
			$scope.getAddressList({
				"customerId": $rootScope.user.userId
			});
		});


	};

	$scope.removeAddress = function(address) {

		/*var confirm = $mdDialog.confirm()
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

			  })*/		

		$mdDialog.show({
			controller: function($scope){
				$scope.removeAddress = function(){
					$mdDialog.cancel();
					removeAddress(address);
				}; 

				$scope.close = function(){
					$mdDialog.cancel();
				}; 
			},
			templateUrl: 'app/modules/checkout/deleteAddressPrompt.html',
			parent: angular.element(document.body),
			clickOutsideToClose:true,
		})
		.then(function(answer) {
			$scope.status = 'You said the information was "' + answer + '".';
		}, function() {
			$scope.status = 'You cancelled the dialog.';
		}); 


	};

	function addressDialogController($scope, address,$timeout) {
		$scope.address = _.clone(address) ;
		$scope.delete = false;
		if (address) {
			$scope.AddUpdate = "update";
			$scope.editName = true;
			$scope.delete = true;
			if($scope.countries)
				$scope.getAddress();
			else{
				$timeout(function(){
					$scope.getAddress();
				},3000);
			}

			$scope.getAddress = function(){
				$scope.country = $scope.address.country;
				$scope.states = _.findWhere($scope.countries,{countryId:$scope.country.countryId}).states;
				$scope.state = $scope.address.state;
				$scope.cities = _.findWhere($scope.states,{stateId:$scope.state.stateId}).city;
				$scope.cty = $scope.address.city;

			};

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
			$scope.address.city={};
			$scope.address.country.countryId = $scope.country.countryId;
			$scope.address.country.name = $scope.country.name;
			$scope.address.state.stateId = $scope.state.stateId;
			$scope.address.state.name = $scope.state.name;
			$scope.address.city.cityId=$scope.cty.cityId;
			$scope.address.city.name=$scope.cty.name;
			address.customer={};
			address.customer.customerId=$rootScope.user.userId;
			addAddress(address);
			$mdDialog.cancel();
		};

		$scope.removeAddress = function(address) {
			removeAddress(address);
			$mdDialog.cancel();
		};

		$scope.getState = function(country){
			$scope.states = country.states;
		};

		$scope.getCity = function(states){
			$scope.cities = states.city;
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

	}

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
			if ($scope.currentOrder.address!== null) {
				$scope.timeLineStatus.addressEntry = true;
				$scope.merchangetTemplate = "app/modules/checkout/deliverySchedule.html";
			} else {
				if($scope.currentOrder.address === null)
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

					if($scope.currentOrder.contactNumber === null && $scope.currentOrder.address !== null){

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
			$scope.currentOrder.delivery.fromTime =$rootScope.expectedTime;
			$scope.currentOrder.delivery.toTime = $scope.deliveryTime.toTime;
			$scope.currentOrder.delivery.date = $filter('date')($scope.delivery.date,'dd/MM/yyyy');//new Date($scope.delivery.date);				$scope.currentOrder.items = $rootScope.myCard;
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
	};

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
	$scope.choosedDate = $filter('date')(new Date(),'EEEE,MMMM,dd,yyyy').split(',');
	console.log('formatter choosed date is ',$scope.choosedDate);
	$scope.formateSelectedDate = function(date){
		$scope.choosedDate = $filter('date')(date,'EEEE,MMMM,dd,yyyy').split(',');
		$rootScope.delivery.date=date;
		$rootScope.deliveryTimeValidation();
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