package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ClassicalMusicPage extends HomePage {

    public static String title = "Classical";

    @FindBy(xpath = "//h4[text()='Global Store']")
    private WebElement globalSection;
    @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
    private WebElement avgReviewSection;
    @FindBy(xpath = "//div[@id='nav-subnav']")
    private WebElement categoryLink;

    @FindBy(xpath = "//div[@class='s-result-list s-search-results sg-row']/div")
    private List<WebElement> products;
    @FindBy(xpath = "//h4[text()='Global Store']/parent::*//input")
    public WebElement globalStoreCheckbox;

    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dclassical&field-keywords=";

    public ClassicalMusicPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return pageTitle.isDisplayed();
    }

    public ClassicalMusicPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public WebElement getGlobalSection() {
        return globalSection;
    }

    public WebElement getAvgReviewSection() {
        return avgReviewSection;
    }

    public WebElement getCategoryLink() {
        return categoryLink;
    }

    public void followCategoryLink() {
        categoryLink.click();
    }

    public void activateGlobalStoreCheckbox() {
        globalStoreCheckbox.click();
    }

    public List<WebElement> getAllPrimeElements() {
        return products;
    }
}
