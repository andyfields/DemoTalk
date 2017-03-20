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
 * One entry for each message and each user who has read it.
 * 
 * Good candidate for NoSQL.
 * @author Owner
 * @version 1.0
 * @created 09-Jun-2012 4:30:46 PM
 */
@Entity
public class TReadBy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	@ManyToOne
	@JoinColumn(name="MessageIdNumber")
	private TMessage message;
	
	@ManyToOne
	@JoinColumn(name="UserIdNumber")
	private TUser user;
	
	private Date dateRead;

	public TReadBy(){

	}

	public TReadBy(TMessage message, TUser tUser, Date dateRead){
		this.message = message;
		this.user = tUser;
		this.dateRead = dateRead;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TMessage getMessage() {
		return message;
	}

	public void setMessage(TMessage message) {
		this.message = message;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public Date getDateRead() {
		return dateRead;
	}

	public void setDateRead(Date dateRead) {
		this.dateRead = dateRead;
	}

}