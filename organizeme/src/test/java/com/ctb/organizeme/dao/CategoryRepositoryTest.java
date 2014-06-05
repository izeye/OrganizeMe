package com.ctb.organizeme.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class CategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void test() {
		Category computer = new Category("Computer");
		categoryRepository.save(computer);

		Category java = new Category("Java", computer);
		categoryRepository.save(java);

		Category english = new Category("English");
		categoryRepository.save(english);

		Iterable<Category> categories = categoryRepository.findAll();
		for (Category category : categories) {
			System.out.println(category);
		}
	}

}
