package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ElectronicsPhotoPage extends HomePage {

    private String title = "Electronics & Photo";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Delectronics&field-keywords=";
    public static String HOME_PATH = "https://www.amazon.co.uk/ref=nav_logo";

    @FindBy(xpath = "//h1[text()='The Electronics Store']")
    protected WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"searchDropdownBox\"]/option[text()='Electronics & Photo']")
    protected WebElement electronicsCategoryDropdown;

    @FindBy(xpath = "//h4[text()='Global Store']")
    protected WebElement globalStoreCategory;

    @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
    protected WebElement avgCustomerReviewCategory;

    @FindBy(xpath = "//*[@class='nav-a-content' and contains(text(),'Electronics')]")
    protected WebElement secondLineLink;

    @FindBy(xpath = "//*[@class='a-icon a-icon-prime a-icon-small s-ref-text-link']")
    protected WebElement primeCheckbox;

    protected String listOfElements = "//*[@class='s-result-list s-search-results sg-row']/div";
    protected String labelsInDescription = "//*[@aria-label='Amazon Prime' or text()='FREE Delivery by Amazon']";


    public ElectronicsPhotoPage(RemoteWebDriver driver) {
        super(driver);
    }

    // 1
    public boolean isElectronicsCategorySelected(){
        return electronicsCategoryDropdown.isSelected();
    }

    // 1
    public boolean isPageLoaded(){
        return pageTitle.isDisplayed();
    }

    // 2
    public boolean isGlobalStoreDisplayed(){
        return globalStoreCategory.isDisplayed();
    }

    // 3
    public boolean isAvgCustomerReviewDisplayed(){
        return avgCustomerReviewCategory.isDisplayed();
    }

    // 4
    public boolean isSecondLineLinkDisplayed(){
        return secondLineLink.isDisplayed();
    }

    // 5
    public boolean isTitleSameAfterReload(){
        return getInitialSecondLineLinkTitle().equals(getReloadSecondLineLinkTitle());
    }

    // 6
    public void checkPrimeLabels(){
        checkPrimeCheckbox();
        for (WebElement el : getListings()) {
            el.findElement(By.xpath(labelsInDescription));
        }
    }

    /*
    helper functions
     */

    private List<WebElement> getListings(){
        return driver.findElements(By.xpath(listOfElements));
    }

    private void checkPrimeCheckbox(){
        primeCheckbox.click();
    }

    // gets the title of the initial page
    private String getInitialSecondLineLinkTitle(){
        return driver.getTitle();
    }

    // gets the tile of the reloaded page
    private String getReloadSecondLineLinkTitle(){
        secondLineLink.click();
        return driver.getTitle();
    }

    // selects the Electronics & Photo category from the dropdown
    public void selectElectronicsCategory(){
        electronicsCategoryDropdown.click();
        search("");
    }

    // executes empty search
    private void search(String searchCriteria){
        getSearchField().sendKeys(searchCriteria);
        clickSubmitButton();
    }
}
