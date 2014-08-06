package com.ctb.organizeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Content> getAllContents(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Content> getMyContents(User author, Pageable pageable) {
		return repository.findByAuthorId(author.getId(), pageable);
	}

	@Override
	public Content getContentById(Long contentId) {
		return repository.findOne(contentId);
	}

	@Transactional
	@Override
	public void saveContent(Content content) {
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

	@Override
	public Content getContentByLocation(String location) {
		return repository.findByLocation(location);
	}

}
