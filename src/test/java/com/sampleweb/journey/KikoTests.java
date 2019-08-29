package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.BabyPage;
import com.sampleweb.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class KikoTests extends BaseTest {

    private BabyPage babyPage;
    private PageNavigator navigator = new PageNavigator();

    @BeforeMethod
    public void setUp() throws Exception {
        navigator.navigateToPage(driver, HomePage.PATH, HomePage.class);
        babyPage = navigator.navigateToPage(driver, BabyPage.PATH, BabyPage.class);
    }


    @Test(groups = "regression")
    public void babyPageTitleTest() throws Exception {
        HomePage page = navigator.navigateToPage(driver, HomePage.PATH, HomePage.class);
        assertThat(page.navigateToBabyPageViaDropDown(), is(true));
    }
    @Test (groups = "regression")
    public void babyPageGlobalStoreTest() {
        assertThat(babyPage.globalStoreOption.isDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void babyPageAverageCustomerReviewTest() {
        assertThat(babyPage.avgCustomerReviewOption.isDisplayed(), is(true));
    }

    @Test (groups = "regression")
    public void isCategoryNameLinkSameAsTitleTest() {
        assertThat(babyPage.isCategoryNameLinkSameAsTitle(), is(true));
    }

    @Test (groups = "regression")
    public void navigateToBabyPageViaSubnavTest() {
        assertThat(babyPage.navigateToBabyPageViaSubnav(), is(true));
    }

    @Test (groups = "regression")
    public void checkPrimeLabelsTest() throws Exception {
        assertThat(babyPage.checkPrimeLabels(), is(true));
    }
}
