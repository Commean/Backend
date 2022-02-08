package eu.commean.backend.repo;

import eu.commean.backend.entity.TrafficCameraNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrafficCameraNodeRepository extends CrudRepository<TrafficCameraNode, UUID> {
	Optional<TrafficCameraNode> findByNameLike(String name);


	Iterable<TrafficCameraNode> findAllWhereLocationNotNull();
}