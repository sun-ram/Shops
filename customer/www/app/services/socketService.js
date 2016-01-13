angular.module('aviate.services')
.service('SocketServices',['$q','api','toastr','myConfig','$rootScope', function($q, api, toastr,myConfig,$rootScope) {
	
	this.getSocket = function(merchant){
		if($rootScope.user.role == "CUSTOMER"){
			var wsUri = myConfig.socket_server_url+$rootScope.user.userId;
			if(!($rootScope.websocket && $rootScope.websocket.url == wsUri)){
				$rootScope.websocket = new WebSocket(wsUri);
				setTimeout(function() {
					$rootScope.socketService();
				}, 500)
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
