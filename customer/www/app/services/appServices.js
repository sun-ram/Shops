angular.module('aviate.services')
.service('AppServices',['$q','api','toastr','CONSTANT','ipCookie','$rootScope',
                   function($q, api, toastr, CONSTANT, ipCookie, $rootScope) {
	
	this.covertStringToDate = function(arrayString){
		var arrayDate = [];
		arrayString.forEach(function(date){
			arrayDate.push(new Date(date));
		});
		return arrayDate;
	};
	
}]);