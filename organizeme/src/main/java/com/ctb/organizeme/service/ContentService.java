package com.ctb.organizeme.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.support.user.domain.User;

public interface ContentService {

	Page<Content> getAllContents(Pageable pageable);

	Page<Content> getMyContents(User author, Pageable pageable);

	Iterable<Content> getContents(Category category);

	Content getContentById(Long contentId);

	void saveContent(Content content);

	void removeContent(Long contentId);

	Content getContentByLocation(String location);

}
