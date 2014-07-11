package com.ctb.organizeme.dao;

import org.springframework.data.repository.CrudRepository;

import com.ctb.organizeme.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Iterable<Category> findAllByOrderByNameAsc();

}
