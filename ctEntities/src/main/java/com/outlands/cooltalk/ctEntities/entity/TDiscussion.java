package com.outlands.cooltalk.ctEntities.entity;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * @author Owner
 * @version 1.0
 * @created 09-Jun-2012 4:29:37 PM
 */
@Entity
public class TDiscussion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	/**
	 * Max length 50
	 */
	private String name;
	private String description;	// Max 2K.
	private Date   created;
	
	@OneToMany(mappedBy="discussion", fetch=FetchType.LAZY)
	private List<TLastMessageRead> lastMessageRead;
	
	@ManyToOne
	@JoinColumn(name="DiscussionCreatorId")
	private TUser discussionCreator;
	
	@ManyToOne
	@JoinColumn(name="MessageSectionId")
	private TMessageSection messageSection;
	@OneToMany(mappedBy="discussion", fetch=FetchType.LAZY)
	private List<TMessage> tMessage;
	
	@ManyToOne
	@JoinColumn(name="subscriberId")
	private TUser subscriber;

	public TDiscussion(){

	}
	
	/**
	 * @param name
	 * @param description
	 * @param created
	 * @param discussionCreator
	 */
	public TDiscussion(String name, String description, Date created,
			TUser discussionCreator, TMessageSection messageSection) {
		super();
		this.name = name;
		this.description = description;
		this.created = created;
		this.discussionCreator = discussionCreator;
		this.messageSection = messageSection;
		
		lastMessageRead = new Vector<TLastMessageRead>();
		tMessage = new Vector<TMessage>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TLastMessageRead> getLastMessageRead() {
		return lastMessageRead;
	}

	public void setLastMessageRead(List<TLastMessageRead> lastMessageRead) {
		this.lastMessageRead = lastMessageRead;
	}

	public List<TMessage> getMessage() {
		return tMessage;
	}

	public void setMessage(List<TMessage> tMessage) {
		this.tMessage = tMessage;
	}

	public TUser getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(TUser subscriber) {
		this.subscriber = subscriber;
	}

	public TMessageSection getMessageSection() {
		return messageSection;
	}

	public void setMessageSection(TMessageSection messageSection) {
		this.messageSection = messageSection;
	}
	
	

	public Date getCreated() {
		return created;
	}



	public void setCreated(Date created) {
		this.created = created;
	}



	public TUser getDiscussionCreator() {
		return discussionCreator;
	}



	public void setDiscussionCreator(TUser discussionCreator) {
		this.discussionCreator = discussionCreator;
	}



	public List<TMessage> gettMessage() {
		return tMessage;
	}



	public void settMessage(List<TMessage> tMessage) {
		this.tMessage = tMessage;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TDiscussion other = (TDiscussion) obj;
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
		
		TDiscussion other = (TDiscussion) obj;
		if (created == null) {
			if (other.created != null)
				return false;
			
		} else if (!created.equals(other.created))
			return false;
		
		if (description == null) {
			if (other.description != null)
				return false;
			
		} else if (!description.equals(other.description))
			return false;
		
		if (id != other.id)
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (this.messageSection == null) {
			if (other.messageSection != null)
				return false;
		} else if (!messageSection.equals(other.messageSection))
			return false;
		
		return true;
	}



	public void finalize() throws Throwable {

	}

	
}