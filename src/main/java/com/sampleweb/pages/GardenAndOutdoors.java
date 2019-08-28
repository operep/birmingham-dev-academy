package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class GardenAndOutdoors extends HomePage {

    public String title = "Garden & Outdoors";
    public String pageTitle = "//h1[text()=\"Garden & Outdoors\"]";
    public String tabHeader = "//span[@class='nav-a-content'][contains(text(),'Garden & Outdoors')]";
    public String tabHeaderLink = "//a[@class='nav-a nav-b']";
    public String globalStore = "//img[@class='s-ref-img-sprite']";
    public String avgCustomerReviews = "//h4[contains(text(),'Avg. Customer Review')]";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Doutdoor&field-keywords=";

    public GardenAndOutdoors(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public GardenAndOutdoors setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public void navigateHere() {
        setDepartmentDropdown(title);
        setSearchCriteria("").clickSubmitButton();
    }

    public boolean openNewLinkInNewTabAndCheckIfCorrect() {
        String tabLink = driver.findElement(By.xpath(tabHeaderLink)).getAttribute("href");

        ((JavascriptExecutor)driver).executeScript("window.open()");;
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        driver.get(tabLink);

        boolean b;
        try {
            if (isTitleCorrect()) {
                b = true;
            } else {
                b = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        driver.switchTo().window(tabs.get(0)); // switch back to main screen
        driver.switchTo().window(tabs.remove(1));
        return b;
    }

    public boolean isTitleCorrect() throws Exception {
        return verifyTitle(driver.findElement(By.xpath(pageTitle)).getText(), title);
    }

    public boolean isGlobalStore() {
        if (driver.findElement(By.xpath(globalStore)) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isAverageReviewDisplayed() {
        if (driver.findElement(By.xpath(avgCustomerReviews)) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isTitleAndMenuLinkEqual() throws Exception {
        return verifyTitle(driver.findElement(By.xpath(pageTitle)).getText(), driver.findElement(By.xpath(tabHeader)).getText());
    }
}
