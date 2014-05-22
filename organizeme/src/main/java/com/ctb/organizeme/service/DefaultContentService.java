package com.ctb.organizeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctb.organizeme.dao.ContentRepository;
import com.ctb.organizeme.domain.Content;

@Service
public class DefaultContentService implements ContentService {

	@Autowired
	private ContentRepository repository;

	@Override
	public Iterable<Content> getAllContents() {
		return repository.findAll();
	}

	@Override
	public void addContent(Content content) {
		repository.save(content);
	}

}
