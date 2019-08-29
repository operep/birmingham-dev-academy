package com.sampleweb.pages;

import com.sampleweb.framework.PageNavigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;
import java.util.List;

public class HealthPage extends HomePage {

    private String title = "Health & Personal Care";
    private PageNavigator navigator = new PageNavigator();
    private static String PATH = "https://www.amazon.co.uk/health-beauty-haircare-wellbeing-dentalcare-shaving-hairremoval/b/?ie=UTF8&node=65801031&ref_=topnav_storetab_d";

    @FindBy(xpath = "//h1[text()='Health & Personal Care']")
    private WebElement healthPageTitle;

    @FindBy(xpath = "//*[@id=\"nav-subnav\"]/a[1]/span")
    private WebElement healthPageNavigationHeader;

    @FindBy(xpath = "//*[@id='searchDropdownBox']/option[25]")
    private WebElement dropDownMenuHealthNavigation;

    @FindBy(xpath = "//*[@id=\"leftNav\"]/h4[2]")
    private WebElement healthPageGlobalStore;

    @FindBy(xpath = "//*[@id=\"searchDropdownBox\"]")
    private WebElement dropDownMenu;

    @FindBy(xpath = "//*[@id=\"nav-search\"]/form/div[2]/div")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"leftNav\"]/h4[8]")
    private WebElement healthPageAverageReviewDisplay;

    @FindBy(xpath = "//*[@id=\"leftNav\"]/ul[3]/div/li/span/span/div/label/input")
    private WebElement healthPagePrimeCheckBox;

    @FindBy(xpath = "//*[@class='a-section aok-relative s-image-fixed-height']")
    private List<WebElement> healthPagePrimeImages;

    private String primeIcon = "//*[@class='a-icon a-icon-prime a-icon-medium']";

    public HealthPage(RemoteWebDriver driver) {
        super(driver);
    }

    private void navigateToHealthPage() throws Exception {
        navigator.navigateToPage(driver, "https://www.amazon.co.uk", HealthPage.class);
        dropDownMenu.click();
        dropDownMenuHealthNavigation.click();
        searchButton.click();
    }

    public boolean isHealthPageLoaded() throws Exception {
        navigateToHealthPage();
        return healthPageTitle.isDisplayed();
    }

    public boolean verifyGlobalStoreExists() throws Exception {
        navigateToHealthPage();
        return healthPageGlobalStore.getText().contentEquals("Global Store");
    }

    public boolean verifyAverageReviewsExist() throws Exception {
        navigateToHealthPage();
        return healthPageAverageReviewDisplay.getText().contentEquals("Avg. Customer Review");
    }

    public boolean verifyFirstLinkHasCatagoryName() throws Exception {
        navigateToHealthPage();
        return healthPageNavigationHeader.getText().contentEquals(title);
    }

    public boolean verifyClickOnHeaderMenuDirectsToSamePage() throws Exception {
        navigateToHealthPage();
        healthPageNavigationHeader.click();
        return driver.getCurrentUrl().contentEquals(PATH);
    }

    public boolean verifySelectPrimeReturnsOnlyPrimeitems() throws Exception {
        navigateToHealthPage();
        healthPagePrimeCheckBox.click();
        Boolean isDisplayed = false;

        for (WebElement image : healthPagePrimeImages) {
            isDisplayed = image.findElement(By.xpath(primeIcon)).isDisplayed();
            if (!isDisplayed) {
                break;
            }
        }

        return isDisplayed;
    }
}

//    public boolean verifyAverageRatingsSortedCorrectly() throws Exception {
//        navigateToHealthPage();
//        driver.findElementByXPath("//*[@id='leftNav']/ul[9]/div/li[1]/span/a/span").click();
//        driver.findElementByXPath("//*[@id='a-autoid-0-announce']/span[1]").click();
//        driver.findElementByXPath("//*[@id='s-result-sort-select_3']").click();
//        driver.findElementByXPath("//*[@id='search']/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[2]/div/span/a/i[1]/span").click();
//        driver.findElementsByXPath("//*[@class='a-icon-alt']").iterator().next().getText().
 //   }
