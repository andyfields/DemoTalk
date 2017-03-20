package com.outlands.cooltalk.web.displayBeans;


/**
 * Bean for setting and getting fields in ResetEmailJsp servlet.
 * 
 */
public class SendResetPasswordBean extends DisplayBean {

	private String resetEMail = "";		// User eMail field.

	public String getResetEMail() {
		return resetEMail;
	}

	public void setResetEMail(String resetEMail) {
		this.resetEMail = resetEMail;
	}
		
}
