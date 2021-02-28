Feature: Configure PIM - Optional Fields


  @pim
  Scenario: Unchecking unnecessary checkboxes
    When enter valid credentials
    And click on login button
    And  verify dashboard is displayed
    And click on PIM
    And click on Configuration drop down
    And click on Optional Fields
    And click on Edit button
    And uncheck unnecessary checkboxes
      | Show SSN field in Personal Details |
      | Show SIN field in Personal Details |
    Then click on save button




