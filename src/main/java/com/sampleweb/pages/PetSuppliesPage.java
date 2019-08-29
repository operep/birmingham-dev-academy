package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class PetSuppliesPage extends HomePage{

    public String title = "Pet Supplies";
    @FindBy(xpath = "//h1[text()=\"Pet Supplies\"]")
    public WebElement pageTitle ;
    @FindBy(xpath = "//*[@id='leftNav']/h4[contains(text(), 'Global Store')]")
    public WebElement globalStoreTitle;
    @FindBy(xpath = "//*[@id='leftNav']/h4[contains(text(), 'Customer Review')]")
    public WebElement customerReviewTitle;
    @FindBy(xpath = "//*[@id='leftNav']/h4[contains(text(), 'Customer Review')]/following-sibling::ul[1]/div/li")
    public List<WebElement> customerReviewStarList;
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dpets&field-keywords=";
    @FindBy(xpath = "//*[@id=\"nav-subnav\"]/a[1]/span")
    public WebElement firstLinkInSecondMenu;

    @FindBy(xpath = "//*[@id=\"leftNav\"]/ul[6]/div/li[1]/span/span/div/label/span/i")
    public WebElement primeCheckbox;
    public String primeElement = "//*[@class=\"a-icon a-icon-prime a-icon-medium\"]";
    public String moreBuyingOptions = "//span[contains(text(), \"More buying choices\")]";
    public String primeOrMoreOptions = primeElement + " | " + moreBuyingOptions;
    public PetSuppliesPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return pageTitle.isDisplayed();
    }

    public PetSuppliesPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public boolean isGlobalStoreCheckboxDisplayed(){
        return globalStoreTitle != null;
    }

    public boolean isAverageCustomerRatingTitleDisplayed(){
        return customerReviewTitle != null;
    }

    public boolean isAverageCustomerRatingFieldsDisplayed(){
        return customerReviewStarList.size() == 4;
    }

    public boolean isFirstLinkInSecondMenuSameAsTitleTest(){
        return firstLinkInSecondMenu.getText().equals("Pet Supplies");
    }

    public void clickOnFirstLinkInSecondMenu(){
        firstLinkInSecondMenu.click();
    }

    public void clickOnPrimeCheckbox(){
        primeCheckbox.click();
    }

    public boolean checkOnlyPrimeItemsAreAvailable(){
        Iterator<WebElement> resultsIterator = driver.findElementsByXPath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div").iterator();
        int count = 0;
        while(resultsIterator.hasNext()){
            WebElement result = resultsIterator.next();
            if(!result.findElement(By.xpath(primeOrMoreOptions)).isDisplayed())
                return false;
        }
        return true;
    }


}
