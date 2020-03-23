package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IndustrialAndScientificPage extends HomePage {

    private final String PAGE_TITLE = "//h1[text()=\"Business, Industrial and Scientific Supplies\"]";
    private final String DROP_DOWN_CAT = "Industrial & Scientific";
    private static final String PATH_ORIG = "https://www.amazon.co.uk/";
    private static final String PATH = "https://www.amazon.co.uk/Business-Industry-Science/b/?ie=UTF8&node=5866054031&ref_=topnav_storetab_indust";

    public IndustrialAndScientificPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToInSciPage() {
        driver.findElementByXPath("//*[@id=\"searchDropdownBox\"]/option[text()='"+ DROP_DOWN_CAT +"']").click();
        driver.findElementByXPath("//*[@class=\"nav-search-submit nav-sprite\"]/input").click();
    }

    public boolean hasTitleDisplayed() {
        return driver.findElement(By.xpath(PAGE_TITLE)).isDisplayed();
    }

    public boolean hasGlobalStoreSection() {
        return driver.findElement(By.xpath("//*[@id=\"leftNav\"]/h4[text()=\"Global Store\"]")).isDisplayed();
    }

    public boolean hasCustomerReviewSection() {
        return driver.findElement(By.xpath("//*[@id=\"leftNav\"]/h4[text()=\"Avg. Customer Review\"]")).isDisplayed();
    }

    public boolean matchesCategoryName() {
        // verify that first link in second line menu name is the same as the category name
        String menuName = driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]/span")).getText();
        String pageName = driver.findElement(By.xpath("//*[@id=\"merchandised-content\"]/div[1]/div[1]/div/h1")).getText();
        return menuName.substring(0, 16).contains(pageName.substring(0, 16));
    }

    public boolean redirectedToSamePage() {
        // verify that  after clicking on the first link in second line menu user is being redirected to the same page
        driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]/span")).click();
        return driver.findElement(By.xpath(PAGE_TITLE)).isDisplayed();
    }

    public boolean verifyPrimeLabel() {
        // check the PRIME checkbox and verify that all items from the results list on the first page have prime label in the description
        driver.findElement(By.xpath("//*[@id=\"leftNav\"]/ul[4]/div/li[1]/span/span/div/label/span/i")).click();
        String amazonPrime =  "//*[@aria-label=\"Amazon Prime\"]";
        return driver.findElements(By.xpath("//*[@class=\"s-result-list s-search-results sg-row\"]")).iterator().next().findElement(By.xpath(amazonPrime)).isDisplayed();
    }

    public static String getPATH() {
        return PATH;
    }

    public static String getOriginalPATH() {
        return PATH_ORIG;
    }

}
