package com.sampleweb.pages;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PCVideoGamesPage extends HomePage {

  public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dvideogames&field-keywords=";

  ////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////               Web Elements                   ///////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////
  @FindBy(xpath = "//h1[text()='PC and Video Games']")
  private WebElement title;

  @FindBy(name = "url")
  private WebElement dropDown;

  @FindBy(xpath = "//h4[text()='Global Store']")
  private WebElement globalStore;

  @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
  private WebElement avgReviewHeader;

  @FindBy(xpath = "//span[@class='nav-a-content' and contains(text(), 'Video Games')]")
  private WebElement firstNavLink;

  @FindBy(xpath = "//input[@type='checkbox' and @name='s-ref-checkbox-419158031']")
  private WebElement primeCheckBox;

  @FindBy(xpath = "//div[contains(@class, 's-search-results')]/div")
  private List<WebElement> products;

  @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
  private WebElement searchBox;

  public PCVideoGamesPage(RemoteWebDriver driver) {
    super(driver);
  }

  public boolean isLoaded() {
    return title.isDisplayed();
  }

  public void navigateByDropdown() {
    // Get the dropdown and select the video games item
    Select drop = new Select(dropDown);
    drop.selectByValue("search-alias=videogames");
    // Empty the search box and then search
    searchBox.clear();
    submitButtonLocator.click();
  }

  public boolean hasGlobal() {
    return globalStore.isDisplayed();
  }

  public boolean hasAvgReview() {
    return avgReviewHeader.isDisplayed();
  }

  public boolean hasSameFirstNavLink() {
    return firstNavLink.isDisplayed();
  }

  public boolean firstLinkSameNav() {
    firstNavLink.click();
    return isLoaded();
  }

  public boolean checkPrimeItems() {
    // Check the prime checkbox
    primeCheckBox.click();

    // All the products
    for (WebElement product : products) {
      try {
        // contains the prime label or a span which contains more buying choices
        // don't need to store in a variable, just want it too exist
        product.findElement(By.xpath(
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
