package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class ElectronicsPhotoPage extends HomePage {

    protected String title = "Electronics & Photo";
    protected String pageTitle = "//h1[text()='The Electronics Store']";

    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Delectronics&field-keywords=";
    public static String HOME_PATH = "https://www.amazon.co.uk/ref=nav_logo";

    protected String electronicsCategoryDropdown = "//*[@id=\"searchDropdownBox\"]/option[text()='Electronics & Photo']";
    protected String globalStoreCategory = "//h4[text()='Global Store']";
    protected String avgCustomerReviewCategory = "//h4[text()='Avg. Customer Review']";
    protected String secondLineLink = "//*[@class='nav-a-content' and contains(text(),'Electronics')]";
    protected String primeCheckbox = "//*[@class='a-icon a-icon-prime a-icon-small s-ref-text-link']";
    protected String listOfElements = "//*[@class='s-result-list s-search-results sg-row']/div";
    protected String labelsInDescription = "//*[@aria-label='Amazon Prime' or text()='FREE Delivery by Amazon']";


    public ElectronicsPhotoPage(RemoteWebDriver driver) {
        super(driver);
    }

    // 1
    public boolean isElectronicsCategorySelected(){
        return driver.findElement(By.xpath(electronicsCategoryDropdown)).isSelected();
    }

    // 1
    public boolean isLoaded(){
        selectElectronicsCategory();
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
    }

    // 2
    public boolean isGlobalStore(){
        return driver.findElement(By.xpath(globalStoreCategory)).isDisplayed();
    }

    // 3
    public boolean isAvgCustomerReview(){
        return driver.findElement(By.xpath(avgCustomerReviewCategory)).isDisplayed();
    }

    // 4
    public boolean isSecondLineLink(){
        return driver.findElement(By.xpath(secondLineLink)).isDisplayed();
    }

    // 5
    public boolean checkTitleAfterReload(){
        return getInitialSecondLineLinkTitle().equals(getReloadSecondLineLinkTitle());
    }

    // 6
    public void checkPrimeLables(){
        checkPrimeCheckbox();
        List<WebElement> elements = driver.findElements(By.xpath(listOfElements));
        for (WebElement el : elements) {
            el.findElement(By.xpath(labelsInDescription));
        }
    }

    /*
    helper functions
     */

    private void checkPrimeCheckbox(){
        driver.findElement(By.xpath(primeCheckbox)).click();
    }


    // gets the title of the initial page
    private String getInitialSecondLineLinkTitle(){
        return driver.getTitle();
    }

    // gets the tile of the reloaded page
    private String getReloadSecondLineLinkTitle(){
        driver.findElementByXPath(secondLineLink).click();
        return driver.getTitle();
    }

    // selects the Electronics & Photo category from the dropdown
    public void selectElectronicsCategory(){
        driver.findElementByXPath(electronicsCategoryDropdown).click();
        emptySearch();
    }

    // executes empty search
    private void emptySearch(){
        getSearchField().sendKeys("");
        clickSubmitButton();
    }
}
