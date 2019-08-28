package com.sampleweb.pages;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class PCVideoGamesPage extends HomePage {

  public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dvideogames&field-keywords=";

  public PCVideoGamesPage(RemoteWebDriver driver) {
    super(driver);
  }

  public boolean isLoaded() {
    return driver.findElement(By.xpath("//h1[text()='PC and Video Games']")).isDisplayed();
  }

  public void navigateByDropdown() {
    // Get the dropdown and select the video games item
    Select drop = new Select(driver.findElement(By.name("url")));
    drop.selectByValue("search-alias=videogames");
    // Empty the search box and then search
    driver.findElement(By.xpath(searchBoxLocator)).clear();
    submitButtonLocator.click();
  }

  public boolean hasGlobal() {
    return driver.findElement(By.xpath("//h4[text()='Global Store']")).isDisplayed();
  }

  public boolean hasAvgReview() {
    return driver.findElement(By.xpath("//h4[text()='Avg. Customer Review']")).isDisplayed();
  }

  public boolean hasSameFirstNavLink() {
    return getFirstNavLink().isDisplayed();
  }

  public boolean firstLinkSameNav() {
    getFirstNavLink().click();
    return isLoaded();
  }

  private WebElement getFirstNavLink() {
    return driver.findElement(
        By.xpath("//*[@id='nav-subnav']/a[1]/span"));
  }

  public boolean checkPrime() {
    WebElement input = driver.findElement(
        By.xpath("//*[@id='leftNav']/ul[5]/div/li[1]/span/span/div/label/input"));
    input.click();
    input = driver.findElement(By.xpath("//*[@id='p_76/419158031']/span/a/div/label/input"));
    return input.isSelected();
  }

  public boolean checkPrimeItems() {
    // Get the parent div
    WebElement div = driver.findElement(
        By.xpath("//div[contains(@class,'s-result-list')]"));

    // All the products
    List<WebElement> children = div.findElements(By.xpath("*"));

    for (WebElement child : children) {
      try {
        // contains the prime label or a span which contains more buying choices
        // don't need to store in a variable, just want it too exist
        child.findElement(By.xpath(
            ".//i[@aria-label='Amazon Prime'] | .//span[contains(text(), 'More buying choices')]"))
             .isDisplayed();
      } catch (NoSuchElementException e) {
        // no prime or more... so fail
        return false;
      }
    }
    return true;
  }
}
