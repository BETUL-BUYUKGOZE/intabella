package com.step_definitions;

import com.pages.LoginPage;
import com.utilities.BrowserUtils;
import com.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LogoutStepdefs {
    LoginPage loginPage = new LoginPage();

    @Then("user able to logout")
    public void user_able_to_logout()  {

        loginPage.userName.click();
        BrowserUtils.waitFor(7);
        loginPage.logoutButton.click();
        Assert.assertTrue(Driver.get().getCurrentUrl().contains("user/login"));

    }


}
