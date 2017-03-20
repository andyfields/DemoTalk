package com.outlands.cooltalk.ctEntities.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * @author Andy Fields
 * @version 1.0
 * @created 04-Jun-2012 12:32:02 PM
 */
@Entity
public class TMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	@ManyToOne()
	@JoinColumn(name="DiscussionId")
	private TDiscussion discussion;
	
	/**
	 * UserId of message creator.
	 */
	@ManyToOne
	@JoinColumn(name="CreatorId")
	private TUser creator;
	/**
	 * If message is reply to another message, which one (displayID).  May be a reply
	 * to several messages and message may have several replies.
	 */
	@ManyToMany(mappedBy="replies")
	private List<TMessage> replyTo;
	
	@ManyToMany
	private List<TMessage>replies;
	
	private Date dateCreated;
	
	private Date dateModified;
	
	private boolean removed;
	
	/**
	 * We do not display primary ID.  We instead display an ID based on creator and
	 * date.
	 */
	private String displayId;
	
	@OneToMany(mappedBy="message", fetch=FetchType.LAZY)
	public List<TReadBy> readBy;
	public String message;
	
	@OneToMany(mappedBy="messageReference", fetch=FetchType.LAZY)
	private List<TSuspensionDetail> suspensionDetail;
	
	@OneToMany(mappedBy="message", fetch=FetchType.LAZY)
	private List<TLastMessageRead>lastMessageRead;

	public TMessage(){
		init();
	}
	

	/**
	 * @param discussion
	 * @param creator
	 * @param replyTo
	 * @param replies
	 * @param dateCreated
	 * @param displayId
	 */
	public TMessage(TDiscussion discussion, TUser creator, String message,
			List<TMessage> replyTo, Date dateCreated,
			String displayId) {
		super();
		
		this.init();
		
		this.discussion = discussion;
		this.creator = creator;
		this.message = message;
		if (replyTo != null)
			this.replyTo = replyTo;
		this.dateCreated = dateCreated;
		this.displayId = displayId;
		
	}
		
	private void init() {
		suspensionDetail = new ArrayList<TSuspensionDetail>();
		lastMessageRead = new ArrayList<TLastMessageRead>();
		readBy = new ArrayList<TReadBy>();
		replies = new ArrayList<TMessage>();
		replyTo = new ArrayList<TMessage>();
		this.dateModified = null;
	}


	public List<TSuspensionDetail> getSuspensionDetail() {
		return suspensionDetail;
	}

	public void setSuspensionDetail(List<TSuspensionDetail> suspensionDetail) {
		this.suspensionDetail = suspensionDetail;
	}

	public List<TLastMessageRead> getLastMessageRead() {
		return lastMessageRead;
	}

	public void setLastMessageRead(List<TLastMessageRead> lastRead) {
		this.lastMessageRead = lastRead;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TDiscussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(TDiscussion discussion) {
		this.discussion = discussion;
	}

	public TUser getCreator() {
		return creator;
	}

	public void setCreator(TUser creator) {
		this.creator = creator;
	}

	public List<TMessage> getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(List<TMessage> replyTo) {
		this.replyTo = replyTo;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}


	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDisplayId() {
		return displayId;
	}

	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

	public List<TReadBy> getReadBy() {
		return readBy;
	}

	public void setReadBy(List<TReadBy> readBy) {
		this.readBy = readBy;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<TMessage> getReplies() {
		return replies;
	}

	public void setReplies(List<TMessage> replies) {
		this.replies = replies;
	}
	
	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}


	public boolean identical(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TMessage other = (TMessage) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateModified == null) {
			if (other.dateModified != null)
				return false;
		} else if (!dateModified.equals(other.dateModified))
			return false;
		if (discussion == null) {
			if (other.discussion != null)
				return false;
		} else if (!discussion.equals(other.discussion))
			return false;
		if (displayId == null) {
			if (other.displayId != null)
				return false;
		} else if (!displayId.equals(other.displayId))
			return false;
		if (id != other.id)
			return false;
		if (lastMessageRead == null) {
			if (other.lastMessageRead != null)
				return false;
		} else if (!lastMessageRead.equals(other.lastMessageRead))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (readBy == null) {
			if (other.readBy != null)
				return false;
		} else if (!readBy.equals(other.readBy))
			return false;
		if (removed != other.removed)
			return false;
		if (replies == null) {
			if (other.replies != null)
				return false;
		} else if (!replies.equals(other.replies))
			return false;
		if (replyTo == null) {
			if (other.replyTo != null)
				return false;
		} else if (!replyTo.equals(other.replyTo))
			return false;
		if (suspensionDetail == null) {
			if (other.suspensionDetail != null)
				return false;
		} else if (!suspensionDetail.equals(other.suspensionDetail))
			return false;
		return true;
	}


	public void finalize() throws Throwable {

	}
	
	

}