package eu.commean.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import eu.commean.backend.dto.node.CreateNodeDto;
import eu.commean.backend.dto.node.NodeDto;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ContextConfiguration(classes = eu.commean.backend.BackendApplication.class)
@Log4j2

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NodeEndpointTests {


	private final UUID nodeUUID = UUID.fromString("4e8f0fd7-d936-42f9-9fd1-7b537f3ba690");
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MockMvc mockMvc;

	@Test
	@Order(1)
	@DisplayName(value = "Create Node")
	public void createNode() throws Exception {
		CreateNodeDto createNodeDto = new CreateNodeDto(nodeUUID, "NYI");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/nodes").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(createNodeDto)))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.api-key", Matchers.matchesRegex("^(?:[A-Za-z\\d+/]{4})*(?:[A-Za-z\\d+/]{3}=|[A-Za-z\\d+/]{2}==)?$"))).andReturn();
		System.setProperty("commean-junit-apikey", JsonPath.parse(result.getResponse().getContentAsString()).read("$.api-key").toString());
		log.debug(System.getProperty("commean-junit-apikey"));
	}

	@Test
	@Order(2)
	@DisplayName(value = "Update Node")
	public void updateNode() throws Exception {
		double[] position = {1.0, 1.0};
		NodeDto nodeDto = new NodeDto(nodeUUID, "TEST001", position);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/nodes").header("api-key", System.getProperty("commean-junit-apikey"))
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(nodeDto)))
				.andExpect(status().isOk());


	}

	@Test
	@Order(3)
	@DisplayName(value = "Get Node")
	public void getNode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/nodes").contentType(MediaType.APPLICATION_JSON).queryParam("id", nodeUUID.toString()))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(nodeUUID.toString()));
	}

}
