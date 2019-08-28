package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MusicalInstrumentsPage extends HomePage {

    public static String HOMEPATH = "https://www.amazon.co.uk";
    public static String PATH = "https://www.amazon.co.uk/musical-instruments/b/?ie=UTF8&node=340837031&ref_=topnav_storetab_MI";

    @FindBy(xpath = "//h1/b[text()='Musical Instruments & DJ Equipment']") private WebElement pageTitle;
    @FindBy(xpath = "//*[@id='searchDropdownBox']/option[text()='Musical Instruments & DJ']") private WebElement selectCategoryFromMenu;
    @FindBy(xpath  = "//h4[text()='Global Store']") private WebElement globalStore;
    private String avgCustomerScore = "//h4[text()='Avg. Customer Review']";
    private String firstLinkInSecondLineMenu = "//*[@class='nav-a-content' and contains(text(),'Musical Instruments')]";
    private String primeCheckbox = "//*[@class='a-icon a-icon-prime a-icon-small s-ref-text-link']";
    private String primeResults = "//*[@class='s-result-list s-search-results sg-row']/div";
    private String primeResultsLabels = ".//i[@aria-label='Amazon Prime'] | .//span[text()='FREE Delivery by Amazon']";
    private String fourStarsAndUp = "//*[@class='a-icon-alt' and text()='4 Stars & Up']";

    public MusicalInstrumentsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public MusicalInstrumentsPage SelectCategoryFromMenu() {
        selectCategoryFromMenu.click();
        clickSubmitButton();
        return this;
    }

    public boolean isTitleCorrect() {
        return pageTitle.isDisplayed();
    }

    public boolean hasGlobalStore() {
        return globalStore.isDisplayed();
    }

    public boolean hasAvgCustomerScore() {
        return driver.findElement(By.xpath(avgCustomerScore)).isDisplayed();
    }

    public boolean isFirstLinkInSecondLineValid() {
        return driver.findElement(By.xpath(firstLinkInSecondLineMenu)).isDisplayed();
    }

    public boolean clickFirstLinkInSecondLineRedirect() {
        if (isFirstLinkInSecondLineValid()) {
            String initialTitle = driver.getTitle();
            driver.findElement(By.xpath(firstLinkInSecondLineMenu)).click();
            return (driver.getTitle().equals(initialTitle));
        } else return false;
    }

    public void checkPrimeBoxVerifyPrimeLabels() {
        driver.findElement(By.xpath(primeCheckbox)).click();
        List<WebElement> results = driver.findElements(By.xpath(primeResults));
        for (WebElement child : results) {
            child.findElement(By.xpath(primeResultsLabels));
        }
    }

    public void filterByReviews() {
        driver.findElement(By.xpath(fourStarsAndUp)).click();
    }
}
