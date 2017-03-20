package com.outlands.cooltalk.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.outlands.cooltalk.web.displayBeans.LoginBean;

public interface LoginCtlr {

	public String login(HttpServletRequest request, Model model);

	public String doPost(HttpServletRequest request, Model model, LoginBean loginBean, BindingResult result);

}
