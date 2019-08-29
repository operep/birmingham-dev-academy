package com.sampleweb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@SuppressWarnings("unused")
public class DVDPage extends HomePage {

    public static String TITLE = "DVD & Blu-ray";

    @FindBy(css = ".bxw-pageheader__title__text>h1")
    private WebElement title;
    @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
    private WebElement globalStoreHeading;
    @FindBy(xpath = "//h4[text()='Global Store']")
    private WebElement avgReviewHeading;
    @FindBy(css = "div#nav-subnav>a:first-child>span")
    private WebElement pageLink;
    @FindBy(xpath = "//*[@id='leftNav']/ul[13]/div/li[1]")
    private WebElement primeCheckbox;
    @FindBy(xpath = "//*[@class='s-result-list s-search-results sg-row']/div")
    private List<WebElement> products;

    public DVDPage(RemoteWebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return title.getText();
    }

    public boolean hasGlobalStore() {
        return globalStoreHeading.isDisplayed();
    }

    public boolean hasAvgReview() {
        return avgReviewHeading.isDisplayed();
    }

    public String getPageLinkText() {
        return pageLink.getText();
    }

    public void clickPageLink() {
        pageLink.click();
    }

    public void clickPrimeCheckbox() {
        primeCheckbox.click();
    }

    public List<WebElement> getProductsOnPage() {
        return products;
    }

}
