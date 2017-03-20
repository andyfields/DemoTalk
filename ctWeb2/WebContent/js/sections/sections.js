$(function() {
    initSections();    
});

function initSections() {
	$("#addNewSection").data("dirty", false);
	
    $("#addNewSection").on("input", function() {
    	addSectionNameChange();
    });
	
    $("#addNewSection").on("hide.bs.modal", function(event) {
    	addNewSectionCloseConfirm(event);
    })
    
    $("#addNewSection").on("show.bs.modal", function(event) {
    	addNewSectionInit(event);
    })
    
    $("#btnConfirmCloseNewSection").click(function(event) {
    	addNewSectionClose(event);
    })
    
    $("#btnCreateSection").click(function(event) {
    	createNewSection(event);
    })
    
	$("#editSection").data("dirty", false);
	
    $("#editSectionName").on("input", function() {
    	editSectionNameChange();
    });
	
    $("#editSection").on("hide.bs.modal", function(event) {
    	editSectionCloseConfirm(event);
    })
    
    $("#editSection").on("show.bs.modal", function(event) {
    	editSectionInit(event);
    })
    
    $("#btnConfirmCloseEditSection").click(function(event) {
    	editSectionClose(event);
    })
    
    $("#btnEditSection").click(function(event) {
    	doEditSection(event);
    })
    
}

//Edit section modal events.
function editSectionInit() {
	$("#editSection").data("dirty", false);
	$("#editSectionName").val("");
	$(".modal-alert").addClass("hidden");
}

function editSectionNameChange() {
	$("#editSection").data("dirty", true);
}

function editSectionCloseConfirm(event) {
	// Attempting to close add new section.  if name is dirty, ask for confirmation.
	if ($("#editSectionName").val() && $("#editSection").data("dirty")) {
		// Display second modal to confirm close of first.
		$("#confirmCloseEditSection").modal("show");
		event.preventDefault();
	} 
}

function editSectionClose(event) {
	// Close will fail if dirty.
	$("#editSection").data("dirty", false);
	$("#editSection").modal("hide");
}

// Send ajax to back end to add section.  Add section to screen.
function doEditSection(event) {
	// All tag buttons dismiss the modal unless prevented.
	var url = "/micro/sections/edit";
	
	var nameVal = $("#editSectionName").val();
	var id = $("#editSection").data("sectionId");
	var sectionNameEl = $("#editSection").data("sectionNameElem");
	
    $.ajax({
        url: url,
        type: 'PATCH',
        // dataType: 'json',  No data returned.  So JSON will cause an error.
        data: {"name": nameVal, "id": id},
        success: function () {
        	doEditSectionSuccess(id, nameVal, sectionNameEl);
        },
        error: function( event, jqxhr, settings, thrownError ) {
            doEditSectionFail(event.responseText);
        }
    });
    
	event.preventDefault();
}

function doEditSectionSuccess(id, nameVal, sectionNameEl) {
	sectionNameEl.html(nameVal);
	
	// Close will fail if dirty.
	$("#editSection").data("dirty", false);
	$("#editSection").modal("hide");
	
}

function doEditSectionFail(errMsg) {
	$(".modal-alert").html(errMsg);
	$(".modal-alert").removeClass("hidden");
}

// New section modal events.
function addNewSectionInit() {
	$("#addNewSection").data("dirty", false);
	$("#newSectionName").val("");
	$(".modal-alert").addClass("hidden");
}

function addSectionNameChange() {
	$("#addNewSection").data("dirty", true);
}

function addNewSectionCloseConfirm(event) {
	// Attempting to close add new section.  if name is dirty, ask for confirmation.
	if ($("#newSectionName").val() && $("#addNewSection").data("dirty")) {
		// Display second modal to confirm close of first.
		$("#confirmCloseNewSection").modal("show");
		event.preventDefault();
	} 
}

function addNewSectionClose(event) {
	// Close will fail if dirty.
	$("#addNewSection").data("dirty", false);
	$("#addNewSection").modal("hide");
}

// Send ajax to back end to add section.  Add section to screen.
function createNewSection(event) {
	// All tag buttons dismiss the modal unless prevented.
	var url = "/micro/sections/create";
	
	var nameVal = $("#newSectionName").val();
	
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {"name": nameVal},
        success: function (sectionAdded) {
        	createNewSectionSuccess(sectionAdded);
        },
        error: function( event, jqxhr, settings, thrownError ) {
            createNewSectionFail(event.responseText);
        }
    });
    
	event.preventDefault();
	
}

function createNewSectionSuccess(sectionAdded) {
	sectionListAddRow(sectionAdded);	// See sectionsList.js.
	
	// Close will fail if dirty.
	$("#addNewSection").data("dirty", false);
	$("#addNewSection").modal("hide");
	
}

function createNewSectionFail(errMsg) {
	$(".modal-alert").html(errMsg);
	$(".modal-alert").removeClass("hidden");
}