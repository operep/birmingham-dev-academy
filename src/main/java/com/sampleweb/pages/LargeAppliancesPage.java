package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LargeAppliancesPage extends HomePage {


    public String title = "Large Appliances";

    public String pageTitleLocator = "//b[text()=\"Large Appliances\"]";
    public String globalStoreBoxLocator = "//input[@name=\"s-ref-checkbox-11259240031\"]";
    public String largeAppliancesDropDownLocator="//select[@name=\'url\']//option[@value=\'search-alias=appliances\']";
    public String globalStoreLabelLocator = "//img[@class=\"s-ref-img-sprite\"]";
    public String subMenuLinkLocator = "//*[@id=\"nav-subnav\"]/a[1]/span";
    public String primeCheckBoxLocator = "//input[@name=\"s-ref-checkbox-419158031\"]";

    protected String CustomerReviewLocator = "//*[@id=\"leftNav\"]/h4[6]";

    public static String HOME_PATH="https://www.amazon.co.uk";
    public static String LARGE_APPLIANCES_PATH="https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dappliances&field-keywords=";

    public LargeAppliancesPage(RemoteWebDriver driver) {
        super(driver);
    }

    public LargeAppliancesPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);
        return this;
    }

    public boolean isLoaded() { return driver.findElement(By.xpath(pageTitleLocator)).isDisplayed(); }

    public boolean selectLargeAppliancesDropDown(){
        driver.findElement(By.xpath(largeAppliancesDropDownLocator)).click();
        emptySearch();
        return isTitleCorrect();
    }

    public boolean isTitleCorrect(){ return driver.findElement(By.xpath(pageTitleLocator)).isDisplayed(); }

    public void emptySearch(){
        getSearchField().sendKeys("");
        clickSubmitButton();
    }

    public boolean globalStoreExists(){
        boolean checkBoxPresent =  driver.findElement(By.xpath(globalStoreBoxLocator)).isDisplayed();
        boolean labelPresent = driver.findElement(By.xpath(globalStoreLabelLocator)).isDisplayed();
        if(labelPresent && checkBoxPresent){
            return true;
        }
        return false;
    }

    public boolean CustomerReviewExists(){
        return driver.findElement(By.xpath(CustomerReviewLocator)).isDisplayed();
    }

    public boolean isSubMenuCorrect(){
        String menuText = driver.findElement(By.xpath(subMenuLinkLocator)).getText();
        if (menuText.equals(title)) {
            return true;
        }
        return false;
    }

    public boolean isSubMenuNavigationCorrect(){
        driver.findElement(By.xpath(subMenuLinkLocator)).click();
        if(isTitleCorrect()){
            return true;
        }
        return false;
    }

    public boolean isPrimeCorrect(){
        boolean isPrime;
        boolean isMore;

        driver.findElement(By.xpath(primeCheckBoxLocator)).click();

        for(int i = 0; i < 24; i++){
            isPrime =  driver.findElement(By.xpath("//div[@class=\"s-result-list s-search-results sg-row\"]//div[@data-index=\'" + i + "\']//i[@class=\"a-icon a-icon-prime a-icon-medium\"]")).isDisplayed();
            if(!isPrime){
                isMore = driver.findElement(By.xpath("//div[@class=\"s-result-list s-search-results sg-row\"]//div[@data-index=\'"+ i +"\']//span[@class=\"a-size-base a-color-secondary\"]")).isDisplayed();
                if(!isMore){
                    return false;
                }
            }
        }
        return true;

    }

}
