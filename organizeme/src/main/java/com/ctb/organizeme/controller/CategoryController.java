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
import com.ctb.organizeme.support.user.domain.UserRole;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/categories/all")
	public String all(Model model) {
		Iterable<Category> items = categoryService.getAllCategories();
		model.addAttribute("categories", items);
		return "categories/list";
	}

	@RequestMapping(value = "/categories/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());

		return "categories/add";
	}

	@RequestMapping(value = "/categories/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(@RequestParam Long parentCategoryId, @RequestParam String newCategoryName) {
		User author = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (UserRole.SUPERVISOR.equals(author.getRole())) {
			Category parentCategoryItem = null;
			if (parentCategoryId != null) {
				parentCategoryItem = categoryService.getCategory(parentCategoryId);
				if (parentCategoryItem == null) {
					//TODO: error
					return false;
				}
			}
			 
			Category categoryItem = new Category(newCategoryName, parentCategoryItem);
			if (categoryService.add(categoryItem)) {
				return true;
			} else {
				//TODO: duplication error? no parent?
				return false;
			}
		} else {
			//TODO: auth error
			return false;
		}
	}
	
}
