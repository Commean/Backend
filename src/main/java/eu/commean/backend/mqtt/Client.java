package eu.commean.backend.mqtt;

import eu.commean.backend.config.MqttProperties;
import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.*;

@Log4j2
public class Client {
	private final static String topicTemplate = "v3/%s@ttn/devices/%s/up";

	private static MqttAsyncClient asyncClient;

	private static MqttProperties mqttProperties;

	public Client(MqttProperties mqttProperties) {
		this.mqttProperties = mqttProperties;
	}

	/**
	 * Initiates a {@link MqttAsyncClient Async Mqtt Client} with preconfigured server URL and client ID. Configure in application.yml
	 *
	 * @return boolean - operation status
	 */
	public static boolean create() {
		if (Client.asyncClient == null) {
			try {
				asyncClient = new MqttAsyncClient(mqttProperties.getTtnUrl(), mqttProperties.getClientId());

				MqttConnectOptions connOpts = new MqttConnectOptions();
				connOpts.setCleanSession(true);
				connOpts.setUserName("%s@ttn".formatted(mqttProperties.getTtnAppId()));
				connOpts.setPassword(mqttProperties.getTtnApiKey().toCharArray());
				log.debug(connOpts.toString());
				MqttToken token = (MqttToken) asyncClient.connect(connOpts);
				token.waitForCompletion(5000);
				log.info("MQTT connected");
				return true;
			} catch (MqttException e) {
				log.error("MQTT connection failed \n{}", e.toString());
				return false;

			}
		}
		return false;
	}

	/**
	 * Return the {@link MqttAsyncClient asyncClient} of the Client Object
	 *
	 * @return {@link MqttAsyncClient asyncClient}
	 */
	public static MqttAsyncClient getAsyncClient() {
		return asyncClient;
	}

	public void setCallback(MqttCallback mqttCallback) {
		asyncClient.setCallback(mqttCallback);

	}

	/**
	 * Subscribe to a MQTT Topic of an Uplink from TTN
	 *
	 * @param devId Topic to subscribe to
	 * @param qos   Ranges from 0-2
	 * @return boolean - operation status
	 */
	public boolean subscribe(String devId, int qos) {
		try {
			asyncClient.subscribe(topicTemplate.formatted(mqttProperties.getTtnAppId(), devId), qos);
			return true;
		} catch (MqttException e) {
			log.error(e.toString());
			return false;

		}

	}

	/**
	 * Subscribe to a MQTT Topic of an Uplink from TTN
	 *
	 * @param devId Id of TTN device
	 * @return boolean - soperation status
	 * @apiNote QoS defaults to 0
	 */
	public boolean subscribe(String devId) {
		try {
			asyncClient.subscribe(topicTemplate.formatted(mqttProperties.getTtnAppId(), devId), 0);
			log.debug(topicTemplate.formatted(mqttProperties.getTtnAppId(), devId));
			return true;
		} catch (MqttException e) {
			log.error(e.toString());
			return false;

		}

	}

}
