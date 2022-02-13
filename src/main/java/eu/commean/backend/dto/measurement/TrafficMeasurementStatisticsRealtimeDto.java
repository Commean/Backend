package eu.commean.backend.dto.measurement;

import eu.commean.backend.entity.TrafficMeasurement;
import eu.commean.backend.enums.TrafficSituation;
import lombok.Data;

import java.util.UUID;

@Data
public class TrafficMeasurementStatisticsRealtimeDto {
	private UUID tcnId;
	private int currentCars;
	private int currentTrucks;
	private int averageTimeInPicture;
	private TrafficSituation trafficSituation;
	private int waitTime = 0;

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

	public TrafficMeasurementStatisticsRealtimeDto(UUID trafficCameraNodeId,int currentCars, int currentTrucks, int averageTimeInPicture) {
		super();
		this.tcnId =trafficCameraNodeId;
		this.currentCars = currentCars;
		this.currentTrucks = currentTrucks;
		this.averageTimeInPicture = averageTimeInPicture;
		this.trafficSituation = calcTrafficSituation();
	}

	static public TrafficMeasurementStatisticsRealtimeDto convertToDto(TrafficMeasurement tm) {
		return new TrafficMeasurementStatisticsRealtimeDto(tm.getTrafficCameraNode().getId(),tm.getCars(), tm.getTrucks	(),
				tm.getAverageTimeInPicture());

	}

}
