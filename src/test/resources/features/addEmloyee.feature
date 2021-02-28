@addEmployee
Feature: Add Employee Functionality

  Background:
    When enter valid credentials
    And click on login button
    And navigate to Add Employee page

  @regression @hw
  Scenario: Add an employee without login details
    And enter First Name "Anna" and Last Name "Black"
    And see what ID number was created
    And click on save button
    Then verify ID is displayed on Personal Details page

  @smoke @hw
  Scenario: Add an employee with login details
    And enter First Name "John" and Last Name "Smith"
    And see what ID number was created
    And click on Create Login Details checkbox
    And enter User Name "johnsmith" and Password "Hum@nhrm123" and Confirm Password "Hum@nhrm123"
    And click on save button
    Then verify ID is displayed on Personal Details page

  @parameter
  Scenario: Add employee without login details but with middle name
    And enter First Name "Marta" and Middle Name "Mary" and Last Name "Ostash"
    And click on save button
    Then verify that "Marta Mary Ostash" is added successfully

  @multiple
  Scenario Outline: Adding multiple employees without login details
    When enter "<FirstName>","<MiddleName>" and "<LastName>"
    And click on save button
    Then verify "<FirstName>", "<MiddleName>" and "<LastName>" is added successfully
    Examples:
      | FirstName | MiddleName | LastName |
      | Mark      | J          | Smith    |
      | John      | K          | Wick     |
      | Anna      | 1          | Black    |
#      | Anna      | 2          | Black    |
#      | Anna      | 3          | Black    |
#      | Anna      | 4          | Black    |
#      | Anna      | 5          | Black    |

#    need to generate the snippets
  @dtWithHeader
  Scenario: Adding multiple employees at one execution
    When add multiple employees and verify they are added successfully
      | FirstName | MiddleName | LastName | EmployeeID |
      | Jack      | J          | Toronto  | 1112223334 |
      | David     | K          | Wick     | 4445556667 |

  @excelTask
  Scenario: Adding multiple employees from excel
    When add multiple employees from excel "Sheet1" sheet and verify they are added

#  @db
  @regression
  Scenario: Add employee and database validation
    And enter First Name "Marta" and Middle Name "Mary" and Last Name "Ostash"
    And capture employeeID
    And click on save button
    And collect employee data from hrms database
    Then verify data from DB and UI is matched



