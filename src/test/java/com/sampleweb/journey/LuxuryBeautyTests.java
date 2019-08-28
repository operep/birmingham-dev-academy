package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.LuxuryBeautyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LuxuryBeautyTests extends BaseTest {

    private PageNavigator navigator = new PageNavigator();
    private LuxuryBeautyPage luxuryBeautyPage;

    @BeforeMethod
    public void setUp() throws Exception {
        luxuryBeautyPage = navigator.navigateToPage(driver, LuxuryBeautyPage.class);
    }

    @Test (groups = "regression")
    public void checkTitleTest() throws Exception {
        assertThat(luxuryBeautyPage.getTitle(), is(luxuryBeautyPage.title));
    }

    @Test (groups = "regression")
    public void checkIfGlobalStoreExistTest() throws Exception {
        assertThat(luxuryBeautyPage.getGlobalStore().isDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void checkCustomerReviewExistTest() throws Exception {
        assertThat(luxuryBeautyPage.getCustomerReview().isDisplayed(), is(true));
    }


    @Test (groups = "regression")
    public void checkFirstLinkSecondMenuSameAsCatTest() throws Exception {
        assertThat(luxuryBeautyPage.getImageLabelOfCurrentPage().getAttribute("alt"), is(luxuryBeautyPage.getTitle()));
    }

    @Test (groups = "regression")
    public void checkIfLinkDirectsToSamePageTest() throws Exception {
        luxuryBeautyPage.clickFirstLinkSecondMenu();
        assertThat(luxuryBeautyPage.getImageLabelOfCurrentPage().getAttribute("alt"), is(luxuryBeautyPage.getTitle()));
    }

    @Test (groups = "regression")
    public void checkIfOnlyContainsPrimeTestOrBuyMoreTest() throws Exception {
        assertThat(luxuryBeautyPage.checkIfAllElementsDisplayedArePrimeOrAddMore(), is(true));
    }


}
