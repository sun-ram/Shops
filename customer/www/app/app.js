'use strict';

var aviateAdmin = angular.module('app', ['toastr',
                                         'ui.router',
                                         'aviate.controllers',
                                         'aviate.factories',
                                         'aviate.directives',
                                         'aviate.providers',
                                         'aviate.services',
                                         'aviate.filters',
                                         'aviate.constant',
                                         'ngMaterial',
                                         'ipCookie',
                                         'ngMdIcons',
                                         'md.data.table',
                                         'ngStorage',
                                         'ngSanitize'
                                         ]).run(['$rootScope', '$state', '$stateParams', '$location', '$window', function ($rootScope, $state, $stateParams, $location, $window) {
                                             $rootScope.$on('$stateChangeSuccess', function() {
                                             	   document.body.scrollTop = document.documentElement.scrollTop = 0;
                                             	});
                                         }]);

angular.module('aviate.providers', ['ngMaterial']);

angular.module('aviate.controllers', ['ngMaterial']);

angular.module('aviate.factories', ['ngMaterial']);

angular.module('aviate.directives', ['ngMaterial']);

angular.module('aviate.services', ['ngMaterial']);

angular.module('aviate.filters', []);

angular.module('aviate.constant', []);
