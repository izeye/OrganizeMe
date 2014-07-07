package com.ctb.organizeme.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctb.organizeme.domain.Collection;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.service.CollectionService;
import com.ctb.organizeme.support.user.domain.User;

@Controller
public class CollectionController {

	@Autowired
	private CollectionService collectionService;

	@RequestMapping("/collection/all")
	public String all(Model model) {
		Iterable<Collection> collections = collectionService
				.getAllCollections();
		model.addAttribute("collections", collections);
		return "collection/list";
	}

	@RequestMapping("/collection/friends")
	public String friends() {
		return "redirect:/collection/mine";
	}

	@RequestMapping("/collection/mine")
	public String mine(Model model) {
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Iterable<Collection> collections = collectionService
				.getMyCollections(author);
		model.addAttribute("collections", collections);
		return "collection/list";
	}

	@RequestMapping(value = "/collection/add", method = RequestMethod.GET)
	public String add() {
		return "collection/add";
	}

	@RequestMapping(value = "/collection/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(@RequestParam String name,
			@RequestParam String description) {
		List<Content> contents = Collections.emptyList();
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Collection collection = new Collection(name, description, contents,
				author);
		collectionService.add(collection);
		return true;
	}

}
