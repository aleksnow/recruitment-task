package com.pandziarze.task.forecast;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastEntry {

	private double temperature;
	private double pressure;
	private double humidity;

	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		setTemperature(Double.parseDouble(main.get("temp").toString()));
		setHumidity(Double.parseDouble(main.get("humidity").toString()));
		setPressure(Double.parseDouble(main.get("pressure").toString()));
	}

	public double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	

}
