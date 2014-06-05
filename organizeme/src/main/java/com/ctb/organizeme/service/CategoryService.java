package com.ctb.organizeme.service;

import com.ctb.organizeme.domain.Category;

public interface CategoryService {

	Iterable<Category> getAllCategories();

	Category getCategory(Long categoryId);

}
