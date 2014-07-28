package com.ctb.organizeme.support.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ctb.organizeme.support.user.dao.UserRepository;
import com.ctb.organizeme.support.user.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join.html";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(User user) {
		userRepository.save(user);
		return "redirect:/login.html";
	}

}
