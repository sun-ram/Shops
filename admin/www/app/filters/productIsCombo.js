angular.module('app')
    .filter('isCombo', function() {
return function (forms, isKit) {
	if(isKit == undefined){
		isKit = false;
	}
           if(isKit != undefined){
   var postforms = [];
   angular.forEach(forms,function(form){
    if(form.isKit == isKit){
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