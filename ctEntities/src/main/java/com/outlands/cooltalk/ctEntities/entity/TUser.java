package com.outlands.cooltalk.ctEntities.entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.outlands.cooltalk.ctCommon.constants.SitePermission;

/**
 * @author Andy Fields
 * @version 1.0
 * @created 04-Jun-2012 12:06:25 PM
 */
@Entity
public class TUser {
	
	public static enum UserStatus {
		CONFIRMATION_SENT,
		USER_CONFIRMED,	
		USER_DELETED,	
		CONFIRMATION_RESENT;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Version
	private long version;
	
	private String primaryEMail;	// Max 255
	private byte[] passwordSalt;	// Size 20
	
	private byte[] passwordHash;	// Size 20
	private String displayName;
	private Date confirmationSent = null;
	private String confirmationCode = null;
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	
	@Column(name="changeEmailCode")
	private String changeEmailCode = null;
	private Date changeEmailSent = null;
	private String changePasswordCode = null;
	private Date changePasswordSent = null;
	private String tagline;
	//private blob profilePhoto;
	private long suspensionLength = 0;  // days.
	private Date suspensionStart = null;
	
	/**
	 * User, Admin, or SuperAdmin
	 */
	@Enumerated
	private SitePermission permission;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<TDisplayName> displayNames;
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<TSuspensionDetail> suspensionDetails;
	
	@ManyToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<TPcIdCookie> pcIdCookies;
	//public LastRead m_LastRead;
	@OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
	private List<TMessage> messages;
	@OneToMany(mappedBy="discussionCreator", fetch=FetchType.LAZY)
	private List<TDiscussion> discussionsCreated;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<TLastMessageRead>messagesRead;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<TReadBy>readBy;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TUser other = (TUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

	//public Discussion m_Discussion;
	@OneToMany(mappedBy="subscriber", fetch=FetchType.LAZY)
	public List<TDiscussion>subscriptions;

	public TUser(){
		primaryEMail = null;
		passwordSalt = null;
		passwordHash = null;
		displayName = null;
		tagline = null;
		displayNames = new ArrayList<TDisplayName>();
		discussionsCreated = new ArrayList<TDiscussion>();
		messages = new ArrayList<TMessage>();
		pcIdCookies = new ArrayList<TPcIdCookie>();
		messagesRead = new ArrayList<TLastMessageRead>();
		readBy = new ArrayList<TReadBy>();
	}

	public void finalize() throws Throwable {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrimaryEMail() {
		return primaryEMail;
	}

	public void setPrimaryEMail(String primaryEMail) {
		this.primaryEMail = primaryEMail;
	}

	public byte[] getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(byte[] passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getConfirmationSent() {
		return confirmationSent;
	}

	public void setConfirmationSent(Date confirmationSent) {
		this.confirmationSent = confirmationSent;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public long getSuspensionLength() {
		return suspensionLength;
	}

	public void setSuspensionLength(long suspensionLength) {
		this.suspensionLength = suspensionLength;
	}

	public Date getSuspensionStart() {
		return suspensionStart;
	}

	public void setSuspensionStart(Date suspensionStart) {
		this.suspensionStart = suspensionStart;
	}

	public SitePermission getPermission() {
		return permission;
	}

	public void setPermission(SitePermission permission) {
		this.permission = permission;
	}

	public List<TDisplayName> getDisplayNames() {
		return displayNames;
	}

	public void setDisplayNames(List<TDisplayName> displayNames) {
		this.displayNames = displayNames;
	}

	public List<TSuspensionDetail> getSuspensionDetails() {
		return suspensionDetails;
	}

	public void setSuspensionDetails(List<TSuspensionDetail> suspensionDetails) {
		this.suspensionDetails = suspensionDetails;
	}

	public List<TDiscussion> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscription(List<TDiscussion> subscription) {
		this.subscriptions = subscription;
	}

	public List<TPcIdCookie> getPcIdCookies() {
		return pcIdCookies;
	}

	public void setPcIdCookies(List<TPcIdCookie> pcIdCookies) {
		this.pcIdCookies = pcIdCookies;
	}

	public List<TMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<TMessage> messages) {
		this.messages = messages;
	}

	public List<TDiscussion> getDiscussionsCreated() {
		return discussionsCreated;
	}

	public List<TLastMessageRead> getMessagesRead() {
		return messagesRead;
	}

	public List<TReadBy> getReadBy() {
		return readBy;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getChangeEmailCode() {
		return changeEmailCode;
	}

	public void setChangeEmailCode(String changeEmailCode) {
		this.changeEmailCode = changeEmailCode;
	}

	public Date getChangeEmailSent() {
		return changeEmailSent;
	}

	public void setChangeEmailSent(Date changeEmailSent) {
		this.changeEmailSent = changeEmailSent;
	}

	public String getChangePasswordCode() {
		return changePasswordCode;
	}

	public void setChangePasswordCode(String changePasswordCode) {
		this.changePasswordCode = changePasswordCode;
	}

	public Date getChangePasswordSent() {
		return changePasswordSent;
	}

	public void setChangePasswordSent(Date changePasswordSent) {
		this.changePasswordSent = changePasswordSent;
	}

	public boolean identical(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		TUser other = (TUser) obj;
		if (confirmationSent == null) {
			if (other.confirmationSent != null)
				return false;
		} else if (!confirmationSent.equals(other.confirmationSent))
			return false;
		
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		
		if (displayNames == null) {
			if (other.displayNames != null)
				return false;
		} else if (other.displayNames == null)
			return false;
		
		if (displayNames != null && displayNames.size() != other.displayNames.size())
			return false;
		
		if (id != other.id)
			return false;
		
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (other.messages != null)
			return false;
		
		if (messagesRead == null) {
			if (other.messagesRead != null)
				return false;
		} else if (other.messagesRead == null)
			return false;
		
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (other.messages == null)
			return false;
		
		if (!Arrays.equals(passwordHash, other.passwordHash))
			return false;
		
		if (!Arrays.equals(passwordSalt, other.passwordSalt))
			return false;
		
		if (pcIdCookies == null) {
			if (other.pcIdCookies != null)
				return false;	
		} else if (other.pcIdCookies != null)
			return false;
		
		if (permission != other.permission)
			return false;
		
		if (primaryEMail == null) {
			if (other.primaryEMail != null)
				return false;
		} else if (!primaryEMail.equals(other.primaryEMail))
			return false;
		
		if (subscriptions == null) {
			if (other.subscriptions != null)
				return false;
		} else if (other.subscriptions != null)
			return false;
		
		if (suspensionDetails == null) {
			if (other.suspensionDetails != null)
				return false;
		} else if (other.suspensionDetails != null)
			return false;
		
		if (suspensionLength != other.suspensionLength)
			return false;
		
		if (suspensionStart == null) {
			if (other.suspensionStart != null)
				return false;
		} else if (!suspensionStart.equals(other.suspensionStart))
			return false;
		
		if (tagline == null) {
			if (other.tagline != null)
				return false;
		} else if (!tagline.equals(other.tagline))
			return false;
		
		if (confirmationCode == null) {
			if (other.confirmationCode != null)
				return false;
		} else if (!confirmationCode.equals(other.confirmationCode))
			return false;
		
		return true;
	}
	
	

}