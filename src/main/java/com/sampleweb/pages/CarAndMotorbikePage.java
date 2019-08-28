package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

// A wild lenny appears ( ͡° ͜ʖ ͡°)
public class CarAndMotorbikePage extends HomePage {

    public static String PATH = "https://www.amazon.co.uk/"; // Amazon home page url
    public String title = "Car & Motorbike";
    public String pageTitle = "//h1[text()=\"Car & Motorbike\"]";
    public String primeLabel = "//*[@aria-label='Amazon Prime']";

    // TODO: Change file paths from absolute to relative
    public String navTitle = "//*[@id=\"nav-subnav\"]/a[1]/span";
    public String dropdownOption = "//*[@id=\"searchDropdownBox\"]/option[11]";
    public String secondLineMenuLink = "//*[@id=\"nav-subnav\"]/a[1]/span";
    public String globalStoreTitle = "//*[@id=\"leftNav\"]/h4[2]";
    public String avgCustomerReviewTitle = "//*[@id=\"leftNav\"]/h4[6]";

    public String resultsList = "//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]";
    public String primeCheckbox = "//*[@id=\"leftNav\"]/ul[4]/div/li[1]/span/span/div/label/input";


    public CarAndMotorbikePage(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Checks if the page title is displayed.
     * @return boolean
     */
    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    /**
     * @param searchText
     */
    public CarAndMotorbikePage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    /**
     * Clicks the dropdown menu option and then the search button
     */
    public void selectOptionFromDropdown() {
        driver.findElement(By.xpath(dropdownOption)).click();
        this.clickSubmitButton();
    }

    /**
     * Clicks the first link on the second line menu
     */
    public void selectSecondLineMenuLink() {
        driver.findElement(By.xpath(secondLineMenuLink)).click();
    }

    /**
     * Clicks the 'Prime' checkbox
     */
    public void selectPrimeCheckbox() {
        driver.findElement(By.xpath(primeCheckbox)).click();
    }


    /**
     * Checks that the page title is the same as first link text on the second line menu
     * @return boolean
     */
    public boolean checkPageTitle() { //TODO: Change method name later
        return driver.findElement(By.xpath(pageTitle)).getText().contains(driver.findElement(By.xpath(navTitle)).getText());
    }

    /**
     * Checks that the global store title exists on the page
     * @return boolean
     */
    public boolean doesGlobalStoreTitleExists() {
        return driver.findElement(By.xpath((globalStoreTitle))).isDisplayed();
    }

    /**
     * Checks that the average customer review title exists on the page
     * @return boolean
     */
    public boolean isAvgCustomerReviewDisplayed() {
        return driver.findElement(By.xpath((avgCustomerReviewTitle))).isDisplayed();
    }

    /**
     * Check that the items in the results list contain prime labels
     * @return boolean
     */
    public boolean checkPrimeLabels() {
        return driver.findElements(By.xpath(resultsList)).iterator().next().findElement(By.xpath(primeLabel)).isDisplayed();
    }


}
