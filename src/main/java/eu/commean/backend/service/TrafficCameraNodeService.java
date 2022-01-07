package eu.commean.backend.service;

import java.util.List;
import java.util.UUID;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;

public interface TrafficCameraNodeService {

	TrafficCameraNode addTrafficCameraNode(TrafficCameraNode tcn);

	List<TrafficCameraNode> getAllTrafficCameraNodes();

	TrafficCameraNode getTrafficCameraNodeById(UUID id);

	List<TrafficCameraNode> getTrafficCameraNodesByCrossroad(Crossroad c);

	void deleteTrafficCameraNodeById(UUID id);
}
