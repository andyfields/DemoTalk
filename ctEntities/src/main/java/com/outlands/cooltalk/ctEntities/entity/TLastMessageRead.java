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
 * Used to record time a message was read by a user.
 * 
 * @author Owner
 * @version 1.0
 * @created 09-Jun-2012 4:30:03 PM
 */
@Entity
public class TLastMessageRead {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private TUser user;
	
	/**
	 * Last time this discussion read.
	 */
	@ManyToOne
	@JoinColumn(name="MessageId")
	private TMessage message;
	
	/**
	 * Last time this discussion read.
	 */
	@ManyToOne
	@JoinColumn(name="DiscussionId")
	private TDiscussion discussion;
	
	private Date lastReadDate;

	public TLastMessageRead(){

	}

	/**
	 * @param discussion
	 * @param user
	 * @param message
	 * @param lastReadDate
	 */
	public TLastMessageRead(TUser user, TMessage message, Date lastReadDate) {
		super();
		this.user = user;
		this.message = message;
		this.discussion = message.getDiscussion();
		this.lastReadDate = lastReadDate;
	}



	public void finalize() throws Throwable {

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

	public TMessage getMessage() {
		return message;
	}

	public void setMessage(TMessage message) {
		this.message = message;
	}

	public Date getLastReadDate() {
		return lastReadDate;
	}

	public void setLastReadDate(Date lastReadDate) {
		this.lastReadDate = lastReadDate;
	}

	public TDiscussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(TDiscussion discussion) {
		this.discussion = discussion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TLastMessageRead other = (TLastMessageRead) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean identical(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TLastMessageRead other = (TLastMessageRead) obj;
		if (discussion == null) {
			if (other.discussion != null)
				return false;
		} else if (discussion.getId() != other.discussion.getId())
			return false;
		if (id != other.id)
			return false;
		if (lastReadDate == null) {
			if (other.lastReadDate != null)
				return false;
		} else if (!lastReadDate.equals(other.lastReadDate))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (message.getId() != other.message.getId())
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (user.getId() != other.user.getId())
			return false;
		return true;
	}	
	
}