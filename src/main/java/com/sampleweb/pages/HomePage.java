package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
    private static String HOME_PAGE_TITLE = "Amazon";

    public static final String PATH = "https://www.amazon.co.uk/ref=nav_logo";
    protected String searchBoxLocator = "//input[@id='twotabsearchtextbox']";

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement submitButtonLocator;

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getSearchField() {
        return driver.findElement(By.xpath(searchBoxLocator));
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }
}
