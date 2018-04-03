package com.pandziarze.task.forecast;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pandziarze.task.utils.CalculateAverage;

public class Forecast {

	private String cityName;
	private String countryCode;
	private double averageTemperature;
	private double averagePressure;
	private double averageHumidity;

	@JsonSetter("list")
	public void calculateAverageValues(List<ForecastEntry> entries) {
		this.averageTemperature = CalculateAverage.toAverageValue(entries, ForecastEntry::getTemperature);
		this.averageHumidity = CalculateAverage.toAverageValue(entries, ForecastEntry::getHumidity);
		this.averagePressure = CalculateAverage.toAverageValue(entries, ForecastEntry::getPressure);
	}

	@JsonProperty("city")
	public void setCity(Map<String, Object> city) {
		setCityName(city.get("name").toString());
		setCountryCode(city.get("country").toString());
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String name) {
		this.cityName = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String country) {
		this.countryCode = country;
	}

	public Double getAverageTemperature() {
		return averageTemperature;
	}

	public Double getAveragePressure() {
		return averagePressure;
	}

	public Double getAverageHumidity() {
		return averageHumidity;
	}

}
