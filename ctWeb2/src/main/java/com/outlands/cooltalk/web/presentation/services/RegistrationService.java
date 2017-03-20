package com.outlands.cooltalk.web.presentation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.outlands.cooltalk.ctCommon.constants.OLSingleMessageConstants;
import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLStringUtil;
import com.outlands.cooltalk.web.displayBeans.RegisterBean;
import com.outlands.cooltalk.web.services.UserService;

@Service
public class RegistrationService {
	
	@Autowired
	private TUserDataService tUserDataService;

	@Autowired
	private UserService userService;

	public void validateRegistrationRequest(RegisterBean bean, BindingResult result) {
		if (!OLStringUtil.isValidEmailAddress(bean.getRegistrationEMail())) {
			result.rejectValue("registrationEMail", OLSingleMessageConstants.REGISTER_EMAIL_INVALID.getMessageKey(), 
					".Invalid email address.");
			return;
		}
		
		TUser userByEmail = tUserDataService.getUserByPrimaryEMail(bean.getRegistrationEMail());

		if (userByEmail != null && 
				!userByEmail.getUserStatus().equals(TUser.UserStatus.CONFIRMATION_SENT) &&
				!userByEmail.getUserStatus().equals(TUser.UserStatus.CONFIRMATION_RESENT)) {
			// EMail in use and already confirmed.
			result.rejectValue("registrationEMail", OLSingleMessageConstants.REGISTER_EMAIL_DUPLICATE.getMessageKey(), ".EMail already in use.");
			
			return; 	
		} 
		
		if (userByEmail != null) {
			// EMail in use, but not confirmed.
			// Is OK.  User is effectively asking to resend confirmation not previously acknowledged.

			// The user can only be deleted in very limited circumstances.  User object must have no pointers from other
			//    table objects!  This will only happen if the user status is confirmation sent which is the case here.
			tUserDataService.delete(userByEmail);
			userByEmail = null;
		}
		
		// Check if display name in use by another user.
		TUser userByDisplayName ;
		try {
			userByDisplayName = tUserDataService.getUserByDisplayName(bean.getDisplayName());
			
		} catch (DaoException e) {
			throw new DaoException("Unable to retrieve user.  displayName: " + bean.getDisplayName(), e);
		}
		
		// userByDisplayName cannot be registering user at this point.  It cannot have the same email.
		
		// If other user is confirmation expired, delete.  
		if (userByDisplayName != null && userService.isUserConfirmExpired(userByDisplayName)) {
			tUserDataService.delete(userByDisplayName);
			userByDisplayName = null;
		}

		if (userByDisplayName != null) {
			result.rejectValue("displayName", OLSingleMessageConstants.REGISTER_DUPLICATE_DISPLAY_NAME.getMessageKey(), ".Display name already in use.");
			return;
		}
		
		// Password must be at least eight characters.  This is flagged in javascript.  If occurs here, someone has attempted to 
		//   hack us!
		if ((bean.getRegistrationPassword() == null || bean.getRegistrationPassword().length() < 8)) {
			result.rejectValue("displayName", OLSingleMessageConstants.REGISTER_INTERNAL_ERROR.getMessageKey(), ".Internal error.");
			return;
		}
		
		// Display name must be least 6 to 50 characters and alphanumeric.  Embedded spaces OK.  This is checked in javascript.
		String displayName = bean.getDisplayName();
		if (displayName != null) displayName = displayName.trim();
		if ((StringUtils.isEmpty(displayName) || displayName.length() < 6 || displayName.length() > 50 || 
				!OLStringUtil.isAlphaNumericSpace(displayName))) {
			result.rejectValue("displayName", OLSingleMessageConstants.REGISTER_INTERNAL_ERROR.getMessageKey(), ".Internal error.");
			return;
		}
		
	}
}
