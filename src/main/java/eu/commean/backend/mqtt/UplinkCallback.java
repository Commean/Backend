package eu.commean.backend.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.commean.backend.entity.TrafficMeasurement;
import eu.commean.backend.pojo.mqtt.Payload;
import eu.commean.backend.pojo.mqtt.ttn.TTNResponse;
import eu.commean.backend.service.NodeService;
import eu.commean.backend.service.TrafficMeasurementService;
import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Log4j2
public class UplinkCallback implements MqttCallback {

	ObjectMapper objectMapper = new ObjectMapper();


	TrafficMeasurementService measurementService;
	NodeService nodeService;

	public UplinkCallback(TrafficMeasurementService measurementService, NodeService nodeService) {
		this.measurementService = measurementService;
		this.nodeService = nodeService;
	}

	@Override
	public void connectionLost(Throwable cause) {
		log.error("Connection was lost!\nReason: {}", cause.getMessage());
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		TTNResponse response = objectMapper.readValue(message.toString(), TTNResponse.class);
		Payload payload = objectMapper.readValue(response.getUplinkMessage().getDecodedPayload().getText(), Payload.class);
		TrafficMeasurement trafficMeasurement = new TrafficMeasurement(payload.getCount().getTruck(), payload.getCount().getCar(), payload.getCount().getBus(), payload.getCount().getMotorbike(), payload.getAtip(), Timestamp.from(Instant.ofEpochSecond((Math.round(payload.getTime())))));
		trafficMeasurement.setNode(nodeService.getNodeById(UUID.fromString(payload.getId())));
		measurementService.addTrafficMeasurement(trafficMeasurement);

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}
}
