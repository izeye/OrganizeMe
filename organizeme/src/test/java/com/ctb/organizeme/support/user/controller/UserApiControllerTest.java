package com.ctb.organizeme.support.user.controller;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ctb.organizeme.support.user.domain.User;

public class UserApiControllerTest {

	private static final String JSESSIONID = "JSESSIONID";

	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void check() {
		String loginUrl = "http://localhost:8080/organizeme/j_spring_security_check";
		String username = "izeye";
		String password = "1234";

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("j_username", username);
		parameters.add("j_password", password);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(
				loginUrl, parameters, String.class);
		System.out.println(responseEntity);

		HttpHeaders headers = responseEntity.getHeaders();
		String cookie = headers.get("Set-Cookie").get(0);
		System.out.println(cookie);

		String[] cookieEntries = cookie.split(";");
		String jSessionId = null;
		for (String cookieEntry : cookieEntries) {
			cookieEntry = cookieEntry.trim();
			if (cookieEntry.startsWith(JSESSIONID)) {
				jSessionId = cookieEntry.split("=")[1];
			}
		}

		String url = "http://localhost:8080/organizeme/api/v1/users/check";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Cookie", JSESSIONID + "=" + jSessionId);
		HttpEntity<String> requestEntity = new HttpEntity<>(null,
				requestHeaders);
		ResponseEntity<User> responseEntityForUser = restTemplate.exchange(url,
				HttpMethod.GET, requestEntity, User.class);
		System.out.println(responseEntityForUser);

		User user = responseEntityForUser.getBody();
		System.out.println(user);
	}

}
