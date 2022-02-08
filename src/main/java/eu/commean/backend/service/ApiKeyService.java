package eu.commean.backend.service;

import eu.commean.backend.entity.ApiKey;


public interface ApiKeyService {
	/**
	 * Generates a secure Random ApiKey, saves it in the database and returns it
	 *
	 * @return ApiKey
	 */
	ApiKey generateApiKey();

	/**
	 * Gets the given ApiKey from Database
	 *
	 * @param key ApiKey to get form Database
	 * @return ApiKey
	 */
	ApiKey getApiKey(String key);

	/**
	 * Checks the Database if this Key exists
	 *
	 * @param key ApiKey to look for
	 * @return count
	 */
	long checkForApiKey(String key);

	void deleteApiKey(String key);
}
