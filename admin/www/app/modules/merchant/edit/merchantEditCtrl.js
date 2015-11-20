angular.module('aviateAdmin.controllers')
.controller("merchantEditCtrl", 
		['$scope', '$rootScope','$state','toastr','MerchantServices', 'CommonServices',
		 function($scope,$rootScope, $state,  toastr, MerchantServices, CommonServices) {

			$scope.getMerchant = function(){
				$scope.merchantDetail = MerchantServices.getMerchantObj();
				$scope.temp = localStorage.getItem('merchantDetails');
				if($scope.merchantDetail){
					localStorage.setItem('merchantDetails',JSON.stringify($scope.merchantDetail));
				}else if($scope.temp && $scope.temp != 'undefined'){
					$scope.merchantDetail = JSON.parse($scope.temp);
				}else{
					localStorage.removeItem('merchantDetails');
					$state.go('app.newmerchant');
				}
				console.info($scope.merchantDetail);
			};
			$scope.getMerchant();
			
            $scope.countryObjectof = function (countryList, keyId){
                for(var i=0;i<countryList.length;i++){
                    if(countryList[i].countryId == keyId){
                        return countryList[i];
                    }
                }
            }
            
            $scope.stateObjectOf = function (stateList, keyId){
                for(var i=0;i<stateList.length;i++){
                    if(stateList[i].stateId == keyId){
                        return stateList[i];
                    }
                }
            }
            $scope.double = function(value) { return value * 2; };
             
			$scope.populateStates = function(country){
                
                $scope.merchantDetail.user.address.country = $scope.countryObjectof($scope.countries, $scope.merchantDetail.user.address.country.countryId);
                $scope.allStates = $scope.merchantDetail.user.address.country.states;
			}
            
            $scope.changeState = function () {
                $scope.merchantDetail.user.address.state = $scope.stateObjectOf($scope.merchantDetail.user.address.country.states, $scope.merchantDetail.user.address.state.stateId);
           
            }
			
			$scope.getCountries = function(){
				CommonServices.getCountries($scope.country).then(function(data){
					$scope.countries=data;
                    $scope.merchantDetail.user.address.country = $scope.countryObjectof ($scope.countries, $scope.merchantDetail.user.address.country.countryId);
                    $scope.merchantDetail.user.address.country.state = $scope.stateObjectOf ($scope.merchantDetail.user.address.country.states, $scope.merchantDetail.user.address.state.stateId);
				});
			}
            
			$scope.getCountries();

			$scope.updateMerchant = function(){
				

				$scope.cnt = JSON.parse($scope.country);
				$scope.st = JSON.parse($scope.state);
				$scope.merchantDetail.user.address.country = {};
				$scope.merchantDetail.user.address.state = {};
				$scope.merchantDetail.user.address.country.countryId = $scope.cnt.countryId;
				$scope.merchantDetail.user.address.country.name = $scope.cnt.name;
				$scope.merchantDetail.user.address.state.stateId = $scope.st.stateId;
				$scope.merchantDetail.user.address.state.name = $scope.st.name;
				
				if ($scope.merchantLogo != "" && $scope.merchantLogo != undefined && $scope.merchantLogo != null) {
					$scope.merchantDetail.logo = {};
					$scope.merchantDetail.logo.image=$scope.merchantLogo.split(",")[1];
					$scope.merchantDetail.logo.type=$scope.merchantLogo ? ($scope.merchantLogo.substring(11).split(";")[0]) : "";
				}
				MerchantServices.updateMerchant($scope.merchantDetail).then(function(data){
					$scope.merchantLogo=null;
					$scope.merchantDetail = null;
					$state.go('app.merchants');
				});
			};
			
			$scope.redirectToMerchantDetails = function(merchant){
				if($rootScope.fromDetailsPage == true){
					MerchantServices.setMerchantObj(merchant);
					$state.go('app.merchantdetails');
					$rootScope.fromDetailsPage = false;
				} else {
					$state.go('app.merchants');
				}
			}

		}]);