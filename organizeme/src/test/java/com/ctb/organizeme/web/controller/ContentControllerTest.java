package com.ctb.organizeme.web.controller;

import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

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

}
