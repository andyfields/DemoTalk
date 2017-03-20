var sectionsList = [];

$(function() {
    initSectionsList();    
});

function initSectionsList() {
	loadSectionsList();
}

function loadSectionsList() {
	
	var url = "/micro/sections/findAll";
	
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function (sections) {
            sectionsReceived(sections);
        },
        error: function( event, jqxhr, settings, thrownError ) {
            alert(thrownError);
        }
    });
	
}

function sectionsReceived(sections) {
	sectionsList = sections;
	
	displaySections();
}
	
function displaySections() {
	for (i = 0; i < sectionsList.length; i++) {
		var section = sectionsList[i];
		sectionListAddRow(section);
	}
}

function sectionListAddRow(section) {
	var rowHtml = $("#section-row-template").html();
	$("#SectionList").append(rowHtml);
	
	var newRow = $("#SectionList .row").last();
	var sectionName = (newRow.find(".section-name"));
	sectionName.html(section.name);
	
	var editTag = (newRow.find(".admin-edit"));
	editTag.data("sectionId", section.id);
	
    // When clicking on the "edit" link, open the edit modal.
	editTag.click(function(event) {
		$("#editSection").modal("show");
		var sectId = editTag.data("sectionId");
		$("#editSection").data("sectionId", editTag.data("sectionId"));
		$("#editSection").data("sectionNameElem", sectionName);
		var sectName = sectionName.html();
		$("#editSectionName").val(sectName);
    })

}
