package com.ctb.organizeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.dao.ContentRepository;
import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.support.user.domain.User;

@Service
public class DefaultContentService implements ContentService {

	@Autowired
	private ContentRepository repository;

	@Override
	public Iterable<Content> getAllContents() {
		return repository.findAllByOrderByIdDesc();
	}

	@Override
	public Iterable<Content> getMyContents(User author) {
		return repository.findByAuthorIdOrderByIdDesc(author.getId());
	}

	@Override
	public Content getContentById(Long contentId) {
		return repository.findOne(contentId);
	}

	@Transactional
	@Override
	public void addContent(Content content) {
		repository.save(content);
	}

	@Override
	public Iterable<Content> getContents(Category category) {
		return repository.findByCategoryOrderByIdDesc(category);
	}

	@Override
	public void removeContent(Long contentId) {
		repository.delete(contentId);
	}

}
