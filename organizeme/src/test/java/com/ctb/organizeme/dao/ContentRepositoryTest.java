package com.ctb.organizeme.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.domain.Content;
import com.ctb.organizeme.domain.ContentType;
import com.ctb.organizeme.domain.Language;
import com.ctb.organizeme.domain.LocationType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class ContentRepositoryTest {

	@Autowired
	ContentRepository contentRepository;

	@Test
	@Transactional
	public void test() {
		ContentType type = ContentType.TEXT;
		Language language = Language.KOREAN;
		String title = "개발자의 하루";
		LocationType locationType = LocationType.LINK;
		String location = "http://devday.tistory.com/";
		Content content1 = new Content(type, language, title, locationType,
				location);
		contentRepository.save(content1);
		System.out.println(content1.getId());

		Iterable<Content> contents = contentRepository.findAll();
		for (Content content : contents) {
			System.out.println(content);
		}
	}

}
