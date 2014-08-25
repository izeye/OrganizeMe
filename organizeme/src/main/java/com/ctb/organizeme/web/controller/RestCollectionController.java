package com.ctb.organizeme.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctb.organizeme.domain.Collection;
import com.ctb.organizeme.service.CollectionService;
import com.ctb.organizeme.support.user.domain.User;

@RestController
public class RestCollectionController {

	@Autowired
	private CollectionService collectionService;

//	@RequestMapping("/collection/all")
//	public String all(Model model) {
//		Iterable<Collection> collections = collectionService
//				.getAllCollections();
//		model.addAttribute("collections", collections);
//		return "collection/list.html";
//	}
//
//	@RequestMapping("/collection/friends")
//	public String friends() {
//		return "redirect:/collection/mine";
//	}
//
//	@RequestMapping("/collection/mine")
//	public String mine(Model model) {
//		User author = (User) SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//		Iterable<Collection> collections = collectionService
//				.getMyCollections(author);
//		model.addAttribute("collections", collections);
//		return "collection/list.html";
//	}
//
//	@RequestMapping(value = "/collection/add", method = RequestMethod.GET)
//	public String add() {
//		return "collection/add.html";
//	}
//
//	@RequestMapping(value = "/collection/add", method = RequestMethod.POST)
//	@ResponseBody
//	public boolean add(@RequestParam String name,
//			@RequestParam String description) {
//		List<Content> contents = Collections.emptyList();
//		User author = (User) SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//		Collection collection = new Collection(name, description, contents,
//				author);
//		collectionService.add(collection);
//		return true;
//	}
	
	@RequestMapping(value = "/api/v1/collections/{id}", method = RequestMethod.GET, headers="Accept=application/json")
	public Collection getCollection(@PathVariable Long id, Model model) {
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Collection collection = collectionService.getCollectionById(id);
		if (!collection.getAuthor().getId().equals(author.getId())) {
			throw new SecurityException("Unauthorized trial.");
		}

		return collection;
	}	

}
