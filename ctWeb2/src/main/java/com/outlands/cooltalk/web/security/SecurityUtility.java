package com.outlands.cooltalk.web.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtility {
	protected static final Logger logger = Logger.getLogger(SecurityUtility.class);

	/**
	 * Get the user details object.
	 * 
	 * @return
	 */
	public UserDetails currentUserDetails(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof UserDetails)
	        	return (UserDetails)principal;
	        else 
	        	return null;
	    }
	    return null;
	}
	
	/**
	 * Returns true if this authority is granted to the current user.  
	 * (See SecurityContants for list of authorities.)
	 * 
	 * @param authority
	 * @return
	 */
//	public boolean isGrantedAuthority(int authority) {
//		// Get string version of the authority.
//		String sAuthority = SitePermission.getSitePermissionDisp(authority);
//		if (sAuthority == null)
//			throw new RuntimeException("No such authority: " + authority);
//		
//		UserDetails userDetails = currentUserDetails();
//		
//		for (GrantedAuthority auth : userDetails.getAuthorities()) {
//			if (sAuthority.equals(auth.getAuthority())) 
//				return true;
//		}
//		
//		return false;
//	}
	
	/**
	 * Get the session salt value that was created at log in.
	 * 
	 * @return
	 */
	public byte[] getSessionSalt() {
		
		UserDetailsImpl currUserDetails = (UserDetailsImpl)currentUserDetails();
		if (currUserDetails == null) 
			return null;
		
		return currUserDetails.getSessionSalt();
		
	}
	
	/**
	 * Set the authentication object.
	 * 
	 * @param auth
	 */
	public void setAuthentication(Authentication auth) {
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(auth);
	}
	
	/**
	 * Invalidate authentication.
	 * 
	 */
	public void invalidateAuthentication() {
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication auth = securityContext.getAuthentication();
	    
	    if (auth != null) {
	    	auth.setAuthenticated(false);
	    }

	}
	
	/**
	 * For debug purposes.
	 */
	public void displaySecurity() {
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();

	    if (authentication != null) {
	    	logger.info("  Security:");
	    	logger.info("  Credentials: " + authentication.getCredentials());
	    	logger.info("  Authenticated: " + authentication.isAuthenticated());
	    	
	    	logger.info("  Authorities");
	    	for (GrantedAuthority auth : authentication.getAuthorities()) {
	    		logger.info("    " + auth.getAuthority());
	    	}
	    }
	}
}
