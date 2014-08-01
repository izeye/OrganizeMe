package com.ctb.organizeme.service;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.support.user.domain.User;

public interface ContentService {

	Iterable<Content> getAllContents();

	Iterable<Content> getMyContents(User author);

	Iterable<Content> getContents(Category category);

	Content getContentById(Long contentId);

	void saveContent(Content content);

	void removeContent(Long contentId);

}
