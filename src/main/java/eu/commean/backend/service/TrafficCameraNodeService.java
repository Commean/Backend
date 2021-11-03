package eu.commean.backend.service;

import java.util.List;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;

public interface TrafficCameraNodeService {

	TrafficCameraNode addTrafficCameraNode(TrafficCameraNode tcn);

	List<TrafficCameraNode> getAllTrafficCameraNodes();

	TrafficCameraNode getTrafficCameraNodeById(int id);

	List<TrafficCameraNode> getTrafficCameraNodesByCrossroad(Crossroad c);

	void deleteTrafficCameraNodeById(int id);
}
