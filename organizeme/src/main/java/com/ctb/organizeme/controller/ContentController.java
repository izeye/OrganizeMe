package com.ctb.organizeme.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.domain.ContentType;
import com.ctb.organizeme.domain.Language;
import com.ctb.organizeme.domain.LocationType;
import com.ctb.organizeme.service.ContentService;
import com.ctb.organizeme.support.user.domain.User;

@Controller
public class ContentController {

	@Autowired
	private ContentService service;

	@RequestMapping("/content")
	public String index(Model model, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		System.out.println(authentication.getName());
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		System.out.println(user.getUsername());

		System.out.println(principal.getName());

		model.addAttribute("contentTypes", ContentType.values());
		model.addAttribute("languages", Language.values());
		model.addAttribute("locationTypes", LocationType.values());

		Iterable<Content> contents = service.getAllContents();
		model.addAttribute("contents", contents);
		return "content/index";
	}

	@RequestMapping("/content/add")
	@ResponseBody
	public boolean add(@RequestParam ContentType type,
			@RequestParam Language language, @RequestParam String title,
			@RequestParam LocationType locationType,
			@RequestParam String location) {
		User owner = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		service.addContent(new Content(type, language, title, locationType,
				location, (User) owner));
		return true;
	}

}
