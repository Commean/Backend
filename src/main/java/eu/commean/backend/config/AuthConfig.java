package eu.commean.backend.config;

import eu.commean.backend.security.ApiKeyAuthFilter;
import eu.commean.backend.security.ApiKeyAuthManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(1)
public class AuthConfig extends WebSecurityConfigurerAdapter {
	private static final String API_KEY_AUTH_HEADER_NAME = "API_KEY";

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		ApiKeyAuthFilter filter = new ApiKeyAuthFilter(API_KEY_AUTH_HEADER_NAME);
		filter.setAuthenticationManager(new ApiKeyAuthManager());

		http.antMatcher("/**").
				csrf().
				disable().
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
				and()
				.addFilter(filter)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/v1/measurements/**")
				.authenticated();

	}
}
