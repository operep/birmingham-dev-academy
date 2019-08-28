package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IndustrialAndScientificPage extends HomePage {

    private String title = "Business, Industrial and Scientific Supplies";
    private String dropDownCategory = "Industrial & Scientific";
    private String pageTitle = "//h1[text()=\"Business, Industrial and Scientific Supplies\"]";
    private static String PATH = "https://www.amazon.co.uk/";

    public IndustrialAndScientificPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToInSciPage() {
        driver.findElementByXPath("//*[@id=\"searchDropdownBox\"]/option[text()='"+ dropDownCategory +"']").click();
        driver.findElementByXPath("//*[@class=\"nav-search-submit nav-sprite\"]/input").click();
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public boolean hasGlobalStoreSection() {
        return driver.findElement(By.xpath("//*[@id=\"leftNav\"]/h4[text()=\"Global Store\"]")).isDisplayed();
    }

    public boolean hasCustomerReviewSection() {
        return driver.findElement(By.xpath("//*[@id=\"leftNav\"]/h4[text()=\"Avg. Customer Review\"]")).isDisplayed();
    }

    public boolean matchesCategoryName() {
        String menuName = driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]/span")).getText();
        String pageName = driver.findElement(By.xpath("//*[@id=\"merchandised-content\"]/div[1]/div[1]/div/h1")).getText();
        return menuName.substring(0, 16).contains(pageName.substring(0, 16));
    }

    public boolean redirectedToSamePage() {
        driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]/span")).click();
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public boolean verifyPrimeLabel() {
        driver.findElement(By.xpath("//*[@id=\"leftNav\"]/ul[4]/div/li[1]/span/span/div/label/span/i")).click();
        String amazonPrime =  "//*[@aria-label=\"Amazon Prime\"]";
        //return driver.findElements(By.xpath("//*[@class=\"s-result-list s-search-results sg-row\"]//*[@class=\"s-include-content-margin s-border-bottom\"]")).iterator().next().findElement(By.xpath(amazonPrime)).isDisplayed();
        return false;
    }

    public IndustrialAndScientificPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public static String getPATH() {
        return PATH;
    }

}
