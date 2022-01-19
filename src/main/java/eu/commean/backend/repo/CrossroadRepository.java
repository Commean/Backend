package eu.commean.backend.repo;

import eu.commean.backend.data.Crossroad;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Deprecated
public interface CrossroadRepository extends CrudRepository<Crossroad, UUID> {

	List<Crossroad> findByCrossroadName(String crossroadName);

	@Override
	@Transactional
	@EntityGraph(attributePaths = {"trafficCameraNode"})
	List<Crossroad> findAll();

}
