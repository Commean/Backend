package eu.commean.backend.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eu.commean.backend.data.Crossroad;

@Repository
public interface CrossroadRepository extends CrudRepository<Crossroad, Integer> {

	List<Crossroad> findByName(String name);

}
