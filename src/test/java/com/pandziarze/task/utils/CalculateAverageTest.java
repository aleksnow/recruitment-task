package com.pandziarze.task.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.pandziarze.task.forecast.ForecastEntry;

public class CalculateAverageTest {

	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	ForecastEntry forecastEntry;
	
	@Test
	public void properAverageCalculateForOneEntry() {
		
		//given
		Mockito.when(forecastEntry.getTemperature()).thenReturn(100.0);

		List<ForecastEntry> forecastList = new ArrayList<>();
		forecastList.add(forecastEntry);
		
		Double avgTemperature = 100.0;
		//when
		Double calculatedAdvTemp = CalculateAverage.toAverageValue(forecastList, ForecastEntry::getTemperature);
				
		//then
		assertNotNull(calculatedAdvTemp);
		assertEquals(avgTemperature, calculatedAdvTemp); 
	}
	
	
	@Test
	public void properAverageCalculateForSeveralEntris() {

		//given
		List<ForecastEntry> forecastList = Stream.of(100.0,100.0,100.0)
				.map(val -> {ForecastEntry ent = new ForecastEntry(); ent.setTemperature(val);return ent;})
				.collect(Collectors.toList());
				
		Double avgTemperature = 100.0;
		
		//when
		Double calculatedAdvTemp = CalculateAverage.toAverageValue(forecastList, ForecastEntry::getTemperature);
	
		//then
		assertNotNull(calculatedAdvTemp);
		assertEquals(avgTemperature, calculatedAdvTemp);
	}
	
	@Test
	public void properAverageCalculateForDifferentEntris() {

		//given
		List<ForecastEntry> forecastList = Stream.of(100.0, 200.0, 300.0, 200.0)
				.map(val -> {ForecastEntry ent = new ForecastEntry(); ent.setTemperature(val);return ent;})
				.collect(Collectors.toList());
				
		Double avgTemperature = 200.0;
		
		//when
		Double calculatedAdvTemp = CalculateAverage.toAverageValue(forecastList, ForecastEntry::getTemperature);
		
		//then
		assertNotNull(calculatedAdvTemp);
		assertEquals(avgTemperature, calculatedAdvTemp);
	}

}