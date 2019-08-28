package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BabyPage extends HomePage {

    public static final String PATH="https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dbaby&field-keywords=";

    public String title = "Baby's Page";
    public String pageTitle="//h1[text()=\"Baby Store\"]";
    public String globalStoreXPath="//option[text()=\"Amazon Global Store\"]";
    public String avgCustomerReviewXPath="//option[text()=\"Avg. Customer Review\"]";
    public String subnav="//descendant-or-self::a[@tabindex=\"35\"]";
    public String categoryXPath="//node()[text()=\"Baby\"]";
    public String primeCheckBox="//preceding-sibling::i[@class=\"a-icon a-icon-prime a-icon-small s-ref-text-link\"]";
    public String listOfElements="//div[@class=\"s-result-list s-search-results sg-row\"]/*";

    public BabyPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean hasGlobalStore() {
        return driver.findElementByXPath(globalStoreXPath).isDisplayed();
    }

    public boolean isAvgReviewDisplayed() {
        return driver.findElementByXPath(avgCustomerReviewXPath).isDisplayed();
    }

    public boolean isCategoryNameLinkSameAsTitle() {
        return driver.findElementsByXPath(subnav).stream()
                .anyMatch(e -> e.findElement(By.xpath(categoryXPath)).isDisplayed());
    }

    public boolean navigateToBabyPageViaSubnav() {
        navigateToBabyPageViaDropDown();
        String titleBefore = driver.findElementByXPath(pageTitle).getText();
        WebElement element = driver.findElementByXPath("//div[@data-category='baby']/a[@href=\"/Baby-Car-Seats-Prams-Nursery/b/?ie=UTF8&node=59624031&ref_=topnav_storetab_by\"]");
        element.click();
        return titleBefore.equals(driver.findElementByXPath(pageTitle).getText());
    }

    public boolean checkPrimeLabels() {
        navigateToBabyPageViaDropDown();
        int prevSize = driver.findElementsByXPath(listOfElements).size();
        int size = (int) driver.findElementsByXPath(listOfElements)
                .stream()
                .filter(e -> e.findElement(By.xpath("//i[@aria-label='Amazon Prime']")).isDisplayed())
                .count();
        return prevSize == size;
    }
}
