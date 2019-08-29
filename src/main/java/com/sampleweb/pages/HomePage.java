package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
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

    public void emptySearch(){
        getSearchField().sendKeys(" ");
        clickSubmitButton();
    }
    public void directToCategory(String value) {
        driver.findElement(By.xpath("//select/option[@value='" + value + "']")).click();
        emptySearch();
    }

    public boolean isTitleCorrect(String title) throws Exception {
        submitButtonLocator.click();
        return verifyTitle(driver.getTitle(), title);
    }
}
