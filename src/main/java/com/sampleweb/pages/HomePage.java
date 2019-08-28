package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
    protected String searchBoxLocator = "//input[@id='twotabsearchtextbox']";

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement submitButtonLocator;
    protected String categoryDropdownLocator = "//*[@id=\"searchDropdownBox\"]";

    public static String PATH = "https://www.amazon.co.uk/ref=nav_logo";


    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getSearchField() {
        return driver.findElement(By.xpath(searchBoxLocator));
    }

    public void selectDropdownCategory(String categoryName) {
        driver.findElementByXPath(categoryDropdownLocator + "/option[contains(text(), '"+ categoryName +"')]").click();
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }
}
