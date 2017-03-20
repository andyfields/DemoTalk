package com.outlands.cooltalk.ctEntities.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * @author Owner
 * @version 1.0
 * @created 04-Jun-2012 12:16:24 PM
 */
@Entity
public class TSuspensionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private TUser user;
	private String reason;
	
	@ManyToOne
	@JoinColumn(name="messageReferenceId")
	private TMessage messageReference;
	
	private Date startDate;
	private Date endDate;

	public TSuspensionDetail(){

	}

	public TSuspensionDetail(TUser user, String reason, Date startDate, Date endDate) {
		super();
		this.user = user;
		this.reason = reason;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public TMessage getMessageReference() {
		return messageReference;
	}

	public void setMessageReference(TMessage messageReference) {
		this.messageReference = messageReference;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void finalize() throws Throwable {

	}

}