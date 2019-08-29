package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BabyPage extends HomePage {

    public static final String PATH="https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dbaby&field-keywords=";

    public String title = "Baby's Page";
    public String categoryXPath="//node()[text()=\"Baby\"]";

    @FindBy(xpath = "//h1[text()=\"Baby Store\"]")
    public WebElement pageTitleElement;

    @FindBy(xpath="//option[text()=\"Amazon Global Store\"]")
    public WebElement globalStoreOption;

    @FindBy(xpath="//option[text()=\"Avg. Customer Review\"]")
    public WebElement avgCustomerReviewOption;

    @FindBy(xpath="//descendant-or-self::a[@tabindex=\"35\"]")
    public List<WebElement> subNavElements;

    @FindBy(xpath="//input[@name=\"s-ref-checkbox-419158031\"]")
    public WebElement primeCheckBox;

    @FindBy(xpath="//div[@class=\"s-result-list s-search-results sg-row\"]/*")
    public List<WebElement> searchResults;

    public BabyPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isCategoryNameLinkSameAsTitle() {
        return subNavElements.stream().anyMatch(e -> e.findElement(By.xpath(categoryXPath)).isDisplayed());
    }

    public boolean navigateToBabyPageViaSubnav() {
        navigateToBabyPageViaDropDown();
        String titleBefore = pageTitleElement.getText();
        WebElement element = driver.findElementByXPath("//div[@data-category='baby']/a[@href=\"/Baby-Car-Seats-Prams-Nursery/b/?ie=UTF8&node=59624031&ref_=topnav_storetab_by\"]");
        element.click();
        return titleBefore.equals(pageTitleElement.getText());
    }

    public boolean checkPrimeLabels() {
        navigateToBabyPageViaDropDown();
        primeCheckBox.click();
        int prevSize = searchResults.size();
        System.out.println(prevSize);
        int size = (int) searchResults.stream()
                .filter(e -> e.findElement(By.xpath("//i[@aria-label='Amazon Prime']")).isDisplayed())
                .count();
        System.out.println(size);
        return prevSize == size;
    }
}
