package eu.commean.backend;

import eu.commean.backend.config.MqttProperties;
import eu.commean.backend.entity.Node;
import eu.commean.backend.mqtt.Client;
import eu.commean.backend.mqtt.UplinkCallback;
import eu.commean.backend.service.NodeService;
import eu.commean.backend.service.TrafficMeasurementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class AppStartupRunner {

	private static MqttProperties mqttProperties;
	TrafficMeasurementService measurementService;
	NodeService nodeService;

	@Autowired
	public AppStartupRunner(MqttProperties mqttProperties, TrafficMeasurementService measurementService, NodeService nodeService) {
		this.mqttProperties = mqttProperties;
		this.measurementService = measurementService;
		this.nodeService = nodeService;
	}


	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Client client = new Client(mqttProperties);
		Client.create();
		this.subscribeToAllNodes(client);
		client.setCallback(new UplinkCallback(measurementService, nodeService));
	}

	private void subscribeToAllNodes(Client client) {

		List<Node> nodes = nodeService.getAllNodesWithTTNConnection();
		if (nodes != null) {
			for (Node node : nodes) {
				log.debug("{},{}", node.getId(), node.getTtnId());
			}
			nodes.stream().forEach(node -> client.subscribe(node.getTtnId()));
			log.debug("Subsribed to {} node(s)", nodes.size());
		}
	}
}