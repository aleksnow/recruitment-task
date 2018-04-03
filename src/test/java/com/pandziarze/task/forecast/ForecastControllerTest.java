package com.pandziarze.task.forecast;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.pandziarze.task.forecast.Forecast;
import com.pandziarze.task.forecast.ForecastController;

@RunWith(SpringRunner.class)
@WebMvcTest(ForecastController.class)
public class ForecastControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ForecastController forecastController;

	@Test
	public void testWithIncorectNumbersOfParameters_expectedStatusError() throws Exception {

		String countryCode = "countryCode";

		mockMvc.perform(get("/average").param(countryCode, "x4")).andExpect(status().is4xxClientError());
	}

	@Test
	public void testWithoutParameters_expectedStatusError() throws Exception {

		mockMvc.perform(get("/average")).andExpect(status().is4xxClientError());
	}

	@Test
	public void isStatusOkWhenConsumeProperData() throws Exception {

		String cityName = "cityName";
		String countryCode = "countryCode";

		Forecast forecast = new Forecast();
		forecast.setCityName("Bielsko");
		forecast.setCountryCode("PL");

		given(forecastController.getWeatherForecast(forecast.getCountryCode(), forecast.getCityName()))
				.willReturn(forecast);

		mockMvc.perform(
				get("/average").param(cityName, "Bielsko").param(countryCode, "PL").contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("cityName", is(forecast.getCityName())))
				.andExpect(jsonPath("countryCode", is(forecast.getCountryCode())));
	}


}