package com.outlands.cooltalk.ctCommon.constants;

public enum OLEmailMessageConstants {
	REGISTRATION_CONFIRMATION_EMAIL("registration.email"),
	RESETPASSWORD_EMAIL("resetpassword.email");	
			
	private String messageKey;
	
	OLEmailMessageConstants(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
}
	

