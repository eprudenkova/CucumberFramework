@featureTag @login #feature level tag
Feature: Login Functionality

  Background:
  @validCreds #scenario level tag
  Scenario: Login with valid credentials
#    Given navigate to HRMS login page //because of Hooks Class with @Before method
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
#    And quit the browser //because of Hooks Class with @After method

  @invalidCreds #adding multiple scenario level tags
  Scenario: Login with invalid credentials
    When  enter invalid credentials
    And click on login button
    Then verify error message

  @hw1
  Scenario: Login with empty username
    When leave blank username
    When enter valid password
    And click on login button
    Then verify error message for empty username

  @hw2
  Scenario: Login with empty password
    When enter valid username
    When leave blank password
    And click on login button
    Then verify error message for empty password
