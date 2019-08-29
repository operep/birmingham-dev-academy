package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
<<<<<<< HEAD
import org.openqa.selenium.support.FindBy;
=======
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f

import java.util.List;

public class ElectronicsPhotoPage extends HomePage {

<<<<<<< HEAD
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

=======
    protected String title = "Electronics & Photo";
    protected String pageTitle = "//h1[text()='The Electronics Store']";

    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Delectronics&field-keywords=";
    public static String HOME_PATH = "https://www.amazon.co.uk/ref=nav_logo";

    protected String electronicsCategoryDropdown = "//*[@id=\"searchDropdownBox\"]/option[text()='Electronics & Photo']";
    protected String globalStoreCategory = "//h4[text()='Global Store']";
    protected String avgCustomerReviewCategory = "//h4[text()='Avg. Customer Review']";
    protected String secondLineLink = "//*[@class='nav-a-content' and contains(text(),'Electronics')]";
    protected String primeCheckbox = "//*[@class='a-icon a-icon-prime a-icon-small s-ref-text-link']";
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
    protected String listOfElements = "//*[@class='s-result-list s-search-results sg-row']/div";
    protected String labelsInDescription = "//*[@aria-label='Amazon Prime' or text()='FREE Delivery by Amazon']";


    public ElectronicsPhotoPage(RemoteWebDriver driver) {
        super(driver);
    }

    // 1
    public boolean isElectronicsCategorySelected(){
<<<<<<< HEAD
        return electronicsCategoryDropdown.isSelected();
=======
        return driver.findElement(By.xpath(electronicsCategoryDropdown)).isSelected();
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
    }

    // 1
    public boolean isPageLoaded(){
<<<<<<< HEAD
        return pageTitle.isDisplayed();
=======
        return driver.findElement(By.xpath(pageTitle)).isDisplayed();
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
    }

    // 2
    public boolean isGlobalStoreDisplayed(){
<<<<<<< HEAD
        return globalStoreCategory.isDisplayed();
=======
        return driver.findElement(By.xpath(globalStoreCategory)).isDisplayed();
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
    }

    // 3
    public boolean isAvgCustomerReviewDisplayed(){
<<<<<<< HEAD
        return avgCustomerReviewCategory.isDisplayed();
=======
        return driver.findElement(By.xpath(avgCustomerReviewCategory)).isDisplayed();
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
    }

    // 4
    public boolean isSecondLineLinkDisplayed(){
<<<<<<< HEAD
        return secondLineLink.isDisplayed();
=======
        return driver.findElement(By.xpath(secondLineLink)).isDisplayed();
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
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
<<<<<<< HEAD
        primeCheckbox.click();
=======
        driver.findElement(By.xpath(primeCheckbox)).click();
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
    }

    // gets the title of the initial page
    private String getInitialSecondLineLinkTitle(){
        return driver.getTitle();
    }

    // gets the tile of the reloaded page
    private String getReloadSecondLineLinkTitle(){
<<<<<<< HEAD
        secondLineLink.click();
=======
        driver.findElementByXPath(secondLineLink).click();
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
        return driver.getTitle();
    }

    // selects the Electronics & Photo category from the dropdown
    public void selectElectronicsCategory(){
<<<<<<< HEAD
        electronicsCategoryDropdown.click();
        search("");
    }

    // executes empty search
    private void search(String searchCriteria){
        getSearchField().sendKeys(searchCriteria);
=======
        driver.findElementByXPath(electronicsCategoryDropdown).click();
        emptySearch();
    }

    // executes empty search
    private void emptySearch(){
        getSearchField().sendKeys("");
>>>>>>> 056d250ec5d5dd867ab09fb8229543a3f749979f
        clickSubmitButton();
    }
}
