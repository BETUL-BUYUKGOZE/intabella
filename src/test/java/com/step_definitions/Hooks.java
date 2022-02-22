package com.step_definitions;

import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Hooks {

  protected WebDriver driver;
  protected Actions actions;
  protected WebDriverWait wait;

  @Before  //should come from cucumber
    public void setUp(){
    Driver.get().manage().window().maximize();
    Driver.get().manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
    Driver.get().get(ConfigurationReader.get("url"));
  }

  @After
  public void tearDown(Scenario scenario){
    if(scenario.isFailed()){
      final byte[] screenshot =((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot,"image/png","screenshot");
    }
    Driver.closeDriver();
  }

  @Before("@db")
  public void setUpdb(){
    System.out.println("\tconnecting to database...");
  }
  @After("@db")
  public void closeDb(){
    System.out.println("\tdisconnecting to database...");
  }

}
