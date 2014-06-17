package com.ctb.organizeme.service;

import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.support.user.domain.User;

public interface ContentService {

	Iterable<Content> getAllContents();

	Iterable<Content> getMyContents(User author);

	void addContent(Content content);

}
