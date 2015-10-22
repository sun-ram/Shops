angular.module('aviate.directives')
.directive('higgidyCarouselImages', function() {
	var directive = {
			scope:true,
			link: {
				post: function(scope, element) {
					scope.setsWidths = function() {
						var totalWidth = scope.carousel.width * scope.carousel.max;
						element.find('img').css({
							width: scope.carousel.width + 'px'
						});
						element.css({
							width: totalWidth + 'px'
						});
					};
					scope.animateScroll = function() {
						element.css( {'margin-left': 0-scope.carousel.width * scope.carousel.current + "px"});
					};
					scope.$watch('carousel.max', scope.setsWidths);
					scope.$watch('carousel.current', scope.animateScroll);
				}
			}
	};
	return directive;
});