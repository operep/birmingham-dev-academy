package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class GiftCardsPage extends HomePage {
    private String title = "Gift Cards & Top Up";
    private static String PATH = "https://www.amazon.co.uk/Giftcards-Giftvouchers-Vouchers-Birthday-Gifts/b/?ie=UTF8&node=1571304031&ref_=topnav_storetab_gc";

    public GiftCardsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public static String getPath(){
        return PATH;
    }

    /**
     * Check page has loaded correctly after navigating to the gift cards page from the amazon home screen
     */
    public boolean isLoaded() {
        //Starting from home page rather than PATH
        PATH = "https://www.amazon.co.uk";
        driver.get(PATH);
        selectGiftCardsFromDropdown();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[1]"));
        return element.isDisplayed() && verifyGiftCardPageTitle();
    }

    /**
     * Navigate to gift card page using drop down list
     */
    private void selectGiftCardsFromDropdown(){
        String dropdownOptionPath = "//*[@id=\"searchDropdownBox\"]";
        driver.findElement((By.xpath(dropdownOptionPath))).click();
        Select select = new Select(driver.findElement(By.xpath(dropdownOptionPath)));
        select.selectByValue("search-alias=gift-cards");
        driver.findElement((By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"))).click();
    }

    /**
     * Verify that gift card page title is correct
     * @return true if card page title is correct
     */
    public boolean verifyGiftCardPageTitle() {
        try{
            //Verify title is correct
            return super.verifyTitle(title, driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[1]/span")).getText());
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
        String globalStoreSelectionPath = "//h4[text()=\"Global Store\"]";

        //True if exists
        return checkIfElementExists(globalStoreSelectionPath);
    }


    /**
     * Verify that average customer review section exists on the page
     * @return true if section exists
     */
    public boolean verifyGiftCardPageCustomerReviewExists(){
        String customerReviewPath = "//h4[text()=\"Avg. Customer Review\"]";

        //True if exists
        return checkIfElementExists(customerReviewPath);
    }

    /**
     * Verify that after clicking on the first link in second line menu user is being redirected to the same page
     * @return true if redirected to same page
     */
    public boolean verifyGiftCardMenuButtonRedirection() {
        WebElement navBarTitle = driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[1]/span"));
        navBarTitle.click();
        String updatedUrl = driver.getCurrentUrl();
        System.out.println(PATH);
        System.out.println(updatedUrl);
        if(PATH.equals(updatedUrl)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify that all items from the 'prime' checkbox results list have prime label in description
     * Excludes items with 'more buying choices' line
     * @return true if all items have prime label
     */
//    public boolean verifyPrimeCheckboxResultsList(){
//
//        return false;
//    }

    /**
     * Checks if element exists on gift cards page
     * @param elementXPath - xpath of element to be checked
     * @return true if exists
     */
    private boolean checkIfElementExists(String elementXPath){
        return driver.findElements(By.xpath(elementXPath)).size() != 0;
    }
}
