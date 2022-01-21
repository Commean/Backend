package eu.commean.backend;

import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.data.TrafficMeasurement;
import eu.commean.backend.service.TrafficCameraNodeService;
import eu.commean.backend.service.TrafficMeasurementService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.util.PGTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ServicesTests {

	final UUID NODE001_ID = UUID.fromString("9541673e-2ab2-4ff0-a555-cf27c73a2a05");
	UUID MEASUREMENT_ID = new UUID(0, 0);
	TrafficCameraNodeService tcns;
	TrafficMeasurementService tms;

	@Autowired
	public ServicesTests(TrafficCameraNodeService tcns, TrafficMeasurementService tms) {
		this.tcns = tcns;
		this.tms = tms;
	}

	@BeforeAll
	void setupTests() {
		TrafficCameraNode trafficCameraNode001 = new TrafficCameraNode(NODE001_ID, "POINT(0,0)");
		TrafficCameraNode trafficCameraNode002 = new TrafficCameraNode(UUID.fromString("711d5bde-4a8f-4370-877e-57225dd348e7"));
		TrafficMeasurement trafficMeasurement001 = new TrafficMeasurement(1, 2, 3, PGTimestamp.from(Instant.now()), trafficCameraNode001);
		TrafficMeasurement trafficMeasurement002 = new TrafficMeasurement(2, 3, 3, PGTimestamp.from(Instant.now().minus(15, ChronoUnit.SECONDS)), trafficCameraNode001);
		TrafficMeasurement trafficMeasurement003 = new TrafficMeasurement(3, 1, 3, PGTimestamp.from(Instant.now().minus(30, ChronoUnit.SECONDS)), trafficCameraNode001);


		tcns.addTrafficCameraNode(trafficCameraNode001);
		tcns.addTrafficCameraNode(trafficCameraNode002);
		tms.addTrafficMeasurement(trafficMeasurement001);
		tms.addTrafficMeasurement(trafficMeasurement002);
		tms.addTrafficMeasurement(trafficMeasurement003);

		MEASUREMENT_ID = trafficMeasurement003.getId();
	}

	@Test
	@DisplayName("Node Service")
	void NodeService() {
		TrafficCameraNode trafficCameraNode003 = tcns.getTrafficCameraNodeById(NODE001_ID);
		Assertions.assertEquals(UUID.fromString("9541673e-2ab2-4ff0-a555-cf27c73a2a05"), trafficCameraNode003.getId());
		//Get All from Database
		List<TrafficCameraNode> trafficCameraNodeList = tcns.getAllTrafficCameraNodes();

		//Get all Nodes from database
		Assertions.assertEquals(2, trafficCameraNodeList.size());

		Assertions.assertNotNull(trafficCameraNodeList.get(0).getLocation());
		Assertions.assertNull(trafficCameraNodeList.get(1).getLocation());

		//Get all Nodes where no Location is empty
		Assertions.assertEquals(1, tcns.getAllTrafficCameraNodesWhereLocatioNotNull().size());

		tcns.deleteTrafficCameraNodeById(UUID.fromString("711d5bde-4a8f-4370-877e-57225dd348e7"));
		Assertions.assertNull(tcns.getTrafficCameraNodeById(UUID.fromString("711d5bde-4a8f-4370-877e-57225dd348e7")));

	}

	@Test
	@DisplayName("Measurement Service")
	void MeasuremnetTest() {

		List<TrafficMeasurement> trafficMeasurementList = tms.getAllMeasrumentsFromTimespan(NODE001_ID, 0, 0, 0, 20);
		Assertions.assertEquals(2, trafficMeasurementList.size());
		Assertions.assertEquals(MEASUREMENT_ID, tms.getLatestMeasurementFromId(NODE001_ID).getId());
		Assertions.assertEquals(3, tms.getMeasurementsByTrafficCameraNode(tcns.getTrafficCameraNodeById(NODE001_ID)).size());
	}

	@Test
	@DisplayName("Api Key Service")
	@Disabled("Not yet implemented")
	void ApiKeyTest() {

	}

}
