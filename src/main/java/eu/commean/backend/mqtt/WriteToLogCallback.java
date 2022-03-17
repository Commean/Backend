package eu.commean.backend.mqtt;

import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Log4j2
public class WriteToLogCallback implements MqttCallback {
	@Override
	public void connectionLost(Throwable cause) {
		log.error(cause.getMessage());
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		log.debug("\nTopic: \n{}\nMessage: \n{}", topic, message);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}
}
