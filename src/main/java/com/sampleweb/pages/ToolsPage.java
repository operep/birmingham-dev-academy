package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToolsPage extends HomePage {
    public static String TITLE = "DIY & Tradesmen Store";
    public static String PATH = "https://www.amazon.co.uk/s?i=diy&k=&ref=nb_sb_noss&url=search-alias%3Ddiy";

    @FindBy(xpath = "//option[@value='search-alias=diy']")
    private WebElement dropdownTitle;

    @FindBy(xpath = "//h1[text()='DIY & Tradesmen Store']")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@id='nav-search-dropdown-card']//option[@selected='selected']")
    private WebElement tabTitle;

    @FindBy(xpath = "//div[@id='leftNav']//h4[text()='Global Store']")
    private WebElement globalSection;

    @FindBy(xpath = "//div[@id='leftNav']//h4[text()='Avg. Customer Review']")
    private WebElement reviewSection;

    @FindBy(xpath = "//div[@class='s-result-list s-search-results sg-row']")
    private List<WebElement> resultsList;

    @FindBy(xpath = "//input[@name='s-ref-checkbox-419158031']")
    private WebElement primeCheckbox;

    @FindBy(xpath="//span[text()='4 Stars & Up']")
    private WebElement fourStarsReview;

    @FindBy(xpath="//span[@id='a-autoid-0-announce']")
    private WebElement filterDropdownMenu;

    @FindBy(xpath="//a[text()='Avg. Customer Review']")
    private WebElement customerReviewFilter;

    @FindBy(xpath="//span[@class='a-declarative']//span[@class='a-icon-alt']")
    private List<WebElement> starRatings;

    @FindBy(xpath="//a[@class='a-link-normal']//span[@class='a-size-base']")
    private List<WebElement> numberOfRatings;

    public ToolsPage(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the dropdown menu where all the categories are.
     */
    public void clickDropdownMenu() {
        dropdownTitle.click();
    }

    /**
     * Clicks the prime check box on the left side of the page.
     */
    public void clickPrimeCheckbox() {
        primeCheckbox.click();
    }

    /**
     * Clicks the four stars and up section on the left side of the page.
     */
    public void clickFourStarsAndUpReview() {
        fourStarsReview.click();
    }

    /**
     * Clicks the drop down menu which contains filters for sorting.
     */
    public void clickDropDownContainer() {
        filterDropdownMenu.click();
    }

    /**
     * Clicks the average review filter from the drop down menu.
     */
    public void clickAverageReviewFilter() {
        customerReviewFilter.click();
    }

    /**
     * Checks if the title of the page is equal to the expected one.
     * @return <CODE>true</CODE> if titles are equal.
     * <CODE>false</CODE> otherwise.
     */
    public boolean isTitleCorrect() {
        return pageTitle.getText().equals(TITLE);
    }

    /**
     * Checks if the title of the page is equal to the title of the selected category.
     * @return <CODE>true</CODE> if titles are equal.
     * <CODE>false</CODE> otherwise.
     */
    public boolean isTitleMatch() {
        return pageTitle.getText().equals(tabTitle.getText());
    }

    /**
     * Checks if the page contains a global section.
     * @return <CODE>true</CODE> if global section exists.
     * <CODE>false</CODE> otherwise.
     */
    public boolean isGlobalSection() {
        return globalSection.isDisplayed();
    }

    /**
     * Checks if the page contains a review section.
     * @return <CODE>true</CODE> if review section exists.
     * <CODE>false</CODE> otherwise.
     */
    public boolean isReviewSection() {
        return reviewSection.isDisplayed();
    }

    /**
     * Checks if the URL is the same after selecting the same category from a different section of the page.
     * @return <CODE>true</CODE> if the URLs are equal.
     * <CODE>false</CODE> otherwise.
     */
    public boolean isLinkTheSame() {
        String initialURL = driver.getCurrentUrl();
        tabTitle.click();
        String finalURL = driver.getCurrentUrl();

        return initialURL.equals(finalURL);
    }

    /**
     * Checks if the prime label exists on all the items from the prime section.
     * @return <CODE>true</CODE> if the prime label exists on all the items.
     * <CODE>false</CODE> otherwise.
     */
    public boolean isPrimeLabel() {
        clickPrimeCheckbox();
        String primeLabel = "//*[@aria-label='Amazon Prime']";

        Iterator<WebElement> itr = resultsList.iterator();
        boolean displayed = true;
        while(itr.hasNext()) {
            displayed = itr.next().findElement(By.xpath(primeLabel)).isDisplayed();
            if (!displayed) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the items are sorted accordingly by star reviews.
     * @return <CODE>true</CODE> if the items are sorted.
     * <CODE>false</CODE> otherwise.
     */
    public boolean isPageSortedByStarReviews() {
        clickFourStarsAndUpReview();
        clickDropDownContainer();
        clickAverageReviewFilter();

        // Get the number of ratings for each item
        List<Integer> nrOfRatings = new ArrayList<Integer>();
        for (int i = 0; i < numberOfRatings.size(); i++) {
            nrOfRatings.add(Integer.parseInt(numberOfRatings.get(i).getText().replace(",", "")));
        }

        // Get all the star ratings from the page and retrieve only the numbers needed for comparison
        List<Double> ratings = new ArrayList<Double>();
        for (int i = 0; i < starRatings.size(); i++) {
            String[] starRatingsSplit = starRatings.get(i).getAttribute("innerText").split(" ");
            ratings.add(Double.parseDouble(starRatingsSplit[0]));
        }

        for (int i = 0; i < ratings.size() - 1; i++) {
            if (ratings.get(i) < ratings.get(i + 1) &&
                nrOfRatings.get(i) <= nrOfRatings.get(i + 1)) {
                return false;
            }
        }

        return true;
    }
}
