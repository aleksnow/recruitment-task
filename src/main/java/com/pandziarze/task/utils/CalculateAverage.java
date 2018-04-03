package com.pandziarze.task.utils;

import java.util.List;
import java.util.function.ToDoubleFunction;

import com.pandziarze.task.forecast.ForecastEntry;

public final class CalculateAverage {

	private CalculateAverage() {
		throw new AssertionError();
	}

	public static Double toAverageValue(List<ForecastEntry> list, ToDoubleFunction<ForecastEntry> maper) {
		Double average = list.stream().mapToDouble(maper).average().orElse(Double.NaN);
		return (double) Math.round(average * 100) / 100;
	}
}