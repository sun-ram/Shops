angular.module('aviate.controllers')
    .controller("checkOutCtrl", ['$scope', '$state', 'toastr', 'CONSTANT', 'CheckOutServices', '$mdDialog', '$rootScope',
        function($scope, $state, toastr, CONSTANT, CheckOutServices, $mdDialog, $rootScope) {



            $scope.addresses = [];
            $scope.delivery = {};
            $scope.currentOrder = {
                address:undefined,
                delivery: {
                    time: "12am",
                    date: "12/10/2015"
                },
                contactNumber:"",
                isVerified:false
            };



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
             $scope.removeAddress = function(address) {

                CheckOutServices.removeAddress({"addressId":address.addressId}).then(function(data) {
                     $scope.getAddressList({
                        "customerId": $rootScope.user.userId
                    });
                });


            };

            function addressDialogController($scope, address) {
                //                $scope.address = _.clone(address) || {
                //                    id: Math.random()
                //                };
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
            };

            $scope.merchangetTemplate = "app/modules/checkout/address.html";

            $scope.goNext = function(id) {
                switch (id) {
                    case "address":
                        if ($scope.currentOrder.address && $scope.currentOrder.contactNumber != null) {
                            $scope.timeLineStatus.addressEntry = true;
                            $scope.merchangetTemplate = "app/modules/checkout/deliverySchedule.html";
                        } else {
                            alert("please choose delivery address / phoneNumber");
                        }
                        break;
                    case "deliverySchedule":
                        if ($scope.delivery.time && $scope.delivery.date) {
                            $scope.currentOrder.delivery = $scope.delivery;
                            $scope.currentOrder.items = $rootScope.myCard;
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
            $scope.getAddressList({
                "customerId": $rootScope.user.userId
            });
            console.log("card items",$rootScope.myCard);
        }
    ]);