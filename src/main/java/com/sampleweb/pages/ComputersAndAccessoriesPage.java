package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class ComputersAndAccessoriesPage extends HomePage {
    public String title = "Computers & Accessories";
    private final String pageTitle = "//*[@id='fst-hybrid-dynamic-h1']/div/h1/b";
    private final String globalStore = "//*[@id='leftNav']/ul[3]/div/li/span/span/div/label/span/span/img";
    private final String averageReviewsText = "//*[@id='leftNav']/h4[9]";
    private final String menuText = "//*[@id='nav-subnav']/a[1]";
    private final String dropDownMenu = "//*[@id='searchDropdownBox']";
    public static String PATH = "https://www.amazon.co.uk/s?i=computers&ref=nb_sb_noss";
    private final String products = "//*[@class='s-result-list s-search-results sg-row']//*[@class='s-include-content-margin s-border-bottom']";
    private final String amazonPrimeCheckbox = "//*[@id='leftNav']/ul[5]/div/li[1]/span/span/div/label/input";
    private final String amazonPrimeLabel = "//*[@aria-label='Amazon Prime' or @class='a-size-base a-color-secondary']";

    public ComputersAndAccessoriesPage(RemoteWebDriver driver){
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public String getTitle(){
        driver.findElement(By.xpath(menuText));
        return driver.findElement(By.xpath(pageTitle)).getText();
    }

    public ComputersAndAccessoriesPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public boolean isGlobalStore(){
       return driver.findElement(By.xpath(globalStore)).isDisplayed();
    }

    public boolean isAverageReviewsDisplayed(){
        return driver.findElement(By.xpath(averageReviewsText)).isDisplayed();
    }

    public String getDropDownMenuText(){
        return driver.findElement(By.xpath(menuText)).getText();
    }

    public boolean checkDropdownRedirectEqualsCurrentPage(){
        driver.findElement(By.xpath(dropDownMenu)).click();
        Select select = new Select(driver.findElement(By.xpath(dropDownMenu)));
        select.selectByValue("search-alias=computers");
        driver.findElement(By.xpath(dropDownMenu)).sendKeys(Keys.RETURN);

        return getTitle().equals(title);
    }

    private WebElement checkAmazonPrimeBox(){
        driver.findElement(By.xpath(amazonPrimeCheckbox)).click();
        return driver.findElement(By.xpath(amazonPrimeCheckbox));
    }

    public boolean checkAmazonPrimeLabels() {
        checkAmazonPrimeBox();
        return driver.findElements(By.xpath(products)).iterator().next().findElement(By.xpath(amazonPrimeLabel)).isDisplayed();
    }

}
