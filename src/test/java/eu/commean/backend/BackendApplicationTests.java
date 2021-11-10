package eu.commean.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.data.TrafficMeasurement;
import eu.commean.backend.repo.CrossroadRepository;
import eu.commean.backend.repo.TrafficCameraNodeRepository;
import eu.commean.backend.repo.TrafficMeasurementRepository;
import eu.commean.backend.service.CrossroadService;
import eu.commean.backend.service.TrafficCameraNodeService;
import eu.commean.backend.service.TrafficMeasurementServiceImpl;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class BackendApplicationTests {

	// Setup Spring Data
	@Autowired
	private TrafficCameraNodeRepository tcnRepo;
	@Autowired
	private CrossroadRepository crRepo;
	@Autowired
	private TrafficMeasurementRepository tmRepo;
	@Autowired
	private CrossroadService cs;
	@Autowired
	private TrafficCameraNodeService tcns;
	@Autowired
	private TrafficMeasurementServiceImpl tms;

	@ExtendWith(SpringExtension.class)
	@BeforeAll
	void setup() {
		// Add test data to mariaDB

		Crossroad crossroadTest = new Crossroad("TEST_01", new Point(0.0, 0.0));
		TrafficCameraNode cameraNodeTest01 = new TrafficCameraNode(new Point(0.0001, 0.0001), crossroadTest);
		TrafficMeasurement trafficMeasurement01 = new TrafficMeasurement(4, 4, 3, 3, 6, 2, Instant.now(),
				cameraNodeTest01);

		// write data to mariaDB
		crRepo.save(crossroadTest);
		tcnRepo.save(cameraNodeTest01);
		tmRepo.save(trafficMeasurement01);
//		tmRepo.createHypertable("traffic_measurement", "timestamp");

	}

	@Test
	@DisplayName("Communication with TimeScaleDB works")
	void TestTimeScaleDB() {
		Crossroad cr = crRepo.findById(1).orElse(null);
		TrafficCameraNode tcn = tcnRepo.findById(1).orElse(null);
		TrafficMeasurement tm = tmRepo.findById(1).orElse(null);

		// check if all data has been written and can be read from DB
		assertNotNull(cr);
		assertNotNull(tcn);
		assertNotNull(tm);

		assertEquals("TEST_01", tcn.getCrossroad().getCrossroadName());

		log.debug("CrossradID: {}, TrafficCammeraID: {}, tcn crossroad: {},tm time: {}, tm tcn id: {}", cr.getId(),
				tcn.getId(), tcn.getCrossroad().getCrossroadName(), tm.getTimestamp().toString(),
				tm.getTrafficCameraNode().getId());
	}

	@Test
	@DisplayName("Service for the Database works as expected")
	void TestServicesForDB() {
		Crossroad c = new Crossroad("TEST_02", new Point(0.0, 0.0));
		TrafficCameraNode tcn01 = new TrafficCameraNode(new Point(0.0001, 0.0001), c);
		TrafficMeasurement tm01 = new TrafficMeasurement(10, 2, 5, 3, 3, Instant.now(), tcn01);
		TrafficMeasurement tm02 = new TrafficMeasurement(10, 2, 5, 3, 3, Instant.now().minus(2, ChronoUnit.DAYS),
				tcn01);
		c = cs.addCrossroad(c);
		tcn01 = tcns.addTrafficCameraNode(tcn01);
		tm01 = tms.addTrafficMeasurement(tm01);
		tm02 = tms.addTrafficMeasurement(tm02);
		assertEquals(2, (int) c.getId());

		List<Crossroad> crossroads = cs.getAllCrossroads();
		assertEquals(2, crossroads.size());
		Hibernate.initialize(crossroads);
		log.debug("Crossroad id0: {}",crossroads.get(0).getTrafficCameraNode().get(0).getLocation());
		assertNotNull(crossroads.get(0).getTrafficCameraNode().get(0));

		assertEquals(1, tms.getAllMeasrumentsFromTimespan(tcn01, "1 d").size());
		assertEquals("TEST_02", cs.getCrossroadByCrossroadName("TEST_02").get(0).getCrossroadName());

		assertEquals(1, tcns.getTrafficCameraNodesByCrossroad(c).size());

		tms.deleteTrafficMeasurementById(3);
		tms.deleteTrafficMeasurementById(2);
		tcns.deleteTrafficCameraNodeById(2);
		cs.deleteCrossroadById(2);
		
		assertNull(tcns.getTrafficCameraNodeById(2));
		assertNull(cs.getCrossroadById(2));

	}

}
