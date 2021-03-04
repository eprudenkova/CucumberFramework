#Author: eprudenkova@yahoo.com

@apiWorkflow
Feature: Syntax HRMS API Workflow
  Description: This feature tests Syntax HRMS API Workflow

  Background:
    Given a JWT is generated

  Scenario: Creating an Employee
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then the status code is 201
    And the employee is created contains key "Message" and value "Entry Created"
    And the employee ID "Employee[0].employee_id" is stored in the global variable to be used for other calls

    And a GET call is made to verify the data is updated for all employees "employee" with
      | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_job_title | emp_status |
      | Anna          | W               | Black        | 1990-01-01   | Female     | IT Analyst    | Employee   |


  Scenario: Retrieving the created Employee
    Given a request is prepared to retrieve the created Employee
    When a GET call is made to retrieve the created Employee
    Then the status code is 200
    And retrieved EmployeeID "employee[0].employee_id" matches the globally stored EmployeeID
    And the retrieved data matched the data used to create the Employee
    And the retrieved data at "employee" matches the data used to create the employee with employee ID "employee[0].employee_id"
      | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_job_title | emp_status |
      | Anna          | W               | Black        | 1990-01-01   | Female     | IT Analyst    | Employee   |


  Scenario: Update the created Employee
    Given a request is prepared to update the Employee
    When a PUT call is made to update the Employee
    Then the status code is 201
    And the updated Employee contains key "Message" and value "Entry updated"

  Scenario: Retrieving the updated Employee
    Given a request is prepared to retrieve the updated Employee
    When a GET call is made to retrieve the updated Employee
    Then the status code is 200
    And the retrieved Employee_Middle_Name "employee[0].emp_middle_name" matches the globally stored employee middle name

  Scenario: Partially updating the Employee
    Given a request is prepared to partially update the Employee
    When a PATCH call is made to partially update the Employee
    Then the status code is 201
    And the partially updated Employee contains key "Message" and value "Entry updated"
    And the retrieved Employee_First_Name "employee[0].emp_firstname" matches the globally stored employee middle name

  Scenario: Delete the created Employee
    Given a request is prepared to delete the Employee
    When a DELETE call is made to delete the Employee
    Then the status code is 201
    And the Employee is deleted contains key "message" and value "Entry deleted"
    And the "employee[0].employee_id" is the same as the one stored in global

  Scenario: Get all Employees
    Given a request is prepared to get all Employees
    When a GET call is made to get all Employees
    Then the status code is 200
    And it contains key1 "Total Employees" and key2 "Employees"

  Scenario: Retrieve all Employees status
    Given a request is prepared to get all Employees status
    When a GET call is made to retrieve all Employees status
    Then the status code is 200
    And it contains the value1 "Employee" and value2 "Worker"





