package com.ctb.organizeme.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.domain.ContentType;
import com.ctb.organizeme.domain.Language;
import com.ctb.organizeme.domain.LocationType;
import com.ctb.organizeme.domain.Progress;
import com.ctb.organizeme.support.user.dao.UserRepository;
import com.ctb.organizeme.support.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class ContentRepositoryTest {

	@Autowired
	ContentRepository contentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	@Transactional
	public void test() {
		Category category = new Category("Computer");
		ContentType type = ContentType.TEXT;
		Language language = Language.KOREAN;
		String title = "개발자의 하루";
		LocationType locationType = LocationType.LINK;
		String location = "http://devday.tistory.com/";
		String username = "izeye";
		User author = userRepository.findByUsername(username);
		Progress progress = Progress.TODO;
		Content content1 = new Content(category, type, language, title,
				locationType, location, author, progress);
		contentRepository.save(content1);
		System.out.println(content1.getId());

		Iterable<Content> contents = contentRepository.findAll();
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void findAllByOrderByIdDesc() {
		Iterable<Content> contents = contentRepository.findAllByOrderByIdDesc();
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void findByAuthorIdOrderByIdDesc() {
		Long authorId = 1L;
		Iterable<Content> contents = contentRepository
				.findByAuthorIdOrderByIdDesc(authorId);
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void findByCategoryOrderByIdDesc() {
		long categoryId = 1L;
		Category category = categoryRepository.findOne(categoryId);
		Iterable<Content> contents = contentRepository
				.findByCategoryOrderByIdDesc(category);
		for (Content content : contents) {
			System.out.println(content);
		}
	}

}
