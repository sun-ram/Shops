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
                address:{line1:"jkhjkhk",line2:"jhgjhg"},
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
