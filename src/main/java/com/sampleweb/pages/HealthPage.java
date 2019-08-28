package com.sampleweb.pages;

import com.sampleweb.framework.PageNavigator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.xml.xpath.XPath;

public class HealthPage extends HomePage {

    public String title = "Health & Personal Care";
    public String pageTitle = "//h1[text()=\"Health & Personal Care\"]";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Ddrugstore&field-keywords=";
    public String pageTitleHead = "//*[@id=\"nav-subnav\"]/a[1]/span";
    public String globalStore = "//*[@id=\"leftNav\"]/h4[2]";
    public PageNavigator navigator = new PageNavigator();

    public HealthPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToHealthPage() throws Exception {
        navigator.navigateToPage(driver, "https://www.amazon.co.uk", HealthPage.class);
        driver.findElementByXPath("//*[@id=\"searchDropdownBox\"]").click();
        driver.findElementByXPath("//*[@id=\"searchDropdownBox\"]/option[25]").click();
        driver.findElementByXPath("//*[@id=\"nav-search\"]/form/div[2]/div").click();
    }

    public boolean isHealthPageLoaded() throws Exception {
        navigateToHealthPage();
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public boolean verifyHealthTitleExists() throws Exception {

        navigateToHealthPage();
        return driver.findElement(By.xpath(pageTitleHead)).getText().contains(title);

    }

    public boolean verifyGlobalStoreExists() throws Exception {
        navigateToHealthPage();

        return driver.findElement(By.xpath(globalStore)).getText().contains("Global Store");
    }

    public boolean verifyAverageReviewsExist() throws Exception {
        navigateToHealthPage();

        return driver.findElementByXPath("//*[@id=\"leftNav\"]/h4[8]").getText().contains("Avg. Customer Review");
    }

    public boolean verifyFirstLinkHasCatagoryName() throws Exception {
        navigateToHealthPage();

        return driver.findElementByXPath("//*[@id=\"nav-subnav\"]/a[1]/span").getText().contains(title);
    }

    public boolean verifyClickOnHeaderMenuDirectsToSamePage() throws Exception {
        navigateToHealthPage();

        driver.findElementByXPath("//*[@id=\"nav-subnav\"]/a[1]/span").click();
        return driver.getCurrentUrl().contentEquals(PATH);
    }

    public boolean verifySelectPrimeReturnsOnlyPrimeitems() throws Exception {
        navigateToHealthPage();

        driver.findElementByXPath("//*[@id=\"leftNav\"]/ul[6]/div/li[1]/span/span/div/label/input").click();

            return driver.findElements(By.xpath("//*[@class='a-section aok-relative s-image-fixed-height']")).iterator().next().
                    findElement(By.xpath("//*[@class='a-icon a-icon-prime a-icon-medium']")).isDisplayed();
        }
    }
