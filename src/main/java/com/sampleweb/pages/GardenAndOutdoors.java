package com.sampleweb.pages;

import com.sampleweb.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import java.lang.Exception;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class GardenAndOutdoors extends HomePage {

    @FindBy(xpath = "//h1[text()='Garden & Outdoors']")
    protected WebElement pageTitle;

    @FindBy(xpath = "//span[@class='nav-a-content'][contains(text(),'Garden & Outdoors')]")
    protected WebElement tabHeader;

    @FindBy(xpath = "//a[@class='nav-a nav-b']")
    protected WebElement tabHeaderLink;

    @FindBy(xpath = "//img[@class='s-ref-img-sprite']")
    protected WebElement globalStore;

    @FindBy(xpath = "//h4[contains(text(),'Avg. Customer Review')]")
    protected WebElement avgCustomerReviews;

    @FindBy(xpath = "//i[contains(@class,'a-icon a-icon-star-medium')]")
    protected List<WebElement> allCustomerReviews;

    @FindBy(xpath = "//select[@id='s-result-sort-select']")
    protected WebElement sortByDropdown;

    @FindBy(xpath = "//input[@name='s-ref-checkbox-419158031']")
    protected WebElement primeCheckbox;

    @FindBy(xpath = "//div[@class='s-result-list s-search-results sg-row']/div")
    protected List<WebElement> itemResultsList;

    @FindBy(xpath = "//*[@aria-label='Amazon Prime' or @class='a-size-base a-color-secondary']")
    protected WebElement primeLabelOrMoreBuyingChoices;

    @FindBy(xpath = "//h2[contains(text(),'customer reviews')]")
    protected WebElement amountOfReviews;

    @FindBy(xpath = "//span[@class='arp-rating-out-of-text a-color-base']")
    protected WebElement overallRating;

    @FindBy(xpath = "//a[@class='a-size-base a-link-normal 5star histogram-review-count a-color-secondary']")
    protected WebElement fiveStarRatings;

    @FindBy(xpath = "//a[@class='a-size-base a-link-normal 4star histogram-review-count a-color-secondary']")
    protected WebElement fourStarRatings;

    @FindBy(xpath = "//a[@class='a-size-base a-link-normal 3star histogram-review-count a-color-secondary']")
    protected WebElement threeStarRatings;

    @FindBy(xpath = "//a[@class='a-size-base a-link-normal 2star histogram-review-count a-color-secondary']")
    protected WebElement twoStarRatings;

    @FindBy(xpath = "//a[@class='a-size-base a-link-normal 1star histogram-review-count a-color-secondary']")
    protected WebElement oneStarRatings;

    public static String TITLE = "Garden & Outdoors";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Doutdoor&field-keywords=";

    private ArrayList<String> tabs;

    public GardenAndOutdoors(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return pageTitle.isDisplayed();
    }

    public GardenAndOutdoors setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public GardenAndOutdoors navigateToGardenAndOutdoorsPage() {
        setDepartmentDropdown(TITLE);
        setSearchCriteria("").clickSubmitButton();
        return this;
    }

    public boolean openNewLinkInNewTabAndCheckIfCorrect() throws Exception {
        String tabLink = tabHeaderLink.getAttribute("href");
        openNewTab(tabLink);
        boolean correctTitle = isTitleCorrect();
        closeNewTab();
        return correctTitle;
    }

    public boolean isTitleCorrect() throws Exception {
        return verifyTitle(pageTitle.getText(), TITLE);
    }

    public boolean isGlobalStore() {
        return globalStore != null;
    }

    public boolean isAverageCustomerReviewDisplayed() {
        return avgCustomerReviews != null;
    }

    public boolean isTitleAndMenuLinkEqual() throws Exception {
        return verifyTitle(pageTitle.getText(), tabHeader.getText());
    }

    public GardenAndOutdoors makePrimeCheckboxChecked() {
        primeCheckbox.click();
        return this;
    }

    public boolean isOnlyPrimeItems() {
        List<WebElement> products = itemResultsList;
        for (WebElement product: products) {
            if (!(primeLabelOrMoreBuyingChoices.isDisplayed())) {
                return false;
            }
        }

        return true;
    }

    //TODO - BELOW
    //Click highest avg customer review rating - DONE
    //order by rating - DONE
    //check orders are in order of rating
    public GardenAndOutdoors selectHighestReviewLevel() {
        allCustomerReviews.get(0).click();
        return this;
    }

    public GardenAndOutdoors orderByRating() {
        new Select((sortByDropdown)).selectByVisibleText("Avg. Customer Review");
        return this;
    }

    private List<Product> initProductItemsFromItemList() {
        ArrayList<Product> products = new ArrayList<Product>();
        List<WebElement> xpathProducts = itemResultsList;

        for (WebElement product: xpathProducts) {
            Product p = new Product();
            p.setName(product.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']")).getText());
            p.setPath(product.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']/parent::a")).getAttribute("href"));

            openNewTab(p.getPath());

            p.setAmountOfReviews(formatAmountOfReviewsAndOverallRating(amountOfReviews.getText()));
            p.setOverallRating(formatAmountOfReviewsAndOverallRating(overallRating.getText()));

            try {
                p.setFiveStarRatings(formatStarRatings(fiveStarRatings.getText()));
            } catch (Exception e) {
                p.setFiveStarRatings(0);
            }

            try {
                p.setFourStarRatings(formatStarRatings(fourStarRatings.getText()));
            } catch (Exception e) {
                p.setFourStarRatings(0);
            }

            try {
                p.setThreeStarRatings(formatStarRatings(threeStarRatings.getText()));
            } catch (Exception e) {
                p.setThreeStarRatings(0);
            }

            try {
                p.setTwoStarRatings(formatStarRatings(twoStarRatings.getText()));
            } catch (Exception e) {
                p.setTwoStarRatings(0);
            }

            try {
                p.setOneStarRatings(formatStarRatings(oneStarRatings.getText()));
            } catch (Exception e) {
                p.setOneStarRatings(0);
            }

            closeNewTab();

            products.add(p);
        }

        return products;
    }

    public boolean isItemsSortedByOrder() {
        initProductItemsFromItemList();
        return false;
    }

    private void openNewTab(String newLink) {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        driver.get(newLink);
    }

    private void closeNewTab() {
        driver.switchTo().window(tabs.get(1)).close();
        driver.switchTo().window(tabs.get(0));
    }

    private double formatAmountOfReviewsAndOverallRating(String preFormattedAmountOfReviews) {
        return Double.parseDouble(preFormattedAmountOfReviews.split(" ")[0]);
    }

    private double formatStarRatings(String preFormattedStarRatings) {
        if (preFormattedStarRatings == null) {
            return 0;
        }
        return Double.parseDouble(preFormattedStarRatings.trim().replace("%", ""));
    }
}
