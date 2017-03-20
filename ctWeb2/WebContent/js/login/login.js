$(function() {
    console.log( "STart!" );    
    init();
    
    console.log( "ready!" );    
});

function init() {
	idPasswordChange();
	
	$('input').on('input',function(){
	    console.log(this.value);
	});
	
    $("#inputEmail").on("input", function() {
    	idPasswordChange();
    });
    
    $("#inputPassword").on("input", function() {
    	idPasswordChange();
    });
}

function idPasswordChange() {

	var emailInvalid = $("#inputEmail").is(":invalid");
	var pwInvalid = $("#inputPassword").is(":invalid");
	
	if (emailInvalid || pwInvalid) {

		$("#loginSubmit").addClass("disabled");
		$("#loginSubmit").attr("disabled","disabled");
	} else {
		$("#loginSubmit").removeClass("disabled");		
		$("#loginSubmit").removeAttr("disabled");		
	}
}