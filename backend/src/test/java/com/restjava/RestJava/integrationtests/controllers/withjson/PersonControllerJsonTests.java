package com.restjava.RestJava.integrationtests.controllers.withjson;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.restjava.RestJava.configs.TestConfigs;
import com.restjava.RestJava.integrationtests.DTO.PersonDTO;
import com.restjava.RestJava.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTests extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static PersonDTO person;
	@BeforeAll
	public static void setUp(){
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		person = new PersonDTO();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException,JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN,TestConfigs.ORIGIN_ERUDIO)
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
						.contentType(TestConfigs.CONTENT_TYPE_JSON)
							.body(person)
							.when()
							.post()
							.then()
							.statusCode(200)
							.extract()
								.body().asString();




		PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
		person = createdPerson;


		Assertions.assertNotNull(createdPerson);

		Assertions.assertNotNull(createdPerson.getId());
		Assertions.assertNotNull(createdPerson.getFirstName());
		Assertions.assertNotNull(createdPerson.getLastName());
		Assertions.assertNotNull(createdPerson.getAddress());
		Assertions.assertNotNull(createdPerson.getGender());

		Assertions.assertTrue(createdPerson.getId() > 0);


		Assertions.assertEquals("Richard",createdPerson.getFirstName());
		Assertions.assertEquals("Stallman",createdPerson.getLastName());
		Assertions.assertEquals("New York City, New York, USA",createdPerson.getAddress());
		Assertions.assertEquals("Male",createdPerson.getGender());
	}

	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() throws JsonMappingException,JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN,TestConfigs.ORIGIN_SEMERU)
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		Assertions.assertNotNull(content);
		Assertions.assertEquals("Invalid CORS request", content);
	}

	private void mockPerson() {
		person.setFirstName("Richard");
		person.setLastName("Stallman");
		person.setAddress("New York City, New York, USA");
		person.setGender("Male");
	}
	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException,JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN,TestConfigs.ORIGIN_ERUDIO)
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.pathParam("id",person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		PersonDTO persistedPerson = objectMapper.readValue(content, PersonDTO.class);
		person = persistedPerson;

		Assertions.assertNotNull(persistedPerson);

		Assertions.assertNotNull(persistedPerson.getId());
		Assertions.assertNotNull(persistedPerson.getFirstName());
		Assertions.assertNotNull(persistedPerson.getLastName());
		Assertions.assertNotNull(persistedPerson.getAddress());
		Assertions.assertNotNull(persistedPerson.getGender());

		Assertions.assertTrue(persistedPerson.getId() > 0);


		Assertions.assertEquals("Richard",persistedPerson.getFirstName());
		Assertions.assertEquals("Stallman",persistedPerson.getLastName());
		Assertions.assertEquals("New York City, New York, USA",persistedPerson.getAddress());
		Assertions.assertEquals("Male",persistedPerson.getGender());
	}

	@Test
	@Order(4)
	public void testFindByIdWithWrongOrigin() throws JsonMappingException,JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_ORIGIN,TestConfigs.ORIGIN_SEMERU)
				.setBasePath("/api/person/v1")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.pathParam("id",person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		PersonDTO persistedPerson = objectMapper.readValue(content, PersonDTO.class);
		person = persistedPerson;

		Assertions.assertNotNull(persistedPerson);

		Assertions.assertNotNull(persistedPerson.getId());
		Assertions.assertNotNull(persistedPerson.getFirstName());
		Assertions.assertNotNull(persistedPerson.getLastName());
		Assertions.assertNotNull(persistedPerson.getAddress());
		Assertions.assertNotNull(persistedPerson.getGender());

		Assertions.assertTrue(persistedPerson.getId() > 0);


		Assertions.assertEquals("Richard",persistedPerson.getFirstName());
		Assertions.assertEquals("Stallman",persistedPerson.getLastName());
		Assertions.assertEquals("New York City, New York, USA",persistedPerson.getAddress());
		Assertions.assertEquals("Male",persistedPerson.getGender());
	}


}
