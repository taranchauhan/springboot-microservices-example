package io.java.spring.microservices.musicchartservice.helpers;

import java.util.Comparator;

import io.java.spring.microservices.musicchartservice.models.ChartItem;

public class ChartItemComparator implements Comparator<ChartItem> {
	@Override
	public int compare(ChartItem a, ChartItem b) {
		// Ascending order
		return a.getChartPosition().getChartPosition() - b.getChartPosition().getChartPosition();
	} 
}
