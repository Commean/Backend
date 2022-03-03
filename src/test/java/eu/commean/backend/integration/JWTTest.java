package eu.commean.backend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import eu.commean.backend.dto.security.LoginDto;
import eu.commean.backend.dto.security.SignUpDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Order(0)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JWTTest {

	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MockMvc mockMvc;

	@Test
	@Order(0)
	@DisplayName("Sign up")
	public void signIn() throws Exception {

		SignUpDto signUpDto = new SignUpDto("4e8f0fd7-d936-42f9-9fd1-7b537f3ba690", "password");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(signUpDto))).andExpect(status().isCreated());

	}

	@Test
	@Order(1)
	@DisplayName("Sign in")
	public void signUp() throws Exception {

		LoginDto loginDto = new LoginDto("4e8f0fd7-d936-42f9-9fd1-7b537f3ba690", "password");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/signin").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(loginDto))).andExpect(status().isOk()).andReturn();
		System.setProperty("commean-jwt-token", JsonPath.parse(result.getResponse().getContentAsString()).read("$.token").toString());

	}
}