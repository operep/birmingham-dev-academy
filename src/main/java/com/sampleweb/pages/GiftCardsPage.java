package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GiftCardsPage extends HomePage {
    private String title = "Gift Cards & Top Up";
    public static String PATH = "https://www.amazon.co.uk/Giftcards-Giftvouchers-Vouchers-Birthday-Gifts/b/?ie=UTF8&node=1571304031&ref_=topnav_storetab_gc";

    @FindBy(xpath = "//*[@id='searchDropdownBox']")
    private WebElement dropdownOption;
    @FindBy(xpath = "//h4[text()='Global Store']")
    private List<WebElement> globalStoreSelection;
    @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
    private List<WebElement> customerReview;
    @FindBy(xpath = "//*[@name='s-ref-checkbox-419158031']")
    private WebElement primeCheckbox;
    @FindBy(xpath = "//*[contains(@class, 's-result-list s-search-results')]/*")
    private List<WebElement> searchResults;
    @FindBy(xpath = "//*[@id='nav-subnav']/a[1]/span")
    private WebElement giftCardMenuButton;
    @FindBy(xpath = "//*[@id='nav-search']/form/div[2]/div/input")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@id='nav-subnav']/a[1]")
    private WebElement pageTitle;
    @FindBy(xpath = "//*[@class='a-icon a-icon-star-medium a-star-medium-4']")
    private WebElement fourStarsElement;
    @FindBy(xpath = "//*[@class='a-dropdown-label'][text()='Sort by:']")
    private WebElement sortByDropdownElement;
    @FindBy(xpath = "//*[@class='a-dropdown-link'][text()='Avg. Customer Review']")
    private WebElement avgCustomerReviewOption;

    private String starRatingPath = ".//i[contains(@class, 'a-icon a-icon-star-small a-star-small')]";
    private String productNumOfReviewsPath = ".//*[@class='a-size-base']";

    public GiftCardsPage(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Check page has loaded correctly after navigating to the gift cards page from the amazon home screen
     */
    public boolean isPageLoaded() {
        selectGiftCardsFromDropdown();
        return pageTitle.isDisplayed();

    }

    /**
     * Navigate to gift card page using drop down list
     */
    private GiftCardsPage selectGiftCardsFromDropdown(){
        clickElement(dropdownOption);
        Select select = new Select(dropdownOption);
        select.selectByValue("search-alias=gift-cards");
        clickElement(searchButton);
        return this;
    }

    /**
     * Verify that gift card page title is correct
     * @return true if card page title is correct
     */
    public boolean verifyGiftCardPageTitle() {
        try{
            return super.verifyTitle(title, pageTitle.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Verify that global store section exists
     * @return true if global store selection exists
     */
    public boolean verifyGiftCardPageGlobalStoreSelectionExists(){
        return checkIfElementExists(globalStoreSelection);
    }

    /**
     * Verify that average customer review section exists on the page
     * @return true if section exists
     */
    public boolean verifyGiftCardPageCustomerReviewExists(){
        return checkIfElementExists(customerReview);
    }

    /**
     * Verify that after clicking on the first link in second line menu user is being redirected to the same page
     * @return true if redirected to same page
     */
    public boolean verifyGiftCardMenuButtonRedirection() {
        clickElement(giftCardMenuButton);
        return PATH.equals(driver.getCurrentUrl());
    }

    /**
     * Click button using util class
     * @param webElement element to be clicked
     */
    private GiftCardsPage clickElement(WebElement webElement){
        webElement.click();
        return this;
    }

    /**
     * Verify that all items from the 'prime' checkbox results list have prime label in description
     * Excludes items with 'more buying choices' line
     * @return true if all items have prime label
     */
    public boolean verifyPrimeCheckboxResultsList(){
        clickElement(primeCheckbox);
        boolean isDisplayed = true;
        for(WebElement result : searchResults) {
            isDisplayed = result.findElement(By.className("a-icon-prime")).isDisplayed();
        }
        return isDisplayed;
    }

    /**
     * Checks if element exists on gift cards page
     * @return true if exists
     */
    private boolean checkIfElementExists(List<WebElement> element){
        return element.size() != 0;
    }

    /**
     * Verifies that the order of items filtered by four star customer rating and
     * ordered by avg. customer review is correct
     * @return true if order is correct
     */
    public boolean verifyFourStarRatingOrderCorrect(){
        clickElement(fourStarsElement);
        sortByAvgCustomerReview();
        boolean isOrderCorrect = true;
        double prevStarValue = 0;
        int prevNumOfReviews = 0;

        for(WebElement result : searchResults){
            WebElement starRating = result.findElement(By.xpath(starRatingPath));
            WebElement numOfReviews = result.findElement(By.xpath(productNumOfReviewsPath));

            double thisStarValue = Double.parseDouble(starRating.getAttribute("innerText").split(" ")[0]);
            int thisNumOfReviewsValue = Integer.parseInt(numOfReviews.getAttribute("innerHTML").replace(",",""));

            if(thisStarValue > prevStarValue){
                isOrderCorrect = false;
            } else if(thisNumOfReviewsValue < prevNumOfReviews) {
                isOrderCorrect = true;
            }

            prevStarValue = thisStarValue;
            prevNumOfReviews = thisNumOfReviewsValue;
        }
        return isOrderCorrect;
    }

    /**
     * Sort items on page by average customer review
     */
    private GiftCardsPage sortByAvgCustomerReview(){
        clickElement(sortByDropdownElement);
        clickElement(avgCustomerReviewOption);
        return this;
    }
}