package com.outlands.cooltalk.web.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Locale;

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

import com.outlands.cooltalk.ctBusiness.services.mail.SendMailService;
import com.outlands.cooltalk.ctCommon.constants.OLConstants;
import com.outlands.cooltalk.ctCommon.constants.OLDialogMessageConstants;
import com.outlands.cooltalk.ctCommon.constants.OLEmailMessageConstants;
import com.outlands.cooltalk.ctCommon.constants.OLSingleMessageConstants;
import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctCommon.properties.OLWebPropertiesService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.ctEntities.entity.TUser.UserStatus;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.utility.OLStringUtil;
import com.outlands.cooltalk.web.constants.OLWebConstants;
import com.outlands.cooltalk.web.displayBeans.MsgDisplayBean;
import com.outlands.cooltalk.web.displayBeans.RegisterBean;
import com.outlands.cooltalk.web.presentation.services.RegistrationService;
import com.outlands.cooltalk.web.services.UserService;


/**
 * Show and handle input from the registration page.
 * 
 * @author Andy Fields
 *
 */
@Controller
public class RegisterCtlr {
	
	@Autowired
	@Qualifier("webProperties")
	private OLWebPropertiesService olWebProperties;

	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private TUserDataService tUserDataService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	@Autowired
	private SendMailService sendMailService;
	
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
	@RequestMapping(value = OLWebConstants.CONFIRM_REGISTRATION_PAGE, method = RequestMethod.GET)
	public String getConfirmReg(HttpServletRequest request, HttpSession session, Model model) throws DaoException {
		
		// Registration code received.
		MsgDisplayBean bean = null;
		
		String sCode = request.getParameter("code");
		TUser user = null;
		if (sCode != null) {
			// Get user for this confirmation code.
			user = tUserDataService.getUserByConfCode(sCode);
		}
		
		if (user == null) {
			// No confirmation code or no user for this confirmation code.  Display error.
			bean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
					OLDialogMessageConstants.REGISTRATION_CONFIRM_ERROR_DIALOG, 
					"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
			
		} else if (user.getUserStatus() == TUser.UserStatus.USER_CONFIRMED) {
			// If this user has already been confirmed, display error.
			bean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
					OLDialogMessageConstants.REGISTRATION_CONFIRM_ALREADYRECEIVED_DIALOG, 
					"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
			
		} else {
						
			// Confirmation received before expiration?
			if (olDateUtils.isExpired(user.getConfirmationSent(), Calendar.MINUTE, OLConstants.CONFIRMATION_EXPIRATION_MINS)) {
				
				// Confirmation expired.
				bean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
						OLDialogMessageConstants.REGISTRATION_CONFIRM_CODEEXPIRED_DIALOG, 
						"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
			} else {
				
				// Confirmation code is good and not expired!
				user.setUserStatus(TUser.UserStatus.USER_CONFIRMED);
				tUserDataService.update(user);
				
				bean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
						OLDialogMessageConstants.REGISTRATION_CONFIRM_OK_DIALOG, 
						"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
			}	
		}
		
		model.addAttribute("display", bean);
			
        return "display";
	}
	
	/**
	 * Display registration page.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = OLWebConstants.REGISTRATION_PAGE, method = RequestMethod.GET)
	public String get(HttpServletRequest request, Model model) {
		
		// Display registration page.
		RegisterBean bean = new RegisterBean();
		model.addAttribute("parms", bean);
			
        return "login/register";
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
	@RequestMapping(value = OLWebConstants.REGISTRATION_PAGE, method = RequestMethod.POST)
	public String post(			
			HttpServletRequest request,
			HttpSession session,
			Model model,
			@ModelAttribute("parms") RegisterBean bean,
			BindingResult result) throws DaoException, ClientProtocolException, IOException {
		
		registrationService.validateRegistrationRequest(bean, result);
		
		if (result.hasErrors()) {

			// Redisplay registration page with BindingResult errors.
			bean.setRegistrationPassword(null);
			model.addAttribute("parms", bean);
				
	        return "login/register";
	        
		}
		
		if (olWebProperties.isDemoMode()) {
			// Create a confirmed user.
			createUser(bean, UserStatus.USER_CONFIRMED, null);
			
			MsgDisplayBean displayBean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
					OLDialogMessageConstants.REGISTER_SUCCESS_DEMO_DIALOG, null, 
					"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
			model.addAttribute("display", displayBean);
							
	        return "display";
			
		} else {
			String confCode = OLStringUtil.getRandomAlpha(20);
			int status = sendConfirmation(bean, request, confCode);
			
			if (status == 200) {
			
				// Create a user with pending confirmation.
				createUser(bean, UserStatus.CONFIRMATION_SENT, confCode);
	
				String[] args = {bean.getRegistrationEMail()};
				MsgDisplayBean displayBean = new MsgDisplayBean(messageSource, sessionLocaleResover.resolveLocale(request),
						OLDialogMessageConstants.REGISTER_EMAIL_SENT_DIALOG, args,
						"/" + OLWebConstants.BROWSER_SERVLET_PATH + OLWebConstants.LOGIN_PAGE);
				model.addAttribute("display", displayBean);
								
		        return "display";
		        
			} else {
				// Redisplay registration page with BindingResult errors.
				result.rejectValue("registrationEMail", OLSingleMessageConstants.REGISTER_EMAIL_SEND_ERROR.getMessageKey(), 
						".Could not send registration email.");
				
				bean.setRegistrationPassword(null);
				model.addAttribute("parms", bean);
					
		        return "login/register";
		      
			}
			
		}
	}
	
	void createUser(RegisterBean bean, UserStatus status, String confirmCode) {
		userService.createNewUser(bean.getRegistrationEMail(), bean.getRegistrationPassword(), bean.getDisplayName(), status, confirmCode);
	}
		
	int sendConfirmation(RegisterBean bean, HttpServletRequest request, String confCode) throws ClientProtocolException, IOException {
		
		Locale locale = sessionLocaleResover.resolveLocale(request);

		InetAddress adrs = InetAddress.getLocalHost();
		String sServer = adrs.getHostAddress();

		sServer += ":" + request.getLocalPort();
		String[] args = new String[1];
		args[0] = "\n\nhttp://" + sServer + request.getContextPath() + OLWebConstants.CONFIRM_REGISTRATION_PAGE + "?code=" + confCode;
		
		return sendMailService.send(bean.getRegistrationEMail(), OLEmailMessageConstants.REGISTRATION_CONFIRMATION_EMAIL, args, locale);
	}
	

}
