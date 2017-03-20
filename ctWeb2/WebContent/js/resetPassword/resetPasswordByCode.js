$(function() {
    console.log( "Start!" );    
    init();
    
    console.log( "ready!" );    
});

function init() {
	screenChange();
	
    $("#inputPassword").on("input", function() {
    	screenChange();
    });
    
    $('#confirmationPassword').on("input", function() {
    	screenChange();
    });
    
    $('#resetPasswordByCodeForm').submit(function(event) {
    	checkSubmit(event);
    });
}

function screenChange() {
	var pw = $("#inputPassword").val();
	var confirm = $("#confirmationPassword").val();
	
	$(".input-warning").addClass("hidden");
	
	if (!pw || !confirm) {
		$("#btnSubmit").addClass("disabled");
		$("#btnSubmit").attr("disabled","disabled");
	} else {
		$("#btnSubmit").removeClass("disabled");
		$("#btnSubmit").removeAttr("disabled");		
	}
}

function checkSubmit(event) {
	console.log("checkSubmit");
	
	var pw = $("#inputPassword").val();
	var confirm = $("#confirmationPassword").val();
	
	var submitOK = true;

	if (pw !== confirm) {
		$(".pw-nomatch-warning").removeClass("hidden");
		submitOK = false;
		event.preventDefault();
		
	} else 	if (pw.length < 8) {
		$(".pw-length-warning").removeClass("hidden");
		submitOK = false;
		event.preventDefault();
	}
	
	return submitOK;
}