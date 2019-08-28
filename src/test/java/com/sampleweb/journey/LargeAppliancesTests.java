package com.sampleweb.journey;

import com.sampleweb.BaseTest;
import com.sampleweb.framework.PageNavigator;
import com.sampleweb.pages.HomePage;
import com.sampleweb.pages.LargeAppliancesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LargeAppliancesTests extends BaseTest {
    private PageNavigator navigator = new PageNavigator();
    private LargeAppliancesPage largeAppliancesPage;

    public void setUp() throws Exception {
        largeAppliancesPage = navigator.navigateToPage(driver, LargeAppliancesPage.LARGE_APPLIANCES_PATH, LargeAppliancesPage.class);
    }

    @Test (groups = "regression")
    public void largeAppliancesNavigationPageTest() {
        LargeAppliancesPage largeAppliancesPage = new LargeAppliancesPage(driver);
        assertThat(largeAppliancesPage.selectLargeAppliancesDropDown(), is(true));
    }

    @Test (groups = "regression")
    public void largeAppliancesGlobalTradeTest() throws Exception {
        setUp();
        assertThat(largeAppliancesPage.globalStoreExists(), is(true));
    }

    @Test (groups= "regression")
    public void largeAppliancesCustomerReviewTest() throws Exception {
        setUp();
        assertThat(largeAppliancesPage.CustomerReviewExists(), is(true));
    }

    @Test (groups ="regression")
    public void largeAppliancesSubMenuTest() throws Exception{
        setUp();
        assertThat(largeAppliancesPage.isSubMenuCorrect(), is(true));
    }

    @Test (groups="regression")
    public void largeAppliancesSubMenuNavigationTest() throws Exception{
        setUp();
        assertThat(largeAppliancesPage.isSubMenuNavigationCorrect(), is(true));
    }

    @Test (groups="regression")
    public void largeAppliancesPrimeTest() throws Exception{
        setUp();
        assertThat(largeAppliancesPage.isPrimeCorrect(), is(true));
    }
}
