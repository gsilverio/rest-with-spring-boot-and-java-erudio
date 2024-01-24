package com.restjava.RestJava.integrationtests.swagger;

import com.restjava.RestJava.configs.TestConfigs;
import com.restjava.RestJava.integrationtests.testcontainers.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTests extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {

		var content = given()
						.basePath("/swagger-ui/index.html")
							.port(TestConfigs.SERVER_PORT)
								.when()
									.get()
								.then()
									.statusCode(200)
								.extract()
									.body()
										.asString();
		Assertions.assertTrue(content.contains("Swagger UI"));
	}

}
