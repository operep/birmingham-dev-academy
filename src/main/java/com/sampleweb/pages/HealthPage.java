package com.sampleweb.pages;

import com.sampleweb.framework.PageNavigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;
import java.nio.file.WatchEvent;
import java.util.List;

public class HealthPage extends HomePage {

    private String title = "Health & Personal Care";
    private PageNavigator navigator = new PageNavigator();
    private static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Ddrugstore&field-keywords=";

    @FindBy(xpath = "//*[@id=\"merchandised-content\"]/div[1]/div[1]/div/h1[text()='Health & Personal Care']")
    private WebElement healthPageTitle;

    @FindBy(xpath = "//*[@id=\"nav-subnav\"]/a[1]/span")
    private WebElement healthPageNavigationHeader;

    @FindBy(xpath = "//*[@id='searchDropdownBox']/option[25]")
    private WebElement dropDownMenuHealthNavigation;

    @FindBy(xpath = "//*[@id=\"leftNav\"]/h4[2]")
    private WebElement healthPageGlobalStore;

    @FindBy(xpath = "//*[@id=\"searchDropdownBox\"]")
    private WebElement dropDownMenu;

    @FindBy(xpath = "//*[@id=\"nav-search\"]/form/div[2]/div")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"leftNav\"]/h4[8]")
    private WebElement healthPageAverageReviewDisplay;

    @FindBy(xpath = "//*[@id=\"leftNav\"]/ul[3]/div/li/span/span/div/label/input")
    private WebElement healthPagePrimeCheckBox;

    @FindBy(xpath = "//*[@class='a-section aok-relative s-image-fixed-height']")
    private List<WebElement> healthPagePrimeImages;

    @FindBy(xpath = "//*[@id='leftNav']/ul[9]/div/li[1]")
    private WebElement fourStarReviewButton;

    @FindBy(xpath = "//*[@id=\"a-autoid-0-announce\"]")
    private WebElement sortByButton;

    @FindBy(xpath = "//*[@id='s-result-sort-select_3']")
    private WebElement sortByReviewButton;

    @FindBy(xpath = "//*[@class='a-icon-alt'][contains(text(), 'out of')]")
    private List<WebElement> ratings;

    @FindBy(xpath = "//*[@class='a-size-base']")
    private List<WebElement> reviews;

    private String primeIcon = "//*[@class='a-icon a-icon-prime a-icon-medium']";

    public HealthPage(RemoteWebDriver driver) {
        super(driver);
    }

    private void navigateToHealthPage() throws Exception {
        navigator.navigateToPage(driver, "https://www.amazon.co.uk", HealthPage.class);
        dropDownMenu.click();
        dropDownMenuHealthNavigation.click();
        searchButton.click();
    }

    public boolean isHealthPageLoaded() throws Exception {
        navigateToHealthPage();
        return healthPageTitle.isDisplayed();
    }

    public boolean verifyGlobalStoreExists() throws Exception {
        navigateToHealthPage();
        return healthPageGlobalStore.getText().contentEquals("Global Store");
    }

    public boolean verifyAverageReviewsExist() throws Exception {
        navigateToHealthPage();
        return healthPageAverageReviewDisplay.getText().contentEquals("Avg. Customer Review");
    }

    public boolean verifyFirstLinkHasCatagoryName() throws Exception {
        navigateToHealthPage();
        return healthPageNavigationHeader.getText().contentEquals(title);
    }

    public boolean verifyClickOnHeaderMenuDirectsToSamePage() throws Exception {
        navigateToHealthPage();
        healthPageNavigationHeader.click();
        return driver.getCurrentUrl().contentEquals(PATH);
    }

    public boolean verifySelectPrimeReturnsOnlyPrimeitems() throws Exception {
        navigateToHealthPage();
        healthPagePrimeCheckBox.click();
        Boolean isDisplayed = false;

        for (WebElement image : healthPagePrimeImages) {
            isDisplayed = image.findElement(By.xpath(primeIcon)).isDisplayed();
            if (!isDisplayed) {
                break;
            }
        }

        return isDisplayed;
    }

    public boolean verifyAverageRatingsSortedCorrectly() throws Exception {
        navigateToHealthPage();
        fourStarReviewButton.click();
        sortByButton.click();
        sortByReviewButton.click();

        double newStarRating;
        double oldStarRating = Double.parseDouble(ratings.get(0).getAttribute("innerText").substring(0,3));
        int oldReviewRating = Integer.parseInt(reviews.get(0).getAttribute("innerText"));
        int newReviewRating;
        boolean correctOrder = false;


        for (WebElement rating: ratings){
            System.out.println("work?" + rating.getAttribute("innerText").substring(0, 3));
            newStarRating = Double.parseDouble(rating.getAttribute("innerText").substring(0, 3));
            newReviewRating = Integer.parseInt(reviews.get(ratings.indexOf(rating)).getAttribute("innerText"));

            if(newStarRating <= oldStarRating) {
                correctOrder = true;

            }
            else if(newReviewRating <= oldReviewRating){
                correctOrder = true;
            }
            else{
                return false;
            }

            oldStarRating = newStarRating;
            oldReviewRating = newReviewRating;
        }
        return correctOrder;
    }
}
