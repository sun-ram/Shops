angular.module('app')
    .filter('productType', function() {
return function (forms, typeName) {
           if(typeName != undefined){
   var postforms = [];
   angular.forEach(forms,function(form){
    if(form.productType != undefined && form.productType.name == typeName){
     postforms.push(form);
    }
   });
   return postforms;
           }
           else{
        	   return forms;
           }
       };
    }
 );