package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImgurService {
	private static final Logger logger = LoggerFactory.getLogger(ImgurService.class);
	@Value("${imgur.client.id}")
	private String clientId;
	private final RestTemplate restTemplate = new RestTemplate();

	public String uploadImage(String base64Image) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + clientId);
		HttpEntity<String> request = new HttpEntity<>(base64Image, headers);
		ResponseEntity<String> response = restTemplate.exchange("https://api.imgur.com/3/image", HttpMethod.POST,
				request, String.class);
		return response.getBody();
	}

	public void deleteImage(String imageId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + clientId);
		restTemplate.exchange("https://api.imgur.com/3/image/" + imageId, HttpMethod.DELETE, new HttpEntity<>(headers),
				Void.class);
	}
}
