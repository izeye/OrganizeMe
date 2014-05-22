package com.ctb.organizeme.service;

import com.ctb.organizeme.domain.Content;

public interface ContentService {

	Iterable<Content> getAllContents();

	void addContent(Content content);

}
