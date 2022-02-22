@a
Feature: Users should be able to logout

  Scenario Outline: logout "<UserType>"
    Given User on the Login Page
    When User login with valid "<Username>" and "<Password>" for "<UserType>"
    Then user able to logout
    Examples:
      | UserType      | Username        | Password    |
      | Driver        | user17          | UserUser123 |
      | Sales Manager | salesmanager101 | UserUser123 |
      | Store Manager | storemanager85  | UserUser123 |


