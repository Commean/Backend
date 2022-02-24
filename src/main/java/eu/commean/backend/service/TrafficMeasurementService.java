package eu.commean.backend.service;

import eu.commean.backend.entity.Node;
import eu.commean.backend.entity.TrafficMeasurement;

import java.util.List;
import java.util.UUID;


/**
 * Node Service - Performs operations on Database via {@link eu.commean.backend.repo.TrafficMeasurementRepository TrafficMeasurementRepository}, Entity: {@link eu.commean.backend.entity.TrafficMeasurement}.
 *
 * @author Luca Nachbar
 **/
public interface TrafficMeasurementService {

	TrafficMeasurement addTrafficMeasurement(TrafficMeasurement tm);

	List<TrafficMeasurement> getAllTrafficMeasurements();

	TrafficMeasurement getTrafficMeasurementById(UUID id);

	List<TrafficMeasurement> getMeasurementsByNode(Node tcn);

	List<TrafficMeasurement> getAllMeasrumentsFromTimespan(UUID id, int days, int hours, int minutes, int seconds);

	TrafficMeasurement getLatestMeasurementFromId(UUID id);

	int currentWaitTimeOnNode(UUID id);

	void deleteTrafficMeasurementById(UUID id);

	void createHypertable();
}
