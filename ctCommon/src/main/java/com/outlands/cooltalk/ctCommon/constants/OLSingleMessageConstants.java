package com.outlands.cooltalk.ctCommon.constants;

public enum OLSingleMessageConstants {
	LOGIN_INVALID_EMAIL_OR_PASSWORD ("login.invalidEmailOrPassword"),
	LOGIN_REGISTRATION_PENDING("login.registration.pending"),
	LOGIN_SUSPENDED("login.suspended"),

	REGISTER_EMAIL_INVALID ("register.email.invalid"),
	REGISTER_EMAIL_DUPLICATE ("register.email.duplicate"),
	REGISTER_DUPLICATE_DISPLAY_NAME("register.duplicatedisplayname"),
	REGISTER_INTERNAL_ERROR("registration.internalerror"),
	REGISTER_EMAIL_SEND_ERROR("register.emailsenderror"),
	
	RESETPASSWORD_EMAIL_INVALID("resetpassword.email.invalid"),
	RESETPASSWORD_EMAIL_NOT_FOUND("resetpassword.email.notfound"),
	RESETPASSWORD_NOT_CONFIRMED("resetpassword.not.confirmed"),
	RESETPASSWORD_EMAIL_SEND_ERROR("resetpassword.emailsenderror"),

	RESETPASSWORD_BY_CODE_PASSWORD_INVALID("resetpassword.by.code.password.invalid.length"),
	
	// Sections page errors.
	SECTIONS_CREATE_DUPLICATE_NAME("sections.create.duplicate.name"),
	SECTIONS_NAME_EMPTY("sections.name.empty"),
	SECTIONS_EDIT_NOTFOUND("sections.edit.notfound");
	
	private String messageKey;
	
	OLSingleMessageConstants(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
}

