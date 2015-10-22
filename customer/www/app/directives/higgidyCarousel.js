angular.module('aviate.directives')
.directive('higgidyCarousel', function() {
	var directive = {
			scope: true,
			link: {
				pre:function(scope, element, attrs) {
					scope.carousel.width = element[0].offsetWidth;
					scope.getWidth = function() {
						scope.carousel.width = element[0].offsetWidth;
					};
					scope.carousel.timeout = attrs.timeout || 1000;
				}
			}
	};
	return directive;
});