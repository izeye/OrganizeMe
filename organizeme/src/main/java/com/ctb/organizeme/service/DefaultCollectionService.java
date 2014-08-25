package com.ctb.organizeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.dao.CollectionRepository;
import com.ctb.organizeme.domain.Collection;
import com.ctb.organizeme.support.user.domain.User;

@Service
public class DefaultCollectionService implements CollectionService {

	@Autowired
	private CollectionRepository repository;

	@Override
	public Iterable<Collection> getAllCollections() {
		return repository.findAllByOrderByIdDesc();
	}

	@Override
	public Iterable<Collection> getMyCollections(User author) {
		return repository.findByAuthorIdOrderByIdDesc(author.getId());
	}

	@Transactional
	@Override
	public void add(Collection collection) {
		repository.save(collection);
	}

	@Override
	public Collection getCollectionById(Long collectionId) {
		return repository.findOne(collectionId);
	}

}
