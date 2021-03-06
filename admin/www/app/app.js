'use strict';

var aviateAdmin = angular.module('app', ['toastr',
                                         'ngStorage',
                                         'ui.router',
                                         'aviateAdmin.controllers',
                                         'aviateAdmin.factories',
                                         'aviateAdmin.directives',
                                         'ngMaterial',
                                         'aviateAdmin.providers',
                                         'aviateAdmin.services',
                                         'aviateAdmin.filters',
                                         'aviateAdmin.constant',
                                         'ngTable',
                                         'ipCookie',
                                         'ngMdIcons',
                                         'md.data.table',
                                         'ngDraggable', 'uiGmapgoogle-maps',
                                         'scDateTime','materialCalendar','ngSanitize'
                                         ]);


angular.module('aviateAdmin.providers', ['ngMaterial']);

angular.module('aviateAdmin.controllers', ['ngMaterial']);

angular.module('aviateAdmin.factories', ['ngMaterial']);

angular.module('aviateAdmin.directives', ['ngMaterial']);

angular.module('aviateAdmin.services', ['ngMaterial']);

angular.module('aviateAdmin.filters', []);

angular.module('aviateAdmin.constant', []);

