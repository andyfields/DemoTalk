package com.outlands.cooltalk.ctCommon.constants;

public enum OLDialogMessageConstants {
	REGISTRATION_CONFIRM_ERROR_DIALOG("registration.confirm.error"),
	REGISTRATION_CONFIRM_ALREADYRECEIVED_DIALOG("registration.confirm.alreadyreceived"),
	REGISTRATION_CONFIRM_CODEEXPIRED_DIALOG("registration.confirm.codeexpired"),
	REGISTRATION_CONFIRM_OK_DIALOG("registration.confirm.ok"),
	
	REGISTER_EMAIL_SENT_DIALOG("register.email.sent"),	
	REGISTER_SUCCESS_DEMO_DIALOG("register.success.demo"),
			
	RESETPASSWORD_EMAIL_SENT_DIALOG("resetpassword.email.sent"),	

	RESETPASSWORD_BY_CODE_ERROR_DIALOG("resetpassword.by.code.error"),
	RESETPASSWORD_BY_CODE_EXPIRED_DIALOG("resetpassword.by.code.expired"),

	RESETPASSWORD_BY_CODE_SUCCESS_DIALOG("resetpassword.by.code.success");	

	private String messageKey;
	
	OLDialogMessageConstants(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
}
	
