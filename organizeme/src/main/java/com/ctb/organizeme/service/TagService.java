package com.ctb.organizeme.service;

import java.util.List;

import com.ctb.organizeme.domain.Tag;

public interface TagService {

	Iterable<Tag> getAllTags();

	Tag getTag(String tagName);

	List<Tag> getTags(List<String> tagNames);

}
