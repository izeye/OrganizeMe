package com.ctb.organizeme.support.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctb.organizeme.support.user.domain.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public Object check() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		System.out.println(principal);
		if (principal instanceof User) {
			return (User) principal;
		} else {
			return null;
		}
	}

}
