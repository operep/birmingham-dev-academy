package com.sampleweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    public static final String PATH = "https://www.amazon.co.uk/ref=nav_logo";
    protected RemoteWebDriver driver;
    private static String HOME_PAGE_TITLE = "Amazon";

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement submitButtonLocator;

    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    protected WebElement dropDownBox;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    protected WebElement twoTabSearchBar;

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean navigateToBabyPage() {
        dropDownBox.findElement(By.xpath("//option[text()='Baby']")).click();
        submitButtonLocator.click();
        return driver.getCurrentUrl().equals(BabyPage.PATH);
    }

    public boolean isTitleCorrect(String title) throws Exception {
        return verifyTitle(driver.getTitle(), title);
    }

    public void clickSubmitButton() {
        submitButtonLocator.click();
    }
}
