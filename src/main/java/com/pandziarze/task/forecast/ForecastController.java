package com.pandziarze.task.forecast;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pandziarze.task.forecast.Forecast;
import com.pandziarze.task.forecast.ForecastService;

@RestController
public class ForecastController {

	private final ForecastService forecastService;

	public ForecastController(ForecastService forecastService) {
		this.forecastService = forecastService;
	}

	@RequestMapping(value= "/average", method = RequestMethod.GET)
	public Forecast getWeatherForecast(@RequestParam("countryCode") String country,
			@RequestParam("cityName") String city) {
		return this.forecastService.getWeatherForecast(country, city);
	}
	
	@RequestMapping("/")
	public String home() {
		return "To receive average values please use http://localhost:8080/average?cityName={yourCityName}&countryCode={yourCountryCode} url";
	}
	
	
	

}
