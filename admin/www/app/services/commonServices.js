angular.module('aviateAdmin.services')
.service('CommonServices',['$q','api','ipCookie','toastr','CONSTANT', function($q, api, ipCookie,toastr, CONSTANT) {
	
	this.getCountries = function(country){
		var d = $q.defer();
		api.Region.getCountries(country, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.countries);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		})
		return d.promise;
	};
}]);
