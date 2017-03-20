package com.outlands.cooltalk.ctCommon.properties;

/**
 * @author Andy Fields
 *
 * Spring class to read and hold global properties for server.  It is a singelton.
 */
public class MailPropertiesServiceImpl extends OLPropertiesServiceImpl implements MailPropertiesService {

	public MailPropertiesServiceImpl(String propsFile) {
		super(propsFile);
	}

	public String getMailServer() {
		return getProperties().getProperty("MailServer");
	}

	public String getFrom() {
		return getProperties().getProperty("From");
	}

	public String getTo() {
		return getProperties().getProperty("To");
	}

	public String getReplyTo() {
		return getProperties().getProperty("ReplyTo");
	}

	public String getServerId() {
		return getProperties().getProperty("ServerId");
	}

	public String getServerPassword() {
		return getProperties().getProperty("ServerPassword");
	}
	
}
