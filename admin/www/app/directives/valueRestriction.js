angular.module('aviateAdmin.directives')

.directive('valueRestriction', function(toastr,CONSTANT) {
	return {
		require: '?ngModel',
		link: function(scope, element, attrs, ngModelCtrl) {
			if (!ngModelCtrl) {
				return;
			}
			var value = attrs.valueRestriction;
			console.log('limit value is ',value);
			element.on('input',function(event){
				  var $input = $(this);
				  console.log('entered value ',parseInt($input.val()));
				  if(parseInt($input.val())> parseInt(value)){
					    $input.val(); 
						toastr.error(CONSTANT.PERCENTAGE_LIMIT_EXCEED.replace('{}',value));
				  }
			})
		}
	};
});