package com.pandziarze.task.utils;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pandziarze.task.exceptions.ConsumeDateException;

public final class ConsumeData {
	private static final Logger logger = LoggerFactory.getLogger(ConsumeData.class);

	private ConsumeData() {
		throw new AssertionError();
	}

	public static <T> T invoke(URI url, Class<T> responseType, RestTemplate restTemplate) {
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();

		try {
			ResponseEntity<T> exchange = restTemplate
				.exchange(request, responseType);
				return exchange.getBody();
		}
		catch (HttpClientErrorException e) {
			logger.error("HttpClientError {} in url {} call", e.getMessage(), url, e);
			ConsumeDateException exc = new ConsumeDateException(e.getMessage());
		  	exc.initCause(e);
			throw exc;
		}
	}
}
