package com.pages;

import com.utilities.ConfigurationReader;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    //findAll check only one correct line.. if two lines are correct we have to change findAll to findBys
    @FindBy(id = "prependedInput") //represent drive.findelement
    public WebElement usernameInput;

    @FindBy(id = "prependedInput2")
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginBtn;

    @FindBy(xpath = "//div[@class='alert alert-error']/div")
    public WebElement errorMessage;

    @FindBy(xpath = "//ul[@class = 'dropdown-menu']/li[4]")
    public WebElement logoutButton;

    @FindBy(css = ".dropdown-toggle")
    public WebElement userName;

    @FindBy(css = ".custom-checkbox__text")
    public WebElement rememberMe;

    @FindBy(xpath = "//fieldset//a")
    public WebElement forgotPasswordLink;


    @FindBy(xpath = "//fieldset//button")
    public WebElement requestButton;

    @FindBy(xpath = "//fieldset//div")
    public WebElement unableToSendEmail;



    public void login(String username, String password) {

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void loginAsStoreManager() {

        String username = ConfigurationReader.get("storemanager_username");
        String password = ConfigurationReader.get("storemanager_password");

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void loginAsSalesManager() {

        String username = ConfigurationReader.get("salesmanager_username");
        String password = ConfigurationReader.get("salesmanager_password");

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void loginAsDriver() {

        String username = ConfigurationReader.get("driver_username");
        String password = ConfigurationReader.get("driver_password");

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }
}
