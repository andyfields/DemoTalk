package com.outlands.cooltalk.web.security;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctCommon.constants.SitePermission;
import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctEntities.entity.TUser;


/**
 * Authenticate ID and password for Spring security.
 * 
 * This class is unused.  We do not use Spring login type screens.  Instead we create an 
 * 		UsernamePasswordAuthenticationToken and store in the SecurityContext ourselves.  
 * 
 * @author Andy Fields
 *
 */
@Service("AuthProv")
public class AuthenticationProviderImpl  implements AuthenticationProvider {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private TUserDataService userDataService;
	
	@Autowired
	private HashUtility hashUtility;
	
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		logger.info("Authenticate");
		
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)auth;
		
		if (!(token.getName() instanceof String) || !(token.getCredentials() instanceof String)) {
			return null;
		}
		
		GrantedAuthority grantGuest = new SimpleGrantedAuthority(SitePermission.ROLE_GUEST.name());
		GrantedAuthority grantUser = new SimpleGrantedAuthority(SitePermission.ROLE_USER.name());
		GrantedAuthority grantAdmin = new SimpleGrantedAuthority(SitePermission.ROLE_ADMIN.name());
		GrantedAuthority grantSuperAdmin  = new SimpleGrantedAuthority(SitePermission.ROLE_SUPER_ADMIN.name());
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		// Validate through the database.
		logger.info("token: " + token);
		TUser user = null;
		try {
			user = userDataService.getUserByPrimaryEMail(token.getName());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Error accessing database.");
		}
			
		if (user == null || !isUserValid(user, token)) {
			
			logger.info("Invalid username or password");
			throw new BadCredentialsException("Invalid username or password");
		}
		
		// Set authorities.  Always at least guest.
		authorities.add(grantGuest);
		if (user.getPermission() == SitePermission.ROLE_USER) 
			authorities.add(grantUser);
		if (user.getPermission() == SitePermission.ROLE_ADMIN) 
			authorities.add(grantAdmin);
		if (user.getPermission() == SitePermission.ROLE_SUPER_ADMIN)
			authorities.add(grantSuperAdmin);
			
		// Set UserDetails object for principal.
		UserDetailsImpl details =  new UserDetailsImpl();
		details.setUserId(user.getId());
		details.setAccountNonExpired(true);
		details.setAccountNonLocked(true);
		details.setAuthorities(authorities);
		details.setCredentialsNonExpired(true);
		details.seteMail(auth.getName());
		details.setEnabled(true);
		details.setPassword(null);
		details.setUsername(user.getDisplayName());
		details.setSessionSalt(hashUtility.getRandomBuf(20));
		
		Authentication result = new UsernamePasswordAuthenticationToken(details, token.getCredentials(),
				authorities);
		return result;
		
	}
	
	/**
	 * Validate login against user object.
	 * 
	 * @param user
	 * @param token
	 * @return
	 */
	private boolean isUserValid(TUser user, UsernamePasswordAuthenticationToken token) {
		
		// EMail OK?
		if (!user.getPrimaryEMail().equals(token.getName()))
			return false;
		
		// Password hashes match? 
		byte[] pwSalt = user.getPasswordSalt();
		byte[] pwHash = hashUtility.hash(pwSalt, "" + token.getCredentials());
		
		if (pwHash.length != user.getPasswordHash().length) 
			return false;
		
		ByteBuffer bufHash = ByteBuffer.allocate(pwHash.length);
		bufHash.put(pwHash);
		ByteBuffer bufStoredHash = ByteBuffer.allocate(pwHash.length);
		bufStoredHash.put(user.getPasswordHash());
		if (!bufStoredHash.equals(bufHash))
			return false;
		
		return true;
	}

	public boolean supports(Class<?> cls) {
		logger.info("class: " + cls);
		
		boolean bSupports = (UsernamePasswordAuthenticationToken.class.isAssignableFrom(cls));
		logger.info("Supported: " + bSupports);
		
		return bSupports;
	}

}
