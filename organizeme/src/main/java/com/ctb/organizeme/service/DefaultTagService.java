package com.ctb.organizeme.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctb.organizeme.dao.TagRepository;
import com.ctb.organizeme.domain.Tag;

@Service
public class DefaultTagService implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public Iterable<Tag> getAllTags() {
		return tagRepository.findAllByOrderByNameAsc();
	}

	@Override
	public Tag getTag(String tagName) {
		Tag tag = tagRepository.findByName(tagName);
		if (tag == null) {
			tag = new Tag(tagName);
			tagRepository.save(tag);
		}
		return tag;
	}

	@Override
	public List<Tag> getTags(List<String> tagNames) {
		List<Tag> tags = new ArrayList<Tag>();
		for (String tagName : tagNames) {
			tags.add(getTag(tagName));
		}
		return tags;
	}

}
