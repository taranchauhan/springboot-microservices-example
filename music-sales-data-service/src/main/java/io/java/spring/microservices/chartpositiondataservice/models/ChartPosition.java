package io.java.spring.microservices.chartpositiondataservice.models;

public class ChartPosition {

	private String musicTrackId;
	private int chartPosition;
	private int weeksOnChart;
	private int peakPosition;
	
	public ChartPosition(String musicTrackId, int chartPosition, int weeksOnChart, int peakPosition) {
		super();
		this.musicTrackId = musicTrackId;
		this.chartPosition = chartPosition;
		this.weeksOnChart = weeksOnChart;
		this.peakPosition = peakPosition;
	}
	public String getMusicTrackId() {
		return musicTrackId;
	}
	public void setMusicTrackId(String musicTrackId) {
		this.musicTrackId = musicTrackId;
	}
	public int getChartPosition() {
		return chartPosition;
	}
	public void setChartPosition(int chartPosition) {
		this.chartPosition = chartPosition;
	}
	public int getWeeksOnChart() {
		return weeksOnChart;
	}
	public void setWeeksOnChart(int weeksOnChart) {
		this.weeksOnChart = weeksOnChart;
	}
	public int getPeakPosition() {
		return peakPosition;
	}
	public void setPeakPosition(int peakPosition) {
		this.peakPosition = peakPosition;
	}
}
