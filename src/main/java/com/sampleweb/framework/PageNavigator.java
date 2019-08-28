package com.sampleweb.framework;

import com.sampleweb.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PageNavigator {

    public <T extends Page> T navigateToBabyProductsPage(RemoteWebDriver driver, Class<T> clazz) throws Exception {
        driver.findElement(By.xpath("//span[contains(text(), 'Department')]")).click();
        driver.findElement(By.xpath("//li//a[text()=\"Children's Books\"]")).click();
        return PageFactory.newPage(driver, clazz);
    }

    public <T extends Page> T navigateToDVDProductsPage(RemoteWebDriver driver, Class<T> clazz) throws Exception {
        driver.findElement(By.cssSelector("div.nav-search-scope")).click();
        driver.findElement(By.xpath("//option[text()='DVD & Blu-ray']")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        return PageFactory.newPage(driver, clazz);
    }

    public <T extends Page> T navigateToPage(RemoteWebDriver driver, String path, Class<T> clazz) throws Exception {
        driver.get(path);
        return PageFactory.newPage(driver, clazz);
    }
}
