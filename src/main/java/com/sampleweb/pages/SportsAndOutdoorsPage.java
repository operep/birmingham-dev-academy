package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class SportsAndOutdoorsPage extends HomePage {

    private String title = "Sports & Outdoors";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dsports&field-keywords=";

    @FindBy(xpath = "//*[@id=\"searchDropdownBox\"]/option[text()=\"Sports & Outdoors\"]")
    private WebElement sportsOnDropDownMenu;
    @FindBy(xpath = "//*[@class=\"nav-search-submit nav-sprite\"]/input")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@id=\"leftNav\"]//*[@name=\"s-ref-checkbox-419158031\"]")
    private WebElement primeCheckbox;
    @FindBy(xpath = "//*[@id=\"leftNav\"]//*[@class=\"a-icon a-icon-star-medium a-star-medium-4\"]/span")
    private WebElement fourStarsOrMore;
    @FindBy(xpath = "//*[@id=\"a-autoid-0-announce\"]")
    private WebElement sortByComboBox;
    @FindBy(xpath = "//*[@id=\"s-result-sort-select_3\"]")
    private WebElement avgReviewInSortByComboBox;


    @FindBy(xpath = "//h1[text()=\"Sports & Outdoors\"]")
    private WebElement pageTitle;
    @FindBy(xpath = "//*[@id=\"leftNav\"]/h4[text()=\"Global Store\"]")
    private WebElement globalStoreLabel;
    @FindBy(xpath = "//*[@id=\"leftNav\"]/h4[text()=\"Avg. Customer Review\"]")
    private WebElement avgCustomerReviewLabel;
    @FindBy(xpath = "//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]/span")
    private WebElement secondLineMenuCategoryName;
    @FindBy(xpath = "//*[@id=\"nav-subnav\"]/a[@class=\"nav-a nav-b\"]")
    private WebElement secondLineMenuCategoryButton;
    @FindBy(xpath = "//*[@class=\"s-result-list s-search-results sg-row\"]//*[@class=\"s-include-content-margin s-border-bottom\"]")
    private List<WebElement> allProductsOnPage;
    @FindBy(xpath = "//i[@class=\"a-icon a-icon-star-small a-star-small-5 aok-align-bottom\"]")
    private List<WebElement> allStarRatingsOnPage;

    private String primeLabelOrMoreBuyingChoices = "//*[@aria-label='Amazon Prime' or @class='a-size-base a-color-secondary']";

    public SportsAndOutdoorsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isOnCorrectPage() { return pageTitle.isDisplayed(); }

    public boolean isGlobalStoreExists() { return globalStoreLabel.isDisplayed(); }

    public boolean isAvgCustomerReviewExists() { return avgCustomerReviewLabel.isDisplayed(); }

    public boolean isPageLinkSameAsCategoryName() { return secondLineMenuCategoryName.getText().contains(title); }

    public boolean isSamePageOnClick(){
        clickOnSecondLineMenuLink();
        return pageTitle.getText().contains(title);
    }

    public boolean primeOrMoreBuyingChoicesExists() {
        selectPrimeOnly();

        Iterator<WebElement> itr = allProductsOnPage.iterator();
        boolean displayedFlag;
        while (itr.hasNext()) {
            displayedFlag = itr.next().findElement(By.xpath(primeLabelOrMoreBuyingChoices)).isDisplayed();
            if (!displayedFlag) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllEntriesAbove4StarThreshold() {
        selectPrimeOnly();
        orderByAvgCustomerReview();

        Iterator<WebElement> itr = allStarRatingsOnPage.iterator();
        double previousRating = 5.0;

        while (itr.hasNext()) {
            double currentRating = Double.parseDouble(itr.next().getText().substring(0,2));
            if (currentRating < 4.0 || currentRating > previousRating){
                return false;
            }
            previousRating = currentRating;
        }
        return true;

    }

    public SportsAndOutdoorsPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public void getSportsURL(){
        sportsOnDropDownMenu.click();
        searchButton.click();
    }

    public void selectPrimeOnly(){
        primeCheckbox.click();
    }

    public void selectFourStarOrAboveOnly(){
        fourStarsOrMore.click();
    }

    public void clickOnSecondLineMenuLink(){
        secondLineMenuCategoryButton.click();
    }

    public void orderByAvgCustomerReview(){
        sortByComboBox.click();
        avgReviewInSortByComboBox.click();
    }

}
