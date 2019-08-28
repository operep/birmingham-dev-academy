package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends Page {

    public static final String PATH = "https://www.amazon.co.uk/ref=nav_logo";
    protected RemoteWebDriver driver;
    private static String HOME_PAGE_TITLE = "Amazon";
    protected String searchBoxLocator = "//input[@id='twotabsearchtextbox']";
    protected String submitButtonLocator = "//input[@type='submit']";
    protected String getDropDown = "//select[@id='searchDropdownBox']";
    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getSearchField() {
        return driver.findElement(By.xpath(searchBoxLocator));
    }

    public WebElement getDropDown() {
        return driver.findElement(By.xpath(getDropDown));
    }

    public boolean navigateToBabyPageViaDropDown() {
        getDropDown().findElement(By.xpath("//option[text()='Baby']")).click();
        clickSubmitButton();
        return driver.getCurrentUrl().equals(BabyPage.PATH);
    }

    public void clickSubmitButton() {
        driver.findElementByXPath(submitButtonLocator).click();
    }

    public boolean isTitleCorrect(String title) throws Exception {
        return verifyTitle(driver.getTitle(), title);
    }
}