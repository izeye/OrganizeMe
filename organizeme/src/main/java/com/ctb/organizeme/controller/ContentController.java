package com.ctb.organizeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.domain.ContentType;
import com.ctb.organizeme.domain.Language;
import com.ctb.organizeme.domain.LocationType;
import com.ctb.organizeme.service.CategoryService;
import com.ctb.organizeme.service.ContentService;
import com.ctb.organizeme.support.user.domain.User;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/content/all")
	public String all(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());

		Iterable<Content> contents = contentService.getAllContents();
		model.addAttribute("contents", contents);
		return "content/list";
	}

	@RequestMapping("/contents")
	@ResponseBody
	public Iterable<Content> search(@RequestParam Long categoryId) {
		if (categoryId == 0) {
			return contentService.getAllContents();
		}

		Category category = categoryService.getCategory(categoryId);
		return contentService.getContents(category);
	}

	@RequestMapping("/content/friends")
	public String friends() {
		return "redirect:/content/mine";
	}

	@RequestMapping("/content/mine")
	public String mine(Model model) {
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Iterable<Content> contents = contentService.getMyContents(author);
		model.addAttribute("contents", contents);
		return "content/list";
	}

	@RequestMapping(value = "/content/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("contentTypes", ContentType.values());
		model.addAttribute("languages", Language.values());
		model.addAttribute("locationTypes", LocationType.values());

		return "content/add";
	}

	@RequestMapping(value = "/content/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(@RequestParam Long categoryId,
			@RequestParam ContentType type, @RequestParam Language language,
			@RequestParam String title,
			@RequestParam LocationType locationType,
			@RequestParam String location) {
		Category category = categoryService.getCategory(categoryId);
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Content content = new Content(category, type, language, title,
				locationType, location, (User) author);
		contentService.addContent(content);
		return true;
	}

}
