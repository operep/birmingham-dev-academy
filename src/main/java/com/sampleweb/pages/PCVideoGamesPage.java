package com.sampleweb.pages;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PCVideoGamesPage extends HomePage {

  public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dvideogames&field-keywords=";

  ////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////               Web Elements                   ///////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////
  @FindBy(xpath = "//h1[text()='PC and Video Games']")
  private WebElement title;

  @FindBy(name = "url")
  private WebElement dropDown;

  @FindBy(xpath = "//h4[text()='Global Store']")
  private WebElement globalStore;

  @FindBy(xpath = "//h4[text()='Avg. Customer Review']")
  private WebElement avgReviewHeader;

  @FindBy(xpath = "//span[@class='nav-a-content' and contains(text(), 'Video Games')]")
  private WebElement firstNavLink;

  @FindBy(xpath = "//input[@type='checkbox' and @name='s-ref-checkbox-419158031']")
  private WebElement primeCheckBox;

  @FindBy(xpath = "//div[contains(@class, 's-search-results')]/div")
  private List<WebElement> products;

  @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
  private WebElement searchBox;

  @FindBy(xpath = "//span[text() = '4 Stars & Up']")
  private WebElement fourStarReview;

  @FindBy(xpath = "//select[@id='s-result-sort-select']/option[text()='Avg. Customer Review']")
  private WebElement avgReviewSortOption;


  public PCVideoGamesPage(RemoteWebDriver driver) {
    super(driver);
  }

  public boolean isLoaded() {
    return title.isDisplayed();
  }

  public void navigateByDropdown() {
    // Get the dropdown and select the video games item
    Select drop = new Select(dropDown);
    drop.selectByValue("search-alias=videogames");
    // Empty the search box and then search
    searchBox.clear();
    submitButtonLocator.click();
  }

  public boolean hasGlobal() {
    return globalStore.isDisplayed();
  }

  public boolean hasAvgReview() {
    return avgReviewHeader.isDisplayed();
  }

  public boolean hasSameFirstNavLink() {
    return firstNavLink.isDisplayed();
  }

  public boolean firstLinkSameNav() {
    firstNavLink.click();
    return isLoaded();
  }

  public boolean checkPrimeItems() {
    // Check the prime checkbox
    primeCheckBox.click();

    // All the products
    for (WebElement product : products) {
      try {
        // contains the prime label or a span which contains more buying choices
        // don't need to store in a variable, just want it too exist
        product.findElement(By.xpath(
            ".//i[@aria-label='Amazon Prime'] | .//span[contains(text(), 'More buying choices')]"))
               .isDisplayed();
      } catch (NoSuchElementException e) {
        // no prime or more... so fail
        return false;
      }
    }
    return true;
  }

  public boolean checkReviewOrder() {
    fourStarReview.click();

    avgReviewSortOption.click();

    Review previous = new Review();

    for (WebElement product : products) {
      WebElement avgReviewElement = product.findElement(By.xpath(".//span[@class='a-icon-alt']"));

      // Remove possible occurrence of best seller label
      String text = product.getText();
      text = formatText(text);

      // Extract the average review and the number of reviews
      int numReviews = Integer.parseInt(text.split(("\n"))[1]);
      float avgReview = Float.parseFloat(avgReviewElement.getAttribute("innerHTML").split(" ")[0]);
      Review current = new Review(numReviews, avgReview);

      // Compare the review
      if (current.compareTo(previous) <= 0) {
        previous = current;
      } else {
        return false;
      }
    }

    return true;
  }

  private String formatText(String text) {
    text = text.replaceFirst("Best Seller\n", "");
    return text;
  }

  /**
   * Class to compare reviews for products
   */
  private static class Review implements Comparable<Review> {

    /**
     * Number of reviews for the product, -1 by default
     */
    private int numReviews;

    /**
     * Average review for the product, -1 by default
     */
    private float avgReview;

    /**
     * Initialise a review to default values
     */
    Review() {
      numReviews = -1;
      avgReview = -1;
    }

    /**
     * Initialise a review with the passed values. Although no checks are given it is assumed these
     * will be > 0
     *
     * @param numReviews number of reviews for the product
     * @param avgReview  average review for the product
     */
    Review(int numReviews, float avgReview) {
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
    public int hashCode() {
      return Objects.hash(numReviews, avgReview);
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

      // Assuming that reviews are initially based of the average review and if they are the same
      // check the number of reviews
      if (avgReview < previous.avgReview || (avgReview == previous.avgReview
          && numReviews <= previous.numReviews)) {
        return -1;
      }
      return 1;
    }
  }
}
