package eu.commean.backend.service;

import eu.commean.backend.data.TrafficCameraNode;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrafficCameraNodeService {

	TrafficCameraNode addTrafficCameraNode(TrafficCameraNode tcn);

	List<TrafficCameraNode> getAllTrafficCameraNodes();

	TrafficCameraNode getTrafficCameraNodeById(UUID id);

	@Deprecated
	Optional<TrafficCameraNode> getTrafficCameraNodesByName(String name);

	List<TrafficCameraNode> getAllTrafficCameraNodesWhereLocatioNotNull();

	void deleteTrafficCameraNodeById(UUID id);
}
