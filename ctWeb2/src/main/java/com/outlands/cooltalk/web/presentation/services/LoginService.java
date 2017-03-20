package com.outlands.cooltalk.web.presentation.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.outlands.cooltalk.ctCommon.constants.OLSingleMessageConstants;
import com.outlands.cooltalk.ctCommon.constants.SitePermission;
import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLHashUtility;
import com.outlands.cooltalk.web.security.HashUtility;
import com.outlands.cooltalk.web.security.UserDetailsImpl;
import com.outlands.cooltalk.web.services.UserService;

@Service
public class LoginService {
	
	@Autowired
	private HashUtility hashUtility;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OLHashUtility olHashUtility;
	
	/**
	 * Given login bean, check to see if user login is valid.  
	 * 
	 * @return
	 * @throws DaoException 
	 */
	public boolean checkLogin(TUser user, String password, BindingResult result) throws DaoException {
		boolean bSuccess = false;
		
		if (user == null) {
			
			// No member found with this eMail.
			result.reject(OLSingleMessageConstants.LOGIN_INVALID_EMAIL_OR_PASSWORD.getMessageKey(), ".Invalid email or password.");
			
		} else if (user.getUserStatus() != TUser.UserStatus.USER_CONFIRMED){
			// Registration never confirmed.
			result.reject(OLSingleMessageConstants.LOGIN_REGISTRATION_PENDING.getMessageKey(), ".Registration email not confirmed.");
			
		} else if (userService.isSuspended(user)){
			
			String[] args = new String[1];
			SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm");
			Date suspensionEnd = userService.getSuspensionEndTime(user);
			args[0] = fmt.format(suspensionEnd.getTime());
			result.reject(OLSingleMessageConstants.LOGIN_SUSPENDED.getMessageKey(), args, "Login suspended");
			
		} else {
			// Hash the entered password. Does it match stored value?
			if (Arrays.equals(olHashUtility.hash(user.getPasswordSalt(), password), user.getPasswordHash())) {
				// Successful login.
				bSuccess = true;
				
			} else {
				// Invalid password.
				bSuccess = false;
				result.reject(OLSingleMessageConstants.LOGIN_INVALID_EMAIL_OR_PASSWORD.getMessageKey(), ".Invalid email or password.");
			}
			
		}
		
		return bSuccess;
	}
	
	/**
	 * Set admin rights.  All parameters may be null for generic login.
	 * 
	 * @param user
	 * @param userId
	 * @param password
	 */
	public Authentication getAuthenticationOjbect(TUser user, String password) {
		GrantedAuthority grantGuest = 
				new SimpleGrantedAuthority(SitePermission.ROLE_GUEST.name());
		GrantedAuthority grantUser = 
				new SimpleGrantedAuthority(SitePermission.ROLE_USER.name());
		GrantedAuthority grantAdmin = 
				new SimpleGrantedAuthority(SitePermission.ROLE_ADMIN.name());
		GrantedAuthority grantSuperAdmin  = 
				new SimpleGrantedAuthority(SitePermission.ROLE_SUPER_ADMIN.name());
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// Set authorities.  Always at least guest.
		authorities.add(grantGuest);
		if (user != null && user.getPermission() == SitePermission.ROLE_USER) {
			authorities.add(grantUser);
		}
		if (user != null && user.getPermission() == SitePermission.ROLE_ADMIN) {
			authorities.add(grantUser);
			authorities.add(grantAdmin);
		}
		if (user != null && user.getPermission() == SitePermission.ROLE_SUPER_ADMIN) {
			authorities.add(grantUser);
			authorities.add(grantAdmin);
			authorities.add(grantSuperAdmin);
		}
			
		// Set UserDetails object for principal.
		UserDetailsImpl details =  new UserDetailsImpl();
		details.setAccountNonExpired(true);
		details.setAccountNonLocked(true);
		details.setAuthorities(authorities);
		details.setCredentialsNonExpired(true);
		details.setEnabled(true);
		details.setPassword(null);
		details.setSessionSalt(hashUtility.getRandomBuf(20));
		
		if (user != null) {
			// Set UserDetails object for principal.
			details.setUserId(user.getId());
			details.seteMail(user.getPrimaryEMail());
			details.setPassword(password);
			details.setUsername(user.getDisplayName());
			
		} else {
			details.setUserId(-1);
			details.seteMail("Guest login");
			details.setUsername("Guest login");
		}

		Authentication auth = new UsernamePasswordAuthenticationToken(details, password,
				authorities);
		
		return auth;
		
	}

}
