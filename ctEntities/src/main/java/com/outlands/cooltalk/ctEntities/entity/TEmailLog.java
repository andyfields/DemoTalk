package com.outlands.cooltalk.ctEntities.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class TEmailLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	private Date dateSent;
	private String server;
	private String serverId;
	private String fromAdrs;
	private String toAdrs;
	private String replyToAdrs;
	private String subject;
	private String content;
	private int status;
	private String responseText;
	
	public TEmailLog () {}
	
	public TEmailLog(Date dateSent, String server, String serverId, String fromAdrs, String toAdrs, String replyToAdrs, String subject,
			String content, int status, String responseText) {
		super();
		this.dateSent = dateSent;
		this.server = server;
		this.serverId = serverId;
		this.fromAdrs = fromAdrs;
		this.toAdrs = toAdrs;
		this.replyToAdrs = replyToAdrs;
		this.subject = subject;
		this.content = content;
		this.status = status;
		this.responseText = responseText;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public Date getDateSent() {
		return dateSent;
	}
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getFromAdrs() {
		return fromAdrs;
	}
	public void setFromAdrs(String fromAdrs) {
		this.fromAdrs = fromAdrs;
	}
	public String getToAdrs() {
		return toAdrs;
	}
	public void setToAdrs(String toAdrs) {
		this.toAdrs = toAdrs;
	}
	public String getReplyToAdrs() {
		return replyToAdrs;
	}
	public void setReplyToAdrs(String replyToAdrs) {
		this.replyToAdrs = replyToAdrs;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	
}
