package eu.commean.backend.service;

import eu.commean.backend.entity.ApiKey;
import eu.commean.backend.repo.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

	ApiKeyRepository apiKeyRepository;

	@Autowired
	public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository) {
		this.apiKeyRepository = apiKeyRepository;
	}

	public ApiKeyServiceImpl() {

	}


	@Override
	public ApiKey generateApiKey() {
		byte[] rawKey = new byte[32];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(rawKey);

		String key = Base64.getEncoder().encodeToString(rawKey);

		return apiKeyRepository.save(new ApiKey(key));

	}

	@Override
	public ApiKey getApiKey(String key) {

		return apiKeyRepository.findByKeyLike(key);
	}

	@Override
	public long checkForApiKey(String key) {
		return apiKeyRepository.countByKeyLike(key);
	}

	@Override
	public void deleteApiKey(String key) {
		apiKeyRepository.deleteApiKeyByKey(key);
	}
}
