package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    protected RemoteWebDriver driver;
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    protected WebElement searchBox;

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement submitButton;

    private static String HOME_PAGE_TITLE = "Amazon";
    public static String PATH = "https://www.amazon.co.uk/";

    @FindBy(xpath = "//select[@id='searchDropdownBox']/option[@value='search-alias=classical']")
    protected WebElement categorySelector;

    @FindBy(xpath = "//h1")
    protected WebElement pageTitle;

    public String getTitle() {
        return pageTitle.getText();
    }

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement getSearchField() {
        return searchBox;
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public HomePage setSearchCategoryClassical() {
        categorySelector.click();
        return this;
    }
}
