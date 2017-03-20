package com.outlands.cooltalk.web.displayBeans;


/**
 * Bean for setting and getting fields in RegistrationJsp servlet.
 * 
 */
public class ResetPasswordByCodeBean extends DisplayBean {

	private String resetPasswordCode = "";		// User eMail field.
	private String inputPassword = "";			// Password field.
	
	private String btnSubmit = "";	

	public ResetPasswordByCodeBean() {}

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public String getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(String btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

}
