package eu.commean.backend.service;

import eu.commean.backend.entity.TrafficCameraNode;
import eu.commean.backend.entity.TrafficMeasurement;

import java.util.List;
import java.util.UUID;

public interface TrafficMeasurementService {

	TrafficMeasurement addTrafficMeasurement(TrafficMeasurement tm);

	List<TrafficMeasurement> getAllTrafficMeasurements();

	TrafficMeasurement getTrafficMeasurementById(UUID id);

	List<TrafficMeasurement> getMeasurementsByTrafficCameraNode(TrafficCameraNode tcn);

	List<TrafficMeasurement> getAllMeasrumentsFromTimespan(UUID id, int days, int hours, int minutes, int seconds);
	
	TrafficMeasurement getLatestMeasurementFromId(UUID id);

	void deleteTrafficMeasurementById(UUID id);

	void createHypertable();
}
