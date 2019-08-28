package com.sampleweb.pages;

import com.sampleweb.framework.PageNavigator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthPage extends HomePage{

    public String title = "Health & Personal Care";
    public String pageTitle = "//h1[text()=\"Health & Personal Care\"]";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Ddrugstore&field-keywords=";
    public String pageTitleHead = "//*[@id=\"nav-subnav\"]/a[1]/span";
    public String globalStore = "//*[@id=\"leftNav\"]/h4[2]";
    public PageNavigator navigator = new PageNavigator();

    public HealthPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void navigateToPage() throws Exception {
        navigator.navigateToPage(driver, "https://www.amazon.co.uk", HealthPage.class);
        driver.findElementByXPath("//*[@id=\"searchDropdownBox\"]").click();
        driver.findElementByXPath("//*[@id=\"searchDropdownBox\"]/option[25]").click();
        driver.findElementByXPath("//*[@id=\"nav-search\"]/form/div[2]/div").click();
    }

    public boolean isLoaded() throws Exception {
        navigateToPage();
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public boolean verifyTitle() throws Exception {

        navigateToPage();
        if(driver.findElement(By.xpath(pageTitleHead)).getText().contains(title)) {
            return true;
        }
        return false;
    }

    public boolean verifyGlobalStore() throws Exception {
        navigateToPage();
        if(driver.findElement(By.xpath(globalStore)).getText().contains("Global Store")){
            return true;
        }
        return false;
    }


    }
