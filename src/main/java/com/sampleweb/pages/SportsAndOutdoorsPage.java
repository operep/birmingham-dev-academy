package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SportsAndOutdoorsPage extends HomePage {

    public String title = "Sports & Outdoors";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dsports&field-keywords=";

    public String sportsOnDropDownMenu = "//*[@id=\"searchDropdownBox\"]/option[text()=\"Sports & Outdoors\"]";
    public String searchButton = "//*[@class=\"nav-search-submit nav-sprite\"]/input";
    public String primeCheckbox = "//*[@id=\"leftNav\"]//*[@name=\"s-ref-checkbox-419158031\"]";

    public String pageTitle = "//h1[text()=\"Sports & Outdoors\"]";
    public String globalStoreLabel = "//*[@id=\"leftNav\"]/h4[text()=\"Global Store\"]";
    public String avgCustomerReviewLabel = "//*[@id=\"leftNav\"]/h4[text()=\"Avg. Customer Review\"]";
    public String secondLineMenuCategoryName = "//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]/span";
    public String secondLineMenuCategoryButton = "//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]";
    public String allProductsOnPage = "//*[@class=\"s-result-list s-search-results sg-row\"]//*[@class=\"s-include-content-margin s-border-bottom\"]";
    private String primeLabelOrMoreBuyingChoices = "//*[@aria-label='Amazon Prime' or @class='a-size-base a-color-secondary']";

    public SportsAndOutdoorsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isOnCorrectPage() { return driver.findElement(By.xpath(pageTitle)).isDisplayed(); }

    public boolean isGlobalStoreExists() { return driver.findElement(By.xpath(globalStoreLabel)).isDisplayed(); }

    public boolean isAvgCustomerReviewExists() { return driver.findElement(By.xpath(avgCustomerReviewLabel)).isDisplayed(); }

    public boolean isPageLinkSameAsCategoryName() { return driver.findElement(By.xpath(secondLineMenuCategoryName)).getText().contains(title); }

    public boolean isSamePageOnClick(){
        clickOnSecondLineMenuLink();
        return driver.findElement(By.xpath(pageTitle)).getText().contains(title);
    }

    public boolean primeOrMoreBuyingChoicesExists() {
        selectPrimeOnly();
        return driver.findElements(By.xpath(allProductsOnPage)).iterator().next().findElement(By.xpath(primeLabelOrMoreBuyingChoices)).isDisplayed();
    }

    public SportsAndOutdoorsPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public void getSportsURL(){
        driver.findElementByXPath(sportsOnDropDownMenu).click();
        driver.findElementByXPath(searchButton).click();
    }

    public void selectPrimeOnly(){
        driver.findElementByXPath(primeCheckbox).click();
    }

    public void clickOnSecondLineMenuLink(){
        driver.findElementByXPath(secondLineMenuCategoryButton).click();
    }

}
