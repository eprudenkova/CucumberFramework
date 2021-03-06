$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/login.feature");
formatter.feature({
  "name": "Login Functionality",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@featureTag"
    },
    {
      "name": "@login"
    },
    {
      "name": "#feature"
    },
    {
      "name": "level"
    },
    {
      "name": "tag"
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.scenario({
  "name": "Login with empty password",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@featureTag"
    },
    {
      "name": "@login"
    },
    {
      "name": "#feature"
    },
    {
      "name": "level"
    },
    {
      "name": "tag"
    },
    {
      "name": "@hw2"
    }
  ]
});
formatter.step({
  "name": "enter valid username",
  "keyword": "When "
});
formatter.match({
  "location": "com.hrms.stepDefinitions.LoginStepDefinition.enter_valid_username()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "leave blank password",
  "keyword": "When "
});
formatter.match({
  "location": "com.hrms.stepDefinitions.LoginStepDefinition.leave_blank_password()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "click on login button",
  "keyword": "And "
});
formatter.match({
  "location": "com.hrms.stepDefinitions.LoginStepDefinition.click_on_login_button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify error message for empty password",
  "keyword": "Then "
});
formatter.match({
  "location": "com.hrms.stepDefinitions.LoginStepDefinition.verify_error_message_for_empty_password()"
});
formatter.result({
  "status": "passed"
});
formatter.embedding("image/png", "embedded0.png", "Login with empty password");
formatter.after({
  "status": "passed"
});
});