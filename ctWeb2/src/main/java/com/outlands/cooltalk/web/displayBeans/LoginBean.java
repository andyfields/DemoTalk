package com.outlands.cooltalk.web.displayBeans;


/**
 * Bean for setting and getting fields in LoginJsp servlet.
 * 
 */
public class LoginBean {

	private String inputEmail = "";		// User eMail field.
	private String inputPassword = "";	// Password field.
	
	private String btnLogin = "";		// Value of btnLogin.
	
	public LoginBean() {}
	
	public String getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public String getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(String btnLogin) {
		this.btnLogin = btnLogin;
	}
	
}
