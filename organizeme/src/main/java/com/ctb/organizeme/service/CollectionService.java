package com.ctb.organizeme.service;

import com.ctb.organizeme.domain.Collection;
import com.ctb.organizeme.support.user.domain.User;

public interface CollectionService {

	Iterable<Collection> getAllCollections();

	Iterable<Collection> getMyCollections(User author);

	void add(Collection collection);
	
	Collection getCollectionById(Long collectionId);

}
