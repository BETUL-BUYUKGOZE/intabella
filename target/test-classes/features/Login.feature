Feature: Login functionality

  Background:
    Given User on the Login Page

  @INTA-898
  Scenario Outline: AC1-(BETUL) login with valid credential (positive)
    When User login with valid "<Username>" and "<Password>" for "<UserType>"
    Then User should be able to login
    And "<MainPage>" should be displayed
    Examples:
      | UserType      | Username        | Password    | MainPage        |
      | Driver        | user10          | UserUser123 | Quick Launchpad |
      | Sales Manager | salesmanager101 | UserUser123 | Dashboard       |
      | Store Manager | storemanager85  | UserUser123 | Dashboard       |

  @INTA-881
  Scenario Outline: AC6-(BETUL) login with invalid credential (negative)
    When Users login with invalid "<username>" and "<password>"
    Then Error "<message>" display
    Examples:
      | username | password    | message                        |
      | user10   | password    | Invalid user name or password. |
      | rabbit   | UserUser123 | Invalid user name or password. |
      |          | UserUser123 | Please fill out this field.    |
      | user10   |             | Please fill out this field.    |
      |          |             | Please fill out this field.    |
      | rabbit   | password    | Invalid user name or password. |


  @INTA-920
  Scenario: AC2-(BETUL) the system shouldn't allow users to access the application without providing credentials
    And the user logged in as "Driver"
    When User close browser after copy the url and paste to the new browser
    Then User should not be able to land on Login Page


  @INTA-916
  Scenario Outline: AC1-(BETUL) Verify Headers
    And the user logged in as "Sales Manager"
    Then User should be able to login
    And User should be able to verify "<Breadcrumb>" "<Page Heading>" "<Page URL>" "<Page Title>"
    Examples:
      | Breadcrumb            | Page Heading                                                                                  | Page URL                  | Page Title |
      | Dashboards/ Dashboard | Dashboards - Fleet - Customers - Sales - Activities - Marketing - Reports & Segments - System | https://qa.intabella.com/ | Dashboard  |

  @INTA-942
  Scenario:AC3-(BETUL) Validate Logging into the Application, closing the Browser without logging out, and opening the application in the Browser again
    And the user logged in as "Driver"
    When User close browser and open the application in the browser again
    Then User should be able to login

  @INTA-943
  Scenario:AC4-(BETUL) Validate whether the leading and trailing spaces entered into the Username field are trimmed
    When Verify the Username field are trimmed
    Then User should be able to login

  @INTA-944
  Scenario Outline:AC5-(BETUL) Validate all the fields in the Login page have the proper placeholders (Username or Email and Password)
    Then Verify the "<Username>" and "<Password>" placeholders are present
    Examples:
      | Username          | Password |
      | Username or Email | Password |

  @INTA-950
  Scenario: Validate the Password text entered into the 'Password' field is toggled to hide its visibility(7)
    When the password entered
    Then Verify that entered password is invisible on UI

@INTA-951
  Scenario: Validate the Password is not visible in the Page Source(8)
  When copy the location of the password inbox in DOM
    Then Verify that entered password is invisible on HTML

  @INTA-952
  Scenario: Validate the copying of the text entered into the Password field (It  should not be applicable)(9)
    When the password entered
    Then the Password can not be copied

@INTA-953
  Scenario:  "Forgot Password" menu
    When user click on "Forgot your password?" link
    Then user lands on the "Forgot Password" page
    And user enters username and clicks on "REQUEST" button.
    And user should be able to change their password

@INTA-954
  Scenario:  Validate user can see the "Remember me on this computer" link on the login page and it should be clickable(11)
    Then that Remember me on this computer link is clickable
@INTA-1041
    Scenario: Validate to login by using the Keyboard keys
      When user enters username and hits the Enter key
     And user enters password and hits the Enter key
      Then User should be able to login
  @INTA-1042 @INTA-891
      Scenario:
        Then Background color  of "LOG IN"  button should be hex code "#0c84a3"





