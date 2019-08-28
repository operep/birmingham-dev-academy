package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class GroceryPage extends HomePage {

    public static String PATH = "https://www.amazon.co.uk/s/ref=nb_sb_noss?url=search-alias%3Dgrocery&field-keywords=";
    public static String HOMEPAGE_PATH = "https://www.amazon.co.uk/";

    private final String title = "Grocery";
    private final String pageTitle = "//h1[text()=\"Grocery\"]";
    private final String submenuTitleComponent = "//span[@class='nav-a-content'][contains(text(),'Grocery')]";

    public GroceryPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        driver.get(HOMEPAGE_PATH);

        navigateToGroceryPageWithDropdown()
                .setSearchCriteria("")
                .clickSubmitButton();

        return driver.findElement(By.xpath(pageTitle)).getText().equals(title);
    }

    private GroceryPage navigateToGroceryPageWithDropdown() {
        driver.findElement(By.xpath("//select[@id='searchDropdownBox']")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Grocery')]")).click();

        return this;
    }

    public boolean isGlobalStoreDisplayed() {
        return driver.findElement(By.xpath("//h4[contains(text(),'Global Store')]")).isDisplayed();
    }

    public boolean isAverageCustomerReviewDisplayed() {
        return driver.findElement(By.xpath("//h4[contains(text(),'Global Store')]")).isDisplayed();
    }

    public boolean isSubmenuTitleMatchingTitle() {
        return driver.findElement(By.xpath(submenuTitleComponent)).getText()
                .equals(driver.findElement(By.xpath(pageTitle)).getText());
    }

    public boolean isSubmenuLinkNavigatingToGroceryPage() {
        driver.findElement(By.xpath(submenuTitleComponent)).findElement(By.xpath("..")).click();

        return driver.findElement(By.xpath(pageTitle)).getText().equals(title);
    }

    public boolean isPrimeItemsFiltered() {
        String sidemenuPrimeLogo = "//div[contains(@id, 'leftNav')]//i[contains(@class, 'a-icon-prime')]";
        String sidemenuPrimeLogoCheckbox = "../..//input[contains(@type, 'checkbox')]";

        WebElement sidemenuPrimeLogoElement = driver.findElement(By.xpath(sidemenuPrimeLogo));
        WebElement checkBoxElement = sidemenuPrimeLogoElement.findElement(By.xpath(sidemenuPrimeLogoCheckbox));

        checkBoxElement.click();

        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 's-search-results')]//div[contains(@class, 's-result-item')]"));

        for (WebElement product : products) {
            WebElement primeElement = product.findElement(By.xpath("//i[contains(@class, 'a-icon-prime')]"));
            WebElement buyChoicesElement = product.findElement(By.xpath("//span[contains(text(),'More buying choices')]"));

            if (!(primeElement.isDisplayed() || buyChoicesElement.isDisplayed())) {
                return false;
            }
        }

        return true;
    }

    public GroceryPage setSearchCriteria(String searchText) {
        getSearchField().sendKeys(searchText);

        return this;
    }

}
