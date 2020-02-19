package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LargeAppliancesPage extends HomePage {


    private String title = "Large Appliances";

    private String pageTitleLocator = "//b[text()=\"Large Appliances\"]";
    private String globalStoreBoxLocator = "//input[@name=\"s-ref-checkbox-11259240031\"]";
    private String largeAppliancesDropDownLocator="//select[@name=\'url\']//option[@value=\'search-alias=appliances\']";
    private String globalStoreLabelLocator = "//img[@class=\"s-ref-img-sprite\"]";
    private String subMenuLinkLocator = "//*[@id=\"nav-subnav\"]/a[1]/span";
    private String primeCheckBoxLocator = "//input[@name=\"s-ref-checkbox-419158031\"]";
    private String CustomerReviewLocator = "//*[@id=\"leftNav\"]/h4[6]";

    //Credit to alex
    private String primeProductListLocator = "//*[@class=\"s-result-list s-search-results sg-row\"]//*[@class=\"s-include-content-margin s-border-bottom\"]";
    private String primeLabelOrMoreLocator = "//*[@aria-label=\'Amazon Prime\' or @class=\'a-size-base a-color-secondary\']";

    public static String PATH ="https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dappliances&field-keywords=";

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
        driver.findElement(By.xpath(primeCheckBoxLocator)).click();
        return driver.findElements(By.xpath(primeProductListLocator)).iterator().next().findElement(By.xpath(primeLabelOrMoreLocator)).isDisplayed();
    }

}
