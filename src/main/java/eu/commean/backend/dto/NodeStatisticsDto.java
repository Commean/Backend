package eu.commean.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class NodeStatisticsDto {
	
	private int id;
	private List<TrafficMeasurementStatisticsRealtimeDto> measurements;
	

}
