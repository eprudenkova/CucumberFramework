package com.hrms.api.Testing;

import com.hrms.utils.ApiConstants;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class generateTokenSteps {

    String baseURI = RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";//RestAssured - java library
    static String token;

    @Given("a JWT is generated")
    public void a_JWT_is_generated() {

        RequestSpecification generateTokenRequest =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "  \"email\": \"email@gmail\",\n" +
                                "  \"password\": \"password\"\n" +
                                "}");

        Response generateTokenResponse = generateTokenRequest.when().post(ApiConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();

        token="Bearer "+generateTokenResponse.jsonPath().getString("token");

    }
}
