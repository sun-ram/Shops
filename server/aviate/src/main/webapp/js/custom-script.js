



$(document).ready(function(){
	$('#loginwrapper').hide();
	function checksize(){
		$( ".product-menu-list" ).toggle( "slide" );
	}		
	$(window).resize(function(){
		//var flag = false;
		if($(window).width() < 767){
			
			$( ".product-menu-list" ).hide();
			$('#toggleMenu').unbind();
			$('#toggleMenu').click(function() {
				checksize();
			});	
		}
		else{
			$( ".product-menu-list" ).show();
		}
	});										
	if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
	 // some code..
	 	$( ".product-menu-list" ).hide();
	 	$('#toggleMenu').click(function() {
	 		alert('gfgf');
	 		checksize();
		});
	}
	else{
		//alert('not mobile');
	}
	
	
	$('#userlogin').click(function(){
		$('#loginwrapper').slideToggle();
	});
	$('#login-close').click(function(){
		$('#loginwrapper').slideUp();
	});
});