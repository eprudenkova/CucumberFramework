#author: eprudenkova@yahoo.com
@featureos
Feature: Search Functionality

  @smoke
  Scenario: search by typing and hit enter
    Given navigate to google application
    When user enters any text
    And click hit enter
    Then verify the result is displayed

  @regression
  Scenario: search by typing and using google search button
    Given navigate to google application
    When user enters any text
    And click on google search button
    Then verify the result is displayed

