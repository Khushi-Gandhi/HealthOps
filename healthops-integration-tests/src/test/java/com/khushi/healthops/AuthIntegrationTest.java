package com.khushi.healthops;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthIntegrationTest {

	@BeforeAll
	static void setUp() {
		RestAssured.baseURI = "http://localhost:4004";
	}
	
	@Test
	public void shouldReturnOkWithValidToken() {
		// 1. Arrange
		// 2. Act
		// 3. Assert
		
		String loginPayload = """
				{
					"email": "testuser@test.com",
					"password": "password123"
				}
				""";
		
		Response response = RestAssured.given()
				.contentType("application/json")
				.body(loginPayload)
				.when()
				.post("/auth/login")
				.then()
				.statusCode(200)
				.body("token", notNullValue())
				.extract()
				.response();
		
		System.out.println("Generated Token: " + response.jsonPath().getString("token"));
	}
	
	@Test
	public void shouldReturnUnauthorizedOnInvaliLogin() {
		// 1. Arrange
		// 2. Act
		// 3. Assert
		
		String loginPayload = """
				{
					"email": "invaliduser@test.com",
					"password": "wrongpassword"
				}
				""";
		
		RestAssured.given()
				.contentType("application/json")
				.body(loginPayload)
				.when()
				.post("/auth/login")
				.then()
				.statusCode(401);
		
	}
}
