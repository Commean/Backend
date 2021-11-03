package eu.commean.backend.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;

public interface TrafficCameraNodeRepository extends CrudRepository<TrafficCameraNode, Integer> {
	List<TrafficCameraNode> findByCrossroad(Crossroad crossroad);
}