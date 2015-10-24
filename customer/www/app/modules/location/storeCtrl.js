angular.module('aviate.controllers').controller("locationcontroller", 
		['$scope','$state', '$mdDialog','$log','LocationService','$rootScope',
		 function($scope, $state, $mdDialog,$log,$LocationService,$rootScope) {

			$scope.status = '';

			$rootScope.geoLocation ={}

			var showLocationDialog = function(ev) {
				$mdDialog.show({
					controller: DialogController,
					templateUrl: 'app/modules/location/locationdialog.tmpl.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true
				})	
				.then(function(answer) {
					$scope.status = 'You said the information was "' + answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
			};

			var settings = {
					enableHighAccuracy:true
			}

			var geoLocation = function(){
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(showPosition, showError,settings);
				} else { 
					toastr("Geolocation is not supported by this browser");
				}
			};

			var showPosition = function(position) {
				$rootScope.geoLocation.latitude = position.coords.latitude;
				$rootScope.geoLocation.longitude = position.coords.longitude;
				$log.debug(position.coords.latitude,$rootScope.geoLocation.longitude);
				showLocationDialog();
			}

			var showError = function(error) {
				switch(error.code) {
				case error.PERMISSION_DENIED:
					$log.debug("User denied the request for Geolocation.");
					showLocationDialog();
					break;
				case error.POSITION_UNAVAILABLE:
					$log.debug("Location information is unavailable.");
					break;
				case error.TIMEOUT:
					$log.debug("The request to get user location timed out.");
					break;
				case error.UNKNOWN_ERROR:
					$log.debug("An unknown error occurred.")
					break;
				}
			}
			geoLocation();
		}]);

function DialogController($scope, $mdDialog,LocationService,$rootScope,$log,toastr,$state) {
	$scope.hide = function() {
		$mdDialog.hide();
	};
	$scope.cancel = function() {
		$mdDialog.cancel();
	};
	$scope.answer = function(answer) {
		$mdDialog.hide(answer);
	};

	$scope.selectedcity=null;
	
	$scope.selectedIndex = 2;

	var location ={
			latitude:$rootScope.geoLocation.latitude,
			longitude:$rootScope.geoLocation.longitude
	}

	LocationService.getStoreByLocation(location).then(function(data) {
		$scope.storeList = data;
	});

	var optionLocation={}

	LocationService.getStoreByLocation(optionLocation).then(function(data) {
		$scope.allStoreList = data;

	});

	$scope.searchByCity = function(selectedcity){
		$scope.selectedcity=selectedcity;
		var request ={
				city:selectedcity
		}
		LocationService.getStoreByLocation(request).then(function(data) {
			$scope.addressList = data;
		});

	}

	$scope.searchByAddress = function(selectedaddress){
		var request ={area:selectedaddress,city:$scope.selectedcity}
		LocationService.getStoreByLocation(request).then(function(data) {
			$scope.filterStoreList = data;
			$log.debug(data);
		});

	}

	$scope.changeStore = function(store){
		$log.debug(store.storeId);
		$state.go('app.products');
		$mdDialog.cancel();
	}

}
