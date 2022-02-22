package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

  //no need to explicitly write constructor because it will use its parents constructor
  @FindBy( css = (".breadcrumb")) //represent drive.findelement
  public WebElement breadcrumb;

}
