package eu.commean.backend.dto;

import eu.commean.backend.dto.measurement.TrafficMeasurementStatisticsRealtimeDto;
import lombok.Data;

import java.util.List;

@Data
public class NodeStatisticsDto {

	private int id;
	private List<TrafficMeasurementStatisticsRealtimeDto> measurements;


}
