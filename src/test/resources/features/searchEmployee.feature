#author: eprudenkova@yahoo.com
@db
Feature: Search Employee Functionality

  Background:
    Given enter valid credentials
    And click on login button
    And navigate to Employee List page

  Scenario: Search All Job Titles
    And collect job titles from UI Employee List Job Title DropDown
    And collect job titles from hrms DB
    Then verify job titles data from UI Employee List Job Title DropDown and DB is matched


  @searchByID
  Scenario Outline: Search multiple employees by ID
    And enter employee ID "<ID>"
    And click on Search button
    Then verify employee ID "<ID>" is displayed
    Examples:
      | ID    |
      | 12320 |
      | 12321 |
      | 12322 |
      | 12323 |
      | 12324 |

  @searchByFullName
  Scenario Outline: Search multiple employees by name
    And enter employee full name "<Employee Name>"
    And click on Search button
    Then verify employee full name "<Employee Name>" is displayed
    Examples:
      | Employee Name |
      | Anna 1 Black  |
      | Anna 2 Black  |
      | Anna 3 Black  |
      | Anna 4 Black  |
      | Anna 5 Black  |