package com.ctb.organizeme.dao;

import org.springframework.data.repository.CrudRepository;

import com.ctb.organizeme.domain.Content;

public interface ContentRepository extends CrudRepository<Content, Long> {

	Iterable<Content> findAllByOrderByIdDesc();

}
