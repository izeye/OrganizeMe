package com.ctb.organizeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctb.organizeme.dao.CategoryRepository;
import com.ctb.organizeme.domain.Category;

@Service
public class DefaultCategoryService implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(Long categoryId) {
		return categoryRepository.findOne(categoryId);
	}

	@Override
	public boolean add(Category categoryItem) {
		categoryRepository.save(categoryItem);
		
		//TODO: error handling
		return true;
	}

}
