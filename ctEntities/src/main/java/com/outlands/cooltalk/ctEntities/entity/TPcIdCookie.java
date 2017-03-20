package com.outlands.cooltalk.ctEntities.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

/**
 * ID cookies of PCs known to be identifed with this user.  Many to many.
 * @author Owner
 * @version 1.0
 * @created 04-Jun-2012 12:24:32 PM
 */
@Entity
public class TPcIdCookie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<TUser> user;
	
	private byte[] pcCode;

	public TPcIdCookie(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getPcCode() {
		return pcCode;
	}

	public List<TUser> getUser() {
		return user;
	}

	public void setUser(List<TUser> user) {
		this.user = user;
	}

	public void setPcCode(byte[] pcCode) {
		this.pcCode = pcCode;
	}

	public void finalize() throws Throwable {

	}

}