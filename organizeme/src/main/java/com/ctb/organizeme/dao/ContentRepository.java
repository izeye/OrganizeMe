package com.ctb.organizeme.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {

	Page<Content> findByAuthorId(Long authorId, Pageable pageable);

	Iterable<Content> findByCategoryOrderByIdDesc(Category category);

}
