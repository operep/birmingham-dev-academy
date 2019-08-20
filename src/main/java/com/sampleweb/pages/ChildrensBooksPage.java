package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChildrensBooksPage extends HomePage {

    public String title = "Children's Books";
    public String pageTitle = "//h1[text()=\"Children's Books\"]";
    public static String PATH = "https://www.amazon.co.uk/childrens-books/b?ie=UTF8&node=69&ref_=sd_allcat_bo_cbo";

    public ChildrensBooksPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    public ChildrensBooksPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

}
