package eu.commean.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.postgresql.util.PGTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

		GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
		factory.createPoint(new Coordinate(14.2980086, 46.636018)); // LONG,LAT
		Crossroad crossroadTest = new Crossroad("TEST_01", "POINT(14.305524230003357 46.62442463652353)");
		TrafficCameraNode cameraNodeTest01 = new TrafficCameraNode("POINT(0,0)", crossroadTest);
		TrafficMeasurement trafficMeasurement01 = new TrafficMeasurement(4, 4, 3, 3, 6, PGTimestamp.from(Instant.now()),
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
		GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
		factory.createPoint(new Coordinate(14.2980086, 46.636018)); // LONG,LAT

		Crossroad c = new Crossroad("TEST_02", "POINT(14.305524230003357 46.62442463652353)");
		TrafficCameraNode tcn01 = new TrafficCameraNode("POINT(0 0)", c);
		TrafficMeasurement tm01 = new TrafficMeasurement(10, 2, 5, 3, 3, PGTimestamp.from(Instant.now()), tcn01);
		TrafficMeasurement tm02 = new TrafficMeasurement(10, 2, 5, 3, 3,
				PGTimestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)), tcn01);
		c = cs.addCrossroad(c);
		tcn01 = tcns.addTrafficCameraNode(tcn01);
		tm01 = tms.addTrafficMeasurement(tm01);
		tm02 = tms.addTrafficMeasurement(tm02);
		// assertEquals(2, (int) c.getId());

		List<Crossroad> crossroads = cs.getAllCrossroads();
		assertEquals(2, crossroads.size());
		Hibernate.initialize(crossroads);
		log.debug("Crossroad id0: {}", crossroads.get(0).getTrafficCameraNode().get(0).getLocation());
		assertNotNull(crossroads.get(0).getTrafficCameraNode().get(0));

		assertEquals(1, tms.getAllMeasrumentsFromTimespan(tcn01.getId(), 1).size());
		assertEquals("TEST_02", cs.getCrossroadByCrossroadName("TEST_02").get(0).getCrossroadName());

		assertEquals(1, tcns.getTrafficCameraNodesByCrossroad(c).size());

		tms.deleteTrafficMeasurementById(3);
		tms.deleteTrafficMeasurementById(2);
		tcns.deleteTrafficCameraNodeById(2);
		cs.deleteCrossroadById(2);

		assertNull(tcns.getTrafficCameraNodeById(2));
		assertNull(cs.getCrossroadById(2));

	}

	@Test
	@DisplayName("Interval Statements")
	void TestIntervalQuerries() {
		TrafficMeasurement tm = tms.getAllMeasrumentsFromTimespan(1, 1).get(0);
		log.info("Mesurement: {}|{}|{}|{}",tm.getId(),tm.getCarIn(),tm.getTruckIn(),tm.getAverageTimeInPicture());
	}

}
