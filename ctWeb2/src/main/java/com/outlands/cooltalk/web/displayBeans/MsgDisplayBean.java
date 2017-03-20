package com.outlands.cooltalk.web.displayBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.outlands.cooltalk.ctCommon.constants.OLDialogMessageConstants;

public class MsgDisplayBean {
	private String title;
	private String banner;
	private List<String>messages;
	private String forwardURL;	// Location for forwarding when OK is pressed.
	
	public MsgDisplayBean(String title, String banner, String message, String forwardURL) {
		super();
		this.title = title;
		this.banner = banner;
		this.messages = new ArrayList<>();
		this.messages.add(message);
		this.forwardURL = forwardURL;
	}
	
	public MsgDisplayBean(String title, String banner, String message[], String forwardURL) {
		super();
		this.title = title;
		this.banner = banner;
		this.messages = Arrays.asList(message);
		this.forwardURL = forwardURL;
	}
	
	// Requires an id in the format where {id}.title, {id}.banner and {id}.message are in the message.properties file.
	public MsgDisplayBean(MessageSource source, Locale locale, OLDialogMessageConstants msgSourceId, String forwardURL) {
		super();
		
		this.title = source.getMessage(msgSourceId.getMessageKey() + ".title", null, locale);
		this.banner = source.getMessage(msgSourceId.getMessageKey() + ".banner", null, locale);
		int count = Integer.parseInt(source.getMessage(msgSourceId.getMessageKey() + ".count", null, locale));
		this.messages = new ArrayList<>();
		
		for (int i = 0; i < count; i++) {
			this.messages.add(source.getMessage(msgSourceId.getMessageKey() + ".message" + i, null, locale));
		}
		this.forwardURL = forwardURL;
	}
	
	public MsgDisplayBean(MessageSource source, Locale locale, OLDialogMessageConstants msgSourceId, String[] args, String forwardURL) {
		super();
		
		this.title = source.getMessage(msgSourceId.getMessageKey() + ".title", null, locale);
		this.banner = source.getMessage(msgSourceId.getMessageKey() + ".banner", null, locale);
		int count = Integer.parseInt(source.getMessage(msgSourceId.getMessageKey() + ".count", null, locale));
		this.messages = new ArrayList<>();
		
		for (int i = 0; i < count; i++) {
			this.messages.add(source.getMessage(msgSourceId.getMessageKey() + ".message" + i, args, locale));
		}
		this.forwardURL = forwardURL;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public List<String> getMessages() {
		return messages;
	}
	public String getMessages(int i) {
		return messages.get(i);
	}
	public String getForwardURL() {
		return forwardURL;
	}
	public void setForwardURL(String forwardURL) {
		this.forwardURL = forwardURL;
	}
	
	public int getCount() {
		return this.messages.size();
	}
}
