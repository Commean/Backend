package eu.commean.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.commean.backend.dto.node.CreateNodeDto;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ContextConfiguration(classes = eu.commean.backend.BackendApplication.class)
@Log4j2

public class NodeEndpointTests {


	final UUID nodeUUID = UUID.fromString("4e8f0fd7-d936-42f9-9fd1-7b537f3ba690");
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName(value = "Create Node")
	public void createNode() throws Exception {
		CreateNodeDto createNodeDto = new CreateNodeDto(nodeUUID, "NYI");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/nodes").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(createNodeDto)))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.apikey", Matchers.matchesRegex("^(?:[A-Za-z\\d+/]{4})*(?:[A-Za-z\\d+/]{3}=|[A-Za-z\\d+/]{2}==)?$")));
		//log.
	}

	public void getNode() throws Exception {
		Map<String, String> body = new HashMap<>();
		body.put("id", "4e8f0fd7-d936-42f9-9fd1-7b537f3ba690");
		mockMvc.perform(MockMvcRequestBuilders.get("api/v1/nodes").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.api").value(""));
	}

}
