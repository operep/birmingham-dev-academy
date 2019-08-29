package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomeKitchen extends HomePage {

    public String title = "Home & Kitchen";
    public String secondMenuTitle = "Home & Garden";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dkitchen&field-keywords=";
    private String globalStoreBoxLocator = "//input[@name=\"s-ref-checkbox-11259240031\"]";
    private String globalStoreLabelLocator = "//img[@class=\"s-ref-img-sprite\"]";
    private String averageCustomerReviewLocator ="//*[@id=\"leftNav\"]/ul[9]";
    public String secondMenuLocator = "//*[@id=\"nav-subnav\"]/a[1]/span";
    private String primeProductListLocator = "//*[@class=\"s-result-list s-search-results sg-row\"]//*[@class=\"s-include-content-margin s-border-bottom\"]";
    private String primeLabelOrMoreLocator = "//*[@aria-label=\'Amazon Prime\' or @class=\'a-size-base a-color-secondary\']";
    private String primeCheckBoxLocator = "//input[@name=\"s-ref-checkbox-419158031\"]";

    public HomeKitchen(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(title)).isDisplayed();
    }

    public boolean isTitleCorrect(String title) throws Exception {
        return verifyTitle(driver.getTitle(), title);
    }

    //looks correct while testing runs idk
    public HomeKitchen directToKitchen() {
        directToCategory("search-alias=kitchen");
        return this;
    }

        public boolean isGlobalStoreSectionThere() {
            try {
                driver.findElement(By.xpath(globalStoreBoxLocator));
                driver.findElement(By.xpath(globalStoreLabelLocator));
                return true;
            } catch (org.openqa.selenium.NoSuchElementException e) {
                return false;
            }
        }

    public boolean isAverageCustomerReviewThere(){
        try {
            driver.findElement(By.xpath(averageCustomerReviewLocator));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPrimeCorrect(){
        driver.findElement(By.xpath(primeCheckBoxLocator)).click();
        return driver.findElements(By.xpath(primeProductListLocator)).iterator().next().findElement(By.xpath(primeLabelOrMoreLocator)).isDisplayed();
    }

    public boolean isSubMenuCorrect(){
        String menuText = driver.findElement(By.xpath(secondMenuLocator)).getText();
        if (menuText.equals(secondMenuTitle)) {
            return true;
        }
        return false;
    }
}
