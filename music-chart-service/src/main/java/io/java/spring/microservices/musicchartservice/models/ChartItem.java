package io.java.spring.microservices.musicchartservice.models;

public class ChartItem {
	private MusicTrack track;
	private ChartPosition chartPosition;
	
	public ChartItem() {
		
	}
	
	public ChartItem(MusicTrack track, ChartPosition chartPosition) {
		super();
		this.track = track;
		this.chartPosition = chartPosition;
	}
	public MusicTrack getTrack() {
		return track;
	}
	public void setTrack(MusicTrack track) {
		this.track = track;
	}
	public ChartPosition getChartPosition() {
		return chartPosition;
	}
	public void setChartPosition(ChartPosition chartPosition) {
		this.chartPosition = chartPosition;
	}
}
