angular.module('aviateAdmin.controllers')
.controller("billingCtrl", 
		['$scope', '$rootScope', '$state','toastr','CommonServices','$mdDialog','CONSTANT','$localStorage','BillingServices',
		 function($scope, $rootScope, $state, toastr, CommonServices, $mdDialog, CONSTANT, $localStorage, BillingServices) {

			$scope.billing = {};

			$scope.getStores = function(){
				$scope.merchant = {};
				$scope.merchant.merchantId = $rootScope.user.merchantId;
				BillingServices.getStoresByMerchant($scope.merchant).then(function(data){
					$scope.stores = data;
				});

			}

			$scope.getUnPaidBills = function(){
				$scope.billing.merchant = {};
				$scope.billing.isPaid = 'N';
				$scope.billing.merchant.merchantId = $rootScope.user.merchantId;
				BillingServices.getBillsByMerchant($scope.billing).then(function(data){
					$scope.unPaidBillsList = data;
					$scope.originalList = data;
					$scope.noOfRecords = $scope.unPaidBillsList.length;
				});
			}

			$scope.getPaidBills = function(){
				$scope.billing.merchant = {};
				$scope.billing.isPaid = 'Y';
				$scope.billing.merchant.merchantId = $rootScope.user.merchantId;
				BillingServices.getBillsByMerchant($scope.billing).then(function(data){
					$scope.paidBillsList = data;
					$scope.originalList = data;
					$scope.noOfRecords = $scope.paidBillsList.length;
				});
			}

			$scope.getUnpaidBillsByStore = function(store){

				if(store == 'All'){
					$scope.unPaidBillsList = $scope.originalList;
					$scope.noOfRecords = $scope.unPaidBillsList.length;
				}else{
					$scope.sortedList = [];

					$scope.originalList.forEach(function(merchant){
						$scope.storeIdinList = merchant.store.storeId;
						if(store == $scope.storeIdinList){
							$scope.sortedList.push(merchant);
						}
					})			
					$scope.unPaidBillsList = $scope.sortedList;
					$scope.noOfRecords = $scope.unPaidBillsList.length;
				}
			}
			
			 $scope.selected = [];
			  
			$scope.updateSelected = function(action, id) {
				if (action === 'add' && $scope.selected.indexOf(id) === -1) {
					$scope.selected.push(id);
				}
				if (action === 'remove' && $scope.selected.indexOf(id) !== -1) {
					$scope.selected.splice($scope.selected.indexOf(id), 1);
					$scope.check = false;
				}
			};

			$scope.updateSelection = function($event, id) {
				var checkbox = $event.target.checked;
				var action = (checkbox ? 'add' : 'remove');
				$scope.updateSelected(action, id);
			};

			$scope.selectAll = function($event) {
				var checkbox = $event.target.checked;
				var action = (checkbox ? 'add' : 'remove');
				for ( var i = 0; i < $scope.unPaidBillsList.length; i++) {
					var entity = $scope.unPaidBillsList[i];
					$scope.updateSelected(action, entity.billingId);
				}
			};
			
			$scope.isSelected = function(id) {
				return $scope.selected.indexOf(id) >= 0;
			};
			
			$scope.isSelectAll = function() {
				if($scope.unPaidBillsList != undefined){
					return $scope.unPaidBillsList.length == $scope.selected.length;
				}
			};
			
			$scope.paySelectedBills = function(){
				if($scope.selected.length != 0){
					console.log("payment",$scope.selected);
				}
			}

		}]);