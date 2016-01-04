angular.module('aviateAdmin.services')
.service('SocketServices',['$q','api','toastr','myConfig','$rootScope', function($q, api, toastr,myConfig,$rootScope) {
	
	this.getSocket = function(merchant){
		if($rootScope.user.role == "MERCHANTADMIN"){
			var wsUri = myConfig.socket_server_url+$rootScope.user.merchantId;
			$rootScope.websocket = new WebSocket(wsUri);
			console.log($rootScope.websocket);
		}else if($rootScope.user.role == "STOREADMIN"){
			var wsUri = myConfig.socket_server_url+$rootScope.user.storeId;
			$rootScope.websocket = new WebSocket(wsUri);
			console.log($rootScope.websocket);
		}
	};

	this.setSocketObj = function(socket){
		this.obj = tax;
	};

	this.getSocketObj = function(){
		return this.obj;
	};

	
}]);

//if($rootScope.user != null && $rootScope.user != undefined && $rootScope.websocket == undefined){
