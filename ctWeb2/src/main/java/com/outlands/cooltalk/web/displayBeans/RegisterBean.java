package com.outlands.cooltalk.web.displayBeans;


/**
 * Bean for setting and getting fields in RegistrationJsp servlet.
 * 
 */
public class RegisterBean extends DisplayBean {

	private String registrationEMail = "";		// User eMail field.
	private String registrationPassword = "";	// Password field.
	private String displayName = "";	
	
	private String btnRegister = "";	

	public RegisterBean() {}

	public String getRegistrationEMail() {
		return registrationEMail;
	}

	public void setRegistrationEMail(String registrationEMail) {
		this.registrationEMail = registrationEMail;
	}

	public String getRegistrationPassword() {
		return registrationPassword;
	}

	public void setRegistrationPassword(String registrationPassword) {
		this.registrationPassword = registrationPassword;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(String btnRegister) {
		this.btnRegister = btnRegister;
	}
	
}
