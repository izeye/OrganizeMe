package com.ctb.organizeme.dao;

import static com.ctb.organizeme.web.controller.ContentController.PAGE_SIZE_CONTENTS;
import static com.ctb.organizeme.web.controller.ContentController.SORT_DIRECTION_CONTENTS;
import static com.ctb.organizeme.web.controller.ContentController.SORT_PROPERTY;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.domain.ContentType;
import com.ctb.organizeme.domain.Language;
import com.ctb.organizeme.domain.LocationType;
import com.ctb.organizeme.domain.Progress;
import com.ctb.organizeme.domain.Tag;
import com.ctb.organizeme.service.CategoryService;
import com.ctb.organizeme.service.TagService;
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
	CategoryService categoryService;

	@Autowired
	TagService tagService;

	@Test
	@Transactional
	public void test() {
		long categoryId = 1L;
		Category category = categoryService.getCategory(categoryId);

		ContentType type = ContentType.TEXT;
		Language language = Language.KOREAN;
		String title = "개발자의 하루";
		LocationType locationType = LocationType.LINK;
		String location = "http://devday.tistory.com/";
		String username = "izeye";
		User author = userRepository.findByUsername(username);
		Progress progress = Progress.TODO;

		String tagName = "computer";
		Tag tag = tagService.getTag(tagName);

		Content content1 = new Content(category, type, language, title,
				locationType, location, author, progress,
				Collections.singletonList(tag));
		contentRepository.save(content1);
		System.out.println(content1.getId());

		Iterable<Content> contents = contentRepository.findAll();
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void findAllWithPageable() {
		Pageable pageable = new PageRequest(0, PAGE_SIZE_CONTENTS,
				SORT_DIRECTION_CONTENTS, SORT_PROPERTY);
		Page<Content> contents = contentRepository.findAll(pageable);
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void findByAuthorIdWithPageable() {
		Long authorId = 1L;
		Pageable pageable = new PageRequest(0, PAGE_SIZE_CONTENTS,
				SORT_DIRECTION_CONTENTS, SORT_PROPERTY);
		Page<Content> contents = contentRepository.findByAuthorId(authorId,
				pageable);
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void findByCategoryOrderByIdDesc() {
		long categoryId = 1L;
		Category category = categoryService.getCategory(categoryId);
		Iterable<Content> contents = contentRepository
				.findByCategoryOrderByIdDesc(category);
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void count() {
		long count = contentRepository.count();
		System.out.println(count);
	}

	@Test
	public void findAllWithPageRequest() {
		Page<Content> pagedContents = contentRepository
				.findAll(new PageRequest(0, 10));
		System.out.println(pagedContents);

		pagedContents = contentRepository.findAll(new PageRequest(1, 10));
		System.out.println(pagedContents);

		pagedContents = contentRepository.findAll(new PageRequest(2, 10));
		System.out.println(pagedContents);
	}

}
