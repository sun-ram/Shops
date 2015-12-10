angular.module('aviate.directives')

.directive('mdTable', function() {
 return {
    restrict: 'E',
    scope: {
      items: '=',
      columns: '='  
    },
    templateUrl: function($scope, $attrs) {
      return $attrs.templateName || "";
    }
  };
});