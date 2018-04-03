package com.pandziarze.task.forecast;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.pandziarze.task.utils.ConsumeData;

@Service
@PropertySource(value="classpath:openweathermap.properties")
public class ForecastService {

	@Value("${openweathermap.api.key}")
	private String apiKey;
	
	private static final String FORECAST_URL =
			"http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

	private static final Logger logger = LoggerFactory.getLogger(ForecastService.class);

	private final RestTemplate restTemplate;


	public ForecastService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	

	@Cacheable("forecast")
	public Forecast getWeatherForecast(String country, String city) {
		logger.info("Requesting weather forecast for {}/{}", country, city);
		URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
		return ConsumeData.invoke(url, Forecast.class, restTemplate);
	}

}
