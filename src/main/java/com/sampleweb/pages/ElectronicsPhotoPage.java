package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
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

    @FindBy(xpath = "//*[@class='a-icon a-icon-star-medium a-star-medium-4']")
    protected WebElement fiveStarsReviews;

    @FindBy(xpath = "//*[@class=\'a-color-state a-text-bold\' and contains(text(), \'4 Stars & Up\')]")
    protected WebElement fourStartsAndUpTitle;

    @FindBy(xpath = "//*[@class=\"aok-inline-block a-spacing-none\"]")
    protected WebElement sortDropdown;

    @FindBy(xpath = "//*[@id='s-result-sort-select_3' and contains(text(), \"Avg. Customer Review\")]")
    protected WebElement sortDropdownAvgCustomerReview;

    @FindBy(xpath = "//*[@class=\"a-dropdown-prompt\"]")
    protected WebElement currentSortCriteria;

    protected String reviewList = "//*[@class='s-result-list s-search-results sg-row']//span[@class='a-icon-alt']";

    public ElectronicsPhotoPage(RemoteWebDriver driver) {
        super(driver);
    }

    // 1
    public boolean isElectronicsCategorySelected(){
        return electronicsCategoryDropdown.isSelected();
    }

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
        selectPrimeCheckbox();
        for (WebElement el : getListings()) {
            el.findElement(By.xpath(labelsInDescription));
        }
    }

    // 7
    public boolean isFiveStarsSelected(){
        selectFiveStarsCriteria();
        return fourStartsAndUpTitle.isDisplayed();
    }

    public boolean isAvgCustomerReviewSortSelected(){
        selectAvgCustomerReviewSortCriteria();
        return currentSortCriteria.getText().equals("Avg. Customer Review");
    }

    public List<Float> getReviewScores() {
        setUpForReviewChecking();
        List<Float> list = new ArrayList<>();

        for (WebElement el : getReviewList()) {
            Float rev = Float.valueOf(el.getAttribute("innerHTML").split(" ")[0]);
            list.add(rev);
        }
        return list;
    }

    /*
    helper functions
     */

    private List<WebElement> getReviewList(){
        return driver.findElements(By.xpath(reviewList));
    }

    // setup for the selecting 4 starts and up and sort by avg. customer review
    public void setUpForReviewChecking(){
        selectAvgCustomerReviewSortCriteria();
    }

    // select Avg. Customer Review as sort criteria
    private void selectAvgCustomerReviewSortCriteria(){
        selectSortByDropdown();
        sortDropdownAvgCustomerReview.click();
    }

    // select drop down for sorting to see sorting options
    private void selectSortByDropdown(){
        selectFiveStarsCriteria();
        sortDropdown.click();
    }

    // select 4 stars and up criteria
    private void selectFiveStarsCriteria(){
        fiveStarsReviews.click();
    }

    // get listings on page
    private List<WebElement> getListings(){
        return driver.findElements(By.xpath(listOfElements));
    }

    // select prime checkbox
    private void selectPrimeCheckbox(){
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
