  var app = angular.module('app', []);  
   app.directive('allowDecimalNumbers', function () {  
        return {  
            restrict: 'A',  
            link: function (scope, elm, attrs, ctrl) {  
                elm.on('keydown', function (event) {  
                    var $input = $(this);  
                    var value = $input.val();  
                    value = value.replace(/[^0-9\.]/g, '')  
                    $input.val(value);  
                   if (event.which == 64 || event.which == 16) {  
                        // numbers  
                        return false;  
                   } if ([8, 13, 27, 37, 38, 39, 40, 110].indexOf(event.which) > -1) {  
                        // backspace, enter, escape, arrows  
                        return true;  
                    } else if (event.which >= 48 && event.which <= 57) {  
                       // numbers                          return true;  
                   } else if (event.which >= 96 && event.which <= 105) {  
                        // numpad number  
                        return true;  
                   } else if ([46, 110, 190].indexOf(event.which) > -1) {  
                       // dot and numpad dot  
                       return true;  
                   } else {  
                       event.preventDefault();  
                      return false;  
                   }  
               });  
           }  
       }  
   });