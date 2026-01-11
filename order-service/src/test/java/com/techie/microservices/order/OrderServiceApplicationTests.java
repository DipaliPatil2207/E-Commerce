package com.techie.microservices.order;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import io.restassured.RestAssured;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@Container
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.33")
			.withDatabaseName("order_service")
			.withUsername("root")
			.withPassword("MySecret123!");


	@LocalServerPort
	Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldOrderPlaced() {
		String requestBody = """
				{
				    "skuCode": "iphone16",
				    "price": 4512,
				    "quantity": 1
				}""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("api/order")
				.then()
				.statusCode(201)
				.body("quantity", Matchers.equalTo(1))
				.body("price", Matchers.equalTo(4512))
				.body("skuCode", Matchers.equalTo("iphone16"))
				.body("id", Matchers.notNullValue());
	}

}
