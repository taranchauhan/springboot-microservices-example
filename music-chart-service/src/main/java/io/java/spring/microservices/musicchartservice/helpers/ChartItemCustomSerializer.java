package io.java.spring.microservices.musicchartservice.helpers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import io.java.spring.microservices.musicchartservice.models.ChartItem;

public class ChartItemCustomSerializer extends StdSerializer<ChartItem> {
   
	private static final long serialVersionUID = 3410503736561850544L;

	public ChartItemCustomSerializer() {
        this(null);
    }
   
    public ChartItemCustomSerializer(Class<ChartItem> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      ChartItem value, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeStringField("id", value.getTrack().getId());
        jgen.writeStringField("artistName", value.getTrack().getArtistName());
        jgen.writeStringField("songName", value.getTrack().getSongName());
        jgen.writeNumberField("chartPosition", value.getChartPosition().getChartPosition());
        jgen.writeNumberField("weeksOnChart", value.getChartPosition().getWeeksOnChart());
        jgen.writeNumberField("peakPosition", value.getChartPosition().getPeakPosition());
        jgen.writeEndObject();
    }
}