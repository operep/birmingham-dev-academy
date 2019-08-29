package com.sampleweb.pages;

import com.sampleweb.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        List<Product> products = null;
        List<WebElement> xpathProducts = itemResultsList;

        for (WebElement product: xpathProducts) {
            WebElement p1 = product;
            Product p = new Product();
            p.setName(p1.findElement(By.xpath("//span[contains(text(),'Dokon Rectangular Garden Table Cover, Waterproof B')]")).getText());
            p.setPath(p1.findElement(By.xpath("//span[contains(text(),'Dokon Rectangular Garden Table Cover, Waterproof B')]/parent::a")).getAttribute("href"));

            openNewTab(p.getPath());

            //TODO - find values below
            p.setAmountOfReviews(0);
            p.setOverallRating(0);
            p.setFiveStarRatings(0);
            p.setFourStarRatings(0);
            p.setThreeStarRatings(0);
            p.setTwoStarRatings(0);
            p.setOneStarRatings(0);
            // //h2[contains(text(),'customer reviews')] - then split this to find 1st string
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
        driver.switchTo().window(tabs.get(0)); // switch back to main screen
        driver.switchTo().window(tabs.remove(1));
    }
}
