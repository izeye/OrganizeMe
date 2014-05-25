package com.ctb.organizeme.support.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctb.organizeme.support.user.dao.UserRepository;
import com.ctb.organizeme.support.user.domain.User;

@Service("userService")
public class DefaultUserService implements UserService, UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public void addUser(User user) {
		repository.save(user);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return repository.findByUsername(username);
	}

}
