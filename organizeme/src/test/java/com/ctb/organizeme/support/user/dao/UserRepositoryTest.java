package com.ctb.organizeme.support.user.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.support.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	@Transactional
	public void test() {
		String username = "izeye";
		String password = "1234";
		User user1 = new User(username, password);
		userRepository.save(user1);
		System.out.println(user1.getId());

		Iterable<User> users = userRepository.findAll();
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	public void findByUsername() {
		String username = "izeye";
		User user = userRepository.findByUsername(username);
		System.out.println(user);
	}

}
