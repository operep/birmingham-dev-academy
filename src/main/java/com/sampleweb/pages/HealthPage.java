package com.sampleweb.pages;

import com.sampleweb.framework.PageNavigator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.xml.xpath.XPath;

public class HealthPage extends HomePage {

    private String title = "Health & Personal Care";
    private PageNavigator navigator = new PageNavigator();

    private String pageTitle = "//h1[text()=\"Health & Personal Care\"]";
    private static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Ddrugstore&field-keywords=";
    private String pageTitleHead = "//*[@id=\"nav-subnav\"]/a[1]/span";
    private String globalStore = "//*[@id=\"leftNav\"]/h4[2]";
    private String dropDownMenu = "//*[@id=\"searchDropdownBox\"]";
    private String searchButton = "//*[@id=\"nav-search\"]/form/div[2]/div";



    public HealthPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToHealthPage() throws Exception {
        navigator.navigateToPage(driver, "https://www.amazon.co.uk", HealthPage.class);
        driver.findElementByXPath(dropDownMenu).click();
        driver.findElementByXPath(dropDownMenu + "/option[25]").click();
        driver.findElementByXPath().click();
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

        driver.findElementByXPath("//*[@id='leftNav']/ul[6]/div/li[1]/span/span/div/label/input").click();

            return driver.findElements(By.xpath("//*[@class='a-section aok-relative s-image-fixed-height']")).iterator().next().
                    findElement(By.xpath("//*[@class='a-icon a-icon-prime a-icon-medium']")).isDisplayed();
    }

    public boolean verifyAverageRatingsSortedCorrectly() throws Exception {
        navigateToHealthPage();
        driver.findElementByXPath("//*[@id='leftNav']/ul[9]/div/li[1]/span/a/span").click();
        driver.findElementByXPath("//*[@id='a-autoid-0-announce']/span[1]").click();
        driver.findElementByXPath("//*[@id='s-result-sort-select_3']").click();
        driver.findElementByXPath("//*[@id='search']/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[2]/div/span/a/i[1]/span").click();
        driver.findElementsByXPath("//*[@class='a-icon-alt']").iterator().next().getText().
    }

    }
