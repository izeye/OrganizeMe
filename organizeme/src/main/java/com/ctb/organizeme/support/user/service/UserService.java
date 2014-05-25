package com.ctb.organizeme.support.user.service;

import com.ctb.organizeme.support.user.domain.User;

public interface UserService {

	void addUser(User user);

	Iterable<User> getAllUsers();

}
