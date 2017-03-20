$(function() {
    console.log( "Start!" );    
    init();
    
    console.log( "ready!" );    
});

function init() {
	screenChange();
	
    $("#resetEMail").on("input", function() {
    	screenChange();
    });    
}

function screenChange() {
	var emailInvalid = $("#resetEMail").is(":invalid");
	
	if (emailInvalid) {

		$("#resetSubmit").addClass("disabled");
		$("#resetSubmit").attr("disabled","disabled");
	} else {
		$("#resetSubmit").removeClass("disabled");		
		$("#resetSubmit").removeAttr("disabled");		
	}
}

