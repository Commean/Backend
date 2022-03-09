package eu.commean.backend.dto.measurement;

import eu.commean.backend.entity.Node;
import eu.commean.backend.entity.TrafficMeasurement;
import eu.commean.backend.enums.TrafficSituation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MeasurementPopupDto {

	private UUID nodeId;
	private String nodeName;
	private TrafficSituation trafficSituation;
	private int waitTime = 0;

	static public MeasurementPopupDto convertToDto(TrafficMeasurement tm, Node node) {

		int trafficAverage = (tm.getCars() + tm.getTrucks()) * tm.getAverageTimeInPicture();
		TrafficSituation trafficSituation;
		if (trafficAverage < 11) {
			trafficSituation = TrafficSituation.FEW;
		} else if (trafficAverage < 30) {
			trafficSituation = TrafficSituation.SOME;
		} else if (trafficAverage < 60) {
			trafficSituation = TrafficSituation.NORMAL;
		} else {
			trafficSituation = TrafficSituation.HIGH;
		}
		return new MeasurementPopupDto(node.getId(), node.getName(), trafficSituation, 0);


	}
}
