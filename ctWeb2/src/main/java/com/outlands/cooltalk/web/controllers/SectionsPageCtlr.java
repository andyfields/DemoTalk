package com.outlands.cooltalk.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface SectionsPageCtlr {

	public String get(HttpServletRequest request, HttpSession session, Model model);

}
