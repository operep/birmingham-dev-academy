package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LuxuryBeautyPage extends HomePage {


    public String title = "Luxury Beauty";
    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dluxury-beauty&field-keywords=";

    @FindBy(xpath = "//img[@alt='Luxury Beauty']")
    private WebElement pageTitle ;

    @FindBy(xpath = "//div[@id='nav-subnav']/a[1]")
    private WebElement titleOfCurrentPage ;

    @FindBy(xpath = "//input[@name='s-ref-checkbox-11259240031']")
    private WebElement globalStoreBox ;

    @FindBy(xpath = "//*[@id='leftNav']/ul[11]")
    private WebElement customerReview ;

    @FindBy(xpath = "//img[@src='https://images-eu.ssl-images-amazon.com/images/G/02/beauty/images/prestige/skylight/sept/UK_lux_SF-Banner_770x100._V312450604_.jpg']")
    private WebElement imageLabelOfCurrentPage ;

    @FindBy(xpath = "//*[@id='nav-subnav']/a[1]")
    private WebElement firstLinkSecondMenu ;

    @FindBy(xpath = "//input[@name='s-ref-checkbox-419158031']")
    private WebElement primeBox ;

    @FindBy(xpath = "//*[@class='s-result-list s-search-results sg-row']")
    private List<WebElement> allProductSeached;

    @FindBy(xpath = "//a[@class='a-link-normal s-ref-text-link']/i[@class='a-icon a-icon-star-medium a-star-medium-4']")
    private WebElement fourStarsAndMore;

    @FindBy(xpath = "//select[@id='s-result-sort-select']")
    private WebElement orderSelect;

    @FindBy(xpath = "//a[@class='a-popover-trigger a-declarative']")
    private List<WebElement> reviewStars;

    // Getting the percentages for different number of stars

    @FindBy(xpath = "//table[@id='histogramTable']/tbody/tr[1]/td[3]/span[@class='a-size-small']")
    private List<WebElement> fiveStarsReviewsPercentages;

    @FindBy(xpath = "//table[@id='histogramTable']/tbody/tr[2]/td[3]/span[@class='a-size-small']")
    private List<WebElement> fourStarsReviewsPercentages;

    @FindBy(xpath = "//table[@id='histogramTable']/tbody/tr[3]/td[3]/span[@class='a-size-small']")
    private List<WebElement> threeStarsReviewsPercentages;

    @FindBy(xpath = "//table[@id='histogramTable']/tbody/tr[4]/td[3]/span[@class='a-size-small']")
    private List<WebElement> twoStarsReviewsPercentages;

    @FindBy(xpath = "//table[@id='histogramTable']/tbody/tr[5]/td[3]/span[@class='a-size-small']")
    private List<WebElement> oneStarsReviewsPercentages;

    @FindBy(xpath = "//span[@class='a-size-base']")
    private List<WebElement> numOfReviews;

    private  List<String> fiveStarsReviewsPercentagesString     = new ArrayList<>();
    private  List<String> fourStarsReviewsPercentagesString     = new ArrayList<>();;
    private  List<String> threeStarsReviewsPercentagesString    = new ArrayList<>();;
    private  List<String> twoStarsReviewsPercentagesString      = new ArrayList<>();;
    private  List<String> oneStarsReviewsPercentagesString      = new ArrayList<>();;
    private  List<String> numOfReviewsString                    = new ArrayList<>();;


    public LuxuryBeautyPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return pageTitle.isDisplayed();
    }

    public String getPath() {return PATH;}

    private WebElement createWebElement(String xpath)
    {
         WebElement createdElement = driver.findElementByXPath(xpath);
        return createdElement;
    }


    private String getTitleOfCurrentPage()
    {
        return titleOfCurrentPage.getText();
    }

    public String getTitle()
    {
        return getTitleOfCurrentPage();
    }

    public boolean isGlobalStoreBoxDisplayed()
    {
        return globalStoreBox.isDisplayed();
    }

    public boolean isCustomerReviewDisplayed()
    {
        return customerReview.isDisplayed();
    }

    public String getImageLabelOfCurrentPage()
    {
        return imageLabelOfCurrentPage.getAttribute("alt");
    }

    public void clickFirstLinkSecondMenu()
    {
        firstLinkSecondMenu.click();
    }

    public boolean checkIfAllElementsDisplayedArePrimeOrAddMore()
    {
        String xpathPrime = "//span[@class='aok-inline-block s-image-logo-view']";
        String xpathMore = "//span[@class='a-size-base a-color-secondary']";
        String xpathCombinedPrimeNMore = xpathPrime + " | " + xpathMore;
        String xpathAllResults = "//*[@class='s-result-list s-search-results sg-row']";

        primeBox.click();

        return allProductSeached.iterator().next().findElement(By.xpath(xpathCombinedPrimeNMore)).isDisplayed();
    }

    private void clickOnFourStarsAndMore()
    {
        fourStarsAndMore.click();
    }

    private void selectOrder(String typeOfOrder)
    {
        Select orderSelectBox = new Select((WebElement) orderSelect);
        orderSelectBox.selectByVisibleText(typeOfOrder);
    }

    private void clickOnStars() throws InterruptedException {
        System.out.println("This is the size of the list: " + reviewStars.size());
        for(int i=0; i<reviewStars.size();i++) {
            reviewStars.get(i).click();

            TimeUnit.SECONDS.sleep(3);

            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");


            fiveStarsReviewsPercentagesString.add(fiveStarsReviewsPercentages.get(i).getText());
            fourStarsReviewsPercentagesString.add(fourStarsReviewsPercentages.get(i).getText());
            threeStarsReviewsPercentagesString.add(threeStarsReviewsPercentages.get(i).getText());
            twoStarsReviewsPercentagesString.add(twoStarsReviewsPercentages.get(i).getText());
            oneStarsReviewsPercentagesString.add(oneStarsReviewsPercentages.get(i).getText());
            numOfReviewsString.add(numOfReviews.get(i).getText());
            System.out.println("The number of the reviews: " + numOfReviews.get(i).getText());

        }
    }

    private List<String> getListBasedOnNumOfStars(int numOfStars)
    {
        switch (numOfStars)
        {
            case 1:
                return oneStarsReviewsPercentagesString;
            case 2:
                return twoStarsReviewsPercentagesString;
            case 3:
                return threeStarsReviewsPercentagesString;
            case 4:
                return fourStarsReviewsPercentagesString;
            default:
                return fiveStarsReviewsPercentagesString;
        }

    }

    private boolean checkOrderbasedOnNumberOfStars(int numberOfStars)
    {
        int intValueOfReview = 0;
        int intValueOfPrecentage = 0;
        int previousReview = 0;
        int previousPercetage = 0;
        List<String> listOfStars = getListBasedOnNumOfStars(numberOfStars);
        for (int i=0; i< listOfStars.size(); i++)
        {
            intValueOfReview = Integer.parseInt(numOfReviews.get(i).getText());
            intValueOfPrecentage = Integer.parseInt(listOfStars.get(i));

            previousReview      = (i == 0)      ? (0) : (Integer.parseInt(numOfReviewsString.get(i-1)));
            previousPercetage   = (i == 0)      ? (0) : (Integer.parseInt(listOfStars.get(i-1)));

            if(intValueOfReview * intValueOfPrecentage > previousReview * previousPercetage)
            {
                return false;
            }
        }

        return true;
    }


    public boolean checkIfAllProductsAreInTheRightOrderBasedOnReview() throws InterruptedException {
        clickOnFourStarsAndMore();
        selectOrder("Avg. Customer Review");
        clickOnStars();
        return checkOrderbasedOnNumberOfStars(5);
    }
}
