package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IndustrialAndScientificPage extends HomePage {

    public String title = "Business, Industrial and Scientific Supplies";
    public String category = "Business, Industrial & Scientific Supplies";
    public String pageTitle = "//h1[text()=\"Business, Industrial and Scientific Supplies\"]";
    public static String PATH = "https://www.amazon.co.uk/";

    public IndustrialAndScientificPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public IndustrialAndScientificPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public void navigateToInSciPage() {
        driver.findElementByXPath("//*[@id=\"searchDropdownBox\"]/option[28]").click();
        driver.findElementByXPath("//*[@id=\"nav-search\"]/form/div[2]/div/input").click();
    }



}
