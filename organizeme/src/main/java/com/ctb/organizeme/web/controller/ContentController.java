package com.ctb.organizeme.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.ctb.organizeme.support.user.domain.UserRole;

@Controller
public class ContentController {

	public static final int PAGE_SIZE_CONTENTS = 10;
	public static final Direction SORT_DIRECTION_CONTENTS = Direction.DESC;
	public static final String SORT_PROPERTY = "id";

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

	@ModelAttribute("user")
	public User populateUsername() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof User) {
			return (User) principal;
		}
		return null;
	}

	@RequestMapping("/content/all")
	public String getAllPagedContents(Model model,
			@RequestParam(defaultValue = "false") boolean mine) {
		Pageable pageable = new PageRequest(0, PAGE_SIZE_CONTENTS,
				SORT_DIRECTION_CONTENTS, SORT_PROPERTY);

		Page<Content> pagedContents = getPagedContents(mine, pageable);
		model.addAttribute("pagedContents", pagedContents);
		model.addAttribute("mine", mine);
		return "content/list.html";
	}

	@RequestMapping("/content/all.json")
	@ResponseBody
	public Page<Content> getAllPagedContents(Model model, Pageable pageable,
			@RequestParam(defaultValue = "false") boolean mine) {
		Page<Content> pagedContents = getPagedContents(mine, pageable);
		return pagedContents;
	}

	private Page<Content> getPagedContents(boolean mine, Pageable pageable) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User author = null;
		if (principal instanceof User) {
			author = (User) principal;
		}

		Page<Content> pagedContents;
		if (mine) {
			pagedContents = contentService.getMyContents(author, pageable);
		} else {
			pagedContents = contentService.getAllContents(pageable);
		}
		for (Content content : pagedContents.getContent()) {
			content.setReader(author);
		}
		return pagedContents;
	}

	@RequestMapping("/content/friends")
	public String friends() {
		return "redirect:/content/mine";
	}

	@RequestMapping("/content/mine")
	public String getMyPagedContents(Model model) {
		return "redirect:/content/all?mine=true";
	}

	@RequestMapping(value = "/contents", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Content> search(@RequestParam Long categoryId) {
		if (categoryId == 0) {
			Pageable pageable = new PageRequest(0, PAGE_SIZE_CONTENTS,
					SORT_DIRECTION_CONTENTS, SORT_PROPERTY);
			return contentService.getAllContents(pageable);
		}

		Category category = categoryService.getCategory(categoryId);
		return contentService.getContents(category);
	}

	@RequestMapping(value = "/contents/{id}", method = RequestMethod.GET)
	public String getContent(@PathVariable Long id, Model model) {
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Content content = contentService.getContentById(id);
		if (!content.getAuthor().getId().equals(author.getId())) {
			throw new SecurityException("Unauthorized trial.");
		}

		model.addAttribute(content);
		return "content/view.html";
	}

	@RequestMapping(value = "/contents", method = RequestMethod.PUT)
	public String updateContent(@Valid Content content,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "content/view.html";
		}

		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Long contentId = content.getId();
		Content foundContent = contentService.getContentById(contentId);
		if (!foundContent.getAuthor().getId().equals(author.getId())) {
			throw new SecurityException("Unauthorized trial.");
		}
		content.setAuthor(author);
		contentService.saveContent(content);
		return "redirect:/content/mine";
	}

	@RequestMapping(value = "/content/add", method = RequestMethod.GET)
	public String add(Content content) {
		return "content/add.html";
	}

	@RequestMapping(value = "/content/add", method = RequestMethod.POST)
	public String add(@Valid Content content, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "content/add.html";
		}

		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		content.setAuthor(author);
		contentService.saveContent(content);
		return "redirect:/content/mine";
	}

	@RequestMapping("/content/remove")
	@ResponseBody
	public boolean remove(@RequestParam Long contentId) {
		Content content = contentService.getContentById(contentId);

		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (user.getRole() != UserRole.SUPERVISOR
				&& !content.getAuthor().getId().equals(user.getId())) {
			throw new IllegalArgumentException(
					"Unauthorized request: remove content (ID: " + contentId
							+ ") by " + user.getUsername());
		}

		contentService.removeContent(contentId);
		return true;
	}

}
