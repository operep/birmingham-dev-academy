package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class PetSuppliesPage extends HomePage{

    public String title = "Pet Supplies";
    public String pageTitle = "//h1[text()=\"" + title + "\"]";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dpets&field-keywords=";
    public String firstLinkInSecondMenu = "//*[@id=\"nav-subnav\"]/a[1]/span";
    public String primeElement = "//*[@class=\"a-icon a-icon-prime a-icon-medium\"]";
    public String moreBuyingOptions = "//span[contains(text(), \"More buying choices\")]";
    public String primeOrMoreOptions = primeElement + " | " + moreBuyingOptions;
    public PetSuppliesPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public PetSuppliesPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public boolean isGlobalStoreCheckboxDisplayed(){
        return driver.findElementByXPath("//*[@id='leftNav']/h4[contains(text(), 'Global Store')]") != null;
    }

    public boolean isAverageCustomerRatingTitleDisplayed(){
        return driver.findElementByXPath("//*[@id='leftNav']/h4[contains(text(), 'Customer Review')]") != null;
    }

    public boolean isAverageCustomerRatingFieldsDisplayed(){
        return driver.findElementsByXPath("//*[@id='leftNav']/h4[contains(text(), 'Customer Review')]/following-sibling::ul[1]/div/li").size() == 4;
    }

    public boolean isFirstLinkInSecondMenuSameAsTitleTest(){
        return driver.findElementByXPath(firstLinkInSecondMenu).getText().equals("Pet Supplies");
    }

    public void clickOnFirstLinkInSecondMenu(){
        driver.findElementByXPath(firstLinkInSecondMenu).click();
    }

    public void clickOnPrimeCheckbox(){
        driver.findElementByXPath("//*[@id=\"leftNav\"]/ul[6]/div/li[1]/span/span/div/label/span/i").click();
    }

    public boolean checkOnlyPrimeItemsAreAvailable(){
        //Not Finished
        List<WebElement> results = driver.findElementsByXPath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]");
        return results.iterator().next().findElement(By.xpath(primeOrMoreOptions)).isDisplayed();
    }


}
