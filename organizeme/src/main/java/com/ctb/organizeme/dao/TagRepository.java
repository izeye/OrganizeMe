package com.ctb.organizeme.dao;

import org.springframework.data.repository.CrudRepository;

import com.ctb.organizeme.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByName(String name);

}
