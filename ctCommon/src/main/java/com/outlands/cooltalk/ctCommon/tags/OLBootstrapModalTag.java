package com.outlands.cooltalk.ctCommon.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.util.StringUtils;

public class OLBootstrapModalTag extends SimpleTagSupport {

	private String modalId;
	private String size;
	private String title;
	private String inputLabel;
	private String inputId;
	private String inputPlaceholder;
	private String button1caption;
	private String button1Id;
	private String button1Dismiss = "false";
	private String button2caption;
	private String button2Id;
	private String button2Dismiss = "false";
	
	private String content = null;

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
      JspWriter out = getJspContext().getOut();
      
       /* use message from the body */
      getJspBody().invoke(sw);
      content = sw.toString();
      
      StringBuilder modal = new StringBuilder();
      
      modal.append("<div id=\"").append(modalId).append("\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"modalLabel\">\n");
      
      String dialogSmall ="";
      if ("small".equalsIgnoreCase(size)) dialogSmall = " dialog-small";
      
      modal.append("  <div class=\"modal-dialog").append(dialogSmall).append("\" role=\"form\">\n");
      modal.append("    <div class='modal-content'>\n");
      
      modal.append(getModalHeader());
      
      modal.append(getModalBody());
      
      modal.append("    </div> <!-- modal-content -->\n");
      modal.append("  </div> <!-- Modal-dialog -->\n");
      
      modal.append("</div> <!-- Modal -->\n");
      
      out.println(modal.toString());

    }
	
	public StringBuilder getModalHeader() {
		StringBuilder header = new StringBuilder();

		header.append("      <div class='modal-header ctblue'>\n");
		header.append("        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n");
		header.append("           &times;\n");
		header.append("        </button>\n");
		header.append("        <h3 id='modal-label' class='modal-title'>").append(title).append("</h3>\n");
		header.append("      </div> <!-- Modal Header -->\n");

		return header;
	}
	
	public StringBuilder getModalBody() {
		
		StringBuilder modalBody = new StringBuilder();
		
		modalBody.append("      <div class='modal-body'>\n");
		modalBody.append("\n");
		modalBody.append("        <div class='row'>\n");
		modalBody.append("          <div class='col-sm-12'>\n");
		modalBody.append("            <div class='form-horizontal'>\n");
		modalBody.append("\n");
		
		modalBody.append(getModalRows());
		
		modalBody.append("            </div> <!-- form-horizonatal -->\n");
		modalBody.append("          </div>   <!-- col -->\n");
		modalBody.append("        </div>     <!-- row -->\n");
		modalBody.append("      </div>        <!-- modal-body -->\n");
		
		return modalBody;

	}
	
	private StringBuilder getModalRows() {
		
		StringBuilder modalRows = getErrorRow();
		
		if (!StringUtils.isEmpty(content)) {
			modalRows.append(getTextContent());
		}
		
		if (!StringUtils.isEmpty(inputId)) {
			modalRows.append(getInputRow());
		}
		
		modalRows.append(getButtons());

		return modalRows;
	}
	
	// Empty error row.
	protected StringBuilder getErrorRow() {
		
		StringBuilder modalContent = new StringBuilder();
        
		modalContent.append("                  <div class='form-group'>\n");
		modalContent.append("                    <div class='col-sm-offset-1 col-sm-11 col-xs-12 alert alert-danger alert-style modal-alert hidden'>\n");
		modalContent.append("                  	 </div>\n");
		modalContent.append("                  </div>\n");
		
		return modalContent;
	}
	
	
	protected StringBuilder getTextContent() {
		
		StringBuilder modalContent = new StringBuilder();
        
		modalContent.append("                  <div class='form-group'>\n");
		modalContent.append("                    <div class='col-sm-offset-1 col-sm-11 col-xs-12'>\n");
		modalContent.append("                      ").append(content).append("\n");
		modalContent.append("                  	 </div>\n");
		modalContent.append("                  </div>\n");
		
		return modalContent;
	}
	
	protected StringBuilder getInputRow() {
		StringBuilder inputRow = new StringBuilder();
		
		inputRow.append("              <div class='form-group'>\n");
		inputRow.append("                <label class='col-sm-4 control-label' for='").append(inputId).append("'>")
				.append(inputLabel).append("</label>\n");
		
		inputRow.append("                <div class='col-sm-8'>\n");
		inputRow.append("                  <input id='").append(inputId).append("' type='text' placeholder='").append(inputPlaceholder)
	                          .append("' class='form-control text-box-width' />\n");
		inputRow.append("                </div>\n");
		inputRow.append("                <div class='col-sm-offset-4 col-sm-8'>\n");
		inputRow.append("                  <span class='section-name-length-warning input-warning hidden'>\n");
		inputRow.append("                    Display name must be between 10 and 100 characters.\n");
		inputRow.append("                  </span> \n");
		inputRow.append("                </div>\n");
		inputRow.append("              </div>\n");

		return inputRow;
	}
	
	protected StringBuilder getButtons() {
		
		StringBuilder buttonRow = new StringBuilder();
		
		buttonRow.append("              <div class='form-group'>\n");
		buttonRow.append("                <div class='col-sm-4'></div>\n");
		buttonRow.append("                <div class='col-sm-8'>\n");
		buttonRow.append("                  <button id='")
				.append(button1Id).append("' type='button' class='btn btn-primary button-padding'")
				.append("true".equalsIgnoreCase(button1Dismiss) ? " data-dismiss='modal'>" : ">")
				.append(button1caption).append("</button>\n");
		
		if (!StringUtils.isEmpty(button2caption)) {
			buttonRow.append("                  <button id='")
					.append(button2Id).append("' type='button' class='btn button-padding'")
					.append("true".equalsIgnoreCase(button2Dismiss) ? " data-dismiss='modal'>" : ">")
					.append(button2caption).append("</button>\n");
		}
		   
		buttonRow.append("                </div>\n");
		buttonRow.append("              </div>\n");

		return buttonRow;
	}

	public String getModalId() {
		return modalId;
	}
	
	public void setModalId(String modalId) {
		this.modalId = modalId;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getButton1caption() {
		return button1caption;
	}
	
	public void setButton1caption(String button1caption) {
		this.button1caption = button1caption;
	}
	
	public String getButton2caption() {
		return button2caption;
	}
	
	public void setButton2caption(String button2caption) {
		this.button2caption = button2caption;
	}
	
	public String getButton1Id() {
		return button1Id;
	}

	public void setButton1Id(String button1Id) {
		this.button1Id = button1Id;
	}

	public String getButton1Dismiss() {
		return button1Dismiss;
	}

	public void setButton1Dismiss(String button1Dismiss) {
		this.button1Dismiss = button1Dismiss;
	}

	public String getButton2Id() {
		return button2Id;
	}

	public void setButton2Id(String button2Id) {
		this.button2Id = button2Id;
	}

	public String getButton2Dismiss() {
		return button2Dismiss;
	}

	public void setButton2Dismiss(String button2Dismiss) {
		this.button2Dismiss = button2Dismiss;
	}
	
	public String getInputLabel() {
		return inputLabel;
	}

	public void setInputLabel(String inputLabel) {
		this.inputLabel = inputLabel;
	}

	public String getInputId() {
		return inputId;
	}

	public void setInputId(String inputId) {
		this.inputId = inputId;
	}

	public String getInputPlaceholder() {
		return inputPlaceholder;
	}

	public void setInputPlaceholder(String inputPlaceholder) {
		this.inputPlaceholder = inputPlaceholder;
	}

}
