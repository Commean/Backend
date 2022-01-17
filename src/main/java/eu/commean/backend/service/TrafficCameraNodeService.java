package eu.commean.backend.service;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;

import java.util.List;
import java.util.UUID;

public interface TrafficCameraNodeService {

	TrafficCameraNode addTrafficCameraNode(TrafficCameraNode tcn);

	List<TrafficCameraNode> getAllTrafficCameraNodes();

	TrafficCameraNode getTrafficCameraNodeById(UUID id);

	List<TrafficCameraNode> getTrafficCameraNodesByCrossroad(Crossroad c);

	List<TrafficCameraNode> getAllTrafficCameraNodesWhereLocatioNotNull();

	void deleteTrafficCameraNodeById(UUID id);
}
