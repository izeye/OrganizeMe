package com.ctb.organizeme.setup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctb.organizeme.support.user.domain.User;
import com.ctb.organizeme.support.user.domain.UserRole;
import com.ctb.organizeme.support.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class Setup {

	@Autowired
	UserService userService;

	@Test
	public void setup() {
		userService.addUser(new User("admin", "1234", UserRole.SUPERVISOR));
		userService.addUser(new User("izeye", "1234", UserRole.USER));
		userService.addUser(new User("always19", "1234", UserRole.USER));
		userService.addUser(new User("guest", "1234", UserRole.USER));
	}

}
