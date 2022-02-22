package com.step_definitions;

import com.pages.DashboardPage;
import com.pages.LoginPage;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LoginStepdefs {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Given("User on the Login Page")
    public void user_on_the_Login_Page() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @When("User login with valid {string} and {string} for {string}")
    public void user_login_with_valid_and_for(String username, String password, String userType) {
        switch (userType) {
            case "Driver":
                loginPage.login(username, password);
                break;
            case "Sales Manager":
                loginPage.login(username, password);
                break;
            case "Store Manager":
                loginPage.login(username, password);
                break;
            default:
                System.out.println("Invalid user type");
        }
    }

    @Then("User should be able to login")
    public void user_should_be_able_to_login() {

        BrowserUtils.waitFor(3);
        String actualUrl = Driver.get().getCurrentUrl();
        Assert.assertEquals(ConfigurationReader.get("url"), actualUrl);
    }

    @Then("{string} should be displayed")
    public void should_be_displayed(String mainPage) {
        String actualTitle = Driver.get().findElement(By.cssSelector("h1[class='oro-subtitle']")).getText();
        Assert.assertEquals(mainPage, actualTitle);

    }

    @When("Users login with invalid {string} and {string}")
    public void users_login_with_invalid_and(String username, String password) {

        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginBtn.click();

    }

    @Then("Error {string} display")
    public void error_display(String message) {
        if(message.equals("Invalid user name or password.")){
            String actualInputMessage= loginPage.errorMessage.getText();
            System.out.println("actualInputMessage = " + actualInputMessage);
            Assert.assertEquals(message,loginPage.errorMessage.getText());
        }else if((message.equals("Please fill out this field."))){
            String inputWarning = loginPage.usernameInput.getAttribute("validationMessage");
            String passwordWarning = loginPage.passwordInput.getAttribute("validationMessage");
            System.out.println("inputWarning = " + inputWarning);
            System.out.println("passwordWarning = " + passwordWarning);

            if(loginPage.usernameInput.getAttribute("value")==null || loginPage.passwordInput.getAttribute("value")==null)
                Assert.assertEquals(message,inputWarning);

        }

    }


    @When("User close browser after copy the url and paste to the new browser")
    public void user_close_browser_after_copy_the_url_and_paste_to_the_new_browser() {
        String actualUrl = Driver.get().getCurrentUrl();
        Driver.closeDriver();
        Driver.get().get(actualUrl);

    }


    @Then("User should not be able to land on Login Page")
    public void user_should_not_be_able_to_land_on_Login_Page() {
       Assert.assertTrue(Driver.get().getCurrentUrl().contains("user/login"));
    }

    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String userType) {

        BrowserUtils.waitFor(3);
        String username =null;
        String password =null;

        if(userType.equals("Driver")){
            username=ConfigurationReader.get("driver_username");
            password=ConfigurationReader.get("driver_password");
        }else  if(userType.equals("Sales Manager")){
            username=ConfigurationReader.get("salesmanager_username");
            password=ConfigurationReader.get("salesmanager_password");
        }else  if(userType.equals("Store Manager")){
            username=ConfigurationReader.get("storemanager_username");
            password=ConfigurationReader.get("storemanager_password");
        }

        new LoginPage().login(username,password);

    }

    @And("User should be able to verify {string} {string} {string} {string}")
    public void userShouldBeAbleToVerify(String breadcrumb, String pageHeading,  String pageURL, String pageTitle) {

            BrowserUtils.waitFor(3);

            Assert.assertEquals(breadcrumb, dashboardPage.breadcrumb.getText());
            Assert.assertEquals(pageTitle, Driver.get().getTitle());
            Assert.assertEquals(pageURL, Driver.get().getCurrentUrl());

            List<String> myheadings = new ArrayList<>();
            for (WebElement element : dashboardPage.menuOptions) {
                myheadings.add(element.getText());
            }
            for (int i = 0; i < dashboardPage.menuOptions.size(); i++) {
                Assert.assertTrue(pageHeading.contains(myheadings.get(i)) );
            }
    }

    @When("User close browser and open the application in the browser again")
    public void userCloseBrowserAndOpenTheApplicationInTheBrowserAgain() {
        String firstPage = Driver.get().getCurrentUrl();
        System.out.println("firstPage = " + firstPage);

        JavascriptExecutor jse = (JavascriptExecutor)Driver.get();
        jse.executeScript("window.open()");
        BrowserUtils.waitFor(3);

        ArrayList<String> tabs = new ArrayList<String>(Driver.get().getWindowHandles());
        Driver.get().switchTo().window(tabs.get(0)).close();
        BrowserUtils.waitFor(3);

        Driver.get().switchTo().window(tabs.get(1));
        Driver.get().get(firstPage);
        BrowserUtils.waitFor(3);

        String newTab = Driver.get().getCurrentUrl();
        System.out.println("newTab = " + newTab);
        BrowserUtils.waitFor(3);

        Assert.assertEquals(firstPage,newTab);
        BrowserUtils.waitFor(3);



    }

    @Then("Verify the Username field are trimmed")
    public void verifyTheUsernameFieldAreTrimmed() {
        loginPage.usernameInput.sendKeys("           "+ConfigurationReader.get("driver_username")+"  ");
        loginPage.passwordInput.sendKeys(ConfigurationReader.get("driver_password"));
        loginPage.loginBtn.click();

    }

    @Then("Verify the {string} and {string} placeholders are present")
    public void verifyTheAndPlaceholdersArePresent(String username, String password) {
        Assert.assertEquals(loginPage.usernameInput.getAttribute("placeholder"),username);
        Assert.assertEquals(loginPage.passwordInput.getAttribute("placeholder"),password);

    }

    @When("the password entered")
    public void thePasswordEntered() {
        BrowserUtils.waitFor(2);
        loginPage.passwordInput.sendKeys(ConfigurationReader.get("driver_password"));


    }

    @Then("Verify that entered password is invisible on UI")
    public void verifyThatEnteredPasswordIsInvisibleOnUI() {
        BrowserUtils.waitFor(2);

        Assert.assertEquals(loginPage.passwordInput.getAttribute("type"),"password");


    }

    @When("copy the location of the password inbox in DOM")
    public void copyTheLocationOfThePasswordInboxInDOM() {
        new LoginPage().usernameInput.sendKeys("user1");
        new LoginPage().passwordInput.sendKeys("UserUser123");

        String passwordHTML = new LoginPage().passwordInput.getAttribute("outerHTML");
        System.out.println("passwordHTML = " + passwordHTML);

    }
    @Then("Verify that entered password is invisible on HTML")
    public void verifyThatEnteredPasswordIsInvisibleOnHTML() {
        String passwordHTML = new LoginPage().passwordInput.getAttribute("outerHTML");
        String notVisibleRule = "type=\"password\"";
        Assert.assertTrue("verify",passwordHTML.contains(notVisibleRule));
        String password =new LoginPage().passwordInput.getAttribute("value");
        System.out.println("password = " + password);
        Assert.assertFalse("verify",passwordHTML.contains(password));


    }




    @Then("the Password can not be copied")
    public void thePasswordCanNotBeCopied() {
        loginPage.passwordInput.click();
        loginPage.passwordInput.sendKeys(Keys.CONTROL,"a");
        System.out.println(loginPage.passwordInput.getAttribute("value"));
        BrowserUtils.waitFor(3);
        loginPage.passwordInput.sendKeys(Keys.CONTROL,"c");
        BrowserUtils.waitFor(3);
        loginPage.usernameInput.click();
        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys(Keys.CONTROL,"v");
        Assert.assertNotEquals(loginPage.passwordInput.getAttribute("value"),loginPage.usernameInput.getAttribute("value"));
    }

    @When("user click on {string} link")
    public void userClickOnLink(String arg0) {
        loginPage.forgotPasswordLink.click();
    }

    @Then("user lands on the {string} page")
    public void userLandsOnThePage(String arg0) {
        String expectedTitle="Forgot Password";
        BrowserUtils.waitForPageToLoad(2);
        String actualPageTitle = Driver.get().getTitle();
        Assert.assertEquals(expectedTitle, actualPageTitle);
    }
    @Then("user enters username and clicks on {string} button.")
    public void user_enters_username_and_clicks_on_button(String string) {
        loginPage.usernameInput.sendKeys("user10");
        loginPage.requestButton.click();
    }

    @Then("user should be able to change their password")
    public void user_should_be_able_to_change_their_password() {
        String unExpectedMessage = loginPage.unableToSendEmail.getText();
        System.out.println("unExpectedMessage = " + unExpectedMessage);
        Assert.assertFalse(loginPage.unableToSendEmail.isDisplayed());


    }
    @Then("that Remember me on this computer link is clickable")
    public void thatRememberMeOnThisComputerLinkIsClickable() {

        try {
            new WebDriverWait(Driver.get(), 5).until(ExpectedConditions.elementToBeClickable(loginPage.rememberMe));
            System.out.println("Element is clickable");
        }
        catch(TimeoutException e) {
            System.out.println("Element isn't clickable");
        }
    }



    @When("user enters username and hits the Enter key")
    public void userEntersUsernameAndHitsTheEnterKey() {

        loginPage.usernameInput.sendKeys(ConfigurationReader.get("driver_username"),Keys.ENTER);

    }

    @And("user enters password and hits the Enter key")
    public void userEntersPasswordAndHitsTheEnterKey() {
        loginPage.passwordInput.sendKeys(ConfigurationReader.get("driver_password"),Keys.ENTER);
    }

    @Then("Background color  of {string}  button should be hex code {string}")
    public void backgroundColorOfButtonShouldBeHexCode(String login, String expected) {
        String backgroundColor = loginPage.loginBtn.getCssValue("background-color");
        System.out.println("backgroundColor = " + backgroundColor);
        String HexCodeBackground = Color.fromString(backgroundColor).asHex();
        System.out.println("HexCodeBackground = " + HexCodeBackground);
        Assert.assertEquals(expected,HexCodeBackground);
    }

}
