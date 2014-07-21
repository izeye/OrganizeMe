package com.ctb.organizeme.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.domain.ContentType;
import com.ctb.organizeme.domain.Language;
import com.ctb.organizeme.domain.LocationType;
import com.ctb.organizeme.domain.Progress;
import com.ctb.organizeme.service.CategoryService;
import com.ctb.organizeme.service.ContentService;
import com.ctb.organizeme.service.TagService;
import com.ctb.organizeme.support.user.domain.User;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TagService tagService;

	@ModelAttribute("contentTypes")
	public List<ContentType> populateContentTypes() {
		return Arrays.asList(ContentType.ALL);
	}

	@ModelAttribute("languages")
	public List<Language> populateLanguages() {
		return Arrays.asList(Language.ALL);
	}

	@ModelAttribute("locationTypes")
	public List<LocationType> populateLocationTypes() {
		return Arrays.asList(LocationType.ALL);
	}

	@ModelAttribute("progresses")
	public List<Progress> populateProgresses() {
		return Arrays.asList(Progress.ALL);
	}

	@ModelAttribute("categories")
	public Iterable<Category> populateCategories() {
		return categoryService.getAllCategories();
	}

	@ModelAttribute("contents")
	public Iterable<Content> populateContents() {
		return contentService.getAllContents();
	}

	@RequestMapping("/content/all")
	public String all(Model model) {
		return "content/list.html";
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
		return "content/list.html";
	}

	@RequestMapping(value = "/content/add", method = RequestMethod.GET)
	public String add(Content content) {
		return "content/add.html";
	}

	@RequestMapping(value = "/content/add", method = RequestMethod.POST)
	public String add(@Valid Content content, BindingResult bindingResult) {
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		content.setAuthor(author);
		if (bindingResult.hasErrors()) {
			return "content/add.html";
		}
		contentService.addContent(content);
		return "redirect:/content/mine";
	}

}
