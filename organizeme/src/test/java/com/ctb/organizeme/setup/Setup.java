package com.ctb.organizeme.setup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctb.organizeme.dao.CategoryRepository;
import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.support.user.domain.User;
import com.ctb.organizeme.support.user.domain.UserRole;
import com.ctb.organizeme.support.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class Setup {

	@Autowired
	UserService userService;

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	public void setup() {
		setupUsers();
		setupCategories();
	}

	private void setupUsers() {
		userService.addUser(new User("admin", "1234", UserRole.SUPERVISOR));
		userService.addUser(new User("izeye", "1234", UserRole.USER));
		userService.addUser(new User("always19", "1234", UserRole.USER));
		userService.addUser(new User("guest", "1234", UserRole.USER));

	}

	private void setupCategories() {
		Category computer = new Category("Computer");
		categoryRepository.save(computer);

		Category java = new Category("Java", computer);
		categoryRepository.save(java);

		Category english = new Category("English");
		categoryRepository.save(english);
	}

}
