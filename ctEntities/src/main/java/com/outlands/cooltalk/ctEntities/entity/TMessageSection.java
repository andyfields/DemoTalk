package com.outlands.cooltalk.ctEntities.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * @author Owner
 * @version 1.0
 * @created 09-Jun-2012 4:30:27 PM
 */
@Entity
public class TMessageSection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@Version
	private long version;
	
	private String name;	// Max 255
	
	private Date created;
	
	@OneToMany(mappedBy="messageSection", fetch=FetchType.EAGER)
	private List<TDiscussion> discussions;
	
	public TMessageSection(){
		this.discussions = new ArrayList<TDiscussion>(); 
	}

	public TMessageSection(String name, Date created) {
		super();
		this.name = name;
		this.created = created;
		this.discussions = new ArrayList<TDiscussion>(); 
	}
	
	public void finalize() throws Throwable {

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

	public List<TDiscussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<TDiscussion> discussions) {
		this.discussions = discussions;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean identical(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TMessageSection other = (TMessageSection) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}