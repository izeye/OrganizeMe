package com.ctb.organizeme.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctb.organizeme.domain.Tag;
import com.ctb.organizeme.service.TagService;

@RestController
public class TagController {

	@Autowired
	private TagService tagService;

	@RequestMapping("/tags")
	public Iterable<Tag> tags() {
		return tagService.getAllTags();
	}

}
