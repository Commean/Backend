package eu.commean.backend.repo;

import org.springframework.data.repository.CrudRepository;

import eu.commean.backend.data.TrafficCameraNode;

public interface TrafficCameraNodeRepository extends CrudRepository<TrafficCameraNode, Integer> {
	public Iterable<TrafficCameraNode> findByLatitude(String latitude);
}