package eu.commean.backend.dto.measurement;

import eu.commean.backend.entity.Node;
import eu.commean.backend.entity.TrafficMeasurement;
import eu.commean.backend.enums.TrafficSituation;
import lombok.Data;

import java.util.UUID;

@Data
public class TrafficMeasurementStatisticsRealtimeDto {
	private UUID nodeId;
	private String nodeName;
	private int currentCars;
	private int currentTrucks;
	private int averageTimeInPicture;
	private TrafficSituation trafficSituation;
	private int waitTime = 0;

	public TrafficMeasurementStatisticsRealtimeDto(UUID nodeId, String nodeName, int currentCars, int currentTrucks, int averageTimeInPicture) {
		this.nodeId = nodeId;
		this.nodeName = nodeName;
		this.currentCars = currentCars;
		this.currentTrucks = currentTrucks;
		this.averageTimeInPicture = averageTimeInPicture;
		this.trafficSituation = calcTrafficSituation();
	}

	static public TrafficMeasurementStatisticsRealtimeDto convertToDto(TrafficMeasurement tm, Node node) {
		return new TrafficMeasurementStatisticsRealtimeDto(tm.getNode().getId(), node.getName(), tm.getCars(), tm.getTrucks(), tm.getAverageTimeInPicture());
	}

	//TODO:Better traffic analysation
	public TrafficSituation calcTrafficSituation() {
		int trafficAverage = (this.currentCars + this.currentTrucks) * this.averageTimeInPicture;

		if (trafficAverage < 11) {
			return TrafficSituation.FEW;
		} else if (trafficAverage < 30) {
			return TrafficSituation.SOME;
		} else if (trafficAverage < 60) {
			return TrafficSituation.NORMAL;
		}
		return TrafficSituation.HIGH;
	}

}
