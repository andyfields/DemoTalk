package com.outlands.cooltalk.web.presentation.services;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.outlands.cooltalk.ctBusiness.services.mail.SendMailService;
import com.outlands.cooltalk.ctCommon.constants.OLEmailMessageConstants;
import com.outlands.cooltalk.ctCommon.constants.OLSingleMessageConstants;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.ctEntities.entity.TUser.UserStatus;
import com.outlands.cooltalk.utility.OLStringUtil;
import com.outlands.cooltalk.web.constants.OLWebConstants;
import com.outlands.cooltalk.web.displayBeans.SendResetPasswordBean;
import com.outlands.cooltalk.web.services.UserService;

@Service
public class ResetPasswordService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SendMailService sendMailService;

	@Autowired
	private SessionLocaleResolver sessionLocaleResover;
	
	public void validateResetPasswordRequest(SendResetPasswordBean bean, TUser userByEmail, BindingResult result) {
		if (!OLStringUtil.isValidEmailAddress(bean.getResetEMail())) {
			result.rejectValue("resetEMail", OLSingleMessageConstants.RESETPASSWORD_EMAIL_INVALID.getMessageKey(), 
					".Invalid email address.");
			return;
		}
		
		if (userByEmail == null || userByEmail.getUserStatus().equals(UserStatus.USER_DELETED) ||
				userService.isUserConfirmExpired(userByEmail)) {
			// EMail dos not exist.
			result.rejectValue("registrationEMail", OLSingleMessageConstants.RESETPASSWORD_EMAIL_NOT_FOUND.getMessageKey(), ".Email not found.");
			
			return; 	
		} 

		if (!userByEmail.getUserStatus().equals(UserStatus.USER_CONFIRMED)) {
			// EMail in use, but not confirmed.
			result.rejectValue("registrationEMail", OLSingleMessageConstants.RESETPASSWORD_NOT_CONFIRMED.getMessageKey(), ".Email not confirmed.");
			
			return; 	
		}
		
	}
	
	/**
	 * Send code to reset password.
	 * 
	 * @param user
	 * @param request
	 * @param passwordCode
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public int sendResetPassword(TUser user, HttpServletRequest request, String passwordCode) throws ClientProtocolException, IOException {
		
		Locale locale = sessionLocaleResover.resolveLocale(request);

		InetAddress adrs = InetAddress.getLocalHost();
		String sServer = adrs.getHostAddress();

		sServer += ":" + request.getLocalPort();
		String[] args = new String[1];
		args[0] = "\n\nhttp://" + sServer + request.getContextPath() + OLWebConstants.RESET_PASSWORD_BY_CODE_PAGE + "?code=" + passwordCode;
		return sendMailService.send(user.getPrimaryEMail(), OLEmailMessageConstants.RESETPASSWORD_EMAIL, args, locale);
	}
		
}
