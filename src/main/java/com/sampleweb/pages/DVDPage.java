package com.sampleweb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@SuppressWarnings("unused")
public class DVDPage extends HomePage {

    public static String TITLE = "DVD & Blu-ray";

    @FindBy(css = ".bxw-pageheader__title__text>h1")
    public WebElement title;

    @FindBy(css = "div#nav-subnav>a:first-child>span")
    public WebElement pageLink;

    @FindBy(xpath = "//*[@id='leftNav']//i[contains(@class, 'a-icon-prime')]")
    public WebElement primeCheckbox;

    @FindBy(xpath = "//*[@class='s-result-list s-search-results sg-row']/div")
    public List<WebElement> products;

    @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
    private WebElement avgReviewHeading;

    @FindBy(xpath = "//h4[text()='Global Store']")
    private WebElement globalStoreHeading;

    @FindBy(xpath = "//*[@class='s-result-list s-search-results sg-row']//span[@class='a-icon-alt']")
    private List<WebElement> productAvgReviewSpans;

    public DVDPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean hasGlobalStore() {
        return globalStoreHeading.isDisplayed();
    }

    public boolean hasAvgReview() {
        return avgReviewHeading.isDisplayed();
    }

    }

}
