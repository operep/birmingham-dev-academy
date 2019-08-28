package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChildrensBooksPage extends HomePage {

    public String title = "Luxury Beauty";
    public String pageTitle = "//img[@alt=\"Luxury Beauty\"]";
    public String selectCategory = "//select[@class=\"nav-search-dropdown\"]";
    public String globalStore = "//input[@name=\"s-ref-checkbox-11259240031\"]";

//  public String pageTitle = "//h1[text()=\"Children's Books\"]";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dluxury-beauty&field-keywords=";

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

    public String getTitle() { return this.title;}

    public String getPath() {return PATH;}

}
