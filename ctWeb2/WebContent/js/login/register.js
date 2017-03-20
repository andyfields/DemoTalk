$(function() {
    console.log( "Start!" );    
    init();
    
    console.log( "ready!" );    
});

function init() {
	screenChange();
	
    $("#displayName").on("input", function() {
    	screenChange();
    });
    
    $("#registrationEMail").on("input", function() {
    	screenChange();
    });
    
    $("#registrationPassword").on("input", function() {
    	screenChange();
    });
    
    $('#confirmationPassword').on("input", function() {
    	screenChange();
    });
    
    $('#registerForm').submit(function(event) {
    	checkSubmit(event);
    });
}

function screenChange() {
	var displayName = $("#displayName").val();
	var email = $("#registrationEMail").val();
	var pw = $("#registrationPassword").val();
	var confirm = $("#confirmationPassword").val();
	
	$(".input-warning").addClass("hidden");
	
	if (!email || !pw || !confirm || !displayName) {
		$("#btnRegister").addClass("disabled");
		$("#btnRegister").attr("disabled","disabled");
	} else {
		$("#btnRegister").removeClass("disabled");
		$("#btnRegister").removeAttr("disabled");		
	}
}

function checkSubmit(event) {
	console.log("checkSubmit");
	
	var displayName = $("#displayName").val();
	var pw = $("#registrationPassword").val();
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
	
	if (!displayName || displayName.trim().length < 6 || displayName.trim().length > 50) {
		$(".display-name-length-warning").removeClass("hidden");
		submitOK = false;
		event.preventDefault();
	} 
	
	var re = /^[a-zA-Z0-9 ]+$/;
	if (displayName && !re.test(displayName)) {
		$(".display-name-character-warning").removeClass("hidden");
		submitOK = false;
		event.preventDefault();
	}
	
	return submitOK;
}