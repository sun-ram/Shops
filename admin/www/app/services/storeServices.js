angular.module('aviateAdmin.services')
.service('StoreServices',['$q','api','toastr','CONSTANT', function($q, api, toastr,CONSTANT) {
	this.getStore = function(store){
		var d = $q.defer();
		api.Store.getStore(store, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					d.resolve(result.storeList);
				}else{
					toastr.error(result.errorString);	
				}
			}
			else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	
	this.addNewStore = function(store){
		var d = $q.defer();
		api.Store.addNewStore(store, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.ADDSTORE);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};
	this.deleteStore = function(store){
		var d = $q.defer();
		api.Store.deleteStore(store, function(err, result){
			if(result){
				if (result.status === CONSTANT.STATUS.SUCCESS) {
					toastr.success(CONSTANT.DELETESTORE);
					d.resolve(result);
				} else {
					toastr.error(result.errorString);
				}
			}else{
				toastr.error(err.errorCode);
			}
		});
		return d.promise;
	};

	this.setStoreObj = function(store){
		this.obj = store;
	};

	this.getStoreObj = function(){
		return this.obj;
	};

	
}]);
