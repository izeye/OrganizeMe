package com.ctb.organizeme.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class TagServiceTest {

	@Autowired
	TagService tagService;

	@Test
	public void getTag() {
		String tagName = "java";
		System.out.println(tagService.getTag(tagName));
	}

	@Test
	public void getTags() {
		List<String> tagNames = Arrays.asList(new String[] { "javascript",
				"requirejs" });
		System.out.println(tagService.getTags(tagNames));
	}

}
