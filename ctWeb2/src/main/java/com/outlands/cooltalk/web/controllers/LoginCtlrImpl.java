package com.outlands.cooltalk.web.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.web.constants.OLWebConstants;
import com.outlands.cooltalk.web.displayBeans.LoginBean;
import com.outlands.cooltalk.web.presentation.services.LoginService;
import com.outlands.cooltalk.web.security.SecurityUtility;


/**
 * Show and handle input from the login page.
 * 
 * @author Andy Fields
 *
 */
@Controller
public class LoginCtlrImpl implements LoginCtlr{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TUserDataService userDataService;
	
	@Autowired
	private SecurityUtility securityUtility;
	
	@Secured({"ROLE_USER", "ROLE_GUEST", "ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
	@RequestMapping(value = OLWebConstants.LOGIN_PAGE, method = RequestMethod.GET)
	@Override
	public String login(HttpServletRequest request, Model model) {
		
		// Security enabled.  Ask user to login.
		LoginBean bean = new LoginBean();
		
		model.addAttribute("loginParms", bean);
			
        return "login/login";
	}

	/**
	 * Process the input from LoginJsp screen.
	 * @throws DaoException 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value = OLWebConstants.LOGIN_PAGE, method = RequestMethod.POST)
	@Secured({"ROLE_USER", "ROLE_GUEST", "ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
	@Override
	public String doPost(
			HttpServletRequest request,
			Model model,
			@ModelAttribute("loginParms") LoginBean loginBean,
			BindingResult result) 
	 {
		
		// Get member by EMail.
		TUser user = userDataService.getUserByPrimaryEMail(loginBean.getInputEmail().trim());
		
		boolean bSuccess = loginService.checkLogin(user, loginBean.getInputPassword(), result);
		
		// If success set session bean and redirect to the main screen.
		if (bSuccess) {
        	
        	// Set logged in.
			Authentication auth = loginService.getAuthenticationOjbect(user, loginBean.getInputPassword());
			securityUtility.setAuthentication(auth);
			
	        return "redirect:/sections.do";
			
		} else {
			// Invalidate authentication if not already done.
			loginBean.setInputPassword(null);
        	HttpSession session = request.getSession(true);
        	if (session != null) 
        		securityUtility.invalidateAuthentication();
			
			return "login/login";

		}
			
	}

}
