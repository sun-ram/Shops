angular.module('aviateAdmin.services')
.service('SocketServices',['$q','api','toastr','myConfig','$rootScope', function($q, api, toastr,myConfig,$rootScope) {
	
	this.getSocket = function(merchant){
		if($rootScope.user.role == "MERCHANTADMIN"){
			var wsUri = myConfig.socket_server_url+$rootScope.user.merchantId;
			if(!($rootScope.websocket && $rootScope.websocket.url == wsUri)){
				$rootScope.websocket = new WebSocket(wsUri);
			}
		}else if($rootScope.user.role == "STOREADMIN"){
			var wsUri = myConfig.socket_server_url+$rootScope.user.storeId;
			if(!($rootScope.websocket && $rootScope.websocket.url == wsUri)){
				$rootScope.websocket = new WebSocket(wsUri);
			}
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
