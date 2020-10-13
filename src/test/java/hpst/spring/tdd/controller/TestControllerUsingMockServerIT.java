/**
 * 
 */
package hpst.spring.tdd.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import hpst.spring.tdd.entity.User;

/**
 * @author harpal
 *
 */

@ExtendWith(MockServerExtension.class)
@MockServerSettings(ports = {3000})
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class TestControllerUsingMockServerIT {

	MockServerClient mockserver;
	
	@BeforeAll
	void setUpMockServer(MockServerClient mockserver) {
		this.mockserver=mockserver;
	}

	@Test
	
	void testMockServerClient(MockServerClient mockserver){
	assertNotNull(mockserver);	
	}
	

	@Test
	void testSetUsers() throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		
		User user= new User();
		user.setId(1l);
		user.setName("Harpal");
		
		String expectedResponse=objectMapper.writeValueAsString(user);
		System.out.println("expected---"+expectedResponse);
		assertNotNull(expectedResponse);
		mockserver.when(HttpRequest.
				request().withMethod("PUT").withPath("/add-user")
				.withBody(JsonBody.json(expectedResponse))).
			    respond(HttpResponse.response().withStatusCode(200));
			   
	
	
	}
	
	
	@Test
	void testGetUsers() throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		
		User user= new User();
		user.setId(1l);
		user.setName("Harpal");
		
		String expectedResponse=objectMapper.writeValueAsString(user);
		System.out.println("expected---"+expectedResponse);
		assertNotNull(expectedResponse);
		mockserver.when(HttpRequest.
				request().withMethod("GET").withPath("/get-user")).
			    respond(HttpResponse.response().withStatusCode(200)
					   .withHeader(new Header("Content-Type","application/json; charset=utf-8"))
					   .withBody(JsonBody.json(expectedResponse)));
			   
	
	
	}
	
	
}
