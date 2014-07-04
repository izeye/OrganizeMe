package com.ctb.organizeme.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ctb.organizeme.domain.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class TagRepositoryTest {

	@Autowired
	TagRepository tagRepository;

	@Test
	@Transactional
	public void findByName() {
		String name = "java";
		Tag tag1 = new Tag(name);
		tagRepository.save(tag1);

		Tag tag = tagRepository.findByName(name);
		System.out.println(tag);
	}

}
