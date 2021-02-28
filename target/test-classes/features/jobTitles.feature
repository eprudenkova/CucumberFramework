#@db
Feature: Admin Menu Job Titles Functionality

  Background:
    When enter valid credentials
    And click on login button
    And navigate to Job Titles Page

  Scenario: Job titles data from UI and DB is matched
    And collect job titles from UI
    And collect job titles from hrms DB
    Then verify job titles data from UI and DB is matched

