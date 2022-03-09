package eu.commean.backend.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import eu.commean.backend.dto.measurement.CreateTrafficMeasurementDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Sql(scripts = "/db/test_init.sql")

@ContextConfiguration(classes = eu.commean.backend.BackendApplication.class)
@Log4j2
@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeasurementEndpointTest {

	private final UUID NODE_UUID = UUID.fromString("4e8f0fd7-d936-42f9-9fd1-7b537f3ba690");
	private final String TOKEN = System.getProperty("commean-jwt-token");
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	MockMvc mockMvc;

	/*@BeforeAll
	void setup(@Autowired DataSource dataSource) {
		try (Connection conn = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(conn, new ClassPathResource("/db/test_init.sql"));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	@Test
	@Order(1)
	public void createMeasurement() throws Exception {
		CreateTrafficMeasurementDto createTrafficMeasurementDto = new CreateTrafficMeasurementDto(Instant.now().getEpochSecond(), 1, 1, 1);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/measurements/oof", NODE_UUID.toString())
						.header("authorization", "Bearer %s".formatted(TOKEN))
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(createTrafficMeasurementDto)))
				.andExpect(status().isCreated());

	}

	@Test
	@Order(2)
	public void getMeasurement() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/measurements/{node}/now", NODE_UUID.toString())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
