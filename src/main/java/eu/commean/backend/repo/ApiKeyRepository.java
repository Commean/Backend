package eu.commean.backend.repo;

import eu.commean.backend.entity.ApiKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApiKeyRepository extends CrudRepository<ApiKey, UUID> {

	ApiKey findByKeyLike(String key);

	void deleteApiKeyByKey(String key);

	long countByKeyLike(String key);
}
