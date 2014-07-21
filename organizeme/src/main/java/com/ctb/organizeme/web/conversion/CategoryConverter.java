package com.ctb.organizeme.web.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.service.CategoryService;

public class CategoryConverter implements Converter<String, Category> {

	@Autowired
	private CategoryService categoryService;

	@Override
	public Category convert(String id) {
		return categoryService.getCategory(Long.valueOf(id));
	}

}
