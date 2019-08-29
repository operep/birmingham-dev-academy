package com.sampleweb.pages;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ToysAndGamesPage extends HomePage {
    public String title = "Toys & Games";
    public String pageTitle = "//h1[text()=\"Toys & Games\"]";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dtoys&field-keywords=";
    protected String dropDownLocator = "//*[@id=\"searchDropdownBox\"]/option[43]";

    private String amazonPrimeCheckbox = "//input[@name=\"s-ref-checkbox-419158031\"]";
    private String allProducts = "//*[@class='s-result-list s-search-results sg-row']";
    private String labels = "//span[@class=\"aok-inline-block s-image-logo-view\"] | //span[@class=\"a-size-base a-color-secondary\"]";

    public ToysAndGamesPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public void selectToys() {
        driver.findElementByXPath(dropDownLocator).click();
        getSearchField().sendKeys("");
        clickSubmitButton();
    }

//    public ToysAndGamesPage getTitle() {
//        driver.findElementByXPath(pageTitle);
//        return this;
//    }

    public boolean verifyGlobalStore() {
        return driver.findElementByXPath("//*[@id=\"leftNav\"]/ul[5]/div/li/span/span/div/label/span/span/img") != null;
    }

    public boolean verifyAvgRating() {
        return driver.findElementByXPath("//*[@id=\"leftNav\"]/ul[9]") != null;
    }

    public boolean verifyCategoryName() {
        driver.findElementByXPath("//*[@id=\"nav-subnav\"]/a[1]");
        WebElement titleElement = driver.findElementByXPath(pageTitle);
        if (titleElement.getText().equals(title)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyClickInMenu() {
        driver.findElementByXPath("//*[@id=\"nav-subnav\"]/a[1]").click();
        WebElement titleElement = driver.findElementByXPath(pageTitle);
        if (titleElement.getText().equals(title)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPrimeBox(){
        driver.findElementByXPath(amazonPrimeCheckbox).click();
        return true;
    }

    public boolean verifyPrime(){
        checkPrimeBox();
        return driver.findElements(By.xpath(allProducts)).iterator().next().findElement(By.xpath(labels)).isDisplayed();
    }
}





