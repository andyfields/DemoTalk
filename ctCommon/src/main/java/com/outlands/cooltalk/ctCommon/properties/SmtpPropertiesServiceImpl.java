package com.outlands.cooltalk.ctCommon.properties;

/**
 * @author Andy Fields
 *
 * Spring class to read and hold global properties for server.  It is a singelton.
 */
public class SmtpPropertiesServiceImpl extends OLPropertiesServiceImpl implements SmtpPropertiesService {

	public SmtpPropertiesServiceImpl(String propsFile) {
		super(propsFile);
	}

	public String getSmtpServer() {	
		return getProperties().getProperty("SMTPServer");
	}

	public String getConfirmReplyAddress() {
		return getProperties().getProperty("ConfirmReplyAddress");
	}

	public int getSmtpPort() {
		return Integer.parseInt(getProperties().getProperty("SMTPPort"));
	}
	
	public String getSmtpUserLogon() {
		return getProperties().getProperty("SMTPUserLogon");	
	}
	
	public String getSmtpUserPassword() {
		return getProperties().getProperty("SMTPUserPassword");
		
	}
	
}
