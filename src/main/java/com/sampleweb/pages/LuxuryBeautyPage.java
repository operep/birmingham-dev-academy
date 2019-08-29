package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LuxuryBeautyPage extends HomePage {


    public String title = "Luxury Beauty";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dluxury-beauty&field-keywords=";

    @FindBy(xpath = "//img[@alt='Luxury Beauty']")
    private WebElement pageTitle ;

    @FindBy(xpath = "//div[@id='nav-subnav']/a[1]")
    private WebElement titleOfCurrentPage ;

    @FindBy(xpath = "//input[@name='s-ref-checkbox-11259240031']")
    private WebElement globalStoreBox ;

    @FindBy(xpath = "//*[@id='leftNav']/ul[11]")
    private WebElement customerReview ;

    @FindBy(xpath = "//img[@src='https://images-eu.ssl-images-amazon.com/images/G/02/beauty/images/prestige/skylight/sept/UK_lux_SF-Banner_770x100._V312450604_.jpg']")
    private WebElement imageLabelOfCurrentPage ;

    @FindBy(xpath = "//*[@id='nav-subnav']/a[1]")
    private WebElement firstLinkSecondMenu ;

    @FindBy(xpath = "//input[@name='s-ref-checkbox-419158031']\n")
    private WebElement primeBox ;

    @FindBy(xpath = "//*[@class='s-result-list s-search-results sg-row']")
    private List<WebElement> allProductSeached;


    public LuxuryBeautyPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return pageTitle.isDisplayed();
    }

    public String getPath() {return PATH;}

    private WebElement createWebElement(String xpath)
    {
         WebElement createdElement = driver.findElementByXPath(xpath);
        return createdElement;
    }


    private String getTitleOfCurrentPage()
    {
        return titleOfCurrentPage.getText();
    }

    public String getTitle()
    {
        return getTitleOfCurrentPage();
    }

    public boolean isGlobalStoreBoxDisplayed()
    {
        return globalStoreBox.isDisplayed();
    }

    public boolean isCustomerReviewDisplayed()
    {
        return customerReview.isDisplayed();
    }

    public String getImageLabelOfCurrentPage()
    {
        return imageLabelOfCurrentPage.getAttribute("alt");
    }

    public void clickFirstLinkSecondMenu()
    {
        firstLinkSecondMenu.click();
    }

    public boolean checkIfAllElementsDisplayedArePrimeOrAddMore()
    {
        String xpathPrime = "//span[@class='aok-inline-block s-image-logo-view']";
        String xpathMore = "//span[@class='a-size-base a-color-secondary']";
        String xpathCombinedPrimeNMore = xpathPrime + " | " + xpathMore;
        String xpathAllResults = "//*[@class='s-result-list s-search-results sg-row']";

        primeBox.click();

        return allProductSeached.iterator().next().findElement(By.xpath(xpathCombinedPrimeNMore)).isDisplayed();
    }
}
