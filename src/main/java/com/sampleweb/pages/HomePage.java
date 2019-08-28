package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
    private static String HOME_PAGE_TITLE = "Amazon";
    protected String searchBoxLocator = "//input[@id='twotabsearchtextbox']";
    protected String submitButtonLocator = "//input[@type='submit']";

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getSearchField() {
        return driver.findElement(By.xpath(searchBoxLocator));
    }

    public void clickSubmitButton() {
        driver.findElementByXPath(submitButtonLocator).click();
    }

    public boolean isTitleCorrect() throws Exception {
        return verifyTitle(driver.getTitle(), HOME_PAGE_TITLE);
    }
}