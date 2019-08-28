package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class CdVinylPage extends HomePage {

    public final static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dpopular&field-keywords=";

    public final String TITLE = "CDs & Vinyl";
    public final String PAGE_TITLE_PATH = "//h1[text()=\"CDs & Vinyl\"]";
    public final String GLOBAL_STORE_CHECKBOX_PATH = "//input[@name=\"s-ref-checkbox-11259240031\"]";
    public final String CD_VINYL_LINK_PATH = "//header/div/div/a[4]";
    //label contains checkbox and image. Check within label that the image contains class with word prime.
    //If its good then the checkbox must be prime's.
    public final String PRIME_CHECKBOX_PATH = "//ul/div/li/span/span/div/label[.//span/i[contains(@class, \"prime\")]]/input[@type=\"checkbox\"]";

    public CdVinylPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        final String PAGE_TITLE_PATH = "//h1[text()=\"CDs & Vinyl\"]";
        return driver.findElement(By.xpath(PAGE_TITLE_PATH)).isDisplayed();
    }

    public CdVinylPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public boolean isExistsTitle() {
        return driver.getTitle().contains(TITLE);
    }

    public boolean isExistsGlobalStore() {
        final String GLOBAL_STORE_CHECKBOX_PATH = "//input[@name=\"s-ref-checkbox-11259240031\"]";
        WebElement storeElement = driver.findElementByXPath(GLOBAL_STORE_CHECKBOX_PATH);
        return storeElement.isDisplayed();
    }

    public boolean isExistsAverageCustomerReview() {
        //Looks for label
        WebElement reviewElementTitle = driver.findElementByXPath("//h4[text() = \"Avg. Customer Review\"]");

        //Only stars are in a unordered list with icons
        WebElement starElementList = driver.findElementByXPath("//ul/div/li/span/a/i");

        return reviewElementTitle.isDisplayed() && starElementList.isDisplayed();
    }

    public boolean isExistsFourthLinkHeader() {

        WebElement linkElement  = driver.findElementByXPath(CD_VINYL_LINK_PATH);
        String elementText = linkElement.getText();
        return elementText.equals(TITLE) && linkElement.isDisplayed();
    }

    public boolean isExistsFourthLinkRedirect() {
        driver.findElementByXPath(CD_VINYL_LINK_PATH).click();

        //Same title means on the same page
        return isExistsTitle();
    }
    public boolean isPrimeCheckboxVisibleAndInItems() {
        //label contains checkbox and image. Check within label that the image contains class with word prime.
        //If its good then the checkbox must be prime's.
        String PRIME_CHECKBOX_PATH = "//ul/div/li/span/span/div/label[.//span/i[contains(@class, \"prime\")]]/input[@type=\"checkbox\"]";
        WebElement primeCheckbox = driver.findElementByXPath(PRIME_CHECKBOX_PATH);
        final String ITEM_CONTAINER_PATH= "//div[@data-asin]";
        primeCheckbox.click();

        //Xpath goes through div and data-asin, according to amazon data-asin is a identification number
        //https://www.nchannel.com/blog/amazon-asin-what-is-an-asin-number/ so each section should have one for each track
        //Search for prime image.
        //Checks prime on CD, Vinyl, mp3.

        List<WebElement> items = driver.findElementsByXPath(ITEM_CONTAINER_PATH);

        //Iterate within item to find prime image class
        int size = (int) driver.findElementsByXPath(ITEM_CONTAINER_PATH)
                .stream()
                .filter(e -> e.findElement(By.xpath(ITEM_CONTAINER_PATH + "//i[contains(@class, \"prime\")]")).isDisplayed())
                .count();

        return items.size() == size;
    }
}
