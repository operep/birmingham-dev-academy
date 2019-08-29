package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.DVDPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DVDTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private DVDPage dvdPage;

    @BeforeMethod
    public void setUp() throws Exception {
        dvdPage = navigator.navigateToDVDProductsPage(driver, DVDPage.class);
    }

    @Test(groups = "regression")
    public void dvdProductsTitleCorrect() {
        assertEquals(dvdPage.title.getText(), DVDPage.TITLE);
    }

    @Test(groups = "regression")
    public void dvdProductsGlobalStoreExists() {
        assertTrue(dvdPage.hasGlobalStore());
    }

    @Test(groups = "regression")
    public void dvdProductsAvgReviewExists() {
        assertTrue(dvdPage.hasAvgReview());
    }

    @Test(groups = "regression")
    public void dvdProductsPageLinkTextEqualsCategory() {
        assertEquals(dvdPage.pageLink.getText(), DVDPage.TITLE);
    }

    @Test(groups = "regression")
    public void dvdProductsPageLinkNavigatesToSamePage() {
        dvdPage.pageLink.click();
        assertEquals(dvdPage.title.getText(), DVDPage.TITLE);
    }

    @Test(groups = "regression")
    public void dvdProductsPrimeItemsHavePrimeLabel() {
        dvdPage.primeCheckbox.click();
        List<WebElement> products = dvdPage.products;
        for (WebElement product : products) {
            product.findElement(By.xpath(".//span[text()='FREE Delivery by '] | .//i[@aria-label='Amazon Prime']"));
        }
    }

}
