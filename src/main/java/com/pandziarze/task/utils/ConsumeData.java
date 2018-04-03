package com.pandziarze.task.utils;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pandziarze.task.exceptions.InvalideResponseCodeException;

public final class ConsumeData {

	private ConsumeData() {
		throw new AssertionError();
	}

	public static <T> T invoke(URI url, Class<T> responseType, RestTemplate restTemplate) {
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();

		ResponseEntity<T> exchange = restTemplate
				.exchange(request, responseType);
		if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
		  	throw new InvalideResponseCodeException("Status code is not 200");
		}
		return exchange.getBody();
	}
}
