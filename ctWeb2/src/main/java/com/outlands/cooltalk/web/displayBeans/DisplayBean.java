package com.outlands.cooltalk.web.displayBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayBean {

	private List<String>successMessages = new ArrayList<>();
	
	public DisplayBean(){}
	
	public DisplayBean(String successMessage) {
		super();
		this.successMessages.add(successMessage);
		
	}

	public DisplayBean(String[] successMessages) {
		super();
		this.successMessages = Arrays.asList(successMessages);
	}
	
	public void addSuccessMessage(String successMessage) {
		successMessages.add(successMessage);
	}

	public List<String> getSuccessMessages() {
		return successMessages;
	}

	public void setSuccessMessages(List<String> successMessages) {
		this.successMessages = successMessages;
	}
	
}
