package com.hrms.api.Testing;

import com.hrms.utils.ApiConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class apiTestingFinalSteps {

    RequestSpecification request;
    Response response;
    static String employeeID;
    static String updated_employee_middle_name;
    static String partially_updated_employee_first_name;


//    -----------------------------Scenario: Creating an Employee
    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        //preparing request to create an employee
        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token);

        request = request.body("{\n" +
                "  \"emp_firstname\": \"Anna\",\n" +
                "  \"emp_lastname\": \"Black\",\n" +
                "  \"emp_middle_name\": \"W\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1990-01-01\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"IT Analyst\"\n" +
                "}");
    }

    @When("a POST call is made to create an employee")
    public void a_POST_call_is_made_to_create_an_employee() {
        //sending the request to create an employee
        response = request.when().post(ApiConstants.CREATE_EMPLOYEE_URI);

    }

    @Then("the status code is {int}")
    public void the_status_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee is created contains key {string} and value {string}")
    public void the_employee_is_created_contains_key_and_value(String key, String value) {
        //assert that the message contains entry created
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee ID {string} is stored in the global variable to be used for other calls")
    public void the_employee_ID_is_stored_in_the_global_variable_to_be_used_for_other_calls(String value) {
        //extract the employee ID from the json response
        employeeID = response.jsonPath().getString("Employee[0].employee_id");
    }

    @Then("a GET call is made to verify the data is updated for all employees {string} with")
    public void a_GET_call_is_made_to_verify_the_data_is_updated_for_all_employees_with(String value, DataTable expectedEmployee) {
        response = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token)//token accessible for us without creating an object
                .queryParam("employee_id", employeeID)
                .when().get(ApiConstants.GET_ONE_EMPLOYEE_URI);

        List<Map<String, String>> actualEmployeeList = response.jsonPath().getList(value);
        List<Map<String, String>> expectedEmployeeList = expectedEmployee.asMaps();

        int index = 0;
        for (Map<String, String> expectedEmployeeMap : expectedEmployeeList) {
            Set<String> keys = expectedEmployeeMap.keySet();
            for (String key : keys) {
                Assert.assertEquals("Value does not match", expectedEmployeeMap.get(key), actualEmployeeList.get(index).get(key));
//                System.out.println("ACTUALLLLLLLL "+expectedEmployeeMap.get(key));
//                System.out.println("EXPECTEDDDDDD "+actualEmployeeList.get(index).get(key));
            }
            index++;
        }
    }

//    --------------------------Scenario: Retrieving the created Employee
    @Given("a request is prepared to retrieve the created Employee")
    public void a_request_is_prepared_to_retrieve_the_created_Employee() {
        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token)
                .queryParam("employee_id", employeeID);//.queryParam() is used to read the values ​​from QueryParameters of a URI call.
    }

    @When("a GET call is made to retrieve the created Employee")
    public void a_GET_call_is_made_to_retrieve_the_created_Employee() {
        //sending the request to retrieve the created employee
        response = request.when().get(ApiConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("retrieved EmployeeID {string} matches the globally stored EmployeeID")
    public void retrieved_EmployeeID_matches_the_globally_stored_EmployeeID(String value) {
        //assert that employee ID is same as the one stored globally
        response.then().assertThat().body(value, equalTo(employeeID));

    }

    @Then("the retrieved data matched the data used to create the Employee")
    public void the_retrieved_data_matched_the_data_used_to_create_the_Employee() {

        JsonPath jpath = response.jsonPath();
        String emp_first_name = jpath.getString("employee[0].emp_firstname");
        String emp_last_name = jpath.getString("employee[0].emp_lastname");
        String emp_middle_name = jpath.getString("employee[0].emp_middle_name");
        String emp_birthday = jpath.getString("employee[0].emp_birthday");
        String emp_gender = jpath.getString("employee[0].emp_gender");
        String emp_job_title = jpath.getString("employee[0].emp_job_title");
        String emp_status = jpath.getString("employee[0].emp_status");

        assertThat(emp_first_name, equalTo("Anna"));
        assertThat(emp_last_name, equalTo("Black"));
        assertThat(emp_middle_name, equalTo("W"));
        assertThat(emp_birthday, equalTo("1990-01-01"));
        assertThat(emp_gender, equalTo("Female"));
        assertThat(emp_job_title, equalTo("IT Analyst"));
        assertThat(emp_status, equalTo("Employee"));
    }


//    -------------------------------Scenario: Update the created Employee
    @Given("a request is prepared to update the Employee")
    public void a_request_is_prepared_to_update_the_Employee() {

        updated_employee_middle_name = "Updated middle name";

        JSONObject payload = new JSONObject();
        payload.put("employee_id", employeeID);
        payload.put("emp_firstname", "Anna");
        payload.put("emp_lastname", "Black");
        payload.put("emp_middle_name", updated_employee_middle_name);
        payload.put("emp_gender", "F");
        payload.put("emp_birthday", "1990-01-01");
        payload.put("emp_status", "Employee");
        payload.put("emp_job_title", "IT Analyst");

        String final_payload = payload.toString();

        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token)
                .body(final_payload);
    }

    @When("a PUT call is made to update the Employee")
    public void a_PUT_call_is_made_to_update_the_Employee() {
        //sending the put request to update the employee
        response = request.when().put(ApiConstants.UPDATE_EMPLOYEE_URI);
    }

    @Then("the updated Employee contains key {string} and value {string}")
    public void the_updated_Employee_contains_key_and_value(String key, String value) {
        //assert the response contains message "Entry updated"
        response.then().assertThat().body(key, equalTo(value));
    }


//    ------------------------Scenario: Retrieving the updated Employee
    @Given("a request is prepared to retrieve the updated Employee")
    public void a_request_is_prepared_to_retrieve_the_updated_Employee() {
        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token)
                .queryParam("employee_id", employeeID);
    }

    @When("a GET call is made to retrieve the updated Employee")
    public void a_GET_call_is_made_to_retrieve_the_updated_Employee() {
        //make a GET call to retrieve the updated Employee
        response = request.when().get(ApiConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the retrieved Employee_Middle_Name {string} matches the globally stored employee middle name")
    public void the_retrieved_Employee_Middle_Name_matches_the_globally_stored_employee_middle_name(String updatedMiddleName) {
        response.then().assertThat().body(updatedMiddleName, equalTo(updated_employee_middle_name));
//        Assert.assertEquals("Verification employee middle name", updatedMiddleName, updated_employee_middle_name);
//
//        String actualMiddleName = response.jsonPath().getString(updatedMiddleName);
//
//        assertThat(actualMiddleName, equalTo(updated_employee_middle_name));
//        Assert.assertEquals(actualMiddleName, updated_employee_middle_name);
    }

//    ----------------------------Scenario: Partially updating the Employee
    @Given("a request is prepared to partially update the Employee")
    public void a_request_is_prepared_to_partially_update_the_Employee() {

        partially_updated_employee_first_name = "Partially updated first name";

        JSONObject payload = new JSONObject();
        payload.put("employee_id", employeeID);
        payload.put("emp_firstname", partially_updated_employee_first_name);

        String final_payload = payload.toString();

        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token)
                .body(final_payload);
    }

    @When("a PATCH call is made to partially update the Employee")
    public void a_PATCH_call_is_made_to_partially_update_the_Employee() {
        response = request.when().patch(ApiConstants.PARTIALLY_UPDATE_EMPLOYEE_URI);
    }

    @Then("the partially updated Employee contains key {string} and value {string}")
    public void the_partially_updated_Employee_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the retrieved Employee_First_Name {string} matches the globally stored employee middle name")
    public void the_retrieved_Employee_First_Name_matches_the_globally_stored_employee_middle_name(String partiallyUpdatedFirstname) {
        response.then().assertThat().body(partiallyUpdatedFirstname, equalTo(partially_updated_employee_first_name));
    }

//    -----------------------Scenario: Delete the created Employee
    @Given("a request is prepared to delete the Employee")
    public void a_request_is_prepared_to_delete_the_Employee() {
        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token)
                .queryParam("employee_id", employeeID);
    }

    @When("a DELETE call is made to delete the Employee")
    public void a_DELETE_call_is_made_to_delete_the_Employee() {
        response = request.when().delete(ApiConstants.DELETE_EMPLOYEE);
    }

    @Then("the Employee is deleted contains key {string} and value {string}")
    public void the_Employee_is_deleted_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the {string} is the same as the one stored in global")
    public void the_is_the_same_as_the_one_stored_in_global(String employee_id) {
        response.then().assertThat().body(employee_id, equalTo(employeeID));
    }

//    ----------------------------Scenario: Get all Employees
    @Given("a request is prepared to get all Employees")
    public void a_request_is_prepared_to_get_all_Employees() {
        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token);
    }

    @When("a GET call is made to get all Employees")
    public void a_GET_call_is_made_to_get_all_Employees() {
        response = request.when().get(ApiConstants.GET_ALL_EMPLOYEE_URI);
    }

    @Then("it contains key1 {string} and key2 {string}")
    public void it_contains_key1_and_key2(String key1, String key2) {

        response.then().assertThat().body(containsString(key1)).body(containsString(key2));

//        String payload = response.asString();
//        JsonPath jp = new JsonPath(payload);
//        int count = jp.getInt("Employees.size()");
//        //print all employee ids
//        for (int i = 0; i < count; i++) {
//            String allEmployeesIDs = jp.getString("Employees[" + i + "].employee_id");
//            System.out.println(allEmployeesIDs);
//        }
    }

//    ---------------------------Scenario: Retrieve all Employees status
    @Given("a request is prepared to get all Employees status")
    public void a_request_is_prepared_to_get_all_Employees_status() {
        request = given()
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APP_JSON)
                .header(ApiConstants.AUTH, generateTokenSteps.token);
    }

    @When("a GET call is made to retrieve all Employees status")
    public void a_GET_call_is_made_to_retrieve_all_Employees_status() {
        response = request.when().get(ApiConstants.GET_EMPLOYEE_STATUS_URI);
    }

    @Then("it contains the value1 {string} and value2 {string}")
    public void it_contains_the_value1_and_value2(String value1, String value2) {
        response.then().assertThat().body(containsString(value1)).body(containsString(value2));
    }
}


