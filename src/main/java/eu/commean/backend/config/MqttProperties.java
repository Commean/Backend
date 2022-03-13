package eu.commean.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "commean.mqtt")

public class MqttProperties {
	private String ttnAppId;
	private String ttnUrl;
	private String clientId;

	public String getTtnAppId() {
		return ttnAppId;
	}

	public void setTtnAppId(String ttnAppId) {
		this.ttnAppId = ttnAppId;
	}

	public String getTtnUrl() {
		return ttnUrl;
	}

	public void setTtnUrl(String ttnUrl) {
		this.ttnUrl = ttnUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
