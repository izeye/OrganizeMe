package com.ctb.organizeme.web.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ctb.organizeme.domain.Tag;
import com.ctb.organizeme.service.TagService;

public class TagsConverter implements Converter<String, List<Tag>> {

	@Autowired
	private TagService tagService;

	@Override
	public List<Tag> convert(String source) {
		List<String> tagNames = new ArrayList<String>();
		for (String tagName : source.split(",")) {
			tagName = tagName.trim();
			if (!tagName.isEmpty()) {
				tagNames.add(tagName);
			}
		}
		return tagService.getTags(tagNames);
	}

}
