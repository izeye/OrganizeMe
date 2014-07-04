package com.ctb.organizeme.service;

import java.util.List;

import com.ctb.organizeme.domain.Tag;

public interface TagService {

	Tag getTag(String tagName);

	List<Tag> getTags(List<String> tagNames);

}
