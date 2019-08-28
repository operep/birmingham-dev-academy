package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class GardenAndOutdoors extends HomePage {

    public String title = "Garden & Outdoors";
    public String pageTitle = "//h1[text()=\"Garden & Outdoors\"]";
    public String tabHeader = "//span[@class='nav-a-content'][contains(text(),'Garden & Outdoors')]";
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

    public boolean isTitleCorrect() throws Exception {
        return verifyTitle(driver.findElement(By.xpath(tabHeader)).getText(), title);
    }
}
