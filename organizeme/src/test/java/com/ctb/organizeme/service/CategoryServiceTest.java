package com.ctb.organizeme.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctb.organizeme.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class CategoryServiceTest {

	@Autowired
	CategoryService categoryService;

	@Test
	public void getAllCategories() {
		Iterable<Category> allCategories = categoryService.getAllCategories();
		for (Category category : allCategories) {
			System.out.println(category);
		}
	}

	@Test
	public void getCategory() {
		Long categoryId = 1L;
		Category category = categoryService.getCategory(categoryId);
		System.out.println(category);
	}

}
