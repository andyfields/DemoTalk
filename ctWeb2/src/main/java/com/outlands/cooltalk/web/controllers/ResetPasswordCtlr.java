package com.outlands.cooltalk.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.outlands.cooltalk.ctCommon.constants.OLConstants;
import com.outlands.cooltalk.ctCommon.constants.OLDialogMessageConstants;
import com.outlands.cooltalk.ctCommon.constants.OLSingleMessageConstants;
import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctCommon.properties.OLWebPropertiesService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.utility.OLStringUtil;
import com.outlands.cooltalk.web.constants.OLWebConstants;
import com.outlands.cooltalk.web.displayBeans.MsgDisplayBean;
import com.outlands.cooltalk.web.displayBeans.ResetPasswordByCodeBean;
import com.outlands.cooltalk.web.displayBeans.SendResetPasswordBean;
import com.outlands.cooltalk.web.presentation.services.ResetPasswordService;
import com.outlands.cooltalk.web.services.UserService;


/**
 * Show and handle input from the send password reset and reset password by code pages.
 * 
 * @author Andy Fields
 *
 */
@Controller
public class ResetPasswordCtlr {
	
	@Autowired
	@Qualifier("webProperties")
	private OLWebPropertiesService olWebProperties;
	
	@Autowired
	private ResetPasswordService resetPasswordService;

	@Autowired
	private TUserDataService tUserDataService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SessionLocaleResolver sessionLocaleResover;
	
	/**
	 * This is for receiving confirmation URL sent out in confirmation e-mail.  We are 
	 * excepting a code parameter.
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(value = OLWebConstants.RESET_PASSWORD_BY_CODE_PAGE, method = RequestMethod.GET)
	public String getResetPasswordByCode(HttpServletRequest request, HttpSession session, Model model) throws DaoException {
		
		// Registration code received.
		MsgDisplayBean bean = null;
		
		String sCode = request.getParameter("code");
		TUser user = null;
		if (sCode != null) {
			// Get user for this confirmation code.
			user = tUserDataService.getUserByChangePasswordCode(sCode);
		}
		
		if (user == null) {
			// No reset password code or no user for this code.  Display error.
			bean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
					OLDialogMessageConstants.RESETPASSWORD_BY_CODE_ERROR_DIALOG, 
					"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);

			model.addAttribute("display", bean);	
	        return "display";

		} else if (userService.isChangePasswordExpired(user)) {
			// Code expired.
			bean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
					OLDialogMessageConstants.RESETPASSWORD_BY_CODE_EXPIRED_DIALOG, 
					"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
			model.addAttribute("display", bean);
	        return "display";
	        
		}
						
		// Confirmation code is good and not expired!  Display change password screen.
		ResetPasswordByCodeBean  resetPasswordBean = new ResetPasswordByCodeBean();
		
		resetPasswordBean.setResetPasswordCode(sCode);
		model.addAttribute("parmsResetPassword", resetPasswordBean);
		
		return "resetPassword/resetPasswordByCode";

	}
	
	
	@RequestMapping(value = OLWebConstants.RESET_PASSWORD_BY_CODE_PAGE, method = RequestMethod.POST)
	public String post(			
			HttpServletRequest request,
			HttpSession session,
			Model model,
			@ModelAttribute("parmsResetPassword") ResetPasswordByCodeBean resetPasswordBean,
			BindingResult result) throws DaoException, ClientProtocolException, IOException {
		
		TUser user = tUserDataService.getUserByChangePasswordCode(resetPasswordBean.getResetPasswordCode());
		
		if (resetPasswordBean.getResetPasswordCode().length() < 8) {
			resetPasswordBean.setInputPassword(null);
			model.addAttribute("parmsResetPassword", resetPasswordBean);
			result.rejectValue("inputPassword", OLSingleMessageConstants.RESETPASSWORD_BY_CODE_PASSWORD_INVALID.getMessageKey(), ".Invalid passowrd.");
			return "resetPassword/resetPasswordByCode";
		}
		
		userService.updateUserPassword(user, resetPasswordBean.getInputPassword());
		
		MsgDisplayBean displayBean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
				OLDialogMessageConstants.RESETPASSWORD_BY_CODE_SUCCESS_DIALOG, null,
				"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
		model.addAttribute("display", displayBean);
						
        return "display";

	}

	/**
	 * Display send reset page.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = OLWebConstants.SEND_RESET_PASSWORD_PAGE, method = RequestMethod.GET)
	public String get(HttpServletRequest request, Model model) {
		
		SendResetPasswordBean bean = new SendResetPasswordBean();
		model.addAttribute("sendResetParms", bean);
			
        return "resetPassword/sendResetPassword";
	}
	
	/**
	 * Receive registration.  Send out registration e-mail (if not in demo mode).
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param bean
	 * @param result
	 * @return
	 * @throws DaoException
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping(value = OLWebConstants.SEND_RESET_PASSWORD_PAGE, method = RequestMethod.POST)
	public String postResetPasswordPage(			
			HttpServletRequest request,
			HttpSession session,
			Model model,
			@ModelAttribute("sendResetParms") SendResetPasswordBean bean,
			BindingResult result) throws DaoException, ClientProtocolException, IOException {
		
		TUser userByEmail = tUserDataService.getUserByPrimaryEMail(bean.getResetEMail());

		resetPasswordService.validateResetPasswordRequest(bean, userByEmail, result);
		
		if (result.hasErrors()) {

			// Redisplay reset password page with BindingResult errors.
			model.addAttribute("sendResetParms", bean);
				
	        return "resetPassword/sendResetPassword";
	        
		}
		
		String changePasswordCode = OLStringUtil.getRandomAlpha(20);
		int status = resetPasswordService.sendResetPassword(userByEmail, request, changePasswordCode);
		
		if (status == 200) {
		
			// Store confirmation code and date in user object. Send change password email.
			userByEmail.setChangePasswordCode(changePasswordCode);
			userByEmail.setChangePasswordSent(olDateUtils.getNow());
			tUserDataService.update(userByEmail);
			
			String[] args = {userByEmail.getPrimaryEMail(), "" + OLConstants.RESET_PASSWORD_EXPIRATION_MINS};
			MsgDisplayBean displayBean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
					OLDialogMessageConstants.RESETPASSWORD_EMAIL_SENT_DIALOG, args,
					"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
			model.addAttribute("display", displayBean);
							
	        return "display";
	        
		} else {
			// Redisplay reset page with BindingResult errors.
			result.rejectValue("registrationEMail", OLSingleMessageConstants.RESETPASSWORD_EMAIL_SEND_ERROR.getMessageKey(), 
					".Could not send reset password email.");
			
			model.addAttribute("sendResetParms", bean);
			
	        return "resetPassword/sendResetPassword";
	      
		}
			
	}

}
