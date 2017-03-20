package com.outlands.cooltalk.ctCommon.services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctCommon.constants.OLEmailMessageConstants;

@Service
public class MessageService {

	@Autowired
	@Qualifier("messageSource")
	MessageSource messageSource;
	
	public static class  EMailMessageBean {
		private String subject;
		private String message;
		
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	public EMailMessageBean createEmailMesssageBean(OLEmailMessageConstants emailId, String[] args, Locale locale) {
		EMailMessageBean bean = new EMailMessageBean();
		
		bean.setSubject(messageSource.getMessage(emailId.getMessageKey() + ".subject", args, locale));
		
		int count = Integer.parseInt(messageSource.getMessage(emailId.getMessageKey()  + ".count", null, locale));
		
		StringBuilder msg = new StringBuilder();
		for (int n = 1; n <= count; n++) {
			if (n == 1) {
				msg.append("\n\n");
			}
			msg.append(messageSource.getMessage(emailId.getMessageKey()  + ".message" + n, args, locale));
		}
		bean.setMessage(msg.toString());
		return bean;
	}
}
