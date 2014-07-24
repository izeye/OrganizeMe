package com.ctb.organizeme.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "redirect:/content/all";
	}

	@RequestMapping("/errors/404.html")
	public String handle404() {
		return "redirect:/";
	}

	@RequestMapping("/login.html")
	public String login() {
		return "login/login.html";
	}
	
	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login/login.html";
	}

}
