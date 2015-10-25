angular.module('aviate.controllers')
.controller("checkOutCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'CheckOutServices','$mdDialog',
		 function($scope, $state, toastr, CONSTANT, CheckOutServices, $mdDialog) {

             
             
              $scope.addresses = [];
            $scope.delivery = {};
            $scope.currentOrder = {
                address: {
                    id: Math.random(),
                    firstName: "saravanan",
                    lastName: "R",
                    address: "chennai",
                    city: "chennai",
                    state: "TN",
                    country: "IN",
                    postalCode: " 600075"
                },
                delivery: {
                    time: "12am",
                    date: "12/10/2015"
                },
                items: [{
                    image: "assets/img/checkout/address.svg",
                    name: "lajsflkajsdlkf",
                    weight: "asdfas",
                    count: 2,
                    prize: 250
                }, {
                    image: "assets/img/checkout/address.svg",
                    name: "lajsflkajsdlkf",
                    weight: "asdfas",
                    count: 2,
                    prize: 250
                }]
            };

            $scope.addresses.push({
                id: Math.random(),
                firstName: "saravanan",
                lastName: "R",
                address: "chennai",
                city: "chennai",
                state: "TN",
                country: "IN",
                postalCode: " 600075"
            });

            $scope.timeLineStatus = {
                addressEntry: false,
                deliveryDate: false,
                verification: false,
                payment: false
            };
             
             


			$scope.addAddress = function(address){
				CheckOutServices.addAddress(address).then(function(data){

				});
			};

			$scope.getAddressList = function(address){
				CheckOutServices.getAddressList(address).then(function(data){

				});
			};

			$scope.removeAddress = function(address){
				CheckOutServices.removeAddress(address).then(function(data){

				});
			};


			$scope.confirmOrder = function(){

				var order = angular.toJson({
					"customerId":$rootScope.user.userId,
					"addressId":$scope.deliveryAddress.addressId,
					"storeId":$rootScope.store.storeId,
					"deliveryDate":$scope.deliveryDate,
					"deliveryTime":$rootScope.deliveryTime,
					"contactNo":$rootScope.contactNo,
					"totalAmount":$rootScope.mycart.cartTotalAmount,
					"orderGrossAmount":$rootScope.mycart.grossAmount,
					"totalTaxAmount":$rootScope.mycart.taxAmount,
					"shippingCharge":$rootScope.mycart.shippingCharges
				});

				CheckOutServices.confirmOrder(order).then(function(data){
					$scope.payment();
				});

				/*

				$http({
					url: serviceUrl + 'aviate/json/checkout/conformorder',
					method: 'POST',
					data: menuJson,
					headers: {
						'Content-Type': 'application/json'
					} 
				}).success(function(result, status, headers){
					$scope.orderId = result.orderId;
					$scope.$apply();
					$scope.payment();
				})
				 */}


			$scope.payment = function() {

				CheckOutServices.payment(order).then(function(data){

					$scope.timeStamp = data.timestamp;
					$scope.hash = data.hashMessage;
					$scope.TOTAL = $rootScope.mycart.grossAmount;
					$scope.shipping = ($rootScope.mycart.cartTotalAmount);
					$scope.$apply();
					document.forms["FirstData"].submit();
				});

			};

				/*			$scope.addresslist =[];
				var paymentJSON = angular.toJson
				({
					"amount":$rootScope.grossAmount
				});
				$http({
					url: "http://182.74.202.178:8181/aviate/json/get/payment",
					method: 'POST',
					data : paymentJSON,
					headers: {
						'Content-Type': 'application/json'
					}
				}).success(function(result, status, headers){
					$scope.timeStamp = result.timestamp;
					$scope.hash = result.hashMessage;
					$scope.TOTAL = $rootScope.grossAmount;
					$scope.shipping = ($scope.cartTotalAmount * .05);
					$scope.$apply();
					document.forms["FirstData"].submit();
				});
			}*/
        
              $scope.selectAddress = function(addressId) {
                var seletecdAddress = _.filter($scope.addresses, function(add) {
                    return add.selected == true;
                })
                _.each(seletecdAddress, function(add) {
                    add.selected = false;
                });
                _.findWhere($scope.addresses, {
                    id: addressId
                }).selected = true;
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
                var temp = _.findWhere($scope.addresses, {
                    id: address.id
                });
                if (temp) {
                    temp = address
                } else {
                    $scope.addresses.push(address);
                }
            };

            function addressDialogController($scope, address) {
                $scope.address = _.clone(address) || {
                    id: Math.random()
                };

                $scope.cancel = function() {
                    $mdDialog.cancel();
                };

                $scope.addAddress = function(address) {
                    addAddress(address);
                    $mdDialog.cancel();
                };
            };

            $scope.merchangetTemplate = "app/modules/checkout/address.html";
          
            $scope.goNext = function(id) {
                switch (id) {
                    case "address":
                        if (_.filter($scope.addresses, function(add) {
                            return add.selected == true
                        }).length > 0) {
                            $scope.timeLineStatus.addressEntry = true;
                            $scope.merchangetTemplate = "app/modules/checkout/deliverySchedule.html";
                        } else {
                            alert("please choose delivery address");
                        }
                        break;
                    case "deliverySchedule":
                        if ($scope.delivery.time && $scope.delivery.date) {
                            $scope.timeLineStatus.deliveryDate = true;
                            $scope.merchangetTemplate = "app/modules/checkout/verifyOrderDetails.html";
                        } else {
                            alert("please choose delivery date");
                        }
                        break;
                    case "verification":
                        $scope.timeLineStatus.verification = true;
                        break;
                    default:
                        console.log("no option found");

                }
            };

        
             
             

		}]);
