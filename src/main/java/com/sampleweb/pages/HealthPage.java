package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthPage extends HomePage{

    public String title = "Health & Personal Care";
    public String pageTitle = "//h1[text()=\"Health & Personal Care\"]";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Ddrugstore&field-keywords=";
    public String pageTitleHead = "//*[@id=\"nav-subnav\"]/a[1]/span";

    public HealthPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public boolean verifyTitle() {
        if(driver.findElement(By.xpath(pageTitleHead)).getText().contains(title)) {
            return true;
        }
        return false;
    }

    
    }
