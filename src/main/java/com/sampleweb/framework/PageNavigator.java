package com.sampleweb.framework;

import com.sampleweb.pages.LuxuryBeautyPage;
import com.sampleweb.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class PageNavigator {

    public <T extends Page> T navigateToBabyProductsPage(RemoteWebDriver driver, Class<T> clazz) throws Exception {
        driver.findElement(By.xpath("//span[contains(text(), 'Department')]")).click();
        driver.findElement(By.xpath("//li//a[text()=\"Children's Books\"]")).click();
        return PageFactory.newPage(driver, clazz);
    }



    public <T extends Page> T navigateToPage(RemoteWebDriver driver, Class<T> clazz) throws Exception {
        WebElement dropDownItem = driver.findElementByXPath("//select[@aria-describedby='searchDropdownDescription']");
        Select dropdown = new Select(dropDownItem);
        dropdown.selectByVisibleText("Luxury Beauty");
        WebElement searchBtn = driver.findElementByXPath("//*[@id='nav-search']/form/div[2]/div/input");
        searchBtn.click();
        return PageFactory.newPage(driver, clazz);
    }
}
