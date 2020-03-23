package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.Select;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
    protected String searchBoxLocator = "//input[@id='twotabsearchtextbox']";
    public static String PATH = "https://www.amazon.co.uk/ref=nav_logo";

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement submitButtonLocator;

    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    protected WebElement departmentDropDownLocator;

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getDepartmentDropdownBox() {
        return departmentDropDownLocator;
    }

    public WebElement getSearchField() {
        return driver.findElement(By.xpath(searchBoxLocator));
    }

    public void setDepartmentDropdown(String option) {
        new Select(getDepartmentDropdownBox()).selectByVisibleText(option);
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }
}
