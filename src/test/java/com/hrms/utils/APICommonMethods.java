package com.hrms.utils;

import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class APICommonMethods {

    /**
     * This method will read the json file that we have created and return it as String
     */
    static String jsonFile;

    public static String readJSON(String fileName) {
        try {
            jsonFile = new String(Files.readAllBytes(Paths.get(fileName)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFile;
    }

    /**
     * @param token
     * @param employeeID
     * @return
     */
    public static RequestSpecification getOneEmployeeRequest(String token, String employeeID) {

        RequestSpecification request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, token)
                .queryParam("employee_id", employeeID);

        return request;
    }
}
