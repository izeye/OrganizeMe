package com.ctb.organizeme.service;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctb.organizeme.domain.Category;
import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.domain.ContentType;
import com.ctb.organizeme.domain.Language;
import com.ctb.organizeme.domain.LocationType;
import com.ctb.organizeme.domain.Progress;
import com.ctb.organizeme.domain.Tag;
import com.ctb.organizeme.support.user.dao.UserRepository;
import com.ctb.organizeme.support.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class ContentServiceTest {

	@Autowired
	ContentService contentService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TagService tagService;

	@Test
	public void getAllContents() {
		Iterable<Content> contents = contentService.getAllContents();
		for (Content content : contents) {
			System.out.println(content);
		}
	}

	@Test
	public void getContentById() {
		Long contentId = 15L;
		Content content = contentService.getContentById(contentId);
		System.out.println(content);
	}

	@Test
	public void addContent() {
		long categoryId = 1L;
		Category category = categoryService.getCategory(categoryId);

		ContentType type = ContentType.TEXT;
		Language language = Language.KOREAN;
		String title = "네이버";
		LocationType locationType = LocationType.LINK;
		String location = "http://www.naver.com/";
		String username = "izeye";
		User owner = userRepository.findByUsername(username);
		Progress progress = Progress.TODO;

		String tagName = "computer";
		Tag tag = tagService.getTag(tagName);

		Content author = new Content(category, type, language, title,
				locationType, location, owner, progress,
				Collections.singletonList(tag));
		contentService.saveContent(author);
	}

}
