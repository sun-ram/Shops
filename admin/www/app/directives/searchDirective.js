angular.module('aviateAdmin.directives')

.directive('searchText', function(filterFilter) {
	return {
		restrict: 'E',
		scope:{
			originalList:"=",
			filteredList:"=",
			pagination:"=",
			totalRecords:"="
		},
		template: '<input ng-model="searchText" type="text" placeholder="Search" ng-change="onChangeSearch(searchText)">',
		link: function(scope, element, attrs) {
		
			scope.onChangeSearch = function (term) {
			
				if(term != ""){
					scope.filteredList = filterFilter(scope.originalList, term);
				    scope.totalRecords = scope.filteredList.length;
				    scope.pagination.limit = 5;
				    scope.pagination.page = 1;
				    
				}else{
					scope.filteredList = scope.originalList;
					scope.totalRecords = scope.filteredList.length;
				}
				
			};
		}
	};
});