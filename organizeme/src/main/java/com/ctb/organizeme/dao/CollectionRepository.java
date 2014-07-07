package com.ctb.organizeme.dao;

import org.springframework.data.repository.CrudRepository;

import com.ctb.organizeme.domain.Collection;

public interface CollectionRepository extends CrudRepository<Collection, Long> {

	Iterable<Collection> findAllByOrderByIdDesc();

	Iterable<Collection> findByAuthorIdOrderByIdDesc(Long authorId);

}
