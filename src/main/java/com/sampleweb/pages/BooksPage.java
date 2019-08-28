package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class BooksPage extends HomePage {

    public String title = "Books";
    public String pageTitle = "//*[@id=\"merchandised-content\"]/div[1]/div[1]/div/h1";
    public String globalStore = "//*[@id=\"leftNav\"]/h4[5]";

    public static String PATH = "https://www.amazon.co.uk";

    public BooksPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void selectBooks(){
        //Select dropdown menu
        driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]")).click();
        //Select option books
        driver.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]/option[10]")).click();
        //Submit search
        driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();

    }

    public boolean doesTitleMatch(){
        return (driver.findElement(By.xpath(pageTitle)).getText().equals(this.title));
    }

    public boolean isGlobalStoreDisplayed(){
        return driver.findElement(By.xpath(globalStore)).isDisplayed();
    }

    public boolean isAvgCustomerDisplayed(){
        String avgCustomers = "//*[@id=\"leftNav\"]/h4[8]";
        return driver.findElement(By.xpath(avgCustomers)).isDisplayed();
    }

    public boolean doesSecondLineMatchs(){
        String secondMenuLink = driver.findElement(By.xpath( "//*[@id=\"nav-subnav\"]/a[1]/span")).getText();
        String currentPageTitle =driver.findElement(By.xpath("//*[@id=\"merchandised-content\"]/div[1]/div[1]/div/h1")).getText();
        return secondMenuLink.equals(currentPageTitle);
    }


    public boolean isSameWhenClicked(){
        //check title of current page
        String previousPageTitle = driver.findElement(By.xpath("//*[@id=\"merchandised-content\"]/div[1]/div[1]/div/h1")).getText();
        //click on first link from second menu
        driver.findElement(By.xpath("//*[@id=\"nav-subnav\"]/a[1]/span")).click();
        //check if the title is the same or not
        String currentPageTitle = driver.findElement(By.xpath("//*[@id=\"merchandised-content\"]/div[1]/div[1]/div/h1")).getText();
        return previousPageTitle.equals(currentPageTitle);
    }


    public boolean isPrimeBoxEverywhere(){
        //tick primebox element
        driver.findElement(By.xpath("//*[@id=\"leftNav\"]/ul[7]/div/li[1]/span/span/div/label/span/i")).click();
        String amazonPrime =  "//*[@aria-label='Amazon Prime']";
        return driver.findElements(By.xpath("//*[@class='s-result-list s-search-results sg-row']//*[@class='s-include-content-margin s-border-bottom']")).iterator().next().findElement(By.xpath(amazonPrime)).isDisplayed();
    }
}