angular.module('aviate.controllers')
.controller("checkOutCtrl",
		['$scope', '$state', 'toastr', 'CONSTANT', 'CheckOutServices',
		 function($scope, $state, toastr, CONSTANT, CheckOutServices) {



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

		}]);
