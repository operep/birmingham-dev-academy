package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MusicalInstrumentsPage extends HomePage {

    public static String HOMEPATH = "https://www.amazon.co.uk";
    public static String PATH = "https://www.amazon.co.uk/musical-instruments/b/?ie=UTF8&node=340837031&ref_=topnav_storetab_MI";

    @FindBy(xpath = "//h1/b[text()='Musical Instruments & DJ Equipment']")
    private WebElement pageTitle;
    @FindBy(xpath = "//select[@id='searchDropdownBox']/option[text()='Musical Instruments & DJ']")
    private WebElement selectCategoryFromMenu;
    @FindBy(xpath = "//h4[text()='Global Store']")
    private WebElement globalStore;
    @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
    private WebElement avgCustomerScore;
    @FindBy(xpath = "//*[@class='nav-a-content' and contains(text(),'Musical Instruments')]")
    private WebElement firstLinkInSecondLineMenu;
    @FindBy(xpath = "//*[@class='a-icon a-icon-prime a-icon-small s-ref-text-link']")
    private WebElement primeCheckbox;

    @FindBy(xpath = "//*[@class='s-result-list s-search-results sg-row']/div")
    private List<WebElement> results;

    private String primeResultsLabels = ".//i[@aria-label='Amazon Prime'] | .//span[text()='FREE Delivery by Amazon']";

    @FindBy(xpath = "//*[@class='a-icon-alt' and text()='4 Stars & Up']")
    private WebElement fourStarsAndUp;

    @FindBy(xpath = "//select[@id='s-result-sort-select']/option[text()='Avg. Customer Review']")
    private WebElement sortByAvgReview;

    private String reviewStars = ".//span[@class='a-icon-alt']";

    public MusicalInstrumentsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public MusicalInstrumentsPage SelectCategoryFromMenu() {
        selectCategoryFromMenu.click();
        clickSubmitButton();
        return this;
    }

    public boolean isTitleCorrect() {
        return pageTitle.isDisplayed();
    }

    public boolean hasGlobalStore() {
        return globalStore.isDisplayed();
    }

    public boolean hasAvgCustomerScore() {
        return avgCustomerScore.isDisplayed();
    }

    public boolean isFirstLinkInSecondLineValid() {
        return firstLinkInSecondLineMenu.isDisplayed();
    }

    public boolean clickFirstLinkInSecondLineRedirect() {
        if (isFirstLinkInSecondLineValid()) {
            String initialTitle = driver.getTitle();
            firstLinkInSecondLineMenu.click();
            return (driver.getTitle().equals(initialTitle));
        } else return false;
    }

    public void checkPrimeBoxVerifyPrimeLabels() {
        primeCheckbox.click();
        for (WebElement res : results) {
            res.findElement(By.xpath(primeResultsLabels));
        }
    }

    public boolean filterByReviews() {
        fourStarsAndUp.click();
        sortByAvgReview.click();

        Review previous = new Review();

        for (WebElement res : results) {
            WebElement avgReviewElement = res.findElement(By.xpath(reviewStars));
            System.out.println(Arrays.toString(res.getText().split("\n")));
            String text = res.getText().replaceFirst("Best Seller\n", "");
            int numReviews = Integer.parseInt(text.split("\n")[1]);
            float avgReview = Float.parseFloat(avgReviewElement.getAttribute("innerHTML").split(" ")[0]);
            Review current = new Review(numReviews, avgReview);

            if (current.compareTo(previous) <= 0) {
                previous = current;
            } else {
                return false;
            }
        }
        return true;
    }

    private class Review implements Comparable<Review> {

        private int numReviews;
        private float avgReview;

        public Review() {
            numReviews = -1;
            avgReview = -1;
        }

        public Review(int numReviews, float avgReview) {
            this.numReviews = numReviews;
            this.avgReview = avgReview;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Review review = (Review) o;
            return numReviews == review.numReviews &&
                    Float.compare(avgReview, review.avgReview) == 0;
        }

        @Override
        public String toString() {
            return "Review{" +
                    "numReviews=" + numReviews +
                    ", avgReview=" + avgReview +
                    '}';
        }

        @Override
        public int compareTo(Review previous) {
            if (previous.numReviews == -1 || numReviews == -1) {
                return -1;
            }
            if (this.equals(previous)) {
                return 0;
            }
            if (avgReview < previous.avgReview || (avgReview == previous.avgReview
                    && numReviews <= previous.numReviews)) {
                return -1;
            }
            return 1;
        }
    }
}
