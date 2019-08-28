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

    protected String departmentDropDownLocator = "//select[@id='searchDropdownBox']";

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

    public void setDepartmentDropdown(String option) {
        new Select(getDepartmentDropdownBox()).selectByVisibleText(option);
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }
}
