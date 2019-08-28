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

public class DVDTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private DVDPage dvdPage;

    @BeforeMethod
    public void setUp() throws Exception {
        dvdPage = navigator.navigateToDVDProductsPage(driver, DVDPage.class);
    }

    @Test(groups = "regression")
    public void dvdProductsTitleCorrect() {
        assertThat(dvdPage.getTitleText(), is(DVDPage.TITLE));
    }

    @Test(groups = "regression")
    public void dvdProductsGlobalStoreExists() {
        assertThat(dvdPage.hasGlobalStore(), is(true));
    }

    @Test(groups = "regression")
    public void dvdProductsAvgReviewExists() {
        assertThat(dvdPage.hasAvgReview(), is(true));
    }

    @Test(groups = "regression")
    public void dvdProductsPageLinkTextEqualsCategory() {
        assertThat(dvdPage.getPageLinkText(), is(DVDPage.TITLE));
    }

    @Test(groups = "regression")
    public void dvdProductsPageLinkNavigatesToSamePage() {
        dvdPage.clickPageLink();
        assertThat(dvdPage.getTitleText(), is(DVDPage.TITLE));
    }

    @Test(groups = "regression")
    public void dvdProductsPrimeItemsHavePrimeLabel() {
        dvdPage.clickPrimeCheckbox();
        List<WebElement> products = dvdPage.getProductsOnPage();
        for (WebElement product : products) {
            product.findElement(By.xpath(".//span[text()='FREE Delivery by '] | .//i[@aria-label='Amazon Prime']"));
        }
    }

}
