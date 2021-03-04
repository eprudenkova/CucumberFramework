package com.hrms.api;

import io.restassured.RestAssured;//baseURI
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;//Response interface, prettyPrint(), then(), jsonPath(), body()
import io.restassured.specification.RequestSpecification;//RequestSpecification interface, when()
import org.junit.Assert;//to verify with Assert class
import org.junit.Test;//to run test with @Test annotations @Test

import static org.hamcrest.Matchers.*;//to verify with equalTo(expected) method

import static io.restassured.RestAssured.*;//given()

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HardCodedExamples {

//    added 2 dependencies
//    rest-assured - to access functionality from restassured
//    json-path - ...


//    given() - setting up APT (prepare) request (pass the request headers, query, patch parameters, body, cookies)
//    when() - making request (what type of call you are making post/get/put)
//    then() -  verifying the response

//    .header() method to set header values


    static String baseURI = RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";//RestAssured - java library
    static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQyMTgwMTYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxNDI2MTIxNiwidXNlcklkIjoiMjM4NCJ9.VM1me0wyUNIDW7aEYiGu9AJreCaN1vp_SgA1A7Azov0";
    static String contentType = "Content-Type";
    static String appJSON = "application/json";
    static String auth = "Authorization";

    @Test
    public void sampleTest() {
//        RestAssured.baseURI="http://3.237.189.167/syntaxapi/api";//RestAssured - java library
//        String token ="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQxMjY4MzUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxNDE3MDAzNSwidXNlcklkIjoiMjM4NCJ9.0PQM_Tkwc7qVFAWGx8-eS4hCCFSdCk8GPh1Qiq7lHqw";
        //preparing request to get one employee
        RequestSpecification preparingGetOneEmployeeRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", "13009");

        //sending the request to the endpoint
        Response getOneEmployeeResponse = preparingGetOneEmployeeRequest.when().get("/getOneEmployee.php");

//        System.out.println(getOneEmployeeResponse.asString());
        getOneEmployeeResponse.prettyPrint();

//        Assert that status code is 200
        getOneEmployeeResponse.then().assertThat().statusCode(200);

    }

    @Test
    public void aPostCreateEmployee() {
        RequestSpecification createEmployeeRequest =
                given()
                        .header("Authorization", token)
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "  \"emp_firstname\": \"Mariii\",\n" +
                                "  \"emp_lastname\": \"Romaniuk\",\n" +
                                "  \"emp_middle_name\": null,\n" +
                                "  \"emp_gender\": \"F\",\n" +
                                "  \"emp_birthday\": \"2021-02-11\",\n" +
                                "  \"emp_status\": \"employee\",\n" +
                                "  \"emp_job_title\": \"IT Analyst\"\n" +
                                "}");
        /**
         * Making a POST call to create Employee
         */
        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");

//        printing response using prettyPrint() method
        createEmployeeResponse.prettyPrint();
        /**
         * Verifying that status code is 201
         */
        createEmployeeResponse
                .then()
                .assertThat()
                .statusCode(201);

//        jsonPath() to view response body which lets us get employee_id
        String empID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");

        /**
         * Verifying that message contains "Entry Created"
         * using equalTo() manually import static package import static org.hamcrest.Matchers.*
         */
        createEmployeeResponse
                .then()
                .assertThat()
                .body("Message", equalTo("Entry Created"));

        /**
         * Verifying that Employee ID is 15847A
         */
        createEmployeeResponse
                .then()
                .assertThat()
                .body("Employee[0].emp_firstname", equalTo("Mariii"));


//        firstNameCreated = createEmployeeResponse.body().jsonPath().getString("Employee[0].emp_firstname");
//        middleNameCreated = createEmployeeResponse.body().jsonPath().getString("Employee[0].emp_middle_name");
//        lastNameCreated = createEmployeeResponse.body().jsonPath().getString("Employee[0].emp_lastname");
//        birthdayCreated = createEmployeeResponse.body().jsonPath().getString("Employee[0].emp_birthday");
//        genderCreated = createEmployeeResponse.body().jsonPath().getString("Employee[0].emp_gender");
//        jobTitleCreated = createEmployeeResponse.body().jsonPath().getString("Employee[0].emp_job_title");
//        statusCreated = createEmployeeResponse.body().jsonPath().getString("Employee[0].emp_status");
    }

    @Test
    public void bGetCreatedEmployee() {
//        preparing request for /getOneEmployee.php
        RequestSpecification getCreatedEmployeeRequest = given()
                .header(contentType, appJSON)
                .header(auth, token)
                .queryParams("employee_id", "15905A");

//        making call to retrieve created employee
        Response getCreatedEmployeeResponse = getCreatedEmployeeRequest
                .when()
                .get("/getOneEmployee.php");

//        first way to assert
        getCreatedEmployeeResponse.then().assertThat().body("employee[0].employee_id", equalTo("15905A"));

//        second way to assert
        String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
        boolean verifyEmployeeID = empID.equalsIgnoreCase("15905A");
        Assert.assertTrue("Verifying Employee ID", verifyEmployeeID);
    }

    @Test
    public void cUpdateEmoloyee() {

//        Preparing request for updateEmployee.php
//        return RequestSpecification
        RequestSpecification updateEmployeeRequest = given()
                .header(contentType, appJSON)
                .header(auth, token)
                .body("{\n" +
                        "\"employee_id\": \"15940A\",\n" +
                        "\"emp_firstname\": \"Tom\",\n" +
                        "\"emp_middle_name\": \"W\",\n" +
                        "\"emp_lastname\": \"Ford\",\n" +
                        "\"emp_birthday\": \"1990-01-01\",\n" +
                        "\"emp_gender\": \"M\",\n" +
                        "\"emp_job_title\": \"CEO\",\n" +
                        "\"emp_status\": \"Employee\"\n" +
                        "}");

//        Implementing request, storing into ref var Response type
        Response updateEmployeeResponse = updateEmployeeRequest
                .when()
                .put("/updateEmployee");


//        Converting JSON object to String and printing response
        String response = updateEmployeeResponse.prettyPrint();

//        Validating the status code is 201
        updateEmployeeResponse.then().assertThat().statusCode(201);

//        1 way verifying firstname
        JsonPath jp = updateEmployeeResponse.jsonPath();
        String firstNameUpdated = jp.getString("employee[0].emp_firstname");
        assertThat(firstNameUpdated, equalTo("Tom"));

//        2 way verifying firstname
        updateEmployeeResponse.then().assertThat().body("employee[0].emp_firstname", equalTo("Tom"));

//        other way use assert from junit
//        ... dopisat
    }

    @Test
    public void dPartiallyUpdateEmployee() {

//        preparing /getOneEmployee.php request
        RequestSpecification partiallyUpdatedEmployeeRequest = given()
                .header(contentType, appJSON)
                .header(auth, token)
                .body("{\n" +
                        "            \"employee_id\": \"15940A\",        \n" +
                        "            \"emp_lastname\": \"White\"\n" +
                        "        }");

        Response partiallyUpdatedEmployeeResponse = partiallyUpdatedEmployeeRequest
                .when()
                .patch("/updatePartialEmplyeesDetails.php");

        String response = partiallyUpdatedEmployeeResponse.prettyPrint();

        partiallyUpdatedEmployeeResponse.then().assertThat().statusCode(201);

        JsonPath jp = partiallyUpdatedEmployeeResponse.jsonPath();
        Object message = jp.get("Message");

        assertThat(message, equalTo("Entry updated"));

        partiallyUpdatedEmployeeResponse.then().body("Message", containsString("Entry updated"));
    }

    @Test
    public void eDeleteEmployee() {

        RequestSpecification deleteEmployeeRequest = given()
                .header(contentType, appJSON)
                .header(auth, token)
                .queryParam("employee_id", "16079A");

        Response deleteEmployeeResponse = deleteEmployeeRequest
                .when()
                .delete("/deleteEmployee.php");

        String response = deleteEmployeeResponse.prettyPrint();

        deleteEmployeeResponse.then().assertThat().statusCode(201);

        deleteEmployeeResponse.then().assertThat().body("message", equalTo("Entry deleted"));

        deleteEmployeeResponse.then().body("message", containsString("Entry deleted"));

        JsonPath jp = deleteEmployeeResponse.jsonPath();
        Object message = jp.get("message");

        assertThat(message, equalTo("Entry deleted"));

        Assert.assertEquals("Verifying message", "Entry deleted", jp.getString("message"));
    }

    @Test
    public void fGetJobTitles() {
        given()
                .header(contentType, appJSON)
                .header(auth, token)
                .when()
                .get("/jobTitle.php")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
