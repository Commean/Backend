package eu.commean.backend.security;

import eu.commean.backend.service.ApiKeyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Log4j2
public class ApiKeyAuthManager implements AuthenticationManager {

	@Autowired
	private ApiKeyService apiKeyService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String principal = (String) authentication.getPrincipal();

		if (apiKeyService.checkForApiKey(principal) != 1) {
			throw new BadCredentialsException("The API key was not found or not the expected value.");
		} else {
			authentication.setAuthenticated(true);
			log.debug("Auth Successful");
			return authentication;
		}


	}
}
