package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class DVDPage extends HomePage {

    public static String TITLE = "DVD & Blu-ray";

    public DVDPage(RemoteWebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getElementText(By.cssSelector(".bxw-pageheader__title__text>h1"));
    }

    public boolean hasGlobalStore() {
        return isElementVisible(By.xpath("//h4[text()='Global Store']"));
    }

    public boolean hasAvgReview() {
        return isElementVisible(By.xpath("//h4[text()='Avg. Customer Review']"));
    }

    public String getPageLinkText() {
        return getElementText(By.cssSelector("div#nav-subnav>a:first-child>span"));
    }

    public void clickPageLink() {
        clickElement(By.cssSelector("div#nav-subnav>a:first-child"));
    }

    public void clickPrimeCheckbox() {
        clickElement(By.xpath("//*[@id='leftNav']/ul[13]/div/li[1]"));
    }

    public List<WebElement> getProductsOnPage() {
        return driver.findElements(By.xpath("//*[@class='s-result-list s-search-results sg-row']/div"));
    }

}
