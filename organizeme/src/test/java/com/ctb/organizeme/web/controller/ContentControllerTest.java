package com.ctb.organizeme.web.controller;

import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.ctb.organizeme.domain.Content;

public class ContentControllerTest {

	RestTemplate restTemplate = new RestTemplate();

	@Test
	@SuppressWarnings("unchecked")
	public void getAllPagedContents() {
		String url = "http://localhost:8080/organizeme/content/all.json?mine={mine}";

		Map<String, Object> allPagedContents = restTemplate.getForObject(url,
				Map.class, false);
		System.out.println(allPagedContents);
	}

	@Test
	public void searchByLocation() {
		String url = "http://localhost:8080/organizeme/contents/search?location={location}";
		String location = "http://devday.tistory.com/";

		Content content = restTemplate.getForObject(url, Content.class,
				location);
		System.out.println(content);
	}

}
