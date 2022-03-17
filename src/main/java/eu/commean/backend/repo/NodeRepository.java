package eu.commean.backend.repo;

import eu.commean.backend.entity.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NodeRepository extends CrudRepository<Node, UUID> {
	Optional<Node> findByNameLike(String name);


	Iterable<Node> findAllWhereLocationNotNull();

	Iterable<Node> findByTtnIdIsNotNull();

	Optional<Node> findByTtnId(String ttnId);


}