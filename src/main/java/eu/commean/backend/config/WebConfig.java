package eu.commean.backend.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Log4j2
public class WebConfig implements WebMvcConfigurer {

	@Value("${commean.cross-server}")
	private String crossServerAddress;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.debug("Server URL is: {}", crossServerAddress);
		registry.addMapping("/**").allowedOrigins(crossServerAddress);
	}

}
