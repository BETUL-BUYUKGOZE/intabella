$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/Login.feature");
formatter.feature({
  "name": "Login functionality",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User on the Login Page",
  "keyword": "Given "
});
formatter.match({
  "location": "com.step_definitions.LoginStepdefs.user_on_the_Login_Page()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "\"Forgot Password\" menu",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@INTA-953"
    }
  ]
});
formatter.step({
  "name": "user click on \"Forgot your password?\" link",
  "keyword": "When "
});
formatter.match({
  "location": "com.step_definitions.LoginStepdefs.userClickOnLink(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user lands on the \"Forgot Password\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "com.step_definitions.LoginStepdefs.userLandsOnThePage(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user enters username and clicks on \"REQUEST\" button.",
  "keyword": "And "
});
formatter.match({
  "location": "com.step_definitions.LoginStepdefs.user_enters_username_and_clicks_on_button(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user should be able to change their password",
  "keyword": "And "
});
formatter.match({
  "location": "com.step_definitions.LoginStepdefs.user_should_be_able_to_change_their_password()"
});
formatter.result({
  "error_message": "java.lang.AssertionError\r\n\tat org.junit.Assert.fail(Assert.java:87)\r\n\tat org.junit.Assert.assertTrue(Assert.java:42)\r\n\tat org.junit.Assert.assertFalse(Assert.java:65)\r\n\tat org.junit.Assert.assertFalse(Assert.java:75)\r\n\tat com.step_definitions.LoginStepdefs.user_should_be_able_to_change_their_password(LoginStepdefs.java:268)\r\n\tat ✽.user should be able to change their password(file:///I:/intabella/src/test/resources/features/Login.feature:85)\r\n",
  "status": "failed"
});
formatter.embedding("image/png", "embedded0.png", "screenshot");
formatter.after({
  "status": "passed"
});
});