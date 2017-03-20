package com.outlands.cooltalk.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctCommon.properties.OLWebPropertiesService;
import com.outlands.cooltalk.web.constants.OLWebConstants;


/**
 * Show and handle input from the registration page.
 * 
 * @author Andy Fields
 *
 */
@Controller
public class SectionsPageCtlrImpl implements SectionsPageCtlr {
	
	@Autowired
	@Qualifier("webProperties")
	private OLWebPropertiesService olWebProperties;

	
	/**
	 * Display sections page.  Not much done here.  Page will request info through microservice calls.
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws DaoException
	 */
	@RequestMapping(value = OLWebConstants.SECTIONS_LIST_PAGE, method = RequestMethod.GET)
	public String get(HttpServletRequest request, HttpSession session, Model model) {
		
        return "sections/sections";
	}	
	
}
