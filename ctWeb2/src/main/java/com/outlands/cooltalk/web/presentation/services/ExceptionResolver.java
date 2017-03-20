package com.outlands.cooltalk.web.presentation.services;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.outlands.cooltalk.web.displayBeans.MsgDisplayBean;


public class ExceptionResolver implements HandlerExceptionResolver {

	//@Override
	public ModelAndView resolveException(
			HttpServletRequest arg0,
			HttpServletResponse arg1, 
			Object arg2, 
			Exception arg3) {
		
		//String sMethod = "resolveException";
		
		Logger log = Logger.getLogger(this.getClass());
		
		MsgDisplayBean errDisplay = null;
//		if (arg3 instanceof InvalidUrlException)  {
//			//arg3.printStackTrace(System.out);
//			log.error("No such URL", arg3);
//			
//			errDisplay = new MsgDisplayBean("Error", "No such URL", 
//					arg3.getMessage(), "/browser/login");
//			
//		} else if (arg3 instanceof NoSuchItemException)  {
//			log.error("No such URL", arg3);
//			
//			errDisplay = new MsgDisplayBean("Error", "No such item", 
//					arg3.getMessage(), "/browser/login");
//
//		} else 
		if (arg3 instanceof InvalidParameterException)  {
			log.error("No such URL", arg3);
			
			errDisplay = new MsgDisplayBean("Error", "Invalid parameter", 
					arg3.getMessage(), "/browser/login");

//		} else if (arg3 instanceof NoSuchMemberException)  {
//			log.error("No such URL", arg3);
//			
//			errDisplay = new MsgDisplayBean("Error", "Member not found in database.", 
//					arg3.getMessage(), "/browser/login");
//
//		} else if (arg3 instanceof AfSecurityException)  {
//			log.error("No such URL", arg3);
//			
//			errDisplay = new MsgDisplayBean("Error", "Security Exception", 
//					arg3.getMessage(), "/spring/logout");
//
//		} else if (arg3 instanceof DownloadException)  {
//			log.error("No such URL", arg3);
//			
//			errDisplay = new MsgDisplayBean("Error", "Unable to download file", 
//					arg3.getMessage(), "/browser/login");
//
//		} else if (arg3 instanceof UploadException)  {
//			log.error("No such URL", arg3);
//			
//			errDisplay = new MsgDisplayBean("Error", "Unable to upload file", 
//					arg3.getMessage(), "/browser/login");
//
		} else {
		
			// For now, send most things to the default message display.
			errDisplay = new MsgDisplayBean("Error", "Internal Exception", 
					new String[]{"Internal Exception occurred:",
					arg3.getClass().getCanonicalName() + " : " + arg3.getMessage()}, "/browser/login");
			
			log.error("Internal Exception", arg3);
		}
		
		ModelAndView mod = new ModelAndView("errDisplay", "errDisplay", errDisplay);
		
		return mod;
	}

}
