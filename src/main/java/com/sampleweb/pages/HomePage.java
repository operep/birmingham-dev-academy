package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    protected RemoteWebDriver driver;

    protected String departmentDropDownLocator = "//select[@id='searchDropdownBox']";
    protected String searchBoxLocator = "//input[@id='twotabsearchtextbox']";

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement submitButtonLocator;

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getDepartmentDropdownBox() {
        return driver.findElement(By.xpath(departmentDropDownLocator));
    }

    public WebElement getSearchField() {
        return driver.findElement(By.xpath(searchBoxLocator));
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }
}
