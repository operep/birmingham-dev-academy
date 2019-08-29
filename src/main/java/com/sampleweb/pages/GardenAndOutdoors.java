package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class GardenAndOutdoors extends HomePage {


    private String pageTitle = "//h1[text()='Garden & Outdoors']";
    private String tabHeader = "//span[@class='nav-a-content'][contains(text(),'Garden & Outdoors')]";
    private String tabHeaderLink = "//a[@class='nav-a nav-b']";
    private String globalStore = "//img[@class='s-ref-img-sprite']";
    private String avgCustomerReviews = "//h4[contains(text(),'Avg. Customer Review')]";
    private String primeCheckbox = "//input[@name='s-ref-checkbox-419158031']";
    private String primeResultsList = "//div[@class='s-result-list s-search-results sg-row']";
    private String primeLabelOrMoreBuyingChoices = "//*[@aria-label='Amazon Prime' or @class='a-size-base a-color-secondary']";

    public static String TITLE = "Garden & Outdoors";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Doutdoor&field-keywords=";

    public GardenAndOutdoors(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
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
        String tabLink = driver.findElement(By.xpath(tabHeaderLink)).getAttribute("href");

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
        return verifyTitle(driver.findElement(By.xpath(pageTitle)).getText(), TITLE);
    }

    public boolean isGlobalStore() {
        return driver.findElement(By.xpath(globalStore)) != null;
    }

    public boolean isAverageCustomerReviewDisplayed() {
        return driver.findElement(By.xpath(avgCustomerReviews)) != null;
    }

    public boolean isTitleAndMenuLinkEqual() throws Exception {
        return verifyTitle(driver.findElement(By.xpath(pageTitle)).getText(), driver.findElement(By.xpath(tabHeader)).getText());
    }

    public void makePrimeCheckboxChecked() {
        driver.findElement(By.xpath(primeCheckbox)).click();
    }

    public boolean isOnlyPrimeItems() {
        makePrimeCheckboxChecked();

        return driver.findElements(By.xpath(primeResultsList)).iterator().next().findElement(By.xpath(primeLabelOrMoreBuyingChoices)).isDisplayed();
    }
}
