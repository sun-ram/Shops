angular.module('aviate.controllers')
.controller("checkOutCtrl", ['$scope', '$state', 'toastr', 'CONSTANT', 'CheckOutServices', '$mdDialog', '$rootScope','MyCartFactory',
                             function($scope, $state, toastr, CONSTANT, CheckOutServices, $mdDialog, $rootScope, MyCartFactory) {

	MyCartFactory.myCartTotalPriceCalculation();

	$scope.addresses = [];
	$scope.delivery = {};
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
		address.customerId = $rootScope.user.userId;
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

	$scope.confirmOrder = function() {
		var menuJson = {
				"customerId":$rootScope.user.userId,
				"addressId":$scope.currentOrder.address.addressId,
				"storeId":$rootScope.store.storeId,
				"deliveryDate":$scope.currentOrder.delivery.date,
				"deliveryTime":$scope.currentOrder.delivery.time,
				"contactNo":$scope.currentOrder.contactNumber,
				"totalAmount":$rootScope.myCart.cartTotalAmount,
				"orderGrossAmount":$rootScope.myCart.grossAmount,
				"totalTaxAmount":$rootScope.myCart.taxAmount,
				"shippingCharge":$rootScope.myCart.shippingCharges,
				"merchantId":$rootScope.store.merchantId
		}

		CheckOutServices.confirmOrder(menuJson).then(function(data) {
			console.log("OrderId", data);
			$scope.orderId = data;
			$scope.payment();
		});
	};

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
		CheckOutServices.removeAddress({"addressId":address.addressId}).then(function(data) {
			$scope.getAddressList({
				"customerId": $rootScope.user.userId
			});
		});


	};

	function addressDialogController($scope, address) {
		$scope.address = _.clone(address) ;
		$scope.delete = false;
		if (address) {
			$scope.AddUpdate = "update";
			$scope.delete = true;

		} else {
			$scope.AddUpdate = "add";
		}
		$scope.cancel = function() {
			$mdDialog.cancel();
		};

		$scope.addAddress = function(address) {
			addAddress(address);
			$mdDialog.cancel();
		};

		$scope.removeAddress = function(address) {
			removeAddress(address);
			$mdDialog.cancel();
		};
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
			if ($scope.currentOrder.address && $scope.currentOrder.contactNumber != null) {
				$scope.timeLineStatus.addressEntry = true;
				$scope.merchangetTemplate = "app/modules/checkout/deliverySchedule.html";
			} else {
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.querySelector('#popupContainer')))
						.clickOutsideToClose(true)
						.title('Alert')
						.content('please choose delivery address / phoneNumber.')
						.ariaLabel('Alert Dialog Demo')
						.ok('Ok')
						.targetEvent()
				);
			}
			break;
		case "deliverySchedule":
			if ($scope.delivery.time && $scope.delivery.date) {
				$scope.currentOrder.delivery = $scope.delivery;
				$scope.currentOrder.items = $rootScope.myCard;
				$scope.timeLineStatus.deliveryDate = true;
				$scope.merchangetTemplate = "app/modules/checkout/verifyOrderDetails.html";
			} else {
				$mdDialog.show(
						$mdDialog.alert()
						.parent(angular.element(document.querySelector('#popupContainer')))
						.clickOutsideToClose(true)
						.title('Alert')
						.content('please choose delivery date.')
						.ariaLabel('Alert Dialog Demo')
						.ok('Ok')
						.targetEvent()
				);
			}
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
	console.log("card items",$rootScope.myCard);
}
]);