package com.ctb.organizeme.controller;

import org.springframework.stereotype.Controller;
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

}
