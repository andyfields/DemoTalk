package com.outlands.cooltalk.ctCommon.smtp;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.apache.log4j.Logger;


public class ApiAuthenticator extends Authenticator {
	
	PasswordAuthentication auth;
	
	Logger log = Logger.getLogger(this.getClass());
	
	public void setPasswordAuthentication(PasswordAuthentication auth) {
		this.auth = auth;
	}
	
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		log.info("Auth retrieved - Logon: " + 
				auth.getUserName() + ", pw: " + auth.getPassword().toString());
		return auth;
	}
}
