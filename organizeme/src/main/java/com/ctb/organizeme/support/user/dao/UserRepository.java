package com.ctb.organizeme.support.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.ctb.organizeme.support.user.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
