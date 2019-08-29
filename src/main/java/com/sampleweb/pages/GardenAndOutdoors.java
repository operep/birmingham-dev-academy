package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class GardenAndOutdoors extends HomePage {

    @FindBy(xpath = "//h1[text()='Garden & Outdoors']")
    protected WebElement pageTitle;

    @FindBy(xpath = "//span[@class='nav-a-content'][contains(text(),'Garden & Outdoors')]")
    protected WebElement tabHeader;

    @FindBy(xpath = "//a[@class='nav-a nav-b']")
    protected WebElement tabHeaderLink;

    @FindBy(xpath = "//img[@class='s-ref-img-sprite']")
    protected WebElement globalStore;

    @FindBy(xpath = "//h4[contains(text(),'Avg. Customer Review')]")
    protected WebElement avgCustomerReviews;

    @FindBy(xpath = "//input[@name='s-ref-checkbox-419158031']")
    protected WebElement primeCheckbox;

    @FindBy(xpath = "//div[@class='s-result-list s-search-results sg-row']/div")
    protected List<WebElement> primeResultsList;

    @FindBy(xpath = "//*[@aria-label='Amazon Prime' or @class='a-size-base a-color-secondary']")
    protected WebElement primeLabelOrMoreBuyingChoices;

    public static String TITLE = "Garden & Outdoors";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Doutdoor&field-keywords=";

    public GardenAndOutdoors(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return pageTitle.isDisplayed();
    }

    public GardenAndOutdoors setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public void navigateToGardenAndOutdoorsPage() {
        setDepartmentDropdown(TITLE);
        setSearchCriteria("").clickSubmitButton();
    }

    public boolean openNewLinkInNewTabAndCheckIfCorrect() throws Exception {
        String tabLink = tabHeaderLink.getAttribute("href");

        ((JavascriptExecutor)driver).executeScript("window.open()");;
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        driver.get(tabLink);

        boolean correctTitle = isTitleCorrect();

        driver.switchTo().window(tabs.get(0)); // switch back to main screen
        driver.switchTo().window(tabs.remove(1));

        return correctTitle;
    }

    public boolean isTitleCorrect() throws Exception {
        return verifyTitle(pageTitle.getText(), TITLE);
    }

    public boolean isGlobalStore() {
        return globalStore != null;
    }

    public boolean isAverageCustomerReviewDisplayed() {
        return avgCustomerReviews != null;
    }

    public boolean isTitleAndMenuLinkEqual() throws Exception {
        return verifyTitle(pageTitle.getText(), tabHeader.getText());
    }

    public void makePrimeCheckboxChecked() {
        primeCheckbox.click();
    }

    public boolean isOnlyPrimeItems() {
        makePrimeCheckboxChecked();

        List<WebElement> products = primeResultsList;
        for (WebElement product: products) {
            if (!(primeLabelOrMoreBuyingChoices.isDisplayed())) {
                return false;
            }
        }

        return true;
    }

    //TODO - BELOW
    //Click highest avg customer review rating
    //order by rating
    //check orders are in order of rating
}
