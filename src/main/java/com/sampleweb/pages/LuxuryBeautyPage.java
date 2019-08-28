package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class LuxuryBeautyPage extends HomePage {


    public String title = "Luxury Beauty";
    public String pageTitle = "//img[@alt='Luxury Beauty']";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dluxury-beauty&field-keywords=";

    public LuxuryBeautyPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public String getPath() {return PATH;}

    private WebElement createWebElement(String xpath)
    {
         WebElement createdElement = driver.findElementByXPath(xpath);
        return createdElement;
    }


    private String getTitleOfCurrentPage()
    {
        return createWebElement("//div[@id='nav-subnav']/a[1]").getText();
    }

    public String getTitle()
    {
        return getTitleOfCurrentPage();
    }

    public WebElement getGlobalStore()
    {
        return createWebElement("//input[@name='s-ref-checkbox-11259240031']");
    }

    public WebElement getCustomerReview()
    {
        return createWebElement("//*[@id='leftNav']/ul[11]");
    }

    public WebElement getImageLabelOfCurrentPage()
    {
        return createWebElement("//img[@src='https://images-eu.ssl-images-amazon.com/images/G/02/beauty/images/prestige/skylight/sept/UK_lux_SF-Banner_770x100._V312450604_.jpg']");
    }

    public void clickFirstLinkSecondMenu()
    {
        createWebElement("//*[@id='nav-subnav']/a[1]").click();
    }

    public boolean checkIfAllElementsDisplayedArePrimeOrAddMore()
    {
        String primeBox = "//input[@name='s-ref-checkbox-419158031']\n";
        String xpathPrime = "//span[@class='aok-inline-block s-image-logo-view']";
        String xpathMore = "//span[@class='a-size-base a-color-secondary']";
        String xpathCombinedPrimeNMore = xpathPrime + " | " + xpathMore;
        String xpathAllResults = "//*[@class='s-result-list s-search-results sg-row']";

        createWebElement(primeBox).click();

        return driver.findElementsByXPath(xpathAllResults).iterator().next().findElement(By.xpath(xpathCombinedPrimeNMore)).isDisplayed();
    }
}
