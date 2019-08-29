package com.sampleweb.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class Page {

    protected RemoteWebDriver driver;

    @FindBy(className = "header__topnavigation__nav-tab lslide active") protected WebElement headerMenu;

    public Page(final RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected String getElementText(By selector) {
        try {
            return driver.findElement(selector).getText();
        } catch (StaleElementReferenceException ex) {
            return getElementText(selector);
        }
    }

    protected Boolean isElementVisible(By selector) {
        try {
            return driver.findElement(selector).isDisplayed();
        } catch (StaleElementReferenceException ex) {
            return isElementVisible(selector);
        }
    }

    protected void clickElement(By selector) {
        try {
            driver.findElement(selector).click();
        } catch (StaleElementReferenceException ex) {
            clickElement(selector);
        }
    }

    protected WebElement getElement(By selector) {
        try {
            return driver.findElement(selector);
        } catch (StaleElementReferenceException ex) {
            return getElement(selector);
        }
    }

    protected boolean verifyTitle(String actual, String expected) throws Exception {
        if(actual.contains(expected)){
            return true;
        }

        throw new Exception("Page verification failed: "
                + String.format("\n Expected: %s page, \n Found: %s page", expected, actual)
        );
    }
}
