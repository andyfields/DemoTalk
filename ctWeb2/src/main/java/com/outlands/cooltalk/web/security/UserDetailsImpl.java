package com.outlands.cooltalk.web.security;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.outlands.cooltalk.ctCommon.constants.SitePermission;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	protected final Log logger = LogFactory.getLog(getClass());

	private Collection<GrantedAuthority> authorities;
	
	private long userId;
	private String username;
	private String eMail;
	private String password = null;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	// Random bytes for hashing.  Most common use is secret value to assure that hidden fields in forms
	//   are not altered.
	private byte[] sessionSalt;		
	
	//@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	//@Override
	public String getPassword() {
		return password;
	}

	//@Override
	public String getUsername() {
		return username;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	//@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	//@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	//@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	//@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public byte[] getSessionSalt() {
		return sessionSalt;
	}

	public void setSessionSalt(byte[] sessionSalt) {
		this.sessionSalt = sessionSalt;
	}

	/**
	 * True if user is admin or super admin.
	 * 
	 * @return True if user is an admin or super admin.
	 */
	public boolean isAdmin() {
		for (GrantedAuthority auth : authorities) {
			if (auth.getAuthority().equals(SitePermission.ROLE_ADMIN) || 
					auth.getAuthority().equals(SitePermission.ROLE_SUPER_ADMIN)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * True if user, admin or super admin set.
	 * 
	 * @return true if authenticated as user or above.
	 */
	public boolean isUser() {
		logger.info("Enter");
		for (GrantedAuthority auth : authorities) {
			if (auth.getAuthority().equals(SitePermission.ROLE_ADMIN) || 
					auth.getAuthority().equals(SitePermission.ROLE_SUPER_ADMIN) ||
					auth.getAuthority().equals(SitePermission.ROLE_USER)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [authorities=" + authorities + ", memberId="
				+ userId + ", username=" + username + ", eMail=" + eMail
				+ ", password=" + password + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired
				+ ", enabled=" + enabled + ", isAdmin()=" + isAdmin()
				+ ", isUser()=" + isUser() + "]";
	}


}
