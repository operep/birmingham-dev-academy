package com.sampleweb.pages;

import com.sun.imageio.plugins.wbmp.WBMPImageReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;

public class HomeKitchen extends HomePage {

    public String title = "Home & Kitchen";
    public String secondMenuTitle = "Home & Garden";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dkitchen&field-keywords=";
    private String globalStoreBoxLocator = "//input[@name=\"s-ref-checkbox-11259240031\"]";
    private String globalStoreLabelLocator = "//img[@class=\"s-ref-img-sprite\"]";
    private String averageCustomerReviewLocator ="//*[@id=\"leftNav\"]/ul[9]";
    public String secondMenuLocator = "//*[@id=\"nav-subnav\"]/a[1]/span";
    private String primeProductListLocator = "//*[@class=\"s-result-list s-search-results sg-row\"]//*[@class=\"s-include-content-margin s-border-bottom\"]";
    private String reviewProductListLocator = "//div[@class=\"sg-col-20-of-24 s-result-item sg-col-0-of-12 sg-col-28-of-32 sg-col-16-of-20 sg-col sg-col-32-of-36 sg-col-12-of-16 sg-col-24-of-28\"]";
    private String starReviewLocator = "//span[@class=\"a-icon-alt\"]";
    private String primeLabelOrMoreLocator = "//*[@aria-label=\'Amazon Prime\' or @class=\'a-size-base a-color-secondary\']";
    private String primeCheckBoxLocator = "//input[@name=\"s-ref-checkbox-419158031\"]";
    private String sortByLocator = "//*[@id=\"s-result-sort-select\"]";
    private String reviewNumber = "//span[@class=\"a-size-base\"]";


    public HomeKitchen(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.findElement(By.xpath(title)).isDisplayed();
    }

    public boolean isTitleCorrect(String title) throws Exception {
        return verifyTitle(driver.getTitle(), title);
    }

    @FindBy(xpath = "//*[@id=\"leftNav\"]/ul[9]/div/li[1]/span/a")
    protected WebElement averageCustomerReviewLinkLocator;

    @FindBy(xpath = "")
    protected WebElement sortBy;

    public HomeKitchen directToKitchen() {
        directToCategory("search-alias=kitchen");
        return this;
    }

        public boolean isGlobalStoreSectionThere() {
            try {
                driver.findElement(By.xpath(globalStoreBoxLocator));
                driver.findElement(By.xpath(globalStoreLabelLocator));
                return true;
            } catch (org.openqa.selenium.NoSuchElementException e) {
                return false;
            }
        }

    public boolean isAverageCustomerReviewThere(){
        try {
            driver.findElement(By.xpath(averageCustomerReviewLocator));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPrimeCorrect(){
        driver.findElement(By.xpath(primeCheckBoxLocator)).click();
        Iterator<WebElement> iterator = driver.findElements(By.xpath(primeProductListLocator)).iterator();
        while(iterator.hasNext()){
            WebElement currWebElement = iterator.next();
            if (!currWebElement.findElement(By.xpath(primeLabelOrMoreLocator)).isDisplayed()){
                return false;
            }
        }
        return true;
    }

    public boolean isSubMenuCorrect(){
        String menuText = driver.findElement(By.xpath(secondMenuLocator)).getText();
        if (menuText.equals(secondMenuTitle)) {
            return true;
        }
        return false;
    }

    public void goToSortHighReviews(){
        averageCustomerReviewLinkLocator.click();
        driver.findElement(By.xpath("//select/option[@value= \"review-rank\"]")).click();
    }

    public boolean checkSort(){
        goToSortHighReviews();
        boolean check = false;
        Iterator<WebElement> ReviewIterator = driver.findElements(By.xpath(starReviewLocator)).iterator();
        Iterator<WebElement> NumberIterator = driver.findElements(By.xpath(reviewNumber)).iterator();
        ReviewIterator.next();
        ReviewIterator.next();
        ReviewIterator.next();
        ReviewIterator.next();
        double prevRating = Double.parseDouble(ReviewIterator.next().getAttribute("innerHTML").substring(0, 2));
        int prevNumber = Integer.parseInt(NumberIterator.next().getAttribute("innerHTML"));
        while(ReviewIterator.hasNext()){
            double currRating = Double.parseDouble(ReviewIterator.next().getAttribute("innerHTML").substring(0, 2));
            int currNumber = Integer.parseInt(NumberIterator.next().getAttribute("innerHTML"));
            if(!((currRating == prevRating && currNumber<prevNumber) || (currRating < prevRating))){
                return false;
            }
        }
        return true;
    }
}
