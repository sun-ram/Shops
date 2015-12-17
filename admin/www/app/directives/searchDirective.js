angular.module('aviateAdmin.directives')

.directive('searchText', function(toastr, filterFilter) {
	return {
		require: '?ngModel',
		link: function(scope, element, attrs) {
			
			scope.oldData = scope.data;
		
			scope.onChangeSearch = function (term, data, pagination) {
			
				if(term != ""){
					data = filterFilter(scope.oldData, term);
				    scope.noOfRecords = data.length;
				    pagination.limit = 5;
				    pagination.page = 1;
				    
				}else{
					data = scope.oldData;
					scope.noOfRecords = data.length;
				}
				
			};
		}
	};
});