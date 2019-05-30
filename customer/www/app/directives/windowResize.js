/*angular.module('aviate.directives')
.directive('resize', function ($window) {
    return function (scope, element) {
        var w = angular.element($window);
        scope.getWindowDimensions = function () {
            return {
                'h': w.height(),
                'w': w.width()
                
                
            };
        };
        scope.$watch(scope.getWindowDimensions, function (newValue, oldValue) {
            scope.windowHeight = newValue.h;
            scope.windowWidth = newValue.w;
            console.log(scope.windowHeight);
            console.log(scope.windowWidth);
            if(value < 450){
		    	   $rootScope.numLimit = 1;
		       }
		       if(value > 450 && value <= 700){
		    	   $rootScope.numLimit = 2;
		       }
		       if(value > 700 && value <= 960){
		    	   $rootScope.numLimit = 3;
		       }
		       if(value > 960 && value <= 1024){
		    	   $rootScope.numLimit = 4;
		       }
		       if(value > 1024 && value <= 1200){
		    	   $rootScope.numLimit = 5;
		       }
		       if(value > 1200 && value <= 1500){
		    	   $rootScope.numLimit = 6;
		       }
		       if(value > 1500){
		    	   
		    	   $rootScope.numLimit = 6;
		       }
		       
		       
            scope.style = function () {
                return {
                    'height': (newValue.h - 100) + 'px',
                        'width': (newValue.w - 100) + 'px'
                };
            };

        }, true);

        w.bind('resize', function () {
            scope.$apply();
        });
    }
})*/