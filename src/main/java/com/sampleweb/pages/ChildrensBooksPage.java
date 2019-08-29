package com.sampleweb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class ChildrensBooksPage extends HomePage {

    @FindBy(xpath = "//h1[text()=\"Children's Books\"]")
    private WebElement pageTitle;

    public static String PATH = "https://www.amazon.co.uk/childrens-books/b?ie=UTF8&node=69&ref_=sd_allcat_bo_cbo";

    public ChildrensBooksPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return pageTitle.isDisplayed();
    }

    public ChildrensBooksPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }
}
