angular.module('aviateAdmin.factories')
    .factory('toast', ['$timeout', '$interval', '$mdToast', function ($timeout, $interval, $mdToast) {
        var service = {};

        var color = "#000";
        var toast = {}
        service.showToast = function (text, color) {
            toast = $mdToast.show(
                $mdToast.simple()
                .content(text)
                .position("top left right")
                .hideDelay(3000)
            );
            $timeout(function () {
                $('md-toast').css('background-color', color + "!important");
            }, 10)
        }
        service.showInfo = function (text) {
            color = "#23A623";
            service.showToast(text, color);
        }
        service.showWarn = function (text) {
            color = "#F5F123";
            service.showToast(text, color);
        }
        service.showError = function (text) {
            color = "#E91010"
            service.showToast(text, color);
        }
        service.showProgress = function (text) {
            color = "#23A623";
            service.showToast(text, color);
        }
        service.hide = function () {
            $mdToast.hide(toast);
        }
        return service;
}]);
